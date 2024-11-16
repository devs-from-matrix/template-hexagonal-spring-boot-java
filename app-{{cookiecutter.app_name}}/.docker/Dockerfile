FROM gcr.io/distroless/java21-debian12

WORKDIR /app

COPY ../../bootstrap/target/{{cookiecutter.domain}}-exec.jar .

CMD ["{{cookiecutter.domain}}-exec.jar"]