from constraint import *


def not_attacking(queen1, queen2):
    if queen1[0] == queen2[0]:
        return False
    if queen1[1] == queen2[1]:
        return False
    if abs((queen2[1] - queen1[1]) / (queen2[0] - queen1[0])) == 1:
        return False
    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    n = int(input())
    queens = [queen for queen in range(1, n+1)]
    domain = [(x, y) for x in range(n) for y in range(n)]
    problem.addVariables(queens, domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------

    for queen in queens:
        for queen1 in queens:
            if queen1 < queen:
                problem.addConstraint(not_attacking, [queen, queen1])

    # ----------------------------------------------------

    if n < 7:
        print(len(problem.getSolutions()))
    else:
        print(problem.getSolution())