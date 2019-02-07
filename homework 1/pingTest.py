#@Author: Dhruv Gupta
#CSC 6220 Project

import os
import sys
import time 
import client_mt_2

import json



messages = dict();

# sentence = "clever fox hopped the wall";
sentence= ''
for i in range(1,500):
	sentence+='a'

counter = 1


for i in sentence:
	messages.update(
		{
		counter:{
		'sno':counter,
		'data':i		
		}
		});

	counter+=1;


# messages.update({2:{2:'b'}});
# messages.update({3:{3:'c'}});
# messages.update({4:{4:'d'}});
# messages.update({5:{5:'e'}});
# messages.update({6:{6:'f'}});
# messages.update({7:{7:'g'}});
# messages.update({8:{8:'h'}}); 


# for i in messages.keys():	
# 	print(messages.get(i))
# 	client_mt_2.send(messages.get(i))

client_mt_2.send(messages)

		