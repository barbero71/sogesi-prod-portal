FROM tomcat:9.0-jdk17

# Rimuove le app di default di Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia il WAR come ROOT per averlo su http://host/ invece di http://host/prod/
COPY target/prod-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Configura il datasource JNDI Oracle tramite variabili d'ambiente
COPY docker/context.xml /usr/local/tomcat/conf/context.xml

ENV JAVA_OPTS="-Duser.timezone=Europe/Rome -Doracle.jdbc.timezoneAsRegion=false"

EXPOSE 8080

CMD ["catalina.sh", "run"]
