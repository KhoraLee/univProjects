#include <stdio.h>

int main(void) {
	int price, paid_money, change;

	printf("물건 값을 입력하시오: ");
	scanf("%d", &price);

	printf("사용자가 낸 돈을 입력하시오: ");
	scanf("%d", &paid_money);

	change = paid_money - price;
	printf("거스름돈: %d\n", change);

	if (change / 5000 >= 1) {
		printf("오천원권: %d장\n", change / 5000);
		change %= 5000;
	}
	if (change / 1000 >= 1) {
		printf("천원권: %d장\n", change / 1000);
		change %= 1000;
	}
	if (change / 500 >= 1) {
		printf("오백원 동전: %d개\n", change / 500);
		change %= 500;
	}
	if (change / 100 >= 1) {
		printf("백원 동전: %d개\n", change / 100);
		change %= 100;
	}

	return 0;
}