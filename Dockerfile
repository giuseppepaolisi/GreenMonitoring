FROM oraclelinux:7-slim

RUN  yum -y install oracle-instantclient-release-el7 && \
     yum -y install oracle-instantclient-basic oracle-instantclient-devel oracle-instantclient-sqlplus && \
     rm -rf /var/cache/yum

# Uncomment if the tools package is added
# ENV PATH=$PATH:/usr/lib/oracle/21/client64/bin

# Builder stage
FROM maven:3.9.0-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline

COPY src/ /app/src/
RUN mvn -B -o package

# Runtime stage
FROM tomcat:9
COPY --from=build /app/target/your-webapp.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
