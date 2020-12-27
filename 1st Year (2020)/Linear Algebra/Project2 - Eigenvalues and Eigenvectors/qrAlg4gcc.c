/**********************************************
- original code from https://rosettacode.org/wiki/QR_decomposition#C
- modified by H. Lee for QR iteration for solving eigenvalue problem 
- visit the website for QR decomposition code in other languages
**********************************************/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
 
typedef struct {
	int m, n;
	double ** v;
} mat_t, *mat;
 
mat matrix_new(int m, int n)
{
	mat x = malloc(sizeof(mat_t));
	x->v = malloc(sizeof(double*) * m);
	x->v[0] = calloc(sizeof(double), m * n);
	for (int i = 0; i < m; i++)
		x->v[i] = x->v[0] + n * i;
	x->m = m;
	x->n = n;
	return x;
}

//Identity matrix
mat matrix_newI(int m, int n)
{
	mat x = malloc(sizeof(mat_t));
	x->v = malloc(sizeof(double*) * m);
	x->v[0] = calloc(sizeof(double), m * n);
	for (int i = 0; i < m; i++){
		x->v[i] = x->v[0] + n * i;
		if (i < n)
			x->v[i][i] = 1;
	}
	x->m = m;
	x->n = n;
	return x;
}
 
void matrix_delete(mat m)
{
	free(m->v[0]);
	free(m->v);
	free(m);
}
 
void matrix_transpose(mat m)
{
	for (int i = 0; i < m->m; i++) {
		for (int j = 0; j < i; j++) {
			double t = m->v[i][j];
			m->v[i][j] = m->v[j][i];
			m->v[j][i] = t;
		}
	}
}
 
mat matrix_copy(int n, double a[][n], int m)
{
	mat x = matrix_new(m, n);
	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			x->v[i][j] = a[i][j];
	return x;
}

//Input matrix in double** format
mat matrix_copy2(int n, double** a, int m)
{
	mat x = matrix_new(m, n);
	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			x->v[i][j] = a[i][j];
	return x;
}
 
mat matrix_mul(mat x, mat y)
{
	if (x->n != y->m) return 0;
	mat r = matrix_new(x->m, y->n);
	for (int i = 0; i < x->m; i++)
		for (int j = 0; j < y->n; j++)
			for (int k = 0; k < x->n; k++)
				r->v[i][j] += x->v[i][k] * y->v[k][j];
	return r;
}
 
mat matrix_minor(mat x, int d)
{
	mat m = matrix_new(x->m, x->n);
	for (int i = 0; i < d; i++)
		m->v[i][i] = 1;
	for (int i = d; i < x->m; i++)
		for (int j = d; j < x->n; j++)
			m->v[i][j] = x->v[i][j];
	return m;
}

// l-1 matrix norm
double matrix_norm1(mat x, mat y)
{
	double max  = 0.0;
	double a;

	for (int i = 0; i < x->m; i++){
		a = 0.0;
		for (int j = 0; j < x->n; j++) {
			a += fabs(x->v[i][j] - y->v[i][j]);
		}
		if (a > max)
			max = a;
	}
	return max;
}
 
/* c = a + b * s */
double *vmadd(double a[], double b[], double s, double c[], int n)
{
	for (int i = 0; i < n; i++)
		c[i] = a[i] + s * b[i];
	return c;
}
 
/* m = I - v v^T */
mat vmul(double v[], int n)
{
	mat x = matrix_new(n, n);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			x->v[i][j] = -2 *  v[i] * v[j];
	for (int i = 0; i < n; i++)
		x->v[i][i] += 1;
 
	return x;
}
 
/* ||x|| */
double vnorm(double x[], int n)
{
	double sum = 0;
	for (int i = 0; i < n; i++) sum += x[i] * x[i];
	return sqrt(sum);
}
 
/* y = x / d */
double* vdiv(double x[], double d, double y[], int n)
{
	for (int i = 0; i < n; i++) y[i] = x[i] / d;
	return y;
}
 
/* take c-th column of m, put in v */
double* mcol(mat m, double *v, int c)
{
	for (int i = 0; i < m->m; i++)
		v[i] = m->v[i][c];
	return v;
}
 
void matrix_show(mat m)
{
	printf("%d x %d\n", m->m, m->n);
	for(int i = 0; i < m->m; i++) {
		for (int j = 0; j < m->n; j++) {
			printf(" %8.3f", m->v[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}
 
void householder(mat m, mat *R, mat *Q)
{
	mat q[m->m];
	mat z = m, z1;
	for (int k = 0; k < m->n && k < m->m - 1; k++) {
		double e[m->m], x[m->m], a;
		z1 = matrix_minor(z, k);
		if (z != m) matrix_delete(z);
		z = z1;
 
		mcol(z, x, k);
		a = vnorm(x, m->m);
		if (m->v[k][k] > 0) a = -a;
 
		for (int i = 0; i < m->m; i++)
			e[i] = (i == k) ? 1 : 0;
 
		vmadd(x, e, a, e, m->m);
		vdiv(e, vnorm(e, m->m), e, m->m);
		q[k] = vmul(e, m->m);
		z1 = matrix_mul(q[k], z);
		if (z != m) matrix_delete(z);
		z = z1;
	}
	matrix_delete(z);
	*Q = q[0];
	*R = matrix_mul(q[0], m);
	for (int i = 1; i < m->n && i < m->m - 1; i++) {
		z1 = matrix_mul(q[i], *Q);
		if (i > 1) matrix_delete(*Q);
		*Q = z1;
		matrix_delete(q[i]);
	}
	//matrix_delete(q[0]);
	z = matrix_mul(*Q, m);
	matrix_delete(*R);
	*R = z;
	//puts("R in Householder"); matrix_show(*R);
	matrix_transpose(*Q);
	//matrix_transpose(*R); //should be deleted
	//puts("R in Householder2"); matrix_show(*R);
}
//*
double D[][4] = {
	{2, -1, -1, 0},
	{-1, 3, -1, -1},
	{-1, -1, 3, -1},
	{0, -1, -1, 2}
};//*/


//* 
double C[][3] = {
	{3, 2, 4},
	{2, 0, 2},
	{4, 2, 3}
};//*/

//*
double B[][2] = {
	{1, 2},
	{2, -1}
};//*/

double A[][2] = {
	{1, 2},
	{2, 5}
};

int main()
{
	int n = 4; 
	int i;
	int MaxIter = 100;
	mat R, Q;
	mat T1 = matrix_copy(n, D, n);
	mat T2;
	mat U = matrix_newI(n,n);
	mat At;
	for (i = 0; i < MaxIter; i++){
		//T1 = QR
		householder(T1, &R, &Q);
		puts("T1"); matrix_show(T1);
		puts("Q"); matrix_show(Q);
		puts("R"); matrix_show(R);

		// T2 = RQ
		T2 = matrix_mul(R,Q);
		U = matrix_mul(U,Q);
		
		// termination criterion
		if (matrix_norm1(T1,T2) < 0.001)
			break;

		// T1 = T2;
		T1 = matrix_copy2(n,T2->v,n);
	}
	printf("Terminated after %d iterations\n", i);
 
	puts("Tk"); matrix_show(T2);
	puts("Qk=U0*...*Uk"); matrix_show(U);
	At = matrix_mul(U,T2);
	matrix_transpose(U);
	At = matrix_mul(At,U);
	//puts("A"); matrix_show(matrix_mul(matrix_mul(U,T2);
	puts("Reconstructed A=Qk * Tk * Qk^T"); matrix_show(At);
	//matrix_transpose(U);
	//puts("R in Householder2"); matrix_show(U);
 
 
	matrix_delete(R);
	matrix_delete(Q);
	matrix_delete(T1);
	matrix_delete(T2);
	matrix_delete(U);
	matrix_delete(At);
	return 0;
}
