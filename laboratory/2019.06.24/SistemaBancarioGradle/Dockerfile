FROM gradle as BUILDER
WORKDIR /usr/app/
COPY build.gradle settings.gradle gradlew /usr/app/
RUN gradle build || return 0
COPY . .
RUN gradle build

FROM openjdk:12
WORKDIR /usr/app/
COPY --from=BUILDER /usr/app/build/libs/SistemaBancarioGradle.jar .
CMD exec java -jar SistemaBancarioGradle.jar
