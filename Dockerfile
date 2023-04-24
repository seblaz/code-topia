FROM eclipse-temurin:17.0.6_10-jdk-focal as dev

# gvm requires curl and unzip
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        curl \
        zip \
        unzip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# install sdkman
RUN curl -s "https://get.sdkman.io" | bash

# install grails
ARG GRAILS_VERSION=5.3.2
SHELL ["bash", "-c", "source $HOME/.sdkman/bin/sdkman-init.sh"]

RUN sdk install grails $GRAILS_VERSION


FROM dev as builder

# package for production
WORKDIR /app
COPY . .
RUN grails prod war

FROM eclipse-temurin:17.0.6_10-jre-focal as prod

WORKDIR /app
# copy production build
COPY --from=builder /app/build/libs/habitos-fantasticos-0.1.war app.war

# start
ENV PORT=8080
CMD [ \
    "bash", \
    "-c", \
    "java -Dserver.port=$PORT -jar app.war" \
]
