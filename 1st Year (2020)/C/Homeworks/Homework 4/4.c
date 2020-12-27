#include <stdio.h>

int main(void) {
	int lightspeed = 300000; // unit : km/s
	int Dis_SE = 149600000; // Distance Between Sun and Earth, unit : km

	printf("���� �ӵ��� %d km/s\n",lightspeed);
	printf("�¾�� �������� �Ÿ��� %d km\n",Dis_SE);

	int arrival_time = Dis_SE / lightspeed; // unit : second
	int arrival_min = arrival_time / 60;
	int arrival_sec = arrival_time % 60;

	printf("���� �ð��� %d �� (%d�� %d��) �Դϴ�.",arrival_time,arrival_min,arrival_sec);

	return 0;
}