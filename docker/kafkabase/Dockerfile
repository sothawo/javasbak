FROM openjdk:8-jre

LABEL maintainer="<pj.meisch@sothawo.com>"

RUN addgroup kafka \
  && adduser --disabled-login --no-create-home --disabled-login --ingroup kafka kafka

USER kafka

ADD ["kafka_2.11-0.11.0.0.tgz", "/"]

WORKDIR kafka_2.11-0.11.0.0

