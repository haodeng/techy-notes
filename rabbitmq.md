# Add user

rabbitmqctl add_user test test

rabbitmqctl set_user_tags test administrator

rabbitmqctl set_permissions -p / test ".*" ".*" ".*"


# Add virtual host

rabbitmqctl add_vhost test_vhost

rabbitmqctl set_permissions -p test_vhost test ".*" ".*" ".*"

# Enable a plugin
rabbitmq-plugins enable rabbitmq_web_stomp

# Start, stop
rabbitmqctl start|stop|restart
