docker pull mysql/mysql-server:5.6.44  # pull mysql-server tag 5.6.44

docker images  # view images, or: docker image ls

docker run --name=mysql-server-5.6.44 -d mysql/mysql-server:5.6.44  # run image mysql/mysql-server:5.6.44 in a new container, --name is optional, -d is running on background

docker ps # show the running containers

docker stop mysql-server-5.6.44   # stop container by name mysql-server-5.6.44

docker ps -f status=exited  # show stopped containersx

docker start mysql-server-5.6.44 # start container again

docker restart mysql-server-5.6.44 # restart container


