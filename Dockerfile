FROM maven AS build
WORKDIR /app
RUN mvn clean install

FROM oraclelinux:7-slim

RUN  yum -y install oracle-instantclient-release-el7 && \
     yum -y install oracle-instantclient-basic oracle-instantclient-devel oracle-instantclient-sqlplus && \
     rm -rf /var/cache/yum

# Uncomment if the tools package is added
# ENV PATH=$PATH:/usr/lib/oracle/21/client64/bin

CMD ["sqlplus", "-v"]
