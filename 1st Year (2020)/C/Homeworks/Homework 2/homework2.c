/*
	File Name : homework2.c
	Author : Seungyun Lee
	Date : April 4th 2020
	Purpose : *
*/

#include <stdio.h> // 표준 입출력 라이브러리 헤더

int main(void)
{
	char lower[26] = "abcdefghijklmnopqrstuvwxyz";
	char upper[26] = "ABCDEFGHIJKLMNOPQRSTUVWXZY";
	char input, output;

	printf("글자를 입력하세요 : ");
	scanf("%c",&input);

	for (int i = 0; i < 26; i++) {
		if (lower[i] == input) {
			output = upper[i];
		}
		else if (upper[i] == input) {
			output = lower[i];
		}
	}

	printf("입력받은 글자는 %c이며 변환후 %c",input,output);
}
