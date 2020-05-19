FROM scottyengineering/java11
LABEL maintainer="Mohammed Elrashidy <elrashidy.muhammad@gmail.com>"

RUN mkdir /mancala

# Add the fat jar
COPY target/Mancala-1.0.jar /mancala/Mancala-1.0.jar

# Yeah lets work from here
WORKDIR /mancala

# HTTP port
EXPOSE 8080

# This runs our game
CMD ["java", "-jar", "Mancala-1.0.jar", "server"]
