FROM java:8-jre-alpine
MAINTAINER Xavier MARIN <marin.xavier@gmail.com>

RUN apk add --update bash && rm -rf /var/cache/apk/*
RUN wget http://dl.bintray.com/vertx/downloads/vert.x-2.1.5.tar.gz && \
    tar zxf vert.x-2.1.5.tar.gz -C /usr/share && \
    rm vert.x-2.1.5.tar.gz && \
    ln -s /usr/share/vert.x-2.1.5/bin/vertx /usr/bin/vertx

RUN mkdir /home/qaobee
ADD src/main/resources/PROD/config.json /home/qaobee/PROD-conf.json
ADD src/main/resources/REC/config.json /home/qaobee/REC-conf.json
ADD src/main/resources/config.json /home/qaobee/DEV-conf.json
ADD build/libs/hive-0.1.zip /home/qaobee/hive-0.1.zip
ADD scripts/docker-runVertx.sh /home/qaobee/docker-runVertx.sh
RUN chmod +x /home/qaobee/docker-runVertx.sh
WORKDIR /home/qaobee
EXPOSE 8080
CMD ["/home/qaobee/docker-runVertx.sh"]