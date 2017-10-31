import csv


def writeOnCSV(header, rows, filename):
	with open(filename, 'wb') as f:
	    writer = csv.writer(f)
	    writer.writerow(header)
	    writer.writerows(rows)


def groupArray(index, row, rowlist):
	groups = {}

	groupnames = getGroupsInColumns(rows,index)


	for i in range(0, len(rowlist)):
		entry = rows[rowlist[i]][index]

		if entry in groups:
			groups[entry].append(rowlist[i])
		else:
			groups[entry] = []
			groups[entry].append(rowlist[i])


	for groupname in groupnames:
		if(groupname not in groups.keys()):
			groups[groupname] = []

	return groups



def group(index, rows):
	groups = {}

	for i in range(0, len(rows)):

		entry = rows[i][index]

		if(entry != '99' and entry!=''):

			if entry in groups:
				groups[entry].append(i)
			else:
				groups[entry] = []
				groups[entry].append(i)

	return groups


def countGroup(groups):
	keys = groups.keys()

	# for key in keys:
	# 	print key +" :"+ str(len(groups[key]))

	keys = groups.keys()
	if '99' in keys:
		keys.remove('99')

	if '' in keys:
		keys.remove('')

	total = 0

	for key in keys:
		total = total + len(groups[key])

	for key in keys:
		pert = float(len(groups[key])/ float(total) )
		pert = pert *100
		print key +" :"+  str(len(groups[key]))+"||"+ str(pert)+"%" 
	print "total :"+str(total) +" 100%"



def getGroupsInColumns(allrows,column):

	groups = []
	for i in range(0,len(allrows)):
		if(allrows[i][column] not in groups):
			if(allrows[i][column] != '99' and allrows[i][column] != '' ):
				groups.append(allrows[i][column])
	return groups


def groupCountWrite(allrows,index_column, filename):

	#group by gender first
	byGender = group(2,allrows)
	countGroup(byGender)
	print "______"

	genders = byGender.keys()
	rows = []
	header = []
	header.append("age")
	
	columnKeys = getGroupsInColumns(allrows,index_column)
	header = header + columnKeys

	for gender in genders:
		temprows = [] 
		temprows.append(gender)
		byColumn = groupArray(index_column, allrows, byGender[gender])
		
		for columnKey in columnKeys:
			temprows.append( len(byColumn[columnKey]))			

		rows.append(temprows)	

	#writeOnCSV(header,rows,filename)	



groupCountWrite(rows,15,"output/age/"+str(15)+".csv")


# num0 = 0
# num1 = 0
# num99 = 0
# numB = 0
# print header[15]
# for i in range(0,len(row)):

# 	ans = rows[i][15]

# 	#print ans

		
# 	#print rows[i][2]
	
# 	if(ans == '0'):
# 		num0 = num0 +1
# 	if(ans == '1'):
# 		num1 = num1 +1
# 	if(ans == '99'):
# 		num99 = num99 +1
# 	if(ans == ''):
# 		numB = numB +1


# print "number 0 "+ str(num0)
# print "number 1 "+ str(num1)
# print "number 99 "+ str(num99)
# print "number B "+ str(numB)







# for i in range(2,len(header)):
# 	groupCountWrite(rows,i,"output/age/"+str(i)+".csv")



# for i in range(2, len(header)):
# 	print "_____________________"
# 	print "column"+str(i)+" "+ header[i]
# countGroup(grouplist[2])