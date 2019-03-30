shutdown -h now

shutdown -h 1, shutdown after 1 mins

shutdown -r now, reboot immediately, r means reboot

halt, euqls to shutdown

reboot

sync, sync data in memory to disk, recommended to use it before reboot or shutdown.

logout, logout the current user, close the session. In GUI level, logout does not work. It has to work in level 3.


useradd test, create test user, and test group, test user will be in test group by default. Test user will be put under /home dir

useradd -d /home/guests test2, create test2 user and put it under /home/guest dir. /home/guest dir will be auto ocreated.

passwd test, set password to user test. 

userdel test, delete the test user, but /home/test dir remains.

userdel -r test, delete the test user and /home/test dir.

