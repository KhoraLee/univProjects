#include <stdio.h>

int main(void) {
	int i, n, sum;

	printf("������ �Է��Ͻÿ�: ");
	scanf("%d", &n);

	i = 1;
	sum = 0;

	while (i <= n) {
		if (i % 2 == 0) { sum += i; }
		i++;
	}
	printf("1���� %d���� ¦������ ���� %d�Դϴ�\n", n, sum);
	return 0;
}