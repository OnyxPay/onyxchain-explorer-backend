FROM golang:alpine3.10 AS builder

WORKDIR /go/src/app
RUN wget -O - https://github.com/gasby88/ontology-holder/tarball/924eb3adf60c8517a3e078e2adfd9348ce2dc510 | tar -xz --strip-components=1 && \
    apk add --no-cache git && \
    go get -d -v ./... && go install -v ./...


FROM alpine:3.10

ENV PORT=8082
ENV ONYXCHAIN_NET=main

RUN apk add --no-cache gettext && \
    addgroup app && \
    adduser -G app -s /bin/sh -D app -h /home/app

USER app
WORKDIR /home/app

COPY --from=builder /go/bin/app /go/src/app/log4go.xml /go/src/app/log4go.xml /go/src/app/install.sql ./
COPY config.json.template run.sh ./

EXPOSE ${PORT}
CMD ["sh", "run.sh"]