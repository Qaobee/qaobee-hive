FROM java:8
MAINTAINER Xavier MARIN <marin.xavier@gmail.com>
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
#RUN export vertx_run_options="-instances 5 -conf /home/qaobee/conf.json"
#RUN export VERTX_OPTS="-XX:PermSize=128m -XX:MaxPermSize=256m -Xms512m -Xmx1g -XX:+UseParallelGC"
CMD ["/home/qaobee/docker-runVertx.sh"]