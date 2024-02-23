FROM openjdk:17-jdk-oracle
ADD src/main/resources/application.properties src/main/resources/application.properties
ADD target/dip_neto-0.0.1-SNAPSHOT.jar cloud_storage.jar
CMD ["java", "-jar", "cloud_storage.jar"]