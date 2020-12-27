#include <stdio.h>

int main(void) {
	int n, r;
	printf("C(n,r) ����� �����մϴ�.\n");
	printf("n�� ���� �Է��ϼ���: ");
	n = get_int();
	printf("r�� ���� �Է��ϼ���: ");
	r = get_int();
	printf("C(%d,%d)�� ���� %d �Դϴ�.\n",n,r,combination(n,r));
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