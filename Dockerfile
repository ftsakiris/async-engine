FROM java:8-jdk-alpine

WORKDIR /code

EXPOSE 8081

ADD releases/async-engine-1.0.0.jar /code/async-engine-1.0.0.jar
CMD java -jar /code/async-engine-1.0.0.jar