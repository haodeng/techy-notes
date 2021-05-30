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
.

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
A Service in Kubernetes is an abstraction which defines a logical set of Pods and a policy by which to access them. 
Services enable a loose coupling between dependent Pods. 
A Service is defined using YAML (preferred) or JSON, like all Kubernetes objects. 
The set of Pods targeted by a Service is usually determined by a LabelSelector

Although each Pod has a unique IP address, those IPs are not exposed outside the cluster without a Service. Services allow your applications to receive traffic. Services can be exposed in different ways by specifying a type in the ServiceSpec:
* ClusterIP (default) - Exposes the Service on an internal IP in the cluster. This type makes the Service only reachable from within the cluster.
* NodePort - Exposes the Service on the same port of each selected Node in the cluster using NAT. Makes a Service accessible from outside the cluster using NodeIP:NodePort. Superset of ClusterIP.
* LoadBalancer - Creates an external load balancer in the current cloud (if supported) and assigns a fixed, external IP to the Service. Superset of NodePort.
* ExternalName - Maps the Service to the contents of the externalName field (e.g. `foo.bar.example.com`), by returning a CNAME record with its value. No proxying of any kind is set up.


## Services and Labels
A Service routes traffic across a set of Pods. Services are the abstraction that allow pods to die and replicate in Kubernetes without impacting your application. Discovery and routing among dependent Pods (such as the frontend and backend components in an application) is handled by Kubernetes Services.

Services match a set of Pods using labels and selectors, a grouping primitive that allows logical operation on objects in Kubernetes. Labels are key/value pairs attached to objects and can be used in any number of ways:

* Designate objects for development, test, and production
* Embed version tags
* Classify an object using tags
...

        # a default service already there
        kubectl get services
    
        # create a new service and expose it to external traffic ((minikube does not support the LoadBalancer option yet)
        kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080
    
        # To find out what port was opened externally (by the NodePort option) 
        kubectl describe services/kubernetes-bootcamp
    
        export NODE_PORT=$(kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}')
        curl $(minikube ip):$NODE_PORT

## Using labels

     # can see the name of the label
     kubectl describe deployment
     
     # use this label to query our list of Pods, service
     kubectl get pods -l app=kubernetes-bootcamp
     kubectl get services -l app=kubernetes-bootcamp
     
     export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
     # apply a new label to pod
     kubectl label pod $POD_NAME version=v1
     
     # check it with the describe pod
     kubectl describe pods $POD_NAME
     
     # query now the list of pods using the new label
     kubectl get pods -l version=v1
 
 ## Deleting a service
 
    kubectl delete service -l app=kubernetes-bootcamp
    
    # confirm it's gone
    kubectl get services
    
    # To confirm that route is not exposed anymore. the app is not reachable anymore from outside of the cluster.
    curl $(minikube ip):$NODE_PORT
    
    # confirm that the app is still running with a curl inside the pod
    kubectl exec -ti $POD_NAME -- curl localhost:8080

# Scaling an application
Scaling is accomplished by changing the number of replicas in a Deployment
Scaling out a Deployment will ensure new Pods are created and scheduled to Nodes with available resources. Scaling will increase the number of Pods to the new desired state. 
Services have an integrated load-balancer that will distribute network traffic to all Pods of an exposed Deployment. Services will monitor continuously the running Pods using endpoints, to ensure the traffic is sent only to available Pods.

    # To see the ReplicaSet created by the Deployment
    kubectl get rs
the name of the ReplicaSet is always formatted as [DEPLOYMENT-NAME]-[RANDOM-STRING]

    # scale the Deployment to 4 replicas.
    kubectl scale deployments/kubernetes-bootcamp --replicas=4
    
    # check. we have 4 instances of the application available
    kubectl get deployments
    
    # There are 4 Pods now, with different IP addresses.
    kubectl get pods -o wide
    
    # The change was registered in the Deployment events log. 
    kubectl describe deployments/kubernetes-bootcamp

# Performing a Rolling Update
Rolling updates allow Deployments' update to take place with zero downtime by incrementally updating Pods instances with new ones. The new Pods will be scheduled on Nodes with available resources.
In Kubernetes, updates are versioned and any Deployment update can be reverted to a previous (stable) version.
if a Deployment is exposed publicly, the Service will load-balance the traffic only to available Pods during the update. An available Pod is an instance that is available to the users of the application.
Rolling updates allow the following actions:

* Promote an application from one environment to another (via container image updates)
* Rollback to previous versions
* Continuous Integration and Continuous Delivery of applications with zero downtime
.
    
        # view the current image version of the app (look at the Image field)
        kubectl describe pods. # Image:          gcr.io/google-samples/kubernetes-bootcamp:v1
    
        # update the image of the application to version 2
        kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=jocatalin/kubernetes-bootcamp:v2
The command notified the Deployment to use a different image for your app and initiated a rolling update.

        # Check the status of the new Pods, and view the old one terminating
        kubectl get pods

## Verify an update

    # find out the exposed IP and Port 
    kubectl describe services/kubernetes-bootcamp
    
    export NODE_PORT=$(kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}')
    # We hit a different Pod with every request and we see that all Pods are running the latest version (v2)
    curl $(minikube ip):$NODE_PORT
    
    # The update can be confirmed also by running a rollout status
    kubectl rollout status deployments/kubernetes-bootcamp
    
    # view the current image version of the app
    kubectl describe pods

## Rollback an update

    # Let’s perform another update, and deploy image tagged as v10
    kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=gcr.io/google-samples/kubernetes-bootcamp:v10
    
    # something is wrong… We do not have the desired number of Pods available.
    kubectl get deployments
    
    # There is no image called v10 in the repository.
    kubectl describe pods
    # image "gcr.io/google-samples/kubernetes-bootcamp:v10": rpc error: code = Unknown desc = Error response from daemon: manifest for gcr.io/google-samples/kubernetes-bootcamp:v10 not found: manifest unknown: Failed to fetch "v10" from request "/v2/google-samples/kubernetes-bootcamp/manifests/v10".
    
    # roll back to our previously working version: v2
    kubectl rollout undo deployments/kubernetes-bootcamp
    
    # Four Pods are running
    kubectl get pods
    
    # the deployment is using a stable version of the app (v2). The Rollback was successful.
    kubectl describe pods
