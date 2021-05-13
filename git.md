
<pre>
git init

git clone -b branch_name https://.../...git
  
git log

git diff
</pre>


# Branch
git branch      # show all branches and the current branch

git branch new_branch         # create a new branch

git checkout new_branch        # switch to new branch

git checkout -b new_branch       #Equal to branch new_branch and checkout new_branch

git branch -d new_branch       # delete a branch

git branch -m new_branch_name    # move a branch to new name



# Fetch and pull
pull is doing a fetch and followed by a git merge. 
Fetch is only get changes to the local repository from remote, no merge is triggered.


# Rebase
Interactive rebase -i

git checkout new_branch

git rebase -i master        # rebase new_branch onto master

git rebase --skip

git rebase --continue

git rebase --abort


A typical rebase process is:
```python
git checkout branch
git rebease -i master
git checkout master
git merge branch
```



# Merge

git checkout master

git merge new_branch     


Rebase can give a clean commit history, but rebase on remote repository may dangerous.

Merge is safe, but commit history remains.



# Alias
git config --global alias.co "checkout"

git config --global alias.st "status"

git config --global alias.hist "log --pretty=format:'%h %ad | %s%d [%an]' --graph --date=short"


List all alias.

git config --global alias.alias "config --get-regexp ^alias\."


# Others
git commit -a -m "commit message" 

git commit --amend -m "correct last message"

Amend can only change the commit message on local repository. 

# Log
git log

git log --pretty=oneline

git log --oneline

git reflog


# Go to revision
git reset --hard hash
  
  Head index will move to hash, recommend to use hash to move back and forth.

git reset --hard HEAD^

move index from HEAD one step back.

git reset --hard HEAD^^^

move index from HEAD 3 steps back, this is equals to:

git reset --hard HEAD~3

use ^ and ~ can only move back, use hash can go both direction. Recommend to use hash.


Mode

--soft

only move the head to the commit, does not touch index file and working tree. 本地库移动HEAD指针，Staging与WorkSpace不变

--mixed
reset index file, but not the working tree. 本地库移动HEAD指针，Staging重置，WorkSpace不变

--hard
reset the index and working tree 本地库移动HEAD指针，Staging与WorkSpace都重置


# diff
git diff

compare local to staging

git diff HEAD

compare local to latest in workspace

git diff HEAD^

etc

git diff a.txt

only compare one file.

# remote

git remote add origin https://---/.git 

set remote repository https://----/.git as origin

git remote -v

view remote repository.

git push origin master

push local change to remote repository (origin) master branch.

# Change Config

git config --global user.email "test@example.com" # change global user.email, for all repositories

git config user.email "test@example.com"  # change current repositoy user.email

git config --list. # show all configs
if u see two user.email by git config --list|grep user.email, that's fine, one for global setting, another is for local repository


# set tracking information 
    git branch --set-upstream-to=origin/<branch> main
