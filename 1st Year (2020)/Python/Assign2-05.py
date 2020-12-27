def isPalindrome(word):
	if len(word)<2:
		return True
	if word[0] != word[-1]:
		return False
	return isPalindrome(word[1:-1])

while True:
	wordin = input("문자열을 입력하세요(-1로 종료): ")
	if wordin == "-1":
		break;
	if isPalindrome(wordin):
		print("회문입니다.")
	else:
		print("회문이 아닙니다.")
	print("-----------------------")
	