#include <stdio.h>

void hanoi_tower(int n, char from, char temp, char to)
{
    if (n == 1)
        printf("���� 1�� %c���� %c���� �ű��.\n", from, to);
    else {
        hanoi_tower(n - 1, from, to, temp);
        printf("���� %d�� %c���� %c���� �ű��.\n", n, from, to);
        hanoi_tower(n - 1, temp, from, to);
    }
}

void main(void)
{
    hanoi_tower(4, 'A', 'B', 'C');
}