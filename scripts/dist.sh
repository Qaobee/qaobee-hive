#!/bin/bash

#{
    red='\e[0;31m'
    green='\e[0;32m'
    NC='\e[0m' # No Color
    echo -e "${green}****************************************************"
    echo -e "Running build"
    echo -e "****************************************************${NC}"
    semver init
    semver inc patch
    tag=$(semver tag)
    ./gradlew -Penv=prod clean build test apidoc javadoc modZip
    STATUS=$?
    if [ $STATUS -eq 0 ]; then
        echo -e "${green}****************************************************"
        echo -e "Collecting GIT stats"
        echo -e "****************************************************${NC}"
        git_stats generate -o build/reports/git
        echo -e "${green}****************************************************"
        echo -e "Build ok, processing openshift deployment"
        echo -e "****************************************************${NC}"
        cp build/libs/hive-0.1.zip ../qaobee-swarn-dist/dist/swarn-mod.zip
        cd ../qaobee-swarn-dist
        git commit -m 'dist' dist/swarn-mod.zip
        rhc app-tidy -l contact@qaobee.com hive
        git push origin master
        cd ../qaobee-hive
        ./scripts/changelog.sh > CHANGELOG.md
        git commit -m 'changelog' CHANGELOG.md
        git push origin master
        echo -e "${green}****************************************************"
        echo -e "Deployment ok, processing docker : $tag"
        echo -e "****************************************************${NC}"
        git tag -a $tag -m "$tag"
        git push origin --tags
        docker build -t qaobee-hive:$tag .
        docker tag -f qaobee-hive:$tag tutum.co/giwi/qaobee-hive:$tag
        docker push tutum.co/giwi/qaobee-hive:$tag
        echo -e "${green}****************************************************"
        echo -e "Deploy doc"
        echo -e "****************************************************${NC}"
        lftp ftp://heber_15054748:zaza666@ftp.hebergratuit.net -e "mirror -e -R --parallel=20 --only-newer --verbose build/docs /htdocs/hive/docs; quit"
        lftp ftp://heber_15054748:zaza666@ftp.hebergratuit.net -e "mirror -e -R --parallel=20 --only-newer --verbose build/reports /htdocs/hive/reports; quit"
    else
        echo -e "${red}****************************************************"
        echo -e "Build Failed"
        echo -e "****************************************************${NC}"
    fi
#}> build.log
#cat build.log | mail -s "Qaobee-swarn build" marin.xavier@gmail.com
