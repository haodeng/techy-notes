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

## 指定系统运行级别, runlevel

0: 关机
1: 单用户（找回丢失密码）
2: 多用户无网络
3: 多用户有网络
4: 保留
5: 图形界面
6: 重启

配置文件 /etc/inittab, modify the number in id:5:initdefault:

switch to a level, eg level 3:

init 3

init 0  # also can shutdown

## Find root password

Swtich to single user runlevel, then modify root password. In single user mode, root user don't need pass to login.

1. Shutdown and start the server

2. press enter when it is still booting, press e then. Try to enter single user mode by enter 1 in the end.

3. Press b to boot the server and enter single user mode. root user 

4. passwd root # change root pass

5. reboot

## Command

mkdir -p /test/foo/bar

rmdir test   # not able to rm a non empty dir

rm -rf test  # -r recusive, -f by force, no promot.

touch a1 a2 # create multiple files

cp -r test test2  #copy dir test to test2. if test2 exists, copy test to test2/test 
