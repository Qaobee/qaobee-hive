FROM centos:centos7
MAINTAINER Xavier MARIN <marin.xavier@gmail.com>
# https://github.com/frodenas/docker-mongodb/blob/master/scripts/first_run.sh


RUN yum -y install net-tools

RUN export mongo_version=3.2.0; \
  cd /opt; \
  curl -O https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-$mongo_version.tgz; \
  tar -zxvf mongodb-linux-x86_64-$mongo_version.tgz; \
  mv mongodb-linux-x86_64-$mongo_version mongodb; \
  export PATH=/opt/mongodb/bin:$PATH; \
  mkdir -p /data/db; \
  mkdir -p /home/mongo

WORKDIR /home/mongo
COPY . .

RUN chmod +x *.sh
# USER mongodb
RUN ./entrypoint.sh

EXPOSE 27017
ENTRYPOINT ["/opt/mongodb/bin/mongod", "--config", "/home/mongo/mongod.conf"]
CMD ["--quiet"]
