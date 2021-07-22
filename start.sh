#!/bin/bash
echo "start of  company service"
cd /home/ubuntu/companyservice
kubectl apply -f company-service.yml 
kubectl apply -f company-deployment_temp.yml
echo "end of  event service"