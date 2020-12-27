#include <stdio.h>

int main(void) {
	int i, j;
	int *pi, *pj;
	pi = &i;
	pj = &j;
	*pi = 5;
	*pj = 10;
	double avg;
	double *pavg = &avg;
	*pavg = (double)(*pi + *pj) / 2;

	printf("pi: %u, pj: %u, pavg: %u\n",pi,pj,pavg);
	printf("i: %d, j: %d, avg: %lf\n",i,j,avg);
}