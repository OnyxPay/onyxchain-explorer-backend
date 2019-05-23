FROM nginx:latest
COPY default.conf /etc/nginx/conf.d/
ARG tag
RUN if [ "${tag}" != "prod" ] ; then SERVERNAME=cepheus ; else SERVERNAME=andromeda ; fi && sed -i "s/SERVERNAME/${SERVERNAME}-explorer.onyxpay.co/g" /etc/nginx/conf.d/default.conf

