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
	printf("왼쪽 하단점의 좌표를 입력하세요 : ");
	scanf("%d %d",&rec.LB.x, &rec.LB.y);
	printf("오른쪽 상단의 좌표를 입력하세요 : ");
	scanf("%d %d", &rec.RT.x, &rec.RT.y);

	printf("면적은 %d이고 둘레는 %d입니다.\n", Cal_area(rec),Cal_peri(rec));
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