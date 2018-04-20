# RES_Labo_03_SMTP | MailRobot

## Project
MailRobot in and application has for main usage to sending factice emails as a prank campains to random victims picked out of a list. It implements the parsing of preseted lists of messages and email and takes as property an SMTP server address and port. With those elements it executes a full automated prank campain.

The purpose of this project is to learn and practive the communication between our mail client and an SMTP mail server. Thereby to send email from a client to a mail server and understant which sintaxe the protocol needs to work properly.

## Mock server
A Mock sever is a factice SMTP server used in a test environnement. It is possible to set it up on a local machine and it will react exactely like a real server. The particularity of this kind of server is that it doesn't forward the recieved emails to the real receiver but keeps them in a local inbox. That makes it possible to consult them and look at all the details about the constuction of the mail. The analyse of what our program is sending is easier to consult. That also avoids to use a regular SMTP server and beeing blacklisted because of a too high amount of sent emails.

### setting up the mock serveur
1. First Get the MockMock executable :
	- In our Git in folder: Docker/MockMockServer/release
	- Official Git : https://github.com/tweakers-dev/MockMock
2. You find the MockMock.jar file in the release folder

### Test locally
1. Open a terminal and start your MockMock server for that type next command :
	- `java -jar MockMock.jar -p 2525`

	(-jar : allows to specify the runnable java you want to execute)
    (-p : allows to specify the reception SMTP port of you Mock server)
2. try to contact your server with telnet in your terminal :
	- `telnet localhost 2525`

3. To see your sent emails connect to the web interface of the mock server:
	- open a web page at : [http://localhost:8282/]()

### Test with Docker
1. Make sure that you have installed Docker.
2. Make sure that you get our Dockerfile. You'll find it in the Docker folder.
3. In terminal build the Dockerfile
	- `docker build -t mockmock . `(don't forget the last point)
	- (you need to be where you dockerfile is to run this command)
4. In terminal run you docker container :
	- `docker run -p 2525:2525 -p 8282:8282 --name MockServer mockmock`

6. To see your email send :
	- open a web page at : [http://localhost:8282/]()

## MailRobot configuration
1. To configure the mail client you need to modifiy the file config.properties.
	- You'll find this file here : MailRobot/config/config.properties
	- smtpServerAddress : The distant serveur adresse (localhost if you use the Mock server in local or witch docker).
	- smtpServerPort : The distant port of the server smtp (2525 if you use our tutorial for mock).
	- numberOfGroups : Number of group you want to create for the campain.
	- witnessesToCC : CC email person who receive all email send as copy. (it is possible to define multiple emails separated with a comma "," but don't use any white spaces and ending with a comma)
```
smtpServerAddress=localhost
smtpServerPort=2525
numberOfGroups=6
witnessesToCC=joel.schar@heig-vd.ch,```

2. To add some message to send you need to modifiy the file messages.utf8.
	- You'll find this file here : MailRobot_Test/config/messages.utf8
    - Every message need a Subject and un message.
    - Between your messages you need to add two equal "==" on a new line .
    - the structure is :

    	```
		Subject: <yourSubject> (newline)
    	then the message
        multiple lines are possible
        ==
        ```

3. To add some recipients you need to modifiy the file victims.RES.utf8
	- You'll find this file here : MailRobot_Test/config/victims.RES.utf8
	- One email per line.
	- Each email should be followind the construction : `fistname.lastname@domain.com`

## Running a prank campaign

When all the properties are set, launching the program will open a connection to the server and begin sending the emails. 

1. Go in folder Mailrobot with terminal, execute a mvn clean install to get the last jar.
2. Go in folder MailRobot_Test and execute this command `java -jar MailRobot.jar ./config/victimes.utf8 ./config/messages.utf8`

