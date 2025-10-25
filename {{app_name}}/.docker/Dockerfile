FROM gcr.io/distroless/java21-debian12

WORKDIR /app

COPY ../../bootstrap/target/{{domain}}-exec.jar .

CMD ["{{domain}}-exec.jar"]