# Étape 1 : Utiliser l'image officielle OpenJDK 21 comme base
FROM openjdk:21-jdk

# Étape 2 : Installer Tomcat 10.1
ENV CATALINA_HOME=/usr/local/tomcat
RUN mkdir -p "$CATALINA_HOME"
WORKDIR "$CATALINA_HOME"
RUN curl -O https://downloads.apache.org/tomcat/tomcat-10/v10.1.10/bin/apache-tomcat-10.1.10.tar.gz \
    && tar -xvf apache-tomcat-10.1.10.tar.gz --strip-components=1 \
    && rm apache-tomcat-10.1.10.tar.gz

# Étape 3 : Copier l'application .war générée par Maven
COPY target/projet_cloud.war $CATALINA_HOME/webapps/projet_cloud.war

# Étape 4 : Exposer le port 8080 pour Tomcat
EXPOSE 8080

# Étape 5 : Lancer Tomcat
CMD ["bin/catalina.sh", "run"]
