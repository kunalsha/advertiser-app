# advertiser-app

Advertiser-App API is a Spring Boot REST JPA project having H2 database as backend.

Advertiser-App API publishes below endpoints for Advertiser.

1 POST new advertiser

POST - /api/advertiser
Body - {
    "advName": "Adv-NameUpdated",
    "advContactName": "Adv-Con3tact-NameUpdated",
   ",
    "advCreditLimit": 785653.1
}

2 PUT update advertiser

PUT - /api/advertiser/{advertiserId}
Body - {
    "advName": "Adv-NameUpdated",
    "advContactName": "Adv-Con3tact-NameUpdated",
   ","advCreditLimit": 785653.1
}

3 DELETE advertiser

	DELETE - /api/advertiser/{advertiserId}

4 GET advertiser

	GET - /api/advertiser/{advertiserId}

5 GET endpoint to validate if the advertiser has enough credit to perform a transaction

	GET - /api/advertiser/{advertiserId}/validate

Dummy data setup is provided as part of this API, Below data set is provided with the API. 

"Adv-Name1", "Adv-Contact-Name1", 3431.00\n
"Adv-Name2", "Adv-Contact-Name2", 3534.50\n
"Adv-Name3", "Adv-Contact-Name3", 7565.10\n
"Adv-Name4", "Adv-Contact-Name4", 7565.78\n
"Adv-Name5", "Adv-Contact-Name5", 3432.45\n


Detailed Documentation of API's can be found in Swagger at below link.
	http://localhost:8080/swagger-ui.html#/

Code coverage analysis conducted by Jococo and confirms on 90%+ test coverage standard by EclEmma reports and can be seen at 

\advertiser-app\advertiser-app\target\site\jacoco\index.html

H2 Console is enabled to display DB transactions

Project code can be build with Maven build command 
mvn clean install package

which will produce advertiser-app 1.0.0-RELEASE.jar or advertiser-app*.jar and docker image with name advertiser/advertiser-app. Application can be run from command prompt with option java -jar advertiser-app*.jar

To run Docker image, below cmd can be used - docker run -p 8080:8080 -t advertiser/advertiser-app
