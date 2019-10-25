#!/bin/bash

echo "install awscli"
pip install --user awscli
export PATH=$PATH:$HOME/.local/bin

echo "ecr login"
eval $(aws ecr get-login --region us-east-2 --no-include-email)
docker-compose push ontsynhandler explorer statistics # TODO: ONXP-1650 Fix holder build

aws ecs update-service --cluster blockexplorer-v2-main --service blockexplorer-v2 --region us-east-2 --force-new-deployment
aws ecs update-service --cluster blockexplorer-v2-test --service blockexplorer-v2-test --region us-east-2 --force-new-deployment