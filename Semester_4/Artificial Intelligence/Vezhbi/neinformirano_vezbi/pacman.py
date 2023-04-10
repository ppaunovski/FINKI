import bisect
from searching_framework.uninformed_search import *
from searching_framework.utils import *


def move_north(pac_coord, new_orientation, edible):
    return (pac_coord[0], pac_coord[1]+1, 'sever'), edible


def move_south(pac_coord, new_orientation, edible):
    return (pac_coord[0], pac_coord[1]-1, 'jug'), edible


def move_east(pac_coord, new_orientation, edible):
    return (pac_coord[0]+1, pac_coord[1], 'istok'), edible


def move_west(pac_coord, new_orientation, edible):
    return (pac_coord[0]-1, pac_coord[1], 'zapad'), edible


def move_forward(pac_coord, edible):
    x = pac_coord[0]
    y = pac_coord[1]
    orientation = pac_coord[2]
    if orientation == 'istok':
        return move_east(pac_coord, 'istok', edible)
    if orientation == 'zapad':
        return move_west(pac_coord, 'zapad', edible)
    if orientation == 'sever':
        return move_north(pac_coord, 'sever', edible)
    if orientation == 'jug':
        return move_south(pac_coord, 'jug', edible)


def move_backwards(pac_coord, edible):
    orientation = pac_coord[2]
    if orientation == 'istok':
        return move_west(pac_coord, 'zapad', edible)
    if orientation == 'zapad':
        return move_east(pac_coord, 'istok', edible)
    if orientation == 'sever':
        return move_south(pac_coord, 'jug', edible)
    if orientation == 'jug':
        return move_north(pac_coord, 'sever', edible)


def turn_right(pac_coord, edible):
    orientation = pac_coord[2]
    if orientation == 'istok':
        return move_south(pac_coord, 'jug', edible)
    if orientation == 'zapad':
        return move_north(pac_coord, 'sever', edible)
    if orientation == 'sever':
        return move_east(pac_coord, 'istok', edible)
    if orientation == 'jug':
        return move_west(pac_coord, 'zapad', edible)


def turn_left(pac_coord, edible):
    orientation = pac_coord[2]
    if orientation == 'istok':
        return move_north(pac_coord, 'sever', edible)
    if orientation == 'zapad':
        return move_south(pac_coord, 'jug', edible)
    if orientation == 'sever':
        return move_west(pac_coord, 'zapad', edible)
    if orientation == 'jug':
        return move_east(pac_coord, 'istok', edible)


def check_valid(state, walls):
    pac = state[0]
    edibles = state[1]
    x = pac[0]
    y = pac[1]

    if x < 0 or x >= 10:
        return False
    if y < 0 or y >= 10:
        return False
    if (x, y) in walls:
        return False

    return True


def check_eat(state):
    pac = state[0]
    edibles = state[1]
    ls = list(edibles)
    x = pac[0]
    y = pac[1]

    if (x, y) in ls:
        ls.remove((x, y))

    return pac, tuple(ls)


class Pacman(Problem):
    def __init__(self, initial, walls):
        super().__init__(initial)
        self.walls = walls

    def successor(self, state):
        successors = dict()
        pac_coord = state[0]
        edible = state[1]

        # prodolzi pravo
        new_state = move_forward(pac_coord, edible)
        if check_valid(new_state, self.walls):
            new_state = check_eat(new_state)
            # print(new_state)
            successors["ProdolzhiPravo"] = new_state

        # prodolzi nazad
        new_state = move_backwards(pac_coord, edible)
        if check_valid(new_state, self.walls):
            new_state = check_eat(new_state)
            # print(new_state)
            successors["ProdolzhiNazad"] = new_state

        # svrti levo
        new_state = turn_left(pac_coord, edible)
        if check_valid(new_state, self.walls):
            new_state = check_eat(new_state)
            # print(new_state)
            successors["SvrtiLevo"] = new_state

        # svrti desno
        new_state = turn_right(pac_coord, edible)
        if check_valid(new_state, self.walls):
            new_state = check_eat(new_state)
            # print(new_state)
            successors["SvrtiDesno"] = new_state

        return successors

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        print(state[1])
        return len(state[1]) == 0


if __name__ == "__main__":
    x = int(input())
    y = int(input())
    orientation = str(input())

    n = int(input())
    dots = []
    for i in range(0, n):
        inp = input().split(',')
        dots.append((int(inp[0]), int(inp[1])))

    walls = (
        (0, 6), (1, 4), (1, 5), (1, 6), (1, 8),
        (2, 1), (2, 6), (3, 1), (4, 1), (4, 8),
        (4, 9), (5, 4), (6, 0), (6, 3), (6, 4),
        (6, 5), (7, 4), (7, 8), (7, 9), (8, 0),
        (8, 8), (8, 9), (9, 0), (9, 1), (9, 2),
        (9, 3), (9, 6)
    )
    coord = (x, y, orientation)
    pacman = Pacman((coord, tuple(dots)), walls)

    print(breadth_first_graph_search(pacman).solution())
