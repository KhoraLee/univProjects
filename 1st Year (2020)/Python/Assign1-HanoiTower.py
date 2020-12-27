def hanoi_tower(n, _from, _temp, _to):
    if (n == 1) :
        print("원판 1을 {}에서 {}으로 옮긴다.\n".format(_from,_to))
        return

    hanoi_tower(n - 1, _from, _to, _temp)
    print("원판 {}을 {}에서 {}으로 옮긴다.\n".format(n,_from,_to))
    hanoi_tower(n - 1, _temp, _from, _to)

if __name__ == "__main__":
    TrayNum = int(input("원판의 개수를 입력하세요 :"))
    hanoi_tower(TrayNum,'A','B','C')
    print("문제 해결")