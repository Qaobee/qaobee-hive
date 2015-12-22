# Qaobee - Hive

API Qaobee

## setup 

- docker (déploiements uniquement) 
    - [https://docs.docker.com/installation/mac/](OSX) 
    - [https://docs.docker.com/installation/ubuntulinux/](Ubuntu) 
    - [https://docs.docker.com/engine/installation/windows/](Windows)
- nodeJs 
    - [https://nodejs.org/en/download/package-manager/](Ubuntu/OSX) 
    - [https://nodejs.org/en/download/](Windows)


    sudo apt-get install git_stats lftp gem
    sudo gem install semver
    sudo npm install -g apidoc

## repports
- ./gradlew test
- ./gradlew apidoc
- git_stats generate -o build/reports/git

## Déploiement
    
    ./scripts/dist.sh

## Docker

### Construction

    ./gradlew -Penv=prod clean build modZip -x test
    docker build -t qaobee-hive .

### Exécution en Prod
    
    docker run --name qswarm-hive -ti -p 8080:8080 -d qaobee-hive
    docker stop qswarm-hive
    
### Exécution locale 

    ./gradlew clean build modZip -x test
    docker build -t qaobee-hive .
    ./run.sh
    
ou

    LOCALHOST=$(docker run --rm --net=host alpine ip route get 8.8.8.8 | awk '{ print $7;  }')
    echo $LOCALHOST
    docker run -t --rm -i \
    -e OPENSHIFT_MONGODB_DB_HOST=mongo \
    -e OPENSHIFT_MONGODB_DB_PORT=27017 \
    -e OPENSHIFT_MONGODB_DB_PASSWORD=hive \
    -e OPENSHIFT_MONGODB_DB_USERNAME=hive \
    --add-host=mongo:$LOCALHOST \
    -p 8080:8080 \
    qaobee-hive:latest
    
    