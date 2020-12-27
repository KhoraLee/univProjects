#include <stdio.h>
#define SIZE 5

void calculate(double *e, double *m, double *e100, double *m100, double *av);
void print_array(double arr[SIZE]);

int main(void) {
	double eng[SIZE] = {4.1,3.0,2.8,4.2,3.5};
	double math[SIZE] = {3.1,3.5,3.3,3.2,2.7};
	double eng100[SIZE];
	double math100[SIZE];
	double avg[SIZE];

	print_array(eng);
	print_array(math);

	calculate(&eng,&math,&eng100,&math100,&avg);

	print_array(eng100);
	print_array(math100);
	print_array(avg);
	return 0;
}

void calculate(double* e, double* m, double* e100, double* m100, double* avg) {
	double rate = 100 / 4.5;
	for (int i = 0; i < SIZE; i++) {
		*(e100 + i) = *(e+i) * rate;
		*(m100 + i) = *(m+i) * rate;
		*(avg+i) = ((*(e100 + i)) + (*(m100 + i))) / 2;
	}

}

void print_array(double arr[SIZE]) {
	for (int i = 0; i < SIZE; i++) {
		printf("%lf ",arr[i]);
	}
	printf("\n");
}