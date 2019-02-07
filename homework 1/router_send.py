# @Author:Dhruv gupta
# Class: CSC 6220
# Homework 2


import json
import socket

def NAK(pak):

	serverName = 'localhost'
	serverPort = 5100;
	clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	clientSocket.connect((serverName,serverPort))

	data = {'NAK': pak}
	val = json.dumps(data)
	data_encode = val.encode()
	clientSocket.send(data_encode)
	clientSocket.close();
	#newlist = clientSocket.recv(2048).decode()
	#print(newlist)
	#print('From server:', modifiedSentence.decode())
	
	return
