#include <stdio.h>

int main(void) {
	int exchange_rate, won;
	double dollar;

	printf("환율을 입력하세요: ");
	scanf("%d",&exchange_rate);
	printf("원화 금액을 입력하시오: ");
	scanf("%d", &won);
	dollar = (double) won / exchange_rate;
	printf("원화 %d원은 %f달러입니다.\n",won,dollar);
}