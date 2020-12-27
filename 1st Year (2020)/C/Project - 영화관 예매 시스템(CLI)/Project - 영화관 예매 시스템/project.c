/*
	File Name : project.c
	Author : Seungyun Lee
	Date : June 7th 2020
	Version : 5 (String)
*/

#include <stdio.h>
#include <stdlib.h>

#define SEAT_ROW 3
#define SEAT_COL 5

struct user_info {
	int id_num;
	char id[10]; // Only less then 10 Characters
	char password[10]; // Only less then 10 Characters
};

int load_usr_info(struct user_info user[]);
int check_login(struct user_info user[],char id[], char pw[]);
int print_seats(int seat[SEAT_ROW][SEAT_COL]);
int reserve(int seat[SEAT_ROW][SEAT_COL], int row, int col, int id);
int cancel(int seat[SEAT_ROW][SEAT_COL], int row, int col, int id);
int BSearch(struct user_info user[],char id[], int low, int high);



int main(void) {
	struct user_info user[10];
	load_usr_info(user);
	int seat[3][5] = { 0, };
	int id_n;
	int quit = 0;
	seat[2][3] = 4000; // for test

	printf("�α����� �Ͻʽÿ�.\n");
	while (1) {
		char id[10], pw[10];

		printf("id : ");
		scanf("%s", &id);
		printf("password : ");
		scanf("%s", &pw);
		id_n = check_login(user,id, pw);
		if (id_n == -1 || id_n == -2) {
			printf("�α��ο� �����Ͽ����ϴ�. �ٽ� �α��� �ϼ���.\n");
		}
		else {
			break;
		}
	} 
	printf("%s�� �ݰ����ϴ�.\n", user[(id_n/10)-1].id);

	while (quit == 0) {
		int sel, c;
		char r;
		printf("1---�¼�Ȯ���ϱ�\n");
		printf("2---�����ϱ�\n");
		printf("3---��������ϱ�\n");
		printf("4---�����ϱ�\n");
		printf("�޴��� �����Ͻÿ�: ");
		scanf("%d", &sel);
		while (getchar() != '\n' && getchar() != EOF);
		int state;
		switch (sel) {
		case 1:
			print_seats(seat);
			break;

		case 2:
			printf("������ ���ϴ� �ڸ��� (ex A2)? ");
			scanf("%c%d", &r, &c);
			state = reserve(seat, r-65, c, id_n);
			if (state == -1) {
				printf("�̹� ����� �ڸ��Դϴ�.\n\n");
			}
			else {
				printf("������ �Ϸ�Ǿ����ϴ�.\n");
				print_seats(seat);
			}
			break;

		case 3:
			printf("������Ҹ� ���ϴ� �ڸ��� (ex A2)? ");
			scanf("%c%d", &r, &c);
			state = cancel(seat, r-65, c, id_n);
			if (state == -1) {
				printf("������� ���� �ڸ��Դϴ�.\n\n");
			}
			else if (state == -2) {
				printf("������Ҹ� �� �� ���� �ڸ��Դϴ�.\n\n");
			}
			else {
				printf("������Ұ� �Ϸ�Ǿ����ϴ�.\n");
				print_seats(seat);
			}
			break;

		case 4:
			quit = 1;
			break;
		}
	}
	printf("�̿��� �ּż� �����մϴ�.\n");
	return 0;
}

int print_seats(int seat[SEAT_ROW][SEAT_COL]) {
	printf("  | 0 1 2 3 4\n");
	printf("-------------\n");
	for (int i = 0; i < SEAT_ROW; i++) {
		printf("%c | %d %d %d %d %d\n", 65+i, seat[i][0], seat[i][1], seat[i][2], seat[i][3], seat[i][4]);
	}
	return 0;
}

int reserve(int seat[SEAT_ROW][SEAT_COL], int row, int col, int id) {
	if (seat[row][col] != 0) { return -1; }
	seat[row][col] = id;
	return 0;
}

int cancel(int seat[SEAT_ROW][SEAT_COL], int row, int col, int id) {
	if (seat[row][col] == 0) {
		return -1;
	}
	else if (seat[row][col] != id) {
		return -2;
	}
	seat[row][col] = 0;
	return 0;
}

int load_usr_info(struct user_info user[]) {
	for (int i = 0; i < 10; i++) {
		user[i].id_num = (i+1) * 10;
		sprintf(user[i].id,"user%d",i);
		sprintf(user[i].password,"pass%d",i);
	}
	return 0;
}

int check_login(struct user_info user[],char id[], char pw[]) {
	int a_n = BSearch(user,id, 0, 9);
	if (a_n == -1) { return -1; }
	if (strcmp(user[a_n].password,pw) == 0) {
		return user[a_n].id_num;
	}
	return -2;
}

int BSearch(struct user_info user[],char id[], int low, int high) {
	if (low > high)
		return -1;

	int mid = (low + high) / 2;
	int state = strcmp(id, user[mid].id);
	if (state == 0) {
		return mid;
	} else if (state >0) {
		return BSearch(user, id, mid + 1, high);
	} else {
		return BSearch(user, id, low, mid - 1);
	}
}