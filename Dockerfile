# Builder stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline

COPY src/ /app/src/
RUN mvn -B -o package

# Runtime stage
FROM tomcat:9-jdk17-adoptopenjdk-hotspot
COPY --from=build /app/target/your-webapp.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
