
    minikube start --cpus 4 --memory 8192 --vm-driver hyperkit
    
add repos

    helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
    helm repo add stable https://kubernetes-charts.storage.googleapis.com/
    helm repo update
    
install chart

    helm install prometheus prometheus-community/kube-prometheus-stack
