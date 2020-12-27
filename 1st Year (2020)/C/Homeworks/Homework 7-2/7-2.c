#include  <stdio.h>
#include  <math.h>

int main(void)
{
    double  a, b, c, d, e;

    printf("계수 a, 계수 b, 계수 c를 차례대로 입력하시오:");
    scanf("%lf %lf %lf", &a, &b, &c);

    if (a == 0){
        printf("방정식의 근은 %f입니다. \n", -c / b);
    } else
    {
        d = b * b - 4.0 * a * c;//판별식
        if (d > 0){
            e = sqrt(d);
            printf("방정식의 근은 %f입니다.\n", (-b + e) / (2.0 * a));
            printf("방정식의 근은 %f입니다.\n", (-b - e) / (2.0 * a));
        } else
        {
            if (d == 0){
                printf("방정식의 근은 %f입니다.\n", (-b) / (2.0 * a));
            } else
            {
                printf("실근이 존재하지 않는다.\n");
            }
        }
    }
    return  0;
}