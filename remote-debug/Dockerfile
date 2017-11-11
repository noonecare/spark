FROM docker.io/airdock/oracle-jdk
ADD target/remote-debug-1.0-SNAPSHOT.jar /opt
WORKDIR /opt
EXPOSE 5005
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005", "-cp", "remote-debug-1.0-SNAPSHOT.jar", "MeetAndBye"]
