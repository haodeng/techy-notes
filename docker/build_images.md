# Building Images Interactively
    do some changes to a running container first, eg: install new app
        
    # Inspect the changes
    # This show the list of added, changed or deleted with capital A, C or D respectively prefixed with each file.
    docker diff <yourContainerId>
    
    # create a new image from changes
    docker commit <yourContainerId>
    # Run the image using 
    docker run -it <newImageId>
    
    # tag your newly created image
    docker tag <newImageId> webserver
    docker run -it webserver

# Building images with Dockerfile

cat Dockerfile

    FROM ubuntu:18.04
    RUN apt-get update && apt-get install apache2 -y && apt-get clean
    COPY html /var/www/html
    CMD ["apache2ctl", "-DFOREGROUND"]
* FROM indicates the base image for our build
* Each RUN line will be executed by Docker during the build
* The COPY instruction copies new files or directories from and adds them to the filesystem of the container at the path .

builds an image from a Dockerfile and a context. The build’s context is the files at a specified location PATH or URL.

    docker build -t webserver .
    
    # then view new image
    docker images
    
    # verify images works by running it
    docker run -d -p 8080:80 --name www webserver

# Docker Build ignore Files
The .dockerignore file would be stored in source control and share with the team to ensure that everyone is consistent.
The ignore file supports directories and Regular expressions to define the restrictions, very similar to .gitignore. 

    echo passwords.txt >> .dockerignore
    
    docker build -t nopassword .
    # check no passwords.txt listed
    docker run nopassword ls /app