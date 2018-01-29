import csv
import math
import numpy as np
import sys
from Table import Table
from clean import ColConverter
import readChi






#Main Method

print sys.argv
vList = getVariableList('Updated-Variables.csv') #Get Variable Description
header = readHeader(sys.argv[2]) #Read the header from one of the datasets which include the question codes

results = []
converter = ColConverter(header)


#print header
clusternames = sys.argv[2:] #Read the dataset names

#print clusternames
clusters = [] #clusters contains all of the respondents and their answer in per dataset

#For each data set
for clustername in clusternames:
	clusterRow = readCSV(clustername) #Get all of the respondent's IDs
                                          # and answers in the dataset
	#print clusterRow
	clusters.append(clusterRow) #Add to the clusters

tableList = [] #list of contingency tables

z=[1.960]

#For every variable
	#
