# @Author:Dhruv gupta
# Class: CSC 6220
# Homework 2 

import socket
import sys
import _thread
import json
from pprint import pprint
import router_send

port = 5100;


def handle(client_socket, address):
    counter = 1;
    results = dict();
    val = client_socket.recv(48000).decode()
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

    last = 0
    # Loop
    for i in list(results):
        if int(i)-last > 1:   # Check results            
            data = {'NAK': last+1}
            val = json.dumps(data)
            data_encode = val.encode()
            client_socket.send(data_encode)
            
            val = client_socket.recv(4800)
            val_json = json.loads(val.decode())

            print()
            print("Inserting dropped packet...")
            pprint(val_json)

            results.update({str(last+1):val_json})
            last+=1;
        else:
            last+=1;    
        # Request Missing Packets

        print("final data: ")
        for i in list(results):
            pprint(results[i])
    #client_socket.send(capitalizedSentence.encode())

    
    router_send.send(results)

    print('closing connection with: '+ str(address))

    data = {'fin': 0}
    val = json.dumps(data)
    data_encode = val.encode()
    client_socket.send(data_encode)
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