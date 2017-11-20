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


def readCSV(filename,isHead = True):
	rows = []
	count = 0
	with open(filename) as csvfile:
	    readCSV = csv.reader(csvfile, delimiter=',')
	    for row in readCSV:
	    	if(not (count==0 and isHead)):
		    	for i in range(0,len(row)):
		    		if(RepresentsInt(row[i])):
		    			#print "REPRESENTS " + row[i]
		    			temp = int(row[i])
		    			row[i] = str(temp)

		    		elif(RepresentsFloat(row[i])): 	
		    			#print "REPRESENTS " + row[i]
		    			temp = float(row[i])
		    			temp = int(temp)
		    			row[i] = str(temp)
		        rows.append(row)
	        count =  count +1
	return rows


def RepresentsInt(s):
    try: 
        int(s)
        return True
    except ValueError:
        return False

def RepresentsFloat(s):
    try: 
        float(s)
        return True
    except ValueError:
        return False


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



def readTableToFloat(table):
	rows = []
	for x in range(1,len(table.rows)):
		rows.append([float(i) for i in table.rows[x]])
	return table.rows[0], rows


def doFile(table,fileNum,results,converter,z):

	header, rows = readTableToFloat(table)
	header , rows = sortTableColumns(header,rows)

	# print "Header after i killed it "+ str(header)
	# print "diz are da header"
	# print header	
	# print "diz are the rows"
	# print rows
	numpiRows = np.asarray(rows)
	labelCols = numpiRows[:,0]
	numpiRows=np.delete(numpiRows, 0, axis=1)

	totals =  getSumRows(numpiRows)
	#print "total: "+str(totals)

	proportions = getProportions(numpiRows, totals)
	
	#print "proportions "+ str(proportions)
	

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
	lenrow = len(totals)

	print "colsum"
	print colSum
	lencol =  len(colSum)


	# print "expected"
	# print expected
	

	for i in range(0,len(expected)):
		for y in range(0,len(expected[i])):
			#print colSum[y]
			expected [i][y] = totals[i][0] * colSum[y] / grandTotal

	
	#print "Expected"
	#print expected
	
	#print "the data"
	#print numpiRows

	chi = ((numpiRows - expected) * (numpiRows - expected)) / expected
	print "Expected"
	print expected
	# print numpiRows

	shapeexpected = np.reshape(expected,(-1,1))
	print shapeexpected

	chistat = np.sum(chi)

	higherOrLower=""


	tolerableFive =  expected.size
	tolerableFive = int(tolerableFive*0.20)


	numFive = 0
	for el in range(0,shapeexpected.size):
		if shapeexpected[el][0] < 5:
			numFive = numFive +1

	if numFive > tolerableFive:
		chistat = np.nan

	if(not np.isnan(chistat)):
		print "observed",
		print numpiRows[0][1]
		print "expected",
		print expected[0][1]
	if(expected[0][1] < numpiRows[0][1] ):
		higherOrLower ="+"
	else: 
		higherOrLower = "-" 


	# print chistat
	"""
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
	"""
	#if(chistat > z):
	thequestion = converter.convert(fileNum)
	print "The H" + str(H)
	print "The Question "+ thequestion
	if(np.isnan(chistat)):
		chistat = ""
	results.append([H,thequestion,chistat,higherOrLower,lencol-1])


def group(index, rows,V, header):
	groups = {}
	#1 Because first index is question name
	if header not in V.keys():
		print "Warning "+ header +" "+"not in Variable description"
	else:
		for i in range(1,len(V[header])):
			entry =  V[header][i][0]
			groups[V[header][i][0]] = []


	for i in range(0, len(rows)):

		entry = rows[i][index]

		if(entry != '-1' and entry!='' and entry != '-1.0'):

			if entry in groups:
				groups[entry].append(i)
			else:
				print "Warning  "+ str(entry) +" is not declared in variable description for question "+ header
				groups[entry] = []
				groups[entry].append(i)

	return groups


def getTable(col,clusters,V, header):

	groups = []
	for c in clusters:
		groups.append(group(col,c,V,header))

	keys = []
	for g in groups:

		for key in g:

			if key not in keys:
				keys.append(key)
	
	return Table(groups,keys,header)


def getVariableList(filename):
	variables = {}	

	
	with open(filename) as csvfile:
	    readCSV = csv.reader(csvfile, delimiter=',')
	    for row in readCSV:

	    	if( row[0] == 'v'):
	    		variables[row[1]]= [row[2]]
	    		lastVar = row[1]    	
	    	else:
	    		variables[lastVar].append((row[0], row[1]))			        
	return variables


vList = getVariableList('Updated-Variables.csv')
header = readHeader('dataset/a.csv')

results = []
converter = ColConverter(header)


#print header
#print header
clusternames = sys.argv[2:]

print clusternames
clusters = []
for clustername in clusternames:
	clusterRow = readCSV(clustername)
	#print clusterRow
	clusters.append(clusterRow)


tableList = []

z=[1.960]
zstr = ['1960']
for y in range(0,len(z)):
	results = [["Question","Feature","Chi","Higher Or Lower", "Degrees of Freedom"]]
	for i in range(1,len(header)-1):
		if header[i] not in vList.keys():
			print "Warning "+ header[i] +" "+" question name not in Variable description will be assigned to null"
			H = "null"
		else:
			H = vList[header[i]][0]
		print "col "+str(i)+" "+ header[i]	
		theTable = getTable(i,clusters,vList,header[i])

		doFile(theTable,i,results,converter,z[y])
		theTable.getPrintable(tableList)
		#print "Table",
		#print theTable.rows
	#print results
	filename = sys.argv[1]
	writeOnCSV(results,filename)
	#print tableList
	writeOnCSV(tableList,"Tables"+filename)

#results = converter.cleanRows(results)
#writeOnCSV(results,filename)

# print "results"
# print results