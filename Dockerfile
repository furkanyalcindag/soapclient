FROM maven:3.6.3-jdk-11-openj9 as build
WORKDIR /SoapClient
COPY . .
USER root
#RUN apt update && apt install curl -y

RUN cat /etc/resolv.conf
#RUN apt update && apt install curl -y
RUN mvn clean install -Dmaven.test.skip=true -s settings.xml
#RUN mvn clean install

FROM openjdk:11-jre-slim
WORKDIR /SoapClient
COPY --from=build /SoapClient/target/consume-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "consume-0.0.1-SNAPSHOT.jar"]
