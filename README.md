# TODO LIST

## 使用した技術要素
#### Spring boot</br>
#### gradle</br>
#### JPA</br>
#### Mysql</br>
#### Thymeleaf</br>
#### Javascript(When confirming password and swithing task status)</br>

## 全体の設計・構成についての説明 
#### Entity(User,Task)
#### Repository(UserRepository,TaskRepository)
#### Controller(UserController,TaskController)
#### Templates(Web pages)

## 開発環境のセットアップ手順

### Step1: 
Create a project by Spring Initializr on Idea.
### Step2: 
Set up dependencies(Web, JPA, Thymeleaf, MySQL).
### Step3: 
Set up infos of database and Thymeleaf in "/src/main/resources/application.properties".
### Step4: 
Add other dependencies needed in "build.gradle".

## How to Run 
If use Idea to open this project, please check this [guide](https://spring.io/guides/gs/intellij-idea/).</br>
### Step1: 
Set up your database info(database,username,password) in "/src/main/resources/application.properties", change 'spring.jpa.hibernate.ddl-auto=none'to 'spring.jpa.hibernate.ddl-auto=create'.</br>
It will create db tables by entities automatically. </br>
After that,change this value back.
### Step2:
Use './gradlew bootRun' command to start and 'ctrl+c' to stop.</br>
Start with http://localhost:port/user/index to register a new account.

## データベース
"resource/db.sql"

## 動作確認が可能な URL(オプション)
Firstly,register a new account, then login.</br>
Page will transferred to home page.</br>
Then you can manage your tasks(create,edit,update status,search with keyword and status).</br>

Register:http://localhost:port/user/index</br>
Login:http://localhost:port/user/index</br>
Home page:http://localhost:port/publish/list</br>

## 出来ているところ
Almost all of requests on specification file.

## 出来ていないところ
### Display every kind of error message on every kind of broswer.
There maybe some bugs.</br>
For example,setCustomValidity()can be show on safari like picture shows.</br>
But chrome didn't show this message.(When i coding it did well,but now failed)</br>
![setCustomValidity](https://github.com/MollyQI3104/pre_demo/blob/master/images/safari%20setCustomValidity.png)

### Display input type "date".
Chrome support datepicker of html5 as picture shows.</br>
![dateOnChrome](https://github.com/MollyQI3104/pre_demo/blob/master/images/chrome%20date.png)
But safari doesn't.
![dateOnSafari](https://github.com/MollyQI3104/pre_demo/blob/master/images/safari%20date.png)

### Only test on safari and chrome.



