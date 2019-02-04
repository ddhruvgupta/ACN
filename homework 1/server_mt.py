# @Author:Dhruv gupta
# Class: CSC 6220
# Homework 2 

import socket
import sys
import _thread

port = 5100;


def handle(client_socket, address):
    counter = 0;
    while (counter<10):
        sentence = client_socket.recv(1024).decode()
        capitalizedSentence = sentence.upper()
        counter=counter +1
        print('Recieved: '+ sentence+' Sent: '+ capitalizedSentence)
        print(str(counter)+' chances used. '+ str(10-counter)+ ' chances left')
        client_socket.send(capitalizedSentence.encode())
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
