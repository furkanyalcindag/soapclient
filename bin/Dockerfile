FROM maven:3.6.3-jdk-11-openj9 as build
WORKDIR /SoapClient
COPY . .
RUN mvn clean install


FROM openjdk:11-jre-slim
WORKDIR /SoapClient
COPY --from=build /SoapClient/target/consume-0.0.1-SNAPSHOT.jar .

CMD["java", "-jar", "consume-0.0.1-SNAPSHOT.jar"]