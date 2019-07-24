# TODO LIST

## How to Run 
If use Idea to open this project, please check this [guide](https://spring.io/guides/gs/intellij-idea/).</br>
### Step1: 
Set up your database info (/src/main/resources/application.properties), change 'spring.jpa.hibernate.ddl-auto=none'to 'spring.jpa.hibernate.ddl-auto=create'.</br>
It will create db tables by entities automatically. </br>
After that,change this value back.
### Step2:
Use './gradlew bootRun' command to start and 'ctrl+c' to stop.</br>
Start with http://localhost:port/user/index to register a new account.

## データベース
resource/db.sql

## 動作確認が可能な URL(オプション)
Firstly,register a new account, then login.</br>
Page will transferred to home page.</br>
Then you can manage your tasks(create,edit,update status,search with keyword and status).</br>

Register:http://localhost:port/user/index</br>
Login:http://localhost:port/user/index</br>
Home page:http://localhost:port/publish/list</br>

## 出来ているところ
Almost of requests on specification file.

## 出来ていないところ
1. error message</br>
test
2. date
3.broswer

## 使用した技術要素
Spring boot
gradle
JPA
Mysql
Thymeleaf
javascript

## 全体の設計・構成についての説明 
MVC
entity
repository
controller
web

## 開発環境のセットアップ手順
Idea
create a project by Spring Initializr
set up dependencies(Web, JPA, Thymeleaf, MySQL)

Mysql:
gradle:



