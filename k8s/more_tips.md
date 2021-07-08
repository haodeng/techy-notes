Dry run to create yaml file

    kubectl create deployment deploy-demo --image=haodeng/deploy-demo:latest --dry-run='client' --output='yaml' > deploy-demo.yaml

    kubectl apply -f deploy-demo.yaml

Check current deployment status:
    
    # status section added, more info also added to spec and metadata
    kubectl get deployment deploy-demo -o yaml

Check event

    kubectl describe pod/deploy-demo-b47bc96f5-dg2rl

Check log

    kubectl logs -f pod/deploy-demo-b47bc96f5-dg2rl

Login to the pod container

    kubectl exec -it pod/deploy-demo-b47bc96f5-c486l -- bin/bash

Delete the depolyment by yaml file

    kubectl delete -f deploy-demo.yaml
    
    # This equals to 
    kubectl delete deployment deploy-demo


# Quick bootup

    kubectl create deployment deploy-demo --image=haodeng/deploy-demo:latest --dry-run='client' --output='yaml' > deploy-demo.yaml
    kubectl apply -f deploy-demo.yaml
    kubectl create service clusterip deploy-demo --tcp=8080:8080 --dry-run=client -o=yaml > service.yaml
    kubectl apply -f service.yaml 
    kubectl port-forward service/deploy-demo 8080:8080
    curl http://localhost:8080/posts
    
