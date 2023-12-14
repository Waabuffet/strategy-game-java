FROM openjdk:8

ENV DISPLAY=host.docker.internal:0

RUN apt update -y
RUN apt-get install -y libxrender1 libxtst6 libxi6

WORKDIR /app

COPY ./entrypoint.sh /root/entrypoint.sh
RUN chmod +x /root/entrypoint.sh

ENTRYPOINT [ "/root/entrypoint.sh" ]