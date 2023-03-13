FROM oracle/graalvm-ce:latest

ENV JAVA_HOME=/usr/local/graalvm-ce-java17-21.3.0
ENV PATH=$PATH:$JAVA_HOME/bin

WORKDIR /app

COPY . /app

CMD ["java", "-jar", "your-application.jar"]
