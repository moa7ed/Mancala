# Mancala

This is a simple project built in Java 11 for the ancient game [Mancala](https://en.wikipedia.org/wiki/Mancala)

It is a 2 players game. In the current version you can create games with 2 players (with custom names) 
these players should play together on the same screen.

### Project Overview
- Java 11
- Dropwizard
- Maven
- Junit5
- FTL
- JQuery
- Check Style

### Model Structure
[Class Diagram](https://drive.google.com/file/d/1zXz_Ni9kmHa1o9Yr1QEShgCcchehPZaa/view?usp=sharing)



### How to run
First you need to compile by
```
mvn package
```
Then you should run
```
java -jar target/Mancala-1.0.jar server
```
Visit http://localhost:8080/games/ to create games and play 

### Check style
I already integrated maven check style plugin which run in maven life cycle to guarantee high standard code

### Docker Integration
You can also run the application on Docker container.

To build the image:
```
docker build -t mancala
```
To run the game in a container 
```
docker run -ti -p 8080:8080 mancala
```
Visit http://localhost:8080/games/ to create games and play