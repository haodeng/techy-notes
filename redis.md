Command

    KEYS pattern
    
    Example:  KEYS myKey:test*

    
    # Check value of the key
    GET key

    # check ttl of a key, -2: key not exist, -1: the key exists but has no associated expire
    TTL key
    
    
    
Install redis-cli only

    wget http://download.redis.io/redis-stable.tar.gz
    tar xvzf redis-stable.tar.gz
    cd redis-stable
    make redis-cli
    

Delete keys have no ttl

    redis-cli -h 10.0.0.x --scan --pattern "pattern*" | while read LINE ; do TTL=`redis-cli -h 10.0.0.x ttl "$LINE"`; if [ $TTL -eq  -1 ]; then redis-cli -h 10.0.0.x del "$LINE"; fi; done;
