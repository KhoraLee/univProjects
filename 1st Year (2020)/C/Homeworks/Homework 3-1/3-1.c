#include <stdio.h>

int main(void) {
	int exchange_rate, won;
	double dollar;

	printf("ȯ���� �Է��ϼ���: ");
	scanf("%d",&exchange_rate);
	printf("��ȭ �ݾ��� �Է��Ͻÿ�: ");
	scanf("%d", &won);
	dollar = (double) won / exchange_rate;
	printf("��ȭ %d���� %f�޷��Դϴ�.\n",won,dollar);
}