#include  <stdio.h>
#include  <math.h>

int main(void)
{
    double  a, b, c, d, e;

    printf("��� a, ��� b, ��� c�� ���ʴ�� �Է��Ͻÿ�:");
    scanf("%lf %lf %lf", &a, &b, &c);

    if (a == 0){
        printf("�������� ���� %f�Դϴ�. \n", -c / b);
    } else
    {
        d = b * b - 4.0 * a * c;//�Ǻ���
        if (d > 0){
            e = sqrt(d);
            printf("�������� ���� %f�Դϴ�.\n", (-b + e) / (2.0 * a));
            printf("�������� ���� %f�Դϴ�.\n", (-b - e) / (2.0 * a));
        } else
        {
            if (d == 0){
                printf("�������� ���� %f�Դϴ�.\n", (-b) / (2.0 * a));
            } else
            {
                printf("�Ǳ��� �������� �ʴ´�.\n");
            }
        }
    }
    return  0;
}