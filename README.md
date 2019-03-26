# gameofthree
This application contains of four maven projects:
 - gameofthree-common : common library for all projects
 - game-of-three-player-one : First player application. Runs on port 8081
 - game-of-three-player-two : Second player application. Runs on port 8082
 - logger : application to display game log.


#Solution:
Players exchnage with jms messages via Apache ActiveMQ broker.
Each player's project is totally independant from others and can be configured by application.properties:

#Example Player One:
- playerId=PlayerOne
- nextPlayer=PlayerTwo
- rootUrl=/gameofthree
- playerUrl=/1
- gameLog=GameLog
	
#Example Player Two:
- server.port=8082
- playerId=PlayerTwo
- nextPlayer=PlayerOne
- rootUrl=/gameofthree
- playerUrl=/2
- gameLog=GameLog
	
#Build order:
1. Build in maven gameofthree-common, by running mvn install from project classpath
2. Build in Maven logger, by running mvn install from project classpath
2. Build in Maven game-of-three-player-one, by running mvn install from project classpath
4. Build in Maven game-of-three-player-two, by running mvn install from project classpath
	
#Run:
1. Start game-of-three-player-one by running mvn spring-boot:run
1. Start game-of-three-player-teo by running mvn spring-boot:run
1. Start logger by running mvn spring-boot:run

#Start game:
 - To start game with PlayerOne use url: http://localhost:8081/gameofthree/1
 - To start game with PlayerOne use url: http://localhost:8082/gameofthree/2

#Output:
Each player has own Console log to display steps.
To see all players steps in one combilned output, please see console of logger project.

#Supported scenatiose:
1. All players and logger are available.
2. One player and logger available:
	- In this case game will wait untill second player will start getting messages.
3. All players available but logger is down.
	- In this case game will run till the end, but combined output log will not be available.
