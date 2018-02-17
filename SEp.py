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
        dataset_names = []

	for x in range(0, len(clusternames)):
                clustername_arr = clusternames[x].split('\\')
                dataset_names.append(clustername_arr[len(clustername_arr)-1])#Getting the dataset name from its file path
                dataset_headers.append("Dataset " + str(x+1))

        results.append(dataset_headers)
	results.append(dataset_names) #Append dataset names

        
        population_and_proportionHeaders = [] #Headers Ni and Pi for each cluster i

        for x in range(0, len(clusternames)):
                population_and_proportionHeaders.append("N"+str(x+1)) #Add Header "Nx" for each cluster x. Total of x

        for x in range(0, len(clusternames)):
                population_and_proportionHeaders.append("P"+str(x+1)+"(a)") #Add Header "Px" for each cluster x. Proportion of x
                population_and_proportionHeaders.append("P"+str(x+1)+"(b)")
                population_and_proportionHeaders.append("P"+str(x+1)+"(etc)")

        #results_headers = ["Question","Feature","Chi","Higher Or Lower", "Degrees of Freedom"] #Results headers                     
	results_headers = ["Feature","Question","Chi","Higher Or Lower", "Degrees of Freedom", "Cut-off", "Is significant"] #Results headers
	results_headers.extend(population_and_proportionHeaders) #Append the population and proportion headers for each cluster to results headers
	results.append(results_headers) #Append these as header names to the results

	

print sys.argv[2:]

