FROM openjdk

MAINTAINER bkhandzad@gmail.com

EXPOSE 8083

COPY --from=atm-emulator_atm_emulator /prj/bankservice-0.0.1.jar /bankservice/bankservice-0.0.1.jar

CMD java -jar /bankservice/bankservice-0.0.1.jar
