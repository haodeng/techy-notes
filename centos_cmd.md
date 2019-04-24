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

## user, group files

/etc/passwd, user info is here. One line per user, 
format, username:x (hiden pass):userid:groupid::home dir:login shell

/etc/group, group info is here.
format, group name:x (hiden pass):groupid:users in the group (not able to view normally)

/etc/shadow, user password info, encoded.
format, username:encoded pass

## 指定系统运行级别

0: 关机
1: 单用户（找回丢失密码）
2: 多用户无网络
3: 多用户有网络
4: 保留
5: 图形界面
6: 重启

配置文件 /etc/inittab
