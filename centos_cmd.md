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

mv test.txt test/ #move test.txt to test dir

mv test.txt test2.txt # rename test.txt to test2.txt

less test.log, Ctrl+B page down, Ctrl+F page up. / search downwards, n search downwards, N search upwards

cat /etc/profile >> 1.txt # append profile to 1.txt

echo "hello w" >> 1.txt # append hello w to 1.txt

cal # show calendar

echo $PATH  # print out PATH env var

head file.txt  # print out first 10 lines of the file, defualt is 10.

head -n 5 file.txt # print out first 5 lines of the file

tail file.txt # print out last 10 lines

tail -n 5 file.txt  #print out last 5 lines

tail -f file.txt # follow the file changes


## Command 2

ln -s file file.ln  # soft link file to file.ln

history # show all history

history 10 # show latest 10 history

!33 # execute history cmd 33.

date # show current date

date +%Y  #show current year

date "+%Y-%m-%d %H:%M:%S"  # show date in defined format yyyy-mm-dd HH24:MI:SS

date -s "2019-05-06 11:11:11"  # set sys date to "2019-05-06 11:11:11" by String

cal # print calendar of current month

cal 2019 # print calendar of 2019

## Find files

find /home -name test.txt  # find file by name test.txt under /home

find . -user user1  # find all files which owner is user1 under .

find  / -size +20M #find all files larger than 20MB,  +n great then, -n less than, n equal to

grep -n test test.txt  # search test in test.txt, and show the line number,  -i ignore case

## Zip unzip

gzip file.txt # zip file.txt to file.txt.gz, file.txt will not be saved

gunzip file.txt.zip  # unzip file.txt.gz to file.txt

tar -cvf test.tar test  # create test.tar from test
 
tar -zcvf test.tar.gz test  # tar and gzip test

tar -zxvf test.tar.gz  # unzip and extract test.tar.gz to current dir

tar -zxvf test.tar.gz -C test2  #unzip and extract test.tar.gz to test2 dir, test2 should be exist.


## user, group and rights

groupadd test  # add group test

useradd -g test tom  # create user tom and put it to test group

passed tom # set password for user tom

chown tom text.txt # change text.txt own to tom

chown tom:test text.txt # change owner to tom and group tp test, use -R change dir recursively

chgrp test text.txt # change text.text to group test, use -R change dir recursively

id tom  # show user tom's user, group info

usermod -g test tom  # change tom to group test

usermod -d /home/tom2 tom  # change tom's login dir to tom2

rights -rwx , r=4 (100), w=2 (010), x=1 (001), rwx = 4+2+1=7, or 111 = 7 

chmod u=rwx,g=rx,o=x test.txt  # u:owner, g=group, o = otherm a = all (u, g, o)

chmod o+w test.txt  # add w to other

chmod a-x text.txt  # revoke x from all 

chmod 751 text.txt # -rwx-rx-x, 777 = -rwxrwxrwx, 644 = -rw-r--r--


## cron job

cron syntax: first * = minute (0 - 59), 

second * =  hour (0 -23), 

third * = day (1-31), 

fouth * = month (1 - 12), 

fifth * = day in a week (0 - 7, 0 and 7 are both for Sunday)

45 22 * * * ls -l /etc >> tmp/text.txt # run cmd at 22:45

0 17 * * 1 ls -l /etc >> tmp/text.txt # run cmd at 17:00 on Monday

*/1 * * * * ls -l /etc >> tmp/text.txt  # run cmd every 1 minute

*/10 4 * * * ls -l /etc >> tmp/text.txt # run cmd every 10 minutes at 4:00, stop at 5:00

0 8,12,16 * * *  ls -l /etc >> tmp/text.txt  # run cmd at 8:00, 12:00 and 16:00

0 5 * * 1-6 ls -l /etc >> tmp/text.txt # run cmd Monday to Saturday at 5:00

crontab -e  # edit cron job

crontab -l # list cron jobs

crontab -r  # remove all cron jobs, remove single job can use -e edit

service crond restart  # restart crond 
