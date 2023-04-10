from searching_framework.uninformed_search import *
from searching_framework.utils import *


# snake_pos -> (x, y, orientation of that body part)
# 0 - down
# 1 - up
# 2 - left
# 3 - right

def move_every_body_part(snake_pos, new_snake_pos):
    f = 0
    prev = list(snake_pos[0])
    for body_part in snake_pos:
        part = list(body_part)
        if f == 0:
            f = 1
            continue
        prev[1] = part[1]
        prev[0] = part[0]
        prev[2] = part[2]
        new_snake_pos.append(tuple(prev))
    # print(new_snake_pos)


# def check_move_forward(snake_head, green_app, red_app):
#     pass
#
#
# def move_forward(snake_pos, green_app, red_app):
#     snake_head = list(snake_pos[-1])
#     new_snake_pos = []
#
#     # move every body part in the direction of its predecessor
#     # applies for moving forward, left nad right
#     move_every_body_part(snake_pos, new_snake_pos)
#
#     check_move_forward(snake_head, green_app, red_app)
#
#     new_snake_pos.append(snake_head)
#
#
# def move_left(snake_pos, green_app, red_app):
#     pass
#
#
# def move_right(snake_pos, green_app, red_app):
#     pass


class Snake(Problem):

    def __init__(self, initial, goal=None):
        super().__init__(initial, goal)
        grid_size = [10, 10]

    def successor(self, state):
        successors = dict()

        # red apples
        red_app = state[2]

        # green apples
        green_app = list(state[1])
        # snake
        snake_pos = state[0]
        snake_head = snake_pos[-1]
        snake_tail = snake_pos[0]
        head_x = snake_head[0]
        head_y = snake_head[1]
        head_d = snake_head[2]

        # move_forward(snake_pos, green_app, red_app)
        # move_left(snake_pos, green_app, red_app)
        # move_right(snake_pos, green_app, red_app)

        # snake_pos -> (x, y, orientation of that body part)
        # 0 - down
        # 1 - up
        # 2 - left
        # 3 - right

        # move forward
        if head_d == 'down' and 0 <= head_x <= 9 and 0 <= head_y - 1 <= 9:  # x, y-1
            if (head_x, head_y - 1) not in red_app and ((head_x, head_y - 1, 'down') or (head_x, head_y - 1, 'up')
                                                        or (head_x, head_y - 1, 'left') or (
                                                                head_x, head_y - 1, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x, head_y - 1, 'down'))
                if (head_x, head_y - 1) in green_app:
                    new_green_app.remove((head_x, head_y - 1))
                    new_snake_pos[::-1].append(snake_tail)
                successors['ProdolzhiPravo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)

        if head_d == 'up' and 0 <= head_x <= 9 and 0 <= head_y + 1 <= 9:  # x, y+1
            if (head_x, head_y + 1) not in red_app and ((head_x, head_y + 1, 'down') or (head_x, head_y + 1, 'up')
                                                        or (head_x, head_y + 1, 'left') or (
                                                        head_x, head_y + 1, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x, head_y + 1, 'up'))
                if (head_x, head_y + 1) in green_app:
                    new_green_app.remove((head_x, head_y + 1))
                    new_snake_pos[::-1].append(snake_tail)
                successors['ProdolzhiPravo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'left' and 0 <= head_x - 1 <= 9 and 0 <= head_y <= 9:  # x-1, y
            if (head_x - 1, head_y) not in red_app and ((head_x - 1, head_y, 'down') or (head_x - 1, head_y, 'up')
                                                        or (head_x - 1, head_y, 'left') or (
                                                                head_x - 1, head_y, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x - 1, head_y, 'left'))
                if (head_x - 1, head_y) in green_app:
                    new_green_app.remove((head_x - 1, head_y))
                    new_snake_pos[::-1].append(snake_tail)
                successors['ProdolzhiPravo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'right' and 0 <= head_x + 1 <= 9 and 0 <= head_y <= 9:  # x+1, y
            if (head_x + 1, head_y) not in red_app and ((head_x + 1, head_y, 'down') or (head_x + 1, head_y, 'up')
                                                        or (head_x + 1, head_y, 'left') or (
                                                                head_x + 1, head_y, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x + 1, head_y, 'right'))
                if (head_x + 1, head_y) in green_app:
                    new_green_app.remove((head_x + 1, head_y))
                    new_snake_pos[::-1].append(snake_tail)
                successors['ProdolzhiPravo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)

        # move right
        if head_d == 'down' and 0 <= head_x - 1 <= 9 and 0 <= head_y <= 9:  # x-1, y
            if (head_x - 1, head_y) not in red_app and ((head_x - 1, head_y, 'down') or (head_x - 1, head_y, 'up')
                                                        or (head_x - 1, head_y, 'left') or (
                                                                head_x - 1, head_y, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x - 1, head_y, 'left'))
                if (head_x - 1, head_y) in green_app:
                    new_green_app.remove((head_x - 1, head_y))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiDesno'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'up' and 0 <= head_x + 1 <= 9 and 0 <= head_y <= 9:  # x+1, y
            if (head_x + 1, head_y) not in red_app and ((head_x + 1, head_y, 'down') or (head_x + 1, head_y, 'up')
                                                        or (head_x + 1, head_y, 'left') or (
                                                                head_x + 1, head_y, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x + 1, head_y, 'right'))
                if (head_x + 1, head_y) in green_app:
                    new_green_app.remove((head_x + 1, head_y))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiDesno'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'left' and 0 <= head_x <= 9 and 0 <= head_y + 1 <= 9:  # x, y+1
            if (head_x, head_y + 1) not in red_app and ((head_x, head_y + 1, 'down') or (head_x, head_y + 1, 'up')
                                                        or (head_x, head_y + 1, 'left') or (
                                                        head_x, head_y + 1, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x, head_y + 1, 'up'))
                if (head_x, head_y + 1) in green_app:
                    new_green_app.remove((head_x, head_y + 1))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiDesno'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'right' and 0 <= head_x <= 9 and 0 <= head_y - 1 <= 9:  # x, y-1
            if (head_x, head_y - 1) not in red_app and ((head_x, head_y - 1, 'down') or (head_x, head_y - 1, 'up')
                                                        or (head_x, head_y - 1, 'left') or (
                                                                head_x, head_y - 1, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x, head_y - 1, 'down'))
                if (head_x, head_y - 1) in green_app:
                    new_green_app.remove((head_x, head_y - 1))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiDesno'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)

        # move left
        if head_d == 'down' and 0 <= head_x + 1 <= 9 and 0 <= head_y <= 9:  # x+1, y
            if (head_x + 1, head_y) not in red_app and ((head_x + 1, head_y, 'down') or (head_x + 1, head_y, 'up')
                                                        or (head_x + 1, head_y, 'left') or (
                                                                head_x + 1, head_y, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x + 1, head_y, 'right'))
                if (head_x + 1, head_y) in green_app:
                    new_green_app.remove((head_x + 1, head_y))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiLevo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'up' and 0 <= head_x - 1 <= 9 and 0 <= head_y <= 9:  # x-1, y
            if (head_x - 1, head_y) not in red_app and ((head_x - 1, head_y, 'down') or (head_x - 1, head_y, 'up')
                                                        or (head_x - 1, head_y, 'left') or (
                                                                head_x - 1, head_y, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x - 1, head_y, 'left'))
                if (head_x - 1, head_y) in green_app:
                    new_green_app.remove((head_x - 1, head_y))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiLevo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'left' and 0 <= head_x <= 9 and 0 <= head_y - 1 <= 9:  # x, y-1
            if (head_x, head_y - 1) not in red_app and ((head_x, head_y - 1, 'down') or (head_x, head_y - 1, 'up')
                                                        or (head_x, head_y - 1, 'left') or (
                                                                head_x, head_y - 1, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x, head_y - 1, 'down'))
                if (head_x, head_y - 1) in green_app:
                    new_green_app.remove((head_x, head_y - 1))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiLevo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)
        if head_d == 'right' and 0 <= head_x <= 9 and 0 <= head_y + 1 <= 9:  # x, y+1
            if (head_x, head_y + 1) not in red_app and ((head_x, head_y + 1, 'down') or (head_x, head_y + 1, 'up')
                                                        or (head_x, head_y + 1, 'left') or (
                                                        head_x, head_y + 1, 'right')) not in snake_pos:
                new_snake_pos = []
                new_green_app = []
                for app in green_app:
                    new_green_app.append(app)
                move_every_body_part(snake_pos, new_snake_pos)
                new_snake_pos.append((head_x, head_y + 1, 'up'))
                if (head_x, head_y + 1) in green_app:
                    new_green_app.remove((head_x, head_y + 1))
                    new_snake_pos[::-1].append(snake_tail)
                successors['SvrtiLevo'] = (tuple(new_snake_pos), tuple(new_green_app), red_app)

        return successors

    def goal_test(self, state):
        return len(state[1]) == 0

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]


if __name__ == '__main__':
    green_apples = ((2, 3), (2, 7), (4, 3), (6, 9), (9, 5))
    # green_apples = ((2, 3), (4, 3))
    red_apples = ((3, 3), (4, 6), (6, 5), (6, 8))

    # snake_pos -> (x, y, orientation of that body part)
    # 0 - down
    # 1 - up
    # 2 - left
    # 3 - right

    snake_position = ((0, 9, 'down'), (0, 8, 'down'), (0, 7, 'down'))

    snake = Snake((snake_position, green_apples, red_apples))

    print(breadth_first_graph_search(snake).solution())
