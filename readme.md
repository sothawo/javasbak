# javasbak

a simple chat application built with
 * Java
 * Vaadin
 * Spring Boot
 * Apache Kafka
 
the project's name is built from the first letters of the language/tools/product used to build it.

Just a demo to show how the language, frameworks and tools work together. 

[detailed description here](https://www.sothawo.com/2017/10/the-simple-web-based-chat-application-now-implemented-with-java/)

## the docker directory

this directory contain the scripts that are necessary to run all components in docker, the files are for docker 
running in swarm mode. To get it going:

    cd docker
    cd kafkabase
    docker build -t javasbak-kafkabase .
    cd ../zookeeper
    docker build -t javasbak-zookeeper .
    cd ../kafkabroker
    docker build -t javasbak-kafkabroker .
    cd ../..
    mvn package 
    cd target
    sh ./build-docker-images.sh
    cd ../docker
    docker stack deploy --compose-file=docker-compose.yml javasbak

