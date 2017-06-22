FROM openjdk

VOLUME /tmp
ADD maven/demo-0.0.1-SNAPSHOT.jar starsback.jar
RUN sh -c 'touch /starsback.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/starsback.jar"]