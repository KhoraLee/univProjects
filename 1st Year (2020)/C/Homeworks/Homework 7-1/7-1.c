#include <stdio.h>

int main(void) {

	int year;

	printf("연도를 입력하시오: ");
	scanf("%d",&year);

	/*윤년의 조건
	1. 연수가 4로 나누어 떨어지는 해는 윤년으로 한다.
	2. 연수가 4, 100으로 나누어 떨어지는 해는 평년으로 한다.
	3. 연수가 4, 100, 400으로 나누어 떨어지는 해는 윤년으로 둔다.
	*/

	if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
		printf("%d년은 윤년입니다\n",year);
		printf("즉, 1년은 366일입니다.\n");
	} else {
		printf("%d년은 윤년이 아닙니다\n", year);
		printf("즉, 1년은 365일입니다.\n");
	}

	return 0;
}