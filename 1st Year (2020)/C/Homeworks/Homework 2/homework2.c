/*
	File Name : homework2.c
	Author : Seungyun Lee
	Date : April 4th 2020
	Purpose : *
*/

#include <stdio.h> // ǥ�� ����� ���̺귯�� ���

int main(void)
{
	char lower[26] = "abcdefghijklmnopqrstuvwxyz";
	char upper[26] = "ABCDEFGHIJKLMNOPQRSTUVWXZY";
	char input, output;

	printf("���ڸ� �Է��ϼ��� : ");
	scanf("%c",&input);

	for (int i = 0; i < 26; i++) {
		if (lower[i] == input) {
			output = upper[i];
		}
		else if (upper[i] == input) {
			output = lower[i];
		}
	}

	printf("�Է¹��� ���ڴ� %c�̸� ��ȯ�� %c",input,output);
}
