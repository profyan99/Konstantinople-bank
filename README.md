# Konstantinople Bank #
My first experience in developing applications with Spring Boot on Java.
Something features may not work, because I'm a noob in enterprise development :) 
So, this project is really simple, but with it some begginers could understand some rules, 
principles of Spring Framework. I think, in present world there is a place to my project architecture 
and selected by me technologies. Good luck!


## Overview

This is a pure version of online bank, which contains
* clients
* bills
* transactions

Application handle users operations with bills:
* put money
* withdraw money
* transfer to another bill

But this is only the main functional, your fantasy isn't limited, so you can add more functionality.

#### At the moment there are two realizations in two branches:

* With Hibernate and Spring Data - **master**
* With JdbcTemplate only - **JdbcTemplate**


## Technology stack:  ###
#### Backend
* Spring Boot **v.1.5.9**
* Spring Security
* Spring WEB
* Spring devtools
* Lombok
* Flyway
* MySQL 
* Maven

#### Frontend
* Vue.js
* Bootstrap 4
* Vuex
* Vue-router
* Axios
* Browserify + vueify

## Preparation

1. You need to install JDK and JRE to your pc\server. (for backend)
2. Please install Node JS/npm (for frontend)
3. Clone Repository in your folder 

       git clone https://github.com/profyan99/Konstantinople-bank.git

## Usage

### Configure connection to database

* Open config file **application.properties**, which is located at *src\main\resources*
* Edit these fields
  
      spring.datasource.url   
      spring.datasource.username
      spring.datasource.password
      spring.datasource.driver-class-name
            
### Configure CORS settings

* Open **SecurityConfig.java**
* Find line 
  
      CorsConfigurationSource corsConfigurationSource() { ...
      
* Change values in **setAllowedOrigins** to what you want


>  Notice: It is for a while, until I bring the configuration into separate files

### Install and start the backend server
* Package and run back-end server

      mvn install spring-boot:repackage
    
      java -jar target\demo-0.0.1-SNAPSHOT-spring-boot.jar

* If you have some errors with UTF-8 encoding, then try

      java -Dfile.encoding=UTF-8 -jar target\demo-0.0.1-SNAPSHOT-spring-boot.jar
      
### Install and run the front-end
* Build front-end 

      cd frontend
      
      npm install

* Run front-end

      npm run build

> *dev* version in development

## Support

1. Github issue
2. profyan@mail.ru



