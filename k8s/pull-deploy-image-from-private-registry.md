# Create a secret for docker registry login
create docker login secret from config.json file

    kubectl create secret generic my-registry-key \
    --from-file=.dockerconfigjson=.docker/config.json \
    --type=kubernetes.io/dockerconfigjson

create docker login secret with login credentials

    kubectl create secret docker-registry my-registry-key \
    --docker-server=https://private-repo \
    --docker-username=user \
    --docker-password=pwd
    
my-registry-key.yaml:

    apiVersion: v1
    kind: Secret
    metadata:
      name: my-registry-key
    data:
      .dockerconfigjson: base64-encoded-contents-of-.docker/config.json-file
    type: kubernetes.io/dockerconfigjson


# Link the secret to deployment
So deployment knows how to access the private registry and pull the image. imagePullSecrets setup the link to my-registry-key secret

    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: my-app
      labels:
        app: my-app
    spec:
      replicas: 1
      selector:
        matchLabels:
          app: my-app
      template:
        metadata:
          labels:
            app: my-app
        spec:
          imagePullSecrets:
          - name: my-registry-key
          containers:
          - name: my-app
            image: privat-repo/my-app:1.1
            imagePullPolicy: Always
            ports:
              - containerPort: 3000
