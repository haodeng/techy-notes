Install

    brew install minikube
  
    minikube start
  
    minikube dashboard
  
    kubectl get all
  
Deploy

    kubectl create deployment deploy-demo --image=haodeng/deploy-demo:latest
    
    kubectl get deployment
    kubectl get pods
    kubectl get service
    kubectl get events


Create service

    kubectl expose deployment deploy-demo --type=LoadBalancer --port=8080
    
    kubectl get services

In another terminal

    minikube tunnel
    
Previous terminal

    kubectl get service
    
Find EXTERNAL-IP:

    curl http://EXTERNAL-IP:8080/posts
  
Scale up & down

    kubectl scale deployments.apps/deploy-demo --replicas=4
    kubectl get pods -o wide
    
    kubectl scale deployments.apps/deploy-demo --replicas=2
    kubectl get pods -o wide
    
    # To stop a pod
    kubectl scale deployments.apps/deploy-demo --replicas=0
    
Cleanup

    kubectl delete service deploy-demo
    kubectl delete deployment deploy-demo
    
Stop

    minikube stop
