#!/bin/bash

TAG=$1
echo "install awscli"
pip install --user awscli
export PATH=$PATH:$HOME/.local/bin

echo "ecr login"
eval $(aws ecr get-login --region us-east-2 --no-include-email)
docker push 866680356172.dkr.ecr.us-east-2.amazonaws.com/blockexplorer-db
docker push 866680356172.dkr.ecr.us-east-2.amazonaws.com/blockexplorer-sync:$TAG
docker push 866680356172.dkr.ecr.us-east-2.amazonaws.com/blockexplorer-api:$TAG
docker push 866680356172.dkr.ecr.us-east-2.amazonaws.com/blockexplorer-nginx:$TAG
aws ecs update-service --service blockexplorer --cluster blockexplorer-$TAG --region us-east-2 --force-new-deployment