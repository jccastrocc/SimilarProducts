FROM openjdk:8-jdk-alpine
COPY "./target/similar-products-1.0.jar" "app.jar"
COPY "./src/main/filters/filter-production.properties" "filter-production.properties"
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "app.jar"]
