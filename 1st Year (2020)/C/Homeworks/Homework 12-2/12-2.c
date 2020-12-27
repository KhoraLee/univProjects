//-------------------------------------

//

//         도전 문제 포함

//

//------------------------------------


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define MAX_TRY 15
#define QUIZ 5

int check(char s[], char a[], char ch,int* i);
int make_ans(char q[],char a[]);
int main(void) {
	srand((unsigned int)time(NULL));
	int cur_try = 0;
	int* ct = &cur_try;
	int order = rand() % 5;

	char quiz[QUIZ][100] = { "meet at midnight","hello world","c programing","end of file","visual studio" };
	char solution[100];
	strncpy(solution, quiz[order],sizeof(quiz[order]));
	char answer[100] = "";
	make_ans(solution,answer);//"____ __ ________";  // 위의 솔루션과 길이가 일치
	char ch;
	while (1) {
		printf("문자열을 입력하시오: %s\n", answer);
		printf("글자를 추측하시오 (남은 횟수 : %d회):",MAX_TRY-cur_try);
		ch = getchar();
		printf("\n");
		int state = check(solution, answer, ch,ct);
		if (state == 1) {
			printf("성공: % s \n", answer);
				break;
		} else if(state == -1) {
			printf("최대 시도 횟수에 도달해 실패...\n");
			break;
		}
		while (ch = getchar() != '\n' && ch != EOF) {}; // 버퍼 제거
	}
	return 0;
}

int check(char s[], char a[], char ch,int *i) {
	for (int i = 0; i < strlen(s); i++) {
		if (s[i] == ch) {
			a[i] = ch;
		}
	}
	*i += 1;
	if (strcmp(s, a) == 0) {
		return 1;
	}
	if (*i >= 15) {
		return -1;
	}
}
int make_ans(char q[], char a[]) {
	for (int i = 0; i < strlen(q); i++) {
		if (q[i] == ' ') {
			strcat(a," ");
		} else {
			strcat(a,"_");
		}
	}
	return 0;
}