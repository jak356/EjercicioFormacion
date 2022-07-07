FROM openjdk
COPY "./target/balancer-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]