FROM openjdk
COPY ./target/GatewayTest-0.0.1-SNAPSHOT.jar .
ENTRYPOINT exec java -jar GatewayTest-0.0.1-SNAPSHOT.jar
EXPOSE 8080