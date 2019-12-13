FROM java:8

MAINTAINER patchipulusu.naresh@gmail.com

ADD docker/startup.sh /
RUN mkdir -p /etc/conf/ens/ens-service
RUN mkdir -p /var/log/ens

ADD src/main/resources/logback-spring.xml /etc/conf/ens/ens-service/logback-spring.xml

EXPOSE 8080

ENTRYPOINT ["sh", "/startup.sh"]

# add the jar file
ADD target/ens.jar ens.jar