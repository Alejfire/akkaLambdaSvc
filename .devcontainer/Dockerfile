FROM mcr.microsoft.com/devcontainers/base:ubuntu

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-11-jdk curl git gnupg ca-certificates apt-transport-https unzip build-essential \
  && rm -rf /var/lib/apt/lists/*

# Install sbt
RUN curl -fsSL https://keybase.io/sbt/pgp_keys.asc | gpg --dearmor -o /usr/share/keyrings/sbt-archive-keyring.gpg \
  && echo "deb [signed-by=/usr/share/keyrings/sbt-archive-keyring.gpg] https://repo.scala-sbt.org/scalasbt/debian all main" \
     | tee /etc/apt/sources.list.d/sbt.list \
  && apt-get update && apt-get install -y sbt \
  && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
