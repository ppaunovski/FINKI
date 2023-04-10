# from searching_framework.uninformed_search import *
# from searching_framework.utils import *
#
#
# class Snake(Problem):
#
#     def __init__(self, initial, goal=None):
#         super().__init__(initial, goal)
#         grid_size = [10, 10]
#
#     def successor(self, state):
#         successors = dict()
#
#         # snake
#         snake_head = state[0][-1]
#         snake_tail = state[0][0]
#         print(snake_head)
#         print(snake_tail)
#         return successors
#
#     def actions(self, state):
#         return self.successor(state).keys()
#
#     def result(self, state, action):
#         return self.successor(state)[action]
#
#     def goal_test(self, state):
#         pass
#
#     def path_cost(self, c, state1, action, state2):
#         return c + 1
#
#     def value(self):
#         raise NotImplementedError
#
#
# if __name__ == '__main__':
#     # num_green = int(input())
#     # green_apples = list()
#
#     # for j in range(num_green):
#     #     green_apples.append([int(i) for i in input().split(",")])
#     #
#     # for i in range(num_green):
#     #     green_apples[i] = tuple(green_apples[i])
#     green_apples = [(2, 3), (2, 7), (4, 3), (6, 9), (9, 5)]
#     red_apples = [(3, 3), (4, 6), (6, 5), (6, 8)]
#     # snake_pos -> (x, y, orientation of that body part)
#     # 0 - down
#     # 1 - up
#     # 2 - left
#     snake_position = ((0, 9, 0), (0, 8, 0), (0, 7, 0))
#     # 3 - right
#
#     snake = Snake((snake_position, green_apples, red_apples))
#
#     print(breadth_first_graph_search(snake).solution())
