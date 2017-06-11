FROM java:8

# Install maven
RUN apt-get update
RUN apt-get install -y maven

WORKDIR /code

# Add pom file
ADD pom.xml /code/pom.xml

# Add settings file
ADD src/main/resources/application.properties /code/application.properties

# Adding source, compile and package into a fat jar
ADD src /code/src
RUN ["mvn", "package"]

# Set environment to an ENV variable, use it to running script
ENV env=$env

EXPOSE 8081
EXPOSE 27017

CMD ["mvn", "spring-boot:run"]