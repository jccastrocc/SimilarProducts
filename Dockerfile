FROM openjdk:8-jdk-alpine
COPY "./target/similar-products-1.0.jar" "app.jar"
COPY "./src/main/filters/filter-develop.properties" "filter-develop.properties"
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=develop", "app.jar"]
