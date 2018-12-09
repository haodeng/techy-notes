git init

git clone -b <branch> https://.../...git
  
git log

git diff


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

