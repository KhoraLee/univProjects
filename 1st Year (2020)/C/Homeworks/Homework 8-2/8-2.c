#include <stdio.h>

int main(void) {
	int n, r;
	printf("C(n,r) 계산을 수행합니다.\n");
	printf("n의 값을 입력하세요: ");
	n = get_int();
	printf("r의 값을 입력하세요: ");
	r = get_int();
	printf("C(%d,%d)의 값은 %d 입니다.\n",n,r,combination(n,r));
}

int get_int(void) {
	int i;
	scanf_s("%d",&i);
	return i;
}
int factorial(int n) {
	int result = 1;
	for (int i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}
int combination(int i,int j) {
	int result = factorial(i) / (factorial(i-j) * factorial(j));
	return result;
}