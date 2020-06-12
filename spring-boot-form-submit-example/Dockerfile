# Derived from official openjdk
FROM openjdk:8
#Add the jar file
ADD ./target/spring-boot-form-submit-example-1.0-SNAPSHOT.war spring-boot-form-submit-example-1.0-SNAPSHOT.war
EXPOSE 8085
ENTRYPOINT [ "java", "-jar", "spring-boot-form-submit-example-1.0-SNAPSHOT.war" ]
