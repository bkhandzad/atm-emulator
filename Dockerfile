FROM maven:3.8.4-openjdk-11

MAINTAINER bkhandzad@gmail.com

USER root

COPY . /tmp/app/

WORKDIR /tmp/app/

RUN mvn clean install package -DskipTests
RUN ll /tmp/app/banckservice/target/
RUN ll /project/
COPY /tmp/app/bankservice/target/bankservice-0.0.1.jar /project/bankservice-0.0.1.jar
COPY /tmp/app/atmservice/target/atmservice-0.0.1.jar /project/atmservice-0.0.1.jar

RUN ll /project

