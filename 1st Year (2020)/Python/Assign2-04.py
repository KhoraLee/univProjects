def multiplication(a,b):
	return a*b
def division(a,b):
	if b==0:
		return "error"
	else:
		return a/b
def sum(a,b):
	return a+b
def sub(a,b):
	if a>b:
		return a-b
	else:
		return b-a

while True:
	a = float(input("첫 번째 수를 입력하세요: "))
	b = float(input("두 번째 수를 입력하세요: "))
	
	print("종료:0, 덧셈:1, 뺄셈:2, 곱셈:3, 나눗셈:4")
	sel = int(input("원하는 계산을 선택하세요: "))
	
	if sel==0:
		print("프로그램을 종료합니다.")
		break;
	elif sel==1:
		result= sum(a,b)
		print("Result = ",result)
	elif sel==2:
		result= sub(a,b)
		print("Result = ",result)
	elif sel==3:
		result= multiplication(a,b)
		print("Result = ",result)
	elif sel==4:
		result= division(a,b)
		if result == "error":
			print("0으로 나눌 수 없습니다. 다시 시작합니다.")
		else:
			print("Result = ",result)
		

