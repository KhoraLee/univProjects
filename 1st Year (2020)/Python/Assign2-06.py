def findMax(a):
	keyset = set(a)
	freqdict = dict()
	for i in a:
		freqdict[i] = a.count(i)
	sort =  sorted(freqdict.items(),key=lambda i: i[1],reverse=True)
	cur = sort[0][1]
	max_value = sort[0][0]
	for k, v in sort:
		if v !=cur:
			break
		if max_value < k:
			max_value = k
	return max_value
	
a1=[1,2,3,4,3,5,2,5,3]
a2=[1,2,3,5,3,5,2,5,3]
print(a1,findMax(a1))
print(a2,findMax(a2))