FROM develar/java
MAINTAINER Leonardo Lira
VOLUME ["/tmp"]
EXPOSE 8888
ADD build/libs/config-server-1.0.0.jar app.jar
RUN touch /app.jar
ENTRYPOINT ["sh", "-c", "java  ${JAVA_OPTS} -Dserver.port=${CONFIG_SERVER_PORT} -Dconfigs.dir=${CONFIG_FILES_DIR} -Dgit.url=${CONFIG_GIT_URL} -Dgit.username=${CONFIG_GIT_USERNAME} -Dgit.password=${CONFIG_GIT_PASSWORD} -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
