FROM docker.io/sequenceiq/spark
ADD target /target
WORKDIR /target

ENTRYPOINT ["spark-submit", "--class", "CalculatePI", "spark-use-docker-demo-1.0-SNAPSHOT-jar-with-dependencies.jar"]