FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 4000

ADD ./target/insurance-api.jar app.jar

RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring-boot.run.profiles=production -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]