#include <stdio.h>
#define SIZE 3

struct student {
	int number;
	char name[20];
	double grade;
};

struct student get_max_stu();

int main(void)
{
	struct student list[SIZE] = {
		   { 20180001, "ȫ�浿", 3.1 }, 
		   { 20180002, "��ö��", 3.2 }, 
		   { 20180003, "�迵��", 3.9 } 
	};
	struct student super_stu = get_max_stu(list);
	printf("������ ���� ���� �л��� (�̸�%s, �й�%d, ����%f)�Դϴ�\n",
		super_stu.name, super_stu.number, super_stu.grade);
}

struct student get_max_stu(struct student s_list[SIZE]) {
	struct student super_stu;
	int i;

	super_stu = s_list[0];

	for (i = 1; i < SIZE; i++) {
		if (s_list[i].grade > super_stu.grade)
			super_stu = s_list[i];
	}
	return super_stu;
}
