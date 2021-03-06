# gameofthree
This application contains of four maven projects:
 - gameofthree-common : common library for all projects
 - game-of-three-player-one : First player application. Runs on port 8081
 - game-of-three-player-two : Second player application. Runs on port 8082
 - logger : application to display game log. Runs on port 8083


# Solution description:
Players exchnage with jms messages via Apache ActiveMQ broker.
Each player's project is totally independant from others and can be configured by application.properties:

## Example Player One:
- playerId=PlayerOne
- nextPlayer=PlayerTwo
- rootUrl=/gameofthree
- playerUrl=/1
- gameLog=GameLog
	
## Example Player Two:
- server.port=8082
- playerId=PlayerTwo
- nextPlayer=PlayerOne
- rootUrl=/gameofthree
- playerUrl=/2
- gameLog=GameLog

# Docker
	From repo folder: 
	cd gameofthree
### Build
	sudo docker-compose build
### Run
	sudo docker-compose up
### Output
	sudo docker logs playerone
	sudo docker logs playertwo
	sudo docker logs logger

# Maven
### Requeried software
	Apache ActiveMQ
### Change application.properties (by default values set to run app in docker container)
	cd gameofthree/game-of-three-player-one/src/main/resources
	replace 
		spring.activemq.broker-url=tcp://activemq:61616?jms.redeliveryPolicy.maximumRedeliveries=1
	with
		pring.activemq.broker-url=tcp://localhost:61616?jms.redeliveryPolicy.maximumRedeliveries=1
		
	cd gameofthree/game-of-three-player-two/src/main/resources
	replace 
		spring.activemq.broker-url=tcp://activemq:61616?jms.redeliveryPolicy.maximumRedeliveries=1
	with
		pring.activemq.broker-url=tcp://localhost:61616?jms.redeliveryPolicy.maximumRedeliveries=1
		
	cd gameofthree/logger/src/main/resources
	replace 
		spring.activemq.broker-url=tcp://activemq:61616?jms.redeliveryPolicy.maximumRedeliveries=1
	with
		pring.activemq.broker-url=tcp://localhost:61616?jms.redeliveryPolicy.maximumRedeliveries=1

### Build order in Maven:
1. Build in maven gameofthree-common, by running mvn install from project classpath
2. Build in Maven logger, by running mvn install from project classpath
2. Build in Maven game-of-three-player-one, by running mvn install from project classpath
4. Build in Maven game-of-three-player-two, by running mvn install from project classpath
	
### Run :
1. Start game-of-three-player-one by running mvn spring-boot:run
1. Start game-of-three-player-teo by running mvn spring-boot:run
1. Start logger by running mvn spring-boot:run

### Output:
Each player has own Console log to display steps.
To see all players steps in one combilned output, please see console of logger project.


# Play game:
 - To start game with PlayerOne use url: http://localhost:8081/gameofthree/1
 - To start game with PlayerOne use url: http://localhost:8082/gameofthree/2


# Supported scenatiose:
1. All players and logger are available.
2. One player and logger available:
	- In this case game will wait untill second player will start getting messages.
3. All players available but logger is down.
	- In this case game will run till the end, but combined output log will not be available.
