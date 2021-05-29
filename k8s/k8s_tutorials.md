# Kubernetes
https://kubernetes.io/docs/tutorials/

# Create a Cluster
## Creating a Cluster by MiniCube

    minikube version
    minikube start
    
## Cluster version
Both the version of the client and as well as the server. 
The client version is the kubectl version; the server version is the Kubernetes version installed on the master. 

    kubectl version
    Client Version: version.Info{Major:"1", Minor:"20", GitVersion:"v1.20.4", GitCommit:"e87da0bd6e03ec3fea7933c4b5263d151aafd07c", GitTreeState:"clean",   BuildDate:"2021-02-18T16:12:00Z", GoVersion:"go1.15.8", Compiler:"gc", Platform:"linux/amd64"}
    Server Version: version.Info{Major:"1", Minor:"20", GitVersion:"v1.20.2", GitCommit:"faecb196815e248d3ecfb03c680a4507229c2a56", GitTreeState:"clean", BuildDate:"2021-01-13T13:20:00Z", GoVersion:"go1.15.5", Compiler:"gc", Platform:"linux/amd64"}
    
## Cluster details

    kubectl cluster-info
    kubectl get nodes

# Deploy an app

    kubectl create deployment kubernetes-bootcamp --image=gcr.io/google-samples/kubernetes-bootcamp:v1
    
    kubectl get deployments

    
    
