#include <stdio.h>

int main(void) { 
	int score[100] = { 0, };
	int student_num=0;
	int sum = 0;
	double avg;
	for (int i = 0; i <100; i++) {
		printf("%d번 학생의 성적을 입력하세요: ",i);
		int tmp;
		scanf("%d", &tmp);
		if (tmp == -1){ break; }
		student_num++;
		score[i] = tmp;
	}
	for (int i = 0; i < student_num;i++) {
		sum += score[i];
	}
	avg = (double) sum / student_num;
	printf("\n학생들의 평균 점수는 %lf점 입니다.\n",avg);
}