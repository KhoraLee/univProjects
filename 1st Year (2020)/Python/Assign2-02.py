while(True):
	num1 = int(input('정수(>=2)를 입력하세요: ')) #정수 입력 받기
	if(num1 == -1): #프로그램 종료 조건
		break
	if(num1<2):
		print('잘못 입력하였습니다.')
	else:
		#소수 찾기
		for i in range(2,num1+1): 
			isPrime = True
			for j in range(2,i):
				if(i%j == 0):
					isPrime = False
					break #실행 시간을 줄이기 위해 소수가 아닌 시점에서 for문 벗어나기
			if(isPrime == True):
				print(i,end=' ')
	print("\n-----------------------")