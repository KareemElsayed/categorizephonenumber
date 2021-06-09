FROM openjdk:8
ADD target/International_Phone_number.jar International_Phone_number.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "International_Phone_number.jar"]

