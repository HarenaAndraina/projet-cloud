# Utiliser l'image officielle Tomcat
FROM tomcat:10.1-jdk21

# Copier l'application dans le répertoire webapps de Tomcat
COPY target/projet-servlet.war /usr/local/tomcat/webapps/

# Exposer le port 8080 pour accéder au serveur Tomcat
EXPOSE 8080

# Commande pour démarrer Tomcat
CMD ["catalina.sh", "run"]
