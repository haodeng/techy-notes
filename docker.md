docker pull mysql/mysql-server:5.6.44  # pull mysql-server tag 5.6.44

docker images  # view images, or: docker image ls

docker run --name=mysql-server-5.6.44 -d mysql/mysql-server:5.6.44  # run image mysql/mysql-server:5.6.44 in a new container, --name is optional, -d is running on background

docker ps # show the running containers

docker stop mysql-server-5.6.44   # stop container by name mysql-server-5.6.44

docker ps -f status=exited  # show stopped containers

docker ps -a # show all containers including these stopped

docker start mysql-server-5.6.44 # start container again

docker restart mysql-server-5.6.44 # restart container

docker rm mysql-server-5.6.44  # rm container by name

docker run --name=mysql-server-5.6.44 -d -e MYSQL_ROOT_HOST=% -p 3306:3306 mysql/mysql-server:5.6.44  # -p port exposing, -e set env vars 

docker run --name=mysql-server-4 -d --env-file /Users/dev/hao/setups/mysql-4.env -p 3307:3306 stevemayne/mysql4:latest  # with env file

docker logs mysql-server-5.6.44   # check the mysql starting logs

docker exec -it mysql-server-5.6.44 mysql -uroot -p  # get the temp pass by : docker logs mysql-server-5.6.44

SET PASSWORD FOR 'root'@'localhost' = PASSWORD('password');  # mysql 5.6 version, new versions use alter user

grant all privileges on *.* to 'root'@'172.17.0.1' identified by 'password';

FLUSH PRIVILEGES;


docker exec -i container_name mysql -uroot dbname < data.sql;  # import data into a schema

docker exec -i mysql-server-4 mysql --max_allowed_packet=800M -uroot -proot  test<test.sql  # define the max size of import sql file.

set global max_allowed_packet=1000000000; # also in mysql, set the param


Edit file in docker container:
docker exec -it <instance name> bash
  
then in container run: apt-get update
apt-get install vim


Edit file in a stopped docker container:
docker cp docker_web_1:/etc/apache2/sites-enabled/apache2.conf .
(correct the file)

docker cp apache.conf docker_web_1:/etc/apache2/sites-enabled/apache2.conf
