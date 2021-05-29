# Kubernetes
https://kubernetes.io/docs/tutorials/

# Create a Cluster
A Kubernetes cluster consists of two types of resources:

* The Control Plane coordinates the cluster
* Nodes are the workers that run applications

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
Kubectl uses the Kubernetes API to interact with the cluster. 

    kubectl cluster-info
    kubectl get nodes
    # use --help to get additional options
    kubectl get nodes --help

# Deploy an app
The Deployment instructs Kubernetes how to create and update instances of your application. 
Once you've created a Deployment, the Kubernetes control plane schedules the application instances included in that Deployment to run on individual Nodes in the cluster.

Once the application instances are created, a Kubernetes Deployment Controller continuously monitors those instances. 
If the Node hosting an instance goes down or is deleted, the Deployment controller replaces the instance with an instance on another Node in the cluster. 
This provides a self-healing mechanism to address machine failure or maintenance.

    kubectl create deployment kubernetes-bootcamp --image=gcr.io/google-samples/kubernetes-bootcamp:v1
    
    kubectl get deployments

## View the app

    # run in a separate terminal, create a proxy that will forward communications into the cluster-wide, private network.
    kubectl proxy
    
    curl http://localhost:8001/version
In order for the new deployment to be accessible without using the Proxy, a Service is required

# Explore Your App
Pods are the atomic unit on the Kubernetes platform. When we create a Deployment on Kubernetes, that Deployment creates Pods with containers inside them (as opposed to creating containers directly). 
Each Pod is tied to the Node where it is scheduled, and remains there until termination (according to restart policy) or deletion. 
In case of a Node failure, identical Pods are scheduled on other available Nodes in the cluster.


A Pod always runs on a Node. A Node is a worker machine in Kubernetes and may be either a virtual or a physical machine, depending on the cluster. Each Node is managed by the Master. 
A Node can have multiple pods, and the Kubernetes master automatically handles scheduling the pods across the Nodes in the cluster. 
The Master's automatic scheduling takes into account the available resources on each Node.

Every Kubernetes Node runs at least:
* Kubelet, a process responsible for communication between the Kubernetes Master and the Node; it manages the Pods and the containers running on a machine.
* A container runtime (like Docker) responsible for pulling the container image from a registry, unpacking the container, and running the application.


        # look for existing Pods
        kubectl get pods
        
        # details. to view what containers are inside that Pod and what images are used to build those containers 
        kubectl describe pods
        
        export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
        # retrieve these app logs (Anything that the application would normally send to STDOUT)
        kubectl logs $POD_NAME
        
        # execute commands directly on the container, exec command
        kubectl exec $POD_NAME -- env
        kubectl exec -ti $POD_NAME -- bash

# Using a Service to Expose Your App

