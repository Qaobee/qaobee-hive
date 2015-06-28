#!/bin/bash

#{
    red='\e[0;31m'
    green='\e[0;32m'
    NC='\e[0m' # No Color
    echo -e "${green}****************************************************"
    echo -e "Running build"
    echo -e "****************************************************${NC}"
    ./gradlew clean build test apidoc javadoc modZip
    STATUS=$?
    if [ $STATUS -eq 0 ]; then
        echo -e "${green}****************************************************"
        echo -e "Collecting GIT stats"
        echo -e "****************************************************${NC}"
        git_stats generate -o build/reports/git
        echo -e "${green}****************************************************"
        echo -e "Build ok, processing openshift deployment"
        echo -e "****************************************************${NC}"
        cp target/swarn-mod.zip ../qaobee-swarn-dist/dist/swarn-mod.zip
        cd ../qaobee-swarn-dist
        git commit -m 'dist' dist/swarn-mod.zip
        rhc app-tidy swarn
        git push origin master
        cd ../qaobee-swarn
        echo -e "${green}****************************************************"
        echo -e "Deploy doc"
        echo -e "****************************************************${NC}"
   #     ncftpput -R -u "heber_15054748" -p "zaza666" ftp.hebergratuit.net /htdocs target/site

    else
        echo -e "${red}****************************************************"
        echo -e "Build Failed"
        echo -e "****************************************************${NC}"
    fi
#}> build.log
#cat build.log | mail -s "Qaobee-swarn build" marin.xavier@gmail.com
