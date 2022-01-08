FROM maven:3.8.4-openjdk-11

MAINTAINER bkhandzad@gmail.com
#VOLUME ["/root/.m2/repository"]

RUN mkdir -p /prj

COPY . /app/

WORKDIR /app/


RUN mvn clean install package -DskipTests

RUN cp ./bankservice/target/bankservice-0.0.1.jar /prj/bankservice-0.0.1.jar
RUN cp ./atmservice/target/atmservice-0.0.1.jar /prj/atmservice-0.0.1.jar
VOLUME ["/prj"]
