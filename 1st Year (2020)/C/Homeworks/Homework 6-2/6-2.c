#include <stdio.h>

int main(void) {
	int fahrenheit;
	printf("화씨온도를 입력하시오: ");
	scanf("%d", &fahrenheit);
	double celsius = ((double)fahrenheit - 32) * 5 / 9;
	printf("섭씨온도는 %lf입니다.",celsius);

	return 0;
}