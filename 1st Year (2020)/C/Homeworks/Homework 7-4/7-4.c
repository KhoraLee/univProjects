#include <stdio.h>

int main(void) {
	int x, y, r;
	
	printf("�ΰ��� ������ �Է��Ͻÿ�(ū��, ������): ");
	scanf("%d %d",&x ,&y);
	
	while (y) {
		r = x % y;
		x = y;
		y = r;
	}
	printf("�ִ� ������� %d�Դϴ�.\n",x);
	return 0;
}