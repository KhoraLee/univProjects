#include <stdio.h>

int main(void) {
	double width, height, area, circumference;
	printf("아래 값들을 입력해주세요.\n");
	printf("사각형의 길이: ");
	scanf("%lf",&width);
	printf("사각형의 높이: ");
	scanf("%lf", &height);

	area = width * height;
	circumference = 2 * (width + height);

	printf("넓이는 %f 입니다.\n",area);

	printf("둘레는 %f 입니다.\n",circumference);
}