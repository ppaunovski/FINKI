from constraint import *

if __name__ == '__main__':
    solver = input()
    problem = Problem(eval(solver + '()'))
    variables = [i for i in range(81)]
    domain = [i for i in range(1, 10)]

    for variable in variables:
        problem.addVariable(variable, domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------

    for i in range(9):
        problem.addConstraint(AllDifferentConstraint(), [j + 9*i for j in range(9)])
        problem.addConstraint(AllDifferentConstraint(), [i + 9*j for j in range(9)])

    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(3) for j in range(3)])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(3) for j in range(3, 6) if i < 3])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(3) for j in range(6, 9) if i < 3])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(3, 6) for j in range(3) if 3 <= i < 6])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(3, 6) for j in range(3, 6) if 3 <= i < 6])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(3, 6) for j in range(6, 9) if 3 <= i < 6])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(6, 9) for j in range(3) if 6 <= i < 9])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(6, 9) for j in range(3, 6) if 6 <= i < 9])
    problem.addConstraint(AllDifferentConstraint(), [9*i + j for i in range(6, 9) for j in range(6, 9) if 6 <= i < 9])

    # ----------------------------------------------------

    print(problem.getSolution())
