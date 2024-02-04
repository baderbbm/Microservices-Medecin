FROM maven:3.8-openjdk-17

WORKDIR /app
COPY . /app

# Créez un fichier de configuration
RUN echo "spring.data.mongodb.host=host.docker.internal" > /app/application.properties
RUN echo "spring.data.mongodb.port=27017" >> /app/application.properties
RUN echo "spring.data.mongodb.database=service" >> /app/application.properties

# Exécutez mvn install pour télécharger les dépendances
RUN mvn install

# Exécutez mvn package pour construire le JAR
RUN mvn -B package

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]