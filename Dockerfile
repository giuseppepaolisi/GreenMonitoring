FROM maven:latest
COPY . /app
WORKDIR /app
CMD ["mvn", "clean", "install"]
