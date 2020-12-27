#include <stdio.h>

int Cal_area(struct rectangle rec);
int Cal_peri(struct rectangle rec);

struct point {
	int x;
	int y;
};
struct rectangle {
	struct point LB; // Left Bottom
	struct point RT; // Right Top
};

int main(void) {
	struct rectangle rec;
	printf("���� �ϴ����� ��ǥ�� �Է��ϼ��� : ");
	scanf("%d %d",&rec.LB.x, &rec.LB.y);
	printf("������ ����� ��ǥ�� �Է��ϼ��� : ");
	scanf("%d %d", &rec.RT.x, &rec.RT.y);

	printf("������ %d�̰� �ѷ��� %d�Դϴ�.\n", Cal_area(rec),Cal_peri(rec));
}

int Cal_area(struct rectangle rec) {
	int base = rec.RT.x - rec.LB.x;
	int height = rec.RT.y - rec.LB.y;
	return base * height;
}
int Cal_peri(struct rectangle rec) {
	int base = rec.RT.x - rec.LB.x;
	int height = rec.RT.y - rec.LB.y;
	return (base + height)*2;
}