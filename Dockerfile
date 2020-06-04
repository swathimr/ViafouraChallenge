FROM openjdk:8
VOLUME /tmp
ADD target/viafoura-challenge-1.0.jar viafoura-challenge-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","viafoura-challenge-1.0.jar"]