#include <stdio.h>
#define SIZE 5

void print_image(int img[][SIZE],int xsize,int ysize);
void brighten_image(int img[][SIZE],int xsize, int ysize);

int main(void) {
	int image[SIZE][SIZE] = {
		{10,20,30,40,50},
		{10,20,30,40,50},
		{10,20,30,40,50},
		{10,20,30,40,50},
		{10,20,30,40,50}
	};

	print_image(image, SIZE, SIZE);
	brighten_image(image, SIZE, SIZE);
	print_image(image, SIZE, SIZE);
	return 0;
}

void print_image(int img[][SIZE], int xsize, int ysize) {
	for (int i = 0; i < ysize; i++) {
		for (int j = 0; j < xsize; j++) {
			printf("%d ", img[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

void brighten_image(int img[][SIZE], int xsize, int ysize) {
	for (int i = 0; i < ysize; i++) {
		for (int j = 0; j < xsize; j++) {
			img[i][j] += 10;
			if (img[i][j] >= 255) { img[i][j] = 255; }
		}
	}
}
