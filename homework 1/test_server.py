# @Author:Dhruv gupta
# Class: CSC 6220
# Homework 2 

import socket
import sys
import _thread
import json
from pprint import pprint

port = 5100;


def handle(client_socket, address):
    counter = 1;
    results = dict();
    val = client_socket.recv(4800).decode()
    # print(val)

    # store results
    results = json.loads(val)
    
    print('Recieved: ')
    pprint(results)

    # Loop   
        # Check results
        # Request Missing Packets


    #client_socket.send(capitalizedSentence.encode())
    print('closing connection with: '+ str(address))
    client_socket.close()

serverSocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
serverSocket.bind(('localhost',port));
print('Starting up on ',serverSocket);
serverSocket.listen(1);
print('the server is ready to receive')

    # Data is read from the connection with recv()
while True:
    client_socket, address = serverSocket.accept()
    print ("request from the ip",address[0])

    # Starts a new thread with every connection the server recives.
    _thread.start_new_thread(handle, (client_socket, address))
