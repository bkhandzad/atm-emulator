FROM openjdk

MAINTAINER bkhandzad@gmail.com

EXPOSE 8082

COPY --from=atm-emulator_atm_emulator /prj/atmservice-0.0.1.jar /atmservice/atmservice-0.0.1.jar

CMD java -jar /atmservice/atmservice-0.0.1.jar
