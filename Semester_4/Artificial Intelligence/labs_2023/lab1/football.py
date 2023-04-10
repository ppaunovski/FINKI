from searching_framework.uninformed_search import *
from searching_framework.utils import *

class Football(Problem):
    def __init__(self, initial, goal=None):
        super().__init__(initial, goal)
        grid_size = [8, 6]

    def successor(self, state):
        successors = dict()
        # state = ( player, ball )
        # enemies = obstacles

        player1 = state[0]
        ball1 = state[1]
        enemies = state[2]

        player_x = player1[0]
        player_y = player1[1]

        ball_x = ball1[0]
        ball_y = ball1[1]

        enemy1 = enemies[0]
        enemy2 = enemies[1]

        obstacle1_x = enemy1[0]
        obstacle1_y = enemy1[1]

        obstacle2_x = enemy2[0]
        obstacle2_y = enemy2[1]

        # valid moves for player:
        # up down right up-right down-right

        # unavailable positions for the ball:
        # obstacle_x + 1, obstacle_y
        # obstacle_x + 1, obstacle_y + 1
        # obstacle_x + 1, obstacle_y - 1
        # obstacle_x - 1, obstacle_y
        # obstacle_x - 1, obstacle_y + 1
        # obstacle_x - 1, obstacle_y - 1
        # obstacle_x, obstacle_y
        # obstacle_x, obstacle_y + 1
        # obstacle_x, obstacle_y - 1

        # unavailable positions for the player:
        # ball_x, ball_y
        # obstacle_x, obstacle_y

        # if the ball is on the opposite pos from
        # the player the ball moves one pos in the
        # same direction as the player

        # check if every move is available for the
        # player, and if it is available for him
        # check if that would cause an unavailable
        # pos for the ball

        unavailable_spots_for_ball = [
            (obstacle1_x + 1, obstacle1_y),
            (obstacle1_x + 1, obstacle1_y + 1),
            (obstacle1_x + 1, obstacle1_y - 1),
            (obstacle1_x - 1, obstacle1_y),
            (obstacle1_x - 1, obstacle1_y + 1),
            (obstacle1_x - 1, obstacle1_y - 1),
            (obstacle1_x, obstacle1_y),
            (obstacle1_x, obstacle1_y + 1),
            (obstacle1_x, obstacle1_y - 1),
            (obstacle2_x + 1, obstacle2_y),
            (obstacle2_x + 1, obstacle2_y + 1),
            (obstacle2_x + 1, obstacle2_y - 1),
            (obstacle2_x - 1, obstacle2_y),
            (obstacle2_x - 1, obstacle2_y + 1),
            (obstacle2_x - 1, obstacle2_y - 1),
            (obstacle2_x, obstacle2_y),
            (obstacle2_x, obstacle2_y + 1),
            (obstacle2_x, obstacle2_y - 1)
        ]

        # move up -> player_x, player_y + 1
        if 0 <= player_x <= 7 and 0 <= player_y + 1 <= 5:
            if player_x == ball_x and player_y + 1 == ball_y:
                if 0 <= ball_x <= 7 and 0 <= ball_y + 1 <= 5 and (ball_x, ball_y + 1) not in unavailable_spots_for_ball:
                    # the ball should move up
                    successors['Gore'] = ((player_x, player_y + 1), (ball_x, ball_y + 1), enemies)
            else:
                if (player_x, player_y + 1) != enemy1 and (player_x, player_y + 1) != enemy2:
                    successors['Gore'] = ((player_x, player_y + 1), (ball_x, ball_y), enemies)
        # move down
        if 0 <= player_x <= 7 and 0 <= player_y - 1 <= 5:
            if player_x == ball_x and player_y - 1 == ball_y:
                if 0 <= ball_x <= 7 and 0 <= ball_y - 1 <= 5 and (ball_x, ball_y - 1) not in unavailable_spots_for_ball:
                    # the ball should move up
                    successors['Dolu'] = ((player_x, player_y - 1), (ball_x, ball_y - 1), enemies)
            else:
                if (player_x, player_y - 1) != enemy1 and (player_x, player_y - 1) != enemy2:
                    successors['Dolu'] = ((player_x, player_y - 1), (ball_x, ball_y), enemies)
        # move right
        if 0 <= player_x + 1 <= 7 and 0 <= player_y <= 5:
            if player_x + 1 == ball_x and player_y == ball_y:
                if 0 <= ball_x + 1 <= 7 and 0 <= ball_y <= 5 and (ball_x + 1, ball_y) not in unavailable_spots_for_ball:
                    # the ball should move up
                    successors['Desno'] = ((player_x + 1, player_y), (ball_x + 1, ball_y), enemies)
            else:
                if (player_x + 1, player_y) != enemy1 and (player_x + 1, player_y) != enemy2:
                    successors['Desno'] = ((player_x + 1, player_y), (ball_x, ball_y), enemies)
        # move up-right
        if 0 <= player_x + 1 <= 7 and 0 <= player_y + 1 <= 5:
            if player_x + 1 == ball_x and player_y + 1 == ball_y:
                if 0 <= ball_x + 1 <= 7 and 0 <= ball_y + 1 <= 5 and (ball_x + 1, ball_y + 1) not in unavailable_spots_for_ball:
                    # the ball should move up
                    successors['Gore-desno'] = ((player_x + 1, player_y + 1), (ball_x + 1, ball_y + 1), enemies)
            else:
                if (player_x + 1, player_y + 1) != enemy1 and (player_x + 1, player_y + 1) != enemy2:
                    successors['Gore-desno'] = ((player_x + 1, player_y + 1), (ball_x, ball_y), enemies)
        # move down-right
        if 0 <= player_x + 1 <= 7 and 0 <= player_y - 1 <= 5:
            if player_x + 1 == ball_x and player_y - 1 == ball_y:
                if 0 <= ball_x + 1 <= 7 and 0 <= ball_y - 1 <= 5 and (ball_x + 1, ball_y - 1) not in unavailable_spots_for_ball:
                    # the ball should move up
                    successors['Dolu-desno'] = ((player_x + 1, player_y - 1), (ball_x + 1, ball_y - 1), enemies)
            else:
                if (player_x + 1, player_y - 1) != enemy1 and (player_x + 1, player_y - 1) != enemy2:
                    successors['Dolu-desno'] = ((player_x + 1, player_y - 1), (ball_x, ball_y), enemies)
        return successors

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        return state[1] in self.goal


if __name__ == '__main__':
    ball = (1, 2)
    player = (0, 1)
    obstacles = ((3, 3), (5, 4))

    football = Football((player, ball, obstacles), ((7, 2), (7, 3)))
    print(breadth_first_graph_search(football).solution())
