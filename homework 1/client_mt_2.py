# @Author:Dhruv gupta
# Class: CSC 6220
# Homework 2


import json
import socket
from pprint import pprint

def send(data):

	serverName = 'localhost'
	serverPort = 5100;
	clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	clientSocket.connect((serverName,serverPort))

	
	val = json.dumps(data)
	data_encode = val.encode()
	clientSocket.send(data_encode)
	print("data sent")

	while(True):
		response = clientSocket.recv(20000)
		ans = response.decode()
		# print("ans=" )
		pprint(ans)
		response_dict = json.loads(response)
		

		if(response_dict.get('NAK')):
			pprint(data[int(response_dict.get('NAK'))])
			val = json.dumps(data[int(response_dict.get('NAK'))])
			data_encode = val.encode()
			clientSocket.send(data_encode)

		else: 
			break	
	
	clientSocket.close();
	
	
	


	#newlist = clientSocket.recv(2048).decode()
	#print(newlist)
	#print('From server:', modifiedSentence.decode())
	
	return 
