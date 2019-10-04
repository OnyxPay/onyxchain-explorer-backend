#!/bin/bash

TAG=$1
echo "install awscli"
pip install --user awscli
export PATH=$PATH:$HOME/.local/bin

echo "ecr login"
eval $(aws ecr get-login --region us-east-2 --no-include-email)
for i in ontsynhandler explorer holder statistics ; do
    docker push 866680356172.dkr.ecr.us-east-2.amazonaws.com/blockexplorer-v2-$i ;
done

if [ $TAG == "main" ] ; then
    aws ecs update-service --cluster blockexplorer-v2-main --service blockexplorer-v2 --region us-east-2 --force-new-deployment ;
    aws ecs update-service --cluster blockexplorer-v2-test --service blockexplorer-v2-test --region us-east-2 --force-new-deployment ;
fi