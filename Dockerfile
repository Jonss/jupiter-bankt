FROM openjdk:8-jdk-alpine

RUN mkdir /jupiter-bank

COPY build/libs/jupiter-bank-0.0.1-SNAPSHOT.jar /jupiter-bank/jupiter-bank-1.0-SNAPSHOT.jar

WORKDIR /jupiter-bank

CMD ["sh", "-c", "java -Dspring.profiles.active=$JAVA_ENV -Djava.security.egd=file:/dev/./urandom -Xms128m -Xmx512m -jar jupiter-bank-1.0-SNAPSHOT.jar"]
