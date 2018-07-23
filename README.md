Developed with the help of:   https://runnable.com/docker/java/dockerize-your-java-application

To build a docker - execute in Window's PowerShell - line by line:

docker login

git clone https://github.com/gilad-bendor/categorize.git
cd categorize

docker build -f Dockerfile -t categorize .
docker run -it -p 8080:8080 --rm -v ${PWD}\categorize:/app -w /app categorize bash -c "rm -rf targer /tomcat/webapps/springwebapp ; mvn clean install && cp /app/target/springwebapp.war /tomcat/webapps/ && /tomcat/bin/catalina.sh run"
