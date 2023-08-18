# Basic 2

    # check version
    docker -v
    
    # delete all running and stopped container 
    docker rm $(docker ps -aq)
    
    # search our desired image in registry
    docker search <image_name>
    
    # check a container's env
    docker exec <container_name or id> env
    
get details of running container

    docker inspect <CONTAINER ID>
    docker inspect <CONTAINER name>
    # docker inspect command outputs lot of details in parsable json. You can get particular fields from that.
    # directly retrieve Created field from json output.
    docker inspect -f '{{json .Created}}' <CONTAINER name>
    
    
Terminate running container

    # Stop the started container. The main process inside the container will receive SIGTERM, and after a grace period, SIGKILL
    docker stop --time 5 <yourContainerId>
    docker stop container_name
    
    # If you have e.g. an hanging container, it's possible to send the SIGKILL signal directly
    docker kill <yourContainerId>
    docker kill container_name

---    
    # Detaching from a running container
    # You can detach from this running container using CTRL-p CTRL-q key sequence.
    docker run -it --name counter loodse/counter
    
    # Attaching to running container
    docker run -d -it --name counter1 loodse/counter
    docker attach counter1
    
## Delete by force (--force)

    docker image rm 33bd5a1580e6
    Error response from daemon: conflict: unable to delete 33bd5a1580e6 (must be forced) - image is being used by stopped container 84764cd3af0c
    docker image rm --force 33bd5a1580e6
    
    
# Managing logs
  
    # check log
    docker logs <container name>
    
    # redirect the redis logs to syslog.
    docker run -d --name redis-syslog --log-driver=syslog redis
    
    # Disable Logging
    # simply set the log-driver to none. No output will be logged.
    docker run -d --name redis-none --log-driver=none redis

    # Which Config?
    docker inspect --format '{{ .HostConfig.LogConfig }}' redis-server
    {json-file map[]}
    
    docker inspect --format '{{ .HostConfig.LogConfig }}' redis-syslog
    {syslog map[]}
    
    docker inspect --format '{{ .HostConfig.LogConfig }}' redis-none
    {none map[]}

# Clean up
    # Remove all unused containers, networks, images.
    docker system prune
    
    # remove all unused images not just dangling ones
    docker system prune -a
