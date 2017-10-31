import numpy as np

class Table(object):

	def __init__(self, groups,keys):
		self.rows = []

		header = ['Clusters']
		header = header + keys
		self.rows.append(header)
		#print keys

		#print len(groups[0][keys[0]])

		#print groups

		for i  in range(0,len(groups)):
			row = [i]

			for k in keys:

				if k not in groups[i]:
					row.append(str(0))
				else:
					row.append(str(len(groups[i][k])))
			self.rows.append(row)
