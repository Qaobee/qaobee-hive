FROM java:8-jre-alpine
MAINTAINER Xavier MARIN <marin.xavier@gmail.com>

RUN apk add --update bash && rm -rf /var/cache/apk/*
RUN mkdir /home/qaobee
ADD application/qaobee-hive-0.1-fat.jar /home/qaobee/qaobee-hive-0.1-fat.jar
ADD scripts/docker-runVertx.sh /home/qaobee/docker-runVertx.sh
RUN chmod +x /home/qaobee/docker-runVertx.sh
WORKDIR /home/qaobee
EXPOSE 8080
CMD ["/home/qaobee/docker-runVertx.sh"]