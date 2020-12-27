#include <stdio.h>

#define NUM_EXAM 3
#define NUM_STUDENT 5

int find_min(int score[NUM_EXAM][NUM_STUDENT], int exam);
int find_max(int score[NUM_EXAM][NUM_STUDENT], int exam);
double find_avg(int score[NUM_EXAM][NUM_STUDENT], int exam);

int main(void) {
	int score[NUM_EXAM][NUM_STUDENT] = {
		{60,80,43,78,93}, //exam1
		{75,59,81,77,81}, //exam2
		{83,74,97,73,81} //exam3
	};

	for (int i = 0; i < 3;i++) {
		printf("시험%d의 최저점:%d, 최고점:%d, 평균:%f \n",i,find_min(score,i), find_max(score, i), find_avg(score, i));
	}
}

int find_min(int score[NUM_EXAM][NUM_STUDENT], int exam) {
	int current_min = score[exam][0];
	for (int i = 1; i < NUM_STUDENT; i++) {
		if (current_min > score[exam][i]) {
			current_min = score[exam][i];
		}
	}

	return current_min;
}
int find_max(int score[NUM_EXAM][NUM_STUDENT], int exam) {
	int current_max = score[exam][0];
	for (int i = 1; i < NUM_STUDENT; i++) {
		if (current_max < score[exam][i]) {
			current_max = score[exam][i];
		}
	}

	return current_max;
}

double find_avg(int score[NUM_EXAM][NUM_STUDENT], int exam) {
	int sum = 0;
	for (int i = 0; i < NUM_STUDENT;i++) {
		sum += score[exam][i];
	}

	return (double)sum / NUM_STUDENT;
}