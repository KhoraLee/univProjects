#include <stdio.h>

int main(void) {
	int price, paid_money, change;

	printf("���� ���� �Է��Ͻÿ�: ");
	scanf("%d", &price);

	printf("����ڰ� �� ���� �Է��Ͻÿ�: ");
	scanf("%d", &paid_money);

	change = paid_money - price;
	printf("�Ž�����: %d\n", change);

	if (change / 5000 >= 1) {
		printf("��õ����: %d��\n", change / 5000);
		change %= 5000;
	}
	if (change / 1000 >= 1) {
		printf("õ����: %d��\n", change / 1000);
		change %= 1000;
	}
	if (change / 500 >= 1) {
		printf("����� ����: %d��\n", change / 500);
		change %= 500;
	}
	if (change / 100 >= 1) {
		printf("��� ����: %d��\n", change / 100);
		change %= 100;
	}

	return 0;
}