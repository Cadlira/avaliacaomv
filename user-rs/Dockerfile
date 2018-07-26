FROM develar/java
MAINTAINER Leonardo Lira
VOLUME ["/tmp"]
ADD build/libs/user-rs-1.0.0.jar app.jar
RUN touch /app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dconfig.server.uri=${CONFIG_SERVER_URL:-http://config-server:8888} -Dconfig.label=${CONFIG_LABEL:-master} -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
