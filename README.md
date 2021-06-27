# saechim-Interior-Mall
 It is a small interior shop project as a repository for practicing MSA structure.





---



### Architecture

- FrontEnd : Single Android Project
- Backend: 7 SpringBoot Project
  - EurekaServer
  - GateWay
  - homeService
  - CategoryService
  - UserService
  - StoryService
  - EtcService

## Running saechim-Interior-Mall locally
saechim-Interior-Mall backend is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Gradle](https://spring.io/guides/gs/gradle/). 

And frontend is a [Android](https://developer.android.com/) application built using [Gradle](https://spring.io/guides/gs/gradle/). 



## The [h2](https://h2database.com/h2-2019-10-14.zip) database must be executed before performing the following command.

---

### Procedures for executing this project It is currently under development and may be added further.  ex) circuitBreaker

1. h2 database setting

   ```
   After executing the h2 database, the following command must be performed on JDBC URL:
   
   jdbc:h2:~/orderservice
   jdbc:h2:~/categoryservice
   jdbc:h2:~/userservice
   jdbc:h2:~/storyservice
   jdbc:h2:~/etcservice
   
   Confirm creating ~/orderservice.mv.db file
   Confirm creating ~/categoryservice.mv.db file
   Confirm creating ~/userservice.mv.db file
   Confirm creating ~/storyservice.mv.db file
   Confirm creating ~/etcservice.mv.db file
   ```

   

2. Android Localserver setting

   ```
   goto frontend/app/src/main/res/value/strings.xml
   
   and change this value 
   <string name="Ipaddress">${yourip}:8080</string>
   
   ```

3. Lauch springboot app

   ```
   These two should be executed in order, and after that, it doesn't matter which one is executed first.
   1. run h2 binary database
   2. brew services start zookeeper
   3. brew services start kafka
   4. cd EurekaServer   ./gradlew bootRun
   5. cd GatewayServer ./gradlew bootRun
   6. ~~~~ 
   ```

4. Lauch Android app

