#include <stdio.h>

int main() {
	int n = 0;
	for (int i = 1; i <= 100; i++) {
		for (int j = 1; j <= 100; j++) {
			for (int k = 1;k <= 100; k++) {
				if (i * i + j * j == k * k) {
					n++;
					printf("���� �ﰢ��: %d %d %d\n",i,j,k);
				}
			}
		}
	}
	printf("�� %d���Դϴ�.\n",n);

	return 0;
}