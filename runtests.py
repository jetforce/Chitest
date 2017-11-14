import os


testings = [("dataset/b.csv","dataset/a.csv") , ("dataset/d.csv","dataset/c.csv"),("dataset/h.csv","dataset/e.csv"),("dataset/i.csv","dataset/f.csv"),("dataset/j.csv","dataset/g.csv"),("dataset/c.csv","dataset/a.csv"),("dataset/e.csv","dataset/a.csv"),("dataset/f.csv","dataset/a.csv"),("dataset/g.csv","dataset/a.csv"),("dataset/e.csv","dataset/f.csv","dataset/g.csv"), ("dataset/h.csv","dataset/i.csv","dataset/j.csv")]


for i in range(len(testings)):
	line = "python readChi.py saveResults"+str(i)+".csv"
	for f in testings[i]:
		line = line +" "+f
	print line
	os.system(line)