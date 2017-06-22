This is a concept-proof demo Stars application

To clone application into your working directory type

    git clone https://github.com/daboome/starsback.git

To start preparations change your current directory to starsback folder

    cd starsback

To run application inside docker container first you need to build artifact.
To do this within starsback folder type
 
    mvn package
    
This would generate the uber-jar artifact that would be used when building a docker image
Then within current folder type

    mvn docker:build
    
This would build ready-to-run docker image containing mentioned uber-jar build at previous step.
Application requires MySQL database to persist data. MySQL database connection is configured within
fabric8 maven plugin. This would bring up new MySQL container and set database connection settings.

Finally to run the starback application with its MySQL container dependency type

    mvn docker:run

The application would start the embedded Tomcat web-server on localhost:8080 

To test the application type within your starsback folder

    mvn surefire:test

Integration tests will be executed against in-memory H2 database.
