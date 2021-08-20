# Tilt
https://tilt.dev/, A toolkit for fixing the pains of multi-service development.

After install, check:

    tilt version

Create a Tilefile, example:

    # Records the current time, then kicks off a server update.
    # Normally, you would let Tilt do deploys automatically, but this
    # shows you how to set up a custom workflow that measures it.
    local_resource(
        'deploy',
        'python record-start-time.py',
    )

    local_resource(
        'compile',
        'mvn install',
        deps=['src', 'pom.xml'],
        resource_deps = ['deploy']
    )

    docker_build('orders-service', '.', dockerfile='./Dockerfile')
    k8s_yaml('deploy.yaml')
    k8s_resource('orders-service', port_forwards=8080, resource_deps=['deploy','compile'])

To start a tilt browser:

    # delpoy whatever defined in k8s deploy.yaml
    tilt up
    
After dev:

    # undploy kubernates objects
    tilt down
    
    
Checkout my example:  https://github.com/haodeng/springboot-with-k8s/tree/main/service-discovery/orders-service

