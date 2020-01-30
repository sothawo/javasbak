FROM openjdk:8-jre

LABEL maintainer="<pj.meisch@sothawo.com>"

RUN addgroup javasbak \
  && adduser --disabled-login --no-create-home --disabled-login --ingroup javasbak javasbak

USER javasbak

ADD ["@project.build.finalName@.jar", "./"]

ENTRYPOINT ["java", "-jar", "@project.build.finalName@.jar"]

EXPOSE 8080
