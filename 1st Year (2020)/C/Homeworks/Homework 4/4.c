#include <stdio.h>

int main(void) {
	int lightspeed = 300000; // unit : km/s
	int Dis_SE = 149600000; // Distance Between Sun and Earth, unit : km

	printf("빛의 속도는 %d km/s\n",lightspeed);
	printf("태양과 지구와의 거리는 %d km\n",Dis_SE);

	int arrival_time = Dis_SE / lightspeed; // unit : second
	int arrival_min = arrival_time / 60;
	int arrival_sec = arrival_time % 60;

	printf("도달 시간은 %d 초 (%d분 %d초) 입니다.",arrival_time,arrival_min,arrival_sec);

	return 0;
}