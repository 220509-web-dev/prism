mvn clean package -Dspring.profiles.active=test
docker build -t prism-api .
docker run -p 5000:5000 prism-api:latest
