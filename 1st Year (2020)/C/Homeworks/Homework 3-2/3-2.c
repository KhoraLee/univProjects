#include <stdio.h>

int main(void) {
	double width, height, area, circumference;
	printf("�Ʒ� ������ �Է����ּ���.\n");
	printf("�簢���� ����: ");
	scanf("%lf",&width);
	printf("�簢���� ����: ");
	scanf("%lf", &height);

	area = width * height;
	circumference = 2 * (width + height);

	printf("���̴� %f �Դϴ�.\n",area);

	printf("�ѷ��� %f �Դϴ�.\n",circumference);
}