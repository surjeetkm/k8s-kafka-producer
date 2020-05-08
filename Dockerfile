FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/k8s-kafka.jar k8s-kafka.jar
ENTRYPOINT ["java","-jar","k8s-kafka.jar"]