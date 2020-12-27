newWord = {"자만추":"자연스러운 만남 추구","핑프":"핑거 프린스(프린세스)의 줄임말로 손 하나 까딱이면서 무언가를 쉽게 얻으려는 사람","별다줄":"별걸 다 줄인다"}

count = 0 
total = 3 #dictionary 의 크기

print("========================================")
print("신조어 목록 : ",end='')
for key in newWord:
	if count<total-1 :
		print(key,',',end=' ')
	else :
		print(key)
	count = count + 1
	
_input = input("원하는 신조어를 입력하세요 : ")
if _input in newWord : 
	print(_input+"의 의미는 "+newWord[_input]+"입니다.")
else:
	print("사전에 없는 단어입니다.")
	