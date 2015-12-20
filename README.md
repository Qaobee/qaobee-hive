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

    ./gradlew -Penv=prod clean build modZip -x test
    docker build -t qaobee-hive .
    // Exécution en Prod
    docker run --name qswarm-hive -ti -p 8080:8080 -d qaobee-hive
    docker stop qswarm-hive
    
Et pour une exécution locale : `./run.sh`
    
    