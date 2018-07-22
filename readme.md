Security - https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/
Rest API - http://www.baeldung.com/rest-with-spring-series/
https://www.boraji.com/spring-mvc-5-spring-security-5-hibernate-5-example

TESTING :

http://www.baeldung.com/httpclient-post-http-request

How to Import :
Java : JDK 1.8+
Maven : 3.3.9+
Eclipse : Oxygen

To Run the app server in Eclipse :
Run->Run COnfiguration -> Maven Build(Double click on it)

Base Director : ${workspace_loc:/bidding-system}
Goals : clean install tomcat7:run
in the same tab -> tick update snapshots & Skip Tests

And run

To test(Should happen after web server starts error free) : 
Search for class BidControllerTest.java ( in src/test/java/)
Right Click -> Run As -> JUnit Test 