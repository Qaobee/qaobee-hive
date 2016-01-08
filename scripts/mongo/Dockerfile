FROM centos:centos7
MAINTAINER Xavier MARIN <marin.xavier@gmail.com>
RUN rpm -Uvh https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm
RUN yum -y install net-tools
RUN yum --enablerepo=epel -y install pwgen

RUN export mongo_version=2.4.14; \
  cd /opt; \
  curl -O https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-$mongo_version.tgz; \
  tar -zxvf mongodb-linux-x86_64-$mongo_version.tgz; \
  mv mongodb-linux-x86_64-$mongo_version mongodb; \
  export PATH=/opt/mongodb/bin:$PATH; \
  mkdir -p /data/db \
  mkdir -p /home/mongo


ENV AUTH yes
ENV JOURNALING yes
WORKDIR /home/mongo
RUN export PATH=/opt/mongodb/bin:$PATH
COPY . .

RUN chmod +x *.sh

EXPOSE 27017 28017
CMD ["/home/mongo/entrypoint.sh"]
