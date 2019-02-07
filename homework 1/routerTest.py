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
    for i in results:
        pprint(results[i])

    # Packet Dropper code

    # random between 1 and length of message 
    # drop = 120 - random
    drop = 120
    for i in list(results):
        if (drop % 125 == 0):
            results.pop(i)
            print("Packet "+i+" dropped")
        drop+=1    

    print('After dropping packet: ')
    
    for i in list(results):
        print(i)
        pprint(results[i])

    last = -1
    # Loop
    for i in list(results):
        if(i != last+1):   # Check results
            router_send.NAK(last);
        else:
            last+=1;    
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
