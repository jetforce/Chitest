import csv
import math
import numpy as np
import sys
from Table import Table
from clean import ColConverter
import readChi as rc


#Main Method

print sys.argv
vList = getVariableList('Updated-Variables.csv') #Get Variable Description
header = rc.readHeader(sys.argv[2]) #Read the header from one of the datasets which include the question codes

results = []
converter = ColConverter(header)


#print header
clusternames = sys.argv[2:] #Read the dataset names

#print clusternames
clusters = [] #clusters contains all of the respondents and their answer in per dataset

#For each data set
for clustername in clusternames:
	clusterRow = rc.readCSV(clustername) #Get all of the respondent's IDs
                                          # and answers in the dataset
	#print clusterRow
	clusters.append(clusterRow) #Add to the clusters

tableList = [] #list of contingency tables

z=[1.960]
for y in range(0,len(z)):
	results = [] #The resulting content that will be written in save.csv

        dataset_headers = []

	for x in range(0, len(clusternames)):
                dataset_headers.append("Dataset " + str(x+1))

        results.append(dataset_headers)
	results.append(clusternames) #Append dataset names

        
        population_and_proportionHeaders = [] #Headers Ni and Pi for each cluster i

        for x in range(0, len(clusternames)):
                population_and_proportionHeaders.append("N"+str(x+1)) #Add Header "Nx" for each cluster x. Total of x

        for x in range(0, len(clusternames)):
                population_and_proportionHeaders.append("P"+str(x+1)) #Add Header "Px" for each cluster x. Proportion of x
                
                             
	results_headers = ["Question","Feature","Chi","Higher Or Lower", "Degrees of Freedom"] #Results headers
	results_headers.extend(population_and_proportionHeaders) #Append the population and proportion headers for each cluster to results headers
	results.append(results_headers) #Append these as header names to the results
        print results

	for i in range(1,len(header)-1): #Iterate over each question
		if header[i] not in vList.keys(): #If the question code is not found in Variable Description
			print "Warning "+ header[i] +" "+" question name not in Variable description will be assigned to null"
			H = "null"
		else:
			H = vList[header[i]][0] #H is the question itself
		print "col "+str(i)+" "+ header[i]	
		theTable = rc.getTable(i,clusters,vList,header[i]) #Generates a table matrix for all datasets to do the chi-test for the question

		#doFile(theTable,i,results,converter,z[y]) #Chi test on the question and then writing it in the file
		theTable.getPrintable(tableList)
		print "Table",
		print theTable.rows
	#print results
	filename = sys.argv[1] #Get filename of the filed to be saved on (save.csv)
	rc.writeOnCSV(results,filename)
	#print tableList
	rc.writeOnCSV(tableList,"Tables "+filename)

print sys.argv[2:]

