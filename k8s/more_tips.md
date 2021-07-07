Dry run to create yaml file

    kubectl create deployment deploy-demo --image=haodeng/deploy-demo:latest --dry-run='client' --output='yaml' > deploy-demo.yaml

    kubectl apply -f deploy-demo.yaml

Check event

    kubectl describe pod/deploy-demo-b47bc96f5-dg2rl

Check log

    kubectl logs -f pod/deploy-demo-b47bc96f5-dg2rl
