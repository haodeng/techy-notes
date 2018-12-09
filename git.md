git init

git clone -b <branch> https://.../...git


git branch

git branch new_branch

git checkout new_branch

git checkout master

# Rebase
Interactive rebase -i

git checkout new_branch

git rebase -i master        # rebase new_branch onto master

git rebase --skip

git rebase --continue

git rebase --abort


# Merge

git checkout master

git merge new_branch     


Rebase can give a clean commit history, but rebase on remote repository may dangerous.

Merge is safe, but commit history remains.

