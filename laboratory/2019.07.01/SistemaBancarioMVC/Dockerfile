FROM gradle as BUILDER
WORKDIR /usr/app/
COPY build.gradle settings.gradle gradlew /usr/app/
RUN gradle build || return 0
COPY . .
RUN gradle bootJar

FROM openjdk:12
WORKDIR /usr/app/
COPY --from=BUILDER /usr/app/build/libs/* ./app.jar
EXPOSE 8080
CMD exec java -jar app.jar
