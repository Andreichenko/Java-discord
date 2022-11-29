FROM openjdk:17-buster
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y nginx
COPY build/libs/bot-0.1.jar /app.jar
COPY ui/index.html /var/www/html/index.html
COPY ui/nginx.conf /etc/nginx/sites-enabled/nginx.conf
EXPOSE 80
COPY start.sh start.sh
RUN chmod 777 start.sh
RUN echo "daemon off;" >> /etc/nginx/nginx.conf
RUN rm /etc/nginx/sites-enabled/default
CMD ./start.sh