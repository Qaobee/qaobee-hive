
#!/bin/bash
export PATH=/opt/mongodb/bin:$PATH
mongod --dbpath /data/db --nojournal &
while ! netstat -an | grep 27017; do sleep 1; done
mongo $DB --eval "db.createUser({ user: 'hive', pwd: 'hive', roles: [ { role: 'readWrite', db: 'hive' } ] });";
./create_all.sh;
mongod --dbpath /data/db --shutdown
