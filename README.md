Developed with the help of:   https://runnable.com/docker/java/dockerize-your-java-application

To build a docker:

docker login
wget ....
cd ...
docker build -f Dockerfile -t demo/spring:maven-3.3-jdk-8 .
docker run -it -p 8080:8080 --rm -v c:/Users/gilad.bendor/Desktop/ParseDocker/categorize:/app -w /app demo/spring:maven-3.3-jdk-8 bash -c "
    rm -rf targer /tomcat/webapps/springwebapp
    mvn clean install &&
    cp /app/target/springwebapp.war /tomcat/webapps/ &&
    /tomcat/bin/catalina.sh run
"
