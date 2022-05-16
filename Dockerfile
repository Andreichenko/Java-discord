
# TEST ON openjdk 17 image
FROM openjdk:17-jre-alpine
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y nginx
COPY build/libs/bot-0.1.jar /app.jar
EXPOSE 80
COPY build.sh start.sh
RUN chmod 777 start.sh
RUN echo "daemon off;" >> /etc/nginx/nginx.conf
RUN rm /etc/nginx/sites-enabled/default