## pre-commit.sh
 
# Store git index status
git stash -q --keep-index
 
PWD=`pwd`
grunt jshint --gruntFile/Gruntfile.js 

# Store tests result
RESULT=$?
 
# Restore previous index status
git stash pop -q
 
[ $RESULT -ne 0 ] && exit 1
exit 0