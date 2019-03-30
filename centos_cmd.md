# shutdown

shutdown -h now

shutdown -h 1, shutdown after 1 mins

shutdown -r now, reboot immediately, r means reboot

halt, euqls to shutdown

reboot

sync, sync data in memory to disk, recommended to use it before reboot or shutdown.

logout, logout the current user, close the session. In GUI level, logout does not work. It has to work in level 3.


# user admin

useradd test, create test user, and test group, test user will be in test group by default. Test user will be put under /home dir

useradd -d /home/guests test2, create test2 user and put it under /home/guest dir. /home/guest dir will be auto ocreated.

passwd test, set password to user test. 

userdel test, delete the test user, but /home/test dir remains.

userdel -r test, delete the test user and /home/test dir.

## check user info

id test, show test user uid, gid and group name.

## switch user

su - test, switch to test user. If switch from root to test, no password requires. Type exit to back to current user.

whoami, check what my current user is

# Group

groupadd test, add test group

groupdel test, delete test group

useradd -g testgroup test, add test user and put him under testgroup group. testgroup must exit.

usermod -g testgroup2 test, put test user to testgroup2




