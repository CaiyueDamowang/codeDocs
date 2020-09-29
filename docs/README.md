# Docks


#### 算法联系


#### git 版本控制

所有人 都可以控制master分支

但是每次push之前（最好是打开之前 进行一次拉取代码）

##### git 操作

``` git pull origin master```   拉取远程仓库的最新代码
``` git fetch && git merge``` 是一样的

 改动文件....  

 ``` git add .```               . 是将当前所有的改动 添加到暂存区

 ``` git commit -m "此处添加注释" ``` 将改动提交到本地，并且更新本地仓库的版本

``` git push origin master```    将本地的仓库  提交到远程，更新远程仓库的版本

``` git log ```
``` git log --graph```   查看历史提交记录（--graph: 树状图)

