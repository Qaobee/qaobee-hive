# Init

    docker build -t qaobee-hive-db .
    ./run.sh
    mongo admin -u admin -p <PASSWORD> --port 27018 --eval "db.getSiblingDB('hive').addUser({user: 'hive', pwd: 'hive',  roles: [ 'readWrite', 'dbAdmin' ]});
    ./create_all_docker.sh 27018
  
# Run
    
    ./run.sh
