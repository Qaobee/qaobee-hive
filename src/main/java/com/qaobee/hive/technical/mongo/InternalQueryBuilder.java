/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */
package com.qaobee.hive.technical.mongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a complex query for mongo with $and and $elemMatch criterias.
 *
 * @author Jerome
 */
@Deprecated
public class InternalQueryBuilder {

    /**
     * List of elements used in LIFO mode.
     */
    private List<Object> listElements = new ArrayList<Object>();

    /**
     * Current element in which add are made.
     */
    private Object currentElement;

    /**
     * First element of the list.
     */
    private Object firstElement;

    /**
     * Adds a criteria / constraint.
     *
     * @param key   (String) : key in the mongo document
     * @param value (String) : value for the constraint
     */
    public void addCriteria(String key, String value) {
        Criteria crit = new Criteria(key, value);
        if (currentElement instanceof AndElement) {
            ((AndElement) currentElement).addElementInAnd(crit);
        } else if (currentElement instanceof ElemMatchElement) {
            ((ElemMatchElement) currentElement).addElementInElemMatch(crit);
        }
    }

    /**
     * Adds a criteria / constraint.
     *
     * @param key      (String) : key in the mongo document
     * @param operator (String) : operator to apply to the constraint (operator starting with $ like $gt)
     * @param value    (String) : value for the constraint
     */
    public void addCriteria(String key, String operator, String value) {
        Criteria crit = new Criteria(key, operator, value);
        if (currentElement instanceof AndElement) {
            ((AndElement) currentElement).addElementInAnd(crit);
        } else if (currentElement instanceof ElemMatchElement) {
            ((ElemMatchElement) currentElement).addElementInElemMatch(crit);
        }
    }

    /**
     * Adds an "$and" element in the query and places it as the active element on which operations are made.
     */
    public void addAndStartAndElement() {
        startElement(new AndElement());
    }

    /**
     * Adds an "$elemMatch" element in the query and places it as the active element on which operations are made.
     *
     * @param key (String) : key of embedded document on which further criterias are made
     */
    public void addAndStartElmenMatchElement(String key) {
        startElement(new ElemMatchElement(key));
    }

    /**
     * Adds the new element in the LIFO list and shifts the current element to this.
     *
     * @param newElement (Object) : new element to start
     */
    private void startElement(Object newElement) {
        // Adds the new element to current if it is set
        if (currentElement != null) {
            if (currentElement instanceof AndElement) {
                ((AndElement) currentElement).addElementInAnd(newElement);
            } else if (currentElement instanceof ElemMatchElement) {
                ((ElemMatchElement) currentElement).addElementInElemMatch(newElement);
            }
        }
        // Shifts to the new one
        currentElement = newElement;
        // Adds to the LIFO list
        listElements.add(currentElement);
        // Sets as the first element of the query if it hasn't yet been done
        if (firstElement == null) {
            firstElement = currentElement;
        }
    }

    /**
     * Ends the current element by pulling the last element of the LIFO list, and setting the new last one as the current element.
     */
    public void endElement() {
        if (listElements == null) {
            System.out.println("Cannot close element : the list of elements is null");
            return;
        }
        if (listElements.size() == 0) {
            System.out.println("Cannot close element : the list of elements is empty");
            return;
        }
        listElements.remove(listElements.size() - 1);
        if (listElements.size() == 0) {
            currentElement = null;
        } else {
            currentElement = listElements.get(listElements.size() - 1);
        }
    }

    /**
     * Generates the query for mongo with MongoAPI objects.
     *
     * @return DBObject : query in mongo objects
     */
    public DBObject generateDBObject() {
        if (firstElement == null) {
            return null;
        }
        BasicDBObject generatedQuery = null;
        if (firstElement instanceof AndElement) {
            generatedQuery = ((AndElement) firstElement).generateDBObject();
        } else if (firstElement instanceof ElemMatchElement) {
            generatedQuery = ((ElemMatchElement) firstElement).generateDBObject();
        }
        if (generatedQuery != null) {
            System.out.println(generatedQuery.toString());
        }
        return generatedQuery;
    }

    /**
     * Class that represents a "$and" element in the query.
     *
     * @author jerome
     */
    private class AndElement {

        /**
         * List of elements in the "And" criteria.
         */
        private List<Object> listAnd = new ArrayList<Object>();

        /**
         * Adds an element in the "And" list.
         *
         * @param element (Object) : element to add
         */
        private void addElementInAnd(Object element) {
            listAnd.add(element);
        }

        /**
         * Generates the query for mongo with MongoAPI objects.
         *
         * @return BasicDBObject : part of the query in mongo objects
         */
        private BasicDBObject generateDBObject() {
            BasicDBList list = new BasicDBList();
            for (Object element : listAnd) {
                if (element instanceof AndElement) {
                    list.add(((AndElement) element).generateDBObject());
                } else if (element instanceof ElemMatchElement) {
                    list.add(new BasicDBObject(((ElemMatchElement) element).key, ((ElemMatchElement) element).generateDBObject()));
                } else if (element instanceof Criteria) {
                    if (((Criteria) element).operator == null) {
                        list.add(new BasicDBObject(((Criteria) element).key, ((Criteria) element).value));
                    } else {
                        list.add(new BasicDBObject(((Criteria) element).key, new BasicDBObject(((Criteria) element).operator, ((Criteria) element).value)));
                    }
                }
            }
            BasicDBObject and = new BasicDBObject();
            and.put("$and", list);
            return and;
        }
    }

    /**
     * Class that represents a "$and" element in the query.
     *
     * @author jerome
     */
    private class ElemMatchElement {

        /**
         * List of elements in the "ElemMatch" criteria.
         */
        private List<Object> listElemMatch = new ArrayList<Object>();

        /**
         * Key of the embedded document in which the "ElemMatch" is applied to.
         */
        private String key;

        /**
         * Constructor.
         *
         * @param key (String) : name of the embedded document
         */
        private ElemMatchElement(String key) {
            this.key = key;
        }

        /**
         * Adds an element in the "ElemMatch" list.
         *
         * @param element (Object) : element to add
         */
        private void addElementInElemMatch(Object element) {
            listElemMatch.add(element);
        }

        /**
         * Generates the query for mongo with MongoAPI objects.
         *
         * @return BasicDBObject : part of the query in mongo objects
         */
        private BasicDBObject generateDBObject() {
            BasicDBObject list = new BasicDBObject();
            for (Object element : listElemMatch) {
                if (element instanceof Criteria) {
                    if (((Criteria) element).operator == null) {
                        list.put(((Criteria) element).key, ((Criteria) element).value);
                    } else {
                        list.put(((Criteria) element).key, new BasicDBObject(((Criteria) element).operator, ((Criteria) element).value));
                    }
                }
            }
            BasicDBObject elemMatch = new BasicDBObject();
            elemMatch.put("$elemMatch", list);
            return elemMatch;
        }
    }

    /**
     * Class that represents a criteria in an "And" or "ElemMatch" unit.
     *
     * @author jerome
     */
    private class Criteria {

        /**
         * Key that represents the field in the Mongo Document.
         */
        private String key;

        /**
         * Operator with $ to apply on the value constraint.
         */
        private String operator;

        /**
         * Value of the field in the Mongo Document.
         */
        private String value;

        /**
         * Constructor.
         *
         * @param key   (String) : name of the field
         * @param value (String) : constraint
         */
        public Criteria(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Constructor.
         *
         * @param key      (String) : name of the field
         * @param operator (String) : operator with $ to apply to the constraint value
         * @param value    (String) : constraint
         */
        public Criteria(String key, String operator, String value) {
            this.key = key;
            this.operator = operator;
            this.value = value;
        }
    }

}
