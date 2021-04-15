Allow access from all machines

    mysql> GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'%' IDENTIFIED BY 'PASSWORD' WITH GRANT OPTION;
    mysql> FLUSH PRIVILEGES;
    
Safe Update Option

    SET SQL_SAFE_UPDATES = 0;
    
This will solve the error: You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column To disable safe mode, toggle the option ....
