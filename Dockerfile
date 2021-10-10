FROM adoptopenjdk/openjdk15:ub1
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]