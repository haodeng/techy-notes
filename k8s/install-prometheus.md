
    minikube start --cpus 4 --memory 8192 --vm-driver hyperkit
    
add repos

    helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
    helm repo add stable https://charts.helm.sh/stable
    helm repo update
    
install chart

    helm install prometheus prometheus-community/kube-prometheus-stack
