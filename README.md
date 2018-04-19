# RES_Labo_03_SMTP

## Project

## Setting up mock SMTP server
The mock sever could help you to test the Email sending, This server receive the email il place of a real SMTP server. That avoid you to be exclued by a real server for flood.


1. First Get the MockMock executable : 
	- In our Git in folder: Docker/MockMockServer/release
	- Official Git : https://github.com/tweakers-dev/MockMock
2. Normaly you have get the MockMock.jar file (you find it in release folder)

### Test in local

1. Open a terminal and start your MockMock server for that type next command :
	- java -jar MockMock.jar -p 2525

	(-jar : allow to specify the runnable java you want to execute)
    (-p : allow to specify the reception SMTP port of you Mock server)
2. try to contact your server with telnet in your terminal :
	- telnet localhost 2525

3. To see your email send :
	- open a web page at : http://localhost:8282/
    
### Test with Docker
1. Make sure that you have installed Docker.
2. Make sure that you get our Dockerfile. You ll find it in folder Docker.
3. In terminal build the Dockerfile
	- docker build -t mockmock . (don't forget the last point)
	- (you need to be where you dockerfile is to run this command)
4. In terminal run you docker container :
	- docker run -p 2525:2525 --name MockServer mockmock

6. To see your email send :
	- open a web page at : http://localhost:8282/
	
## Tool configuration

1. To configure the application you need to modifiy the file config.properties.
	- You'll find this file here : MailRobot/config/config.properties

		- smtpServerAddress : The distant serveur adresse (localhost if you use the Mock server in local or witch docker).
		- smtpServerPort : The distant port of the server smtp (2525 if you use our tutorial for mock).
		- numberOfGroups : Number of group whose you want to send email.
		- witnessesToCC : CC email person who receive all email send as copy. 
	
2. To add some message to send you need to modifiy the file messages.utf8.
	- You'll find this file here : MailRobot/config/messages.utf8
	
    	- Every message need a Subject and un message.
    	- Between your messages you need to add two equal "==".
    	- the structure is "Subject: `<yourSubject>`newline then the message.
    	
3. To add some recipients you need to modifiy the file victims.RES.utf8
	- You'll find this file here : MailRobot/config/victims.RES.utf8
		- One email per line.

## Running a prank campaign
