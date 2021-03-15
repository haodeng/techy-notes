Allow access from all machines

    mysql> GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'%' IDENTIFIED BY 'PASSWORD' WITH GRANT OPTION;
    mysql> FLUSH PRIVILEGES;
    
