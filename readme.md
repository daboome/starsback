This is a concept-proof demo Stars application

To clone application into your working directory type

git clone https://github.com/daboome/starsback.git

To start the application type within your working directory

cd starsback; mvn spring-boot:run

The application would start the embedded Tomcat web-server on localhost:8080 

To test the application type within your working directory

cd starsback; mvn surefire:test

CAUTION:
This application happens to work with persistent data.
MySQL database connection is required. To run the application install and start MySQL database server 
with username root and empty password, or use docker container. Stars database schema first needs to be created.