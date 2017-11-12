mvn clean package
docker build -t rest-service .
docker run -d -p 9000:8080 --name rest-service rest-service
