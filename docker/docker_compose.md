
    docker-compose --version
    
    # start
    docker-compose up
    docker-compose -f your_compose_file.yml up
    
    # start in detached mode
    docker-compose up -d
    
    # build image before init the container from there
    docker-compose up --build
    
    # check what is running, only shows containers defined in compose file
    docker-compose ps
    docker-compose -f your_compose_file.yml ps
    
    # stop
    docker-compose stop
    
    # Down
    docker compose -d fiename.yml down  # stop and remove the container. Stop only stops the running instance
    
    # remove containers, Pass --volumes to also remove the data volume used by containers
    docker-compose down --volumes


    # new version of compose
    docker compose .... ....
