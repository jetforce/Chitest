import csv
import math
import numpy as np
import sys
from Table import Table
from clean import ColConverter



def writeOnCSV(rows, filename):
	with open(filename, 'wb') as f:
	    writer = csv.writer(f)
	    #writer.writerow(header)
	    writer.writerows(rows)


def readHeader(filename):
	num=0
	rows = []

	with open(filename) as csvfile:
	    readCSV = csv.reader(csvfile, delimiter=',')    
	    for row in readCSV:
	    	if(num==0):
	    		return row



def readCSVtoDouble(filename):
	num=0
	rows = []

	with open(filename) as csvfile:
	    readCSV = csv.reader(csvfile, delimiter=',')
	    
	    for row in readCSV:
	    	if(num==0):
	    		header = row
	        else:
	        	rows.append([float(i) for i in row])
	        num= num +1
	return header, rows


def readCSV(filename):
	rows = []
	with open(filename) as csvfile:
	    readCSV = csv.reader(csvfile, delimiter=',')
	    for row in readCSV:
	        rows.append(row)
	return rows





def getPercentColumn(body, colIdx):
	total = 0
	percents = []
	boygirl = []
	for i in range(0, len(body)):
		total = total + int(body[i][colIdx])

	for i in range(0, len(body)):
		boygirl.append(body[i][colIdx])
		percents.append( float(float(body[i][colIdx])/total))

	return percents, boygirl



def getSumRows(rows):
	return np.sum(rows, axis=1).reshape(-1,1)


def getProportions(rows, totals):
	

	proportions = np.copy(rows)


	proportions = proportions / totals.reshape(-1,1)

	index=0
	getStandardError(proportions,totals)
	return proportions


def getStandardError(proportions,totals):
 	return np.sqrt(proportions*(1-proportions)/ totals.reshape(-1,1))


def getProportionPerColumn(numpiRows):
	colSum = np.sum(numpiRows, axis=0)
	return colSum



def sortTableColumns(header, rows):

	newheader = header[1:]
	newheader = [float(i) for i in newheader]

	pihead = np.asarray(newheader[0:])
	pirows = np.asarray(rows)
	labelCols = pirows[:,0]
	pirows = np.delete(pirows, 0, axis=1)
	i = np.argsort(pihead)
	pirows = pirows[:,i]
	pihead = pihead[i]
	pirows = np.hstack(( labelCols.reshape(-1,1),pirows))
	
	return [header[0]] + [str(i) for i in pihead.tolist()], pirows.tolist()



def doAccumulate(header, rows):
	

	newheader = [str(header[1])+"+"+ str(header[2])]
	newheader = [header[0]] + newheader


	#for i in range(3,len(header)-1):
	temp = ""
	for y  in range(3, len(header)-1):
		temp = temp + "+"+str(header[y])
	newheader.append(temp)

	print "new header"
	print newheader	
	newrow = []
	
	for row in rows:
		temprow = []
		temprow.append(row[1]+row[2])
		#print "len of row"+str(len(row)) 
		#for i in range(3,len(row)-1):
		temp = 0
		for y  in range(3, len(row)-1):
				#print row[y]
			temp = temp + row[y]	
		temprow.append(temp)
			#print "add"
		newrow.append([row[0]] +temprow)

	return newheader, newrow


def groupRows(rows):
	#print rows

	pirow = np.asarray(rows)

	pirow = pirow[pirow[:,0].argsort()]

	#print pirow

	group1 = pirow[0] + pirow[1] + pirow[2]
	group2 = pirow[3] + pirow[4] + pirow[5]
	group3 = pirow[6] + pirow[7] + pirow[8]

	group1 = group1.tolist()
	group2 = group2.tolist()
	group3 = group3.tolist()

	group1[0] = 9.11
	group2[0] = 12.14
	group3[0] = 15.17

	newrows = [group1,group2,group3]

	#print newrows
	return newrows



def readTableToFloat(table):

	rows = []

	for x in range(1,len(table.rows)):
		rows.append([float(i) for i in table.rows[x]])

	return table.rows[0], rows


def doFile(table,fileNum,results,converter,z):


	header, rows = readTableToFloat(table)
	header , rows = sortTableColumns(header,rows)



	print "diz are da header"
	print header
	
	print "diz are the rows"
	print rows


	numpiRows = np.asarray(rows)
	labelCols = numpiRows[:,0]
	numpiRows=np.delete(numpiRows, 0, axis=1)

	totals =  getSumRows(numpiRows)
	print "total: "+str(totals)



	proportions = getProportions(numpiRows, totals)
	print "proportions "+ str(proportions)
	errors = getStandardError(proportions,totals)

	upperBounds = proportions + errors*z
	lowerBounds = proportions - errors*z

	colSum = getProportionPerColumn(numpiRows)	
	PopulationCount = np.sum(colSum)
	PopQuestionProp = colSum / PopulationCount



	expected = np.copy(numpiRows)
	grandTotal = np.sum(colSum) 
	print "totals"
	print totals
	print "colsum"
	print colSum
	print expected
	for i in range(0,len(expected)):
		for y in range(0,len(expected[i])):
		
			print colSum[y]
			expected [i][y] = totals[i][0] * colSum[y] / grandTotal


	print "Expected"
	print expected

	chi = (numpiRows - expected) * (numpiRows - expected) / expected
	chistat = np.sum(chi)
	print "Chi-Square"
	print chi
	print "Chi -stat"
	print chistat
	print "Population count "+ str(PopulationCount)
	print "Pop Count "+ str(colSum)
	print "Errors" + str(errors)
	print "Pop Proportions "+ str(PopQuestionProp) 
	print "Lower "+ str(lowerBounds)
	print "Upper "+str(upperBounds)

	if(chistat > z):
		thequestion = converter.convert(fileNum)
		results.append([thequestion,chistat])


def group(index, rows):
	groups = {}

	for i in range(0, len(rows)):

		entry = rows[i][index]

		if(entry != '-1' and entry!='' and entry != '-1.0'):

			if entry in groups:
				groups[entry].append(i)
			else:
				groups[entry] = []
				groups[entry].append(i)

	return groups


def getTable(col,clusters):

	groups = []
	for c in clusters:
		groups.append(group(col,c))

	keys = []

	for g in groups:

		for key in g:

			if key not in keys:
				keys.append(key)
	
	return Table(groups,keys)




header = readHeader('mine.csv')
clusternames = sys.argv[1:]

print clusternames

clusters = []
for clustername in clusternames:
	clusterRow = readCSV(clustername)
	clusters.append(clusterRow)

results = []


converter = ColConverter()






z=[1.960]
zstr = ['1960']
for y in range(0,len(z)):
	results = [["Feature","Chi"]]
	for i in range(3,570):
		doFile(getTable(i,clusters),i,results,converter,z[y])
	print results
	filename = "ChiZ"+zstr[y]+".csv"
	writeOnCSV(results,filename)


#results = converter.cleanRows(results)
#writeOnCSV(results,filename)

# print "results"
# print results