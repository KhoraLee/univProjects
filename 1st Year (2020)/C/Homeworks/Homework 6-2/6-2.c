#include <stdio.h>

int main(void) {
	int fahrenheit;
	printf("ȭ���µ��� �Է��Ͻÿ�: ");
	scanf("%d", &fahrenheit);
	double celsius = ((double)fahrenheit - 32) * 5 / 9;
	printf("�����µ��� %lf�Դϴ�.",celsius);

	return 0;
}