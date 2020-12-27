#선교사와 식인종 문제 using DFS Algorithm
#강의 왼쪽에 선교사 3명과 식인종 3명이 있고 모두를 강 오른쪽으로 건너게끔 해야한다.
#조건 1 : 강의 왼쪽이나 오른쪽에서 선교사의 수보다 식인종의 수가 많으면 안된다.
#조건 2 : 보트에는 최대 2명이 탑승할 수 있으며 최소1명이 탑승해야 움직일 수 있다.

class State():
    def __init__(self, cannibalLeft, missionaryLeft, boat, cannibalRight, missionaryRight, action):
        self.cannibalLeft = cannibalLeft
        self.missionaryLeft = missionaryLeft
        self.boat = boat
        self.cannibalRight = cannibalRight
        self.missionaryRight = missionaryRight
        self.action = action
        self.parent = None

    def is_goal(self):
        if self.cannibalLeft == 0 and self.missionaryLeft == 0:
            return True
        else:
            return False

    def is_valid(self):
        if self.missionaryLeft >= 0 and self.missionaryRight >= 0 \
                and self.cannibalLeft >= 0 and self.cannibalRight >= 0 \
                and (self.missionaryLeft == 0 or self.missionaryLeft >= self.cannibalLeft) \
                and (self.missionaryRight == 0 or self.missionaryRight >= self.cannibalRight):
            return True
        else:
            return False


def successors(cur_state):
    children = list()
    if cur_state.boat == 'left': #보트위치가 왼쪽일때
        # send two missionaries from left to right
        new_state = State(cur_state.cannibalLeft, cur_state.missionaryLeft - 2, 'right',
                          cur_state.cannibalRight, cur_state.missionaryRight + 2,
                          "왼쪽에서 오른쪽으로 두명의 선교사를 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send two cannibals from left to right
        new_state = State(cur_state.cannibalLeft - 2, cur_state.missionaryLeft, 'right',
                          cur_state.cannibalRight + 2, cur_state.missionaryRight,
                          "왼쪽에서 오른쪽으로 두명의 식인종을 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send one missionary and one cannibal from left to right
        new_state = State(cur_state.cannibalLeft - 1, cur_state.missionaryLeft - 1, 'right',
                          cur_state.cannibalRight + 1, cur_state.missionaryRight + 1,
                          "왼쪽에서 오른쪽으로 선교사와 식인종을 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send one missionary from left to right
        new_state = State(cur_state.cannibalLeft, cur_state.missionaryLeft - 1, 'right',
                          cur_state.cannibalRight, cur_state.missionaryRight + 1,
                          "왼쪽에서 오른쪽으로 한명의 선교사를 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send one cannibal from left to right
        new_state = State(cur_state.cannibalLeft - 1, cur_state.missionaryLeft, 'right',
                          cur_state.cannibalRight + 1, cur_state.missionaryRight,
                          "왼쪽에서 오른족으로 한명의 식인종을 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)
    else: #보트위치가 오른쪽일때
        # send two missionaries from right to left
        new_state = State(cur_state.cannibalLeft, cur_state.missionaryLeft + 2, 'left',
                          cur_state.cannibalRight, cur_state.missionaryRight - 2,
                          "오른쪽에서 왼쪽으로 두명의 선교사를 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send two cannibals from right to left
        new_state = State(cur_state.cannibalLeft + 2, cur_state.missionaryLeft, 'left',
                          cur_state.cannibalRight - 2, cur_state.missionaryRight,
                          "오른쪽에서 왼쪽으로 두명의 식인종을 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send one missionary and one cannibal from right to left
        new_state = State(cur_state.cannibalLeft + 1, cur_state.missionaryLeft + 1, 'left',
                          cur_state.cannibalRight - 1, cur_state.missionaryRight - 1,
                          "오른쪽에서 왼쪽으로 선교사와 식인종을 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send one missionary from right to left
        new_state = State(cur_state.cannibalLeft, cur_state.missionaryLeft + 1, 'left',
                          cur_state.cannibalRight, cur_state.missionaryRight - 1,
                          "오른쪽에서 왼쪽으로 한명의 선교사를 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

        # send one cannibal from right to left
        new_state = State(cur_state.cannibalLeft + 1, cur_state.missionaryLeft, 'left',
                          cur_state.cannibalRight - 1, cur_state.missionaryRight,
                          "오른쪽에서 왼쪽으로 한명의 식인종을 보냄")
        if new_state.is_valid():
            new_state.parent = cur_state
            children.append(new_state)

    return children


def depth_limited_search(node, depth):
    if node.is_goal():
        return node
    elif depth == 0:
        return None
    else:
        children = successors(node)
        for child in children:
            result = depth_limited_search(child, depth - 1)
            if result:
                return result
        return None


def iterative_deepening_depth_first_search(root, max_depth):
    for i in range(max_depth):
        return depth_limited_search(root, max_depth)


def print_solution(solution):
    path = list()
    path.append(solution)
    parent = solution.parent

    while parent:
        path.append(parent)
        parent = parent.parent

    for i in range(1, len(path)):
        state = path[len(path) - i - 1]
        print ( str(i)+" 번째 움직임" + ": " + state.action)



def main():
    initial_state = State(3, 3, 'left', 0, 0, "Initialize") #초기화
    solution = iterative_deepening_depth_first_search(initial_state, 11)#DFS 알고리즘을 통한 문제 해결
    print_solution(solution)


# if called from the command line, call main()
if __name__ == "__main__":
    main()