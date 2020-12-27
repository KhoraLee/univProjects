num1 = int(input('첫 번째 정수를 입력하세요: '))
num2 = int(input('두 번째 정수를 입력하세요: '))
num3 = int(input('세 번째 정수를 입력하세요: '))
if (num1>num2 and num1>num3):
	max = num1
elif (num2>num1 and num2>num3):
	max = num2
else:
	max = num3

print("%d이 가장 큽니다." %max)