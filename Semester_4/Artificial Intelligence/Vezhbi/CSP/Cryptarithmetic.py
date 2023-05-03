from constraint import *


def sum_of_edinici(n1, m1, r):
    return (n1 + m1) % 10 == r


def sum_of_desetki(n1, m1, n2, m2, r2):
    return sum_of_edinici((n1+m1)//10 + n2, m2, r2)


def sum_of_stotki(n1, m1, n2, m2, n3, m3, r3):
    return sum_of_edinici(((n1 + m1) // 10 + n2 + m2) // 10 + n3, m3, r3)


def sum_of_iljdarki(n1, m1, n2, m2, n3, m3, n4, m4, r4):
    return sum_of_edinici((((n1 + m1) // 10 + n2 + m2) // 10 + n3+m3) // 10 + n4, m4, r4)


def valid(n4, m4, r5):
    return (n4+m4) // 10 == r5


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    variables = ["S", "E", "N", "D", "M", "O", "R", "Y"]
    for variable in variables:
        problem.addVariable(variable, Domain(set(range(10))))

    # ---Tuka dodadete gi ogranichuvanjata----------------

    num1 = ["S", "E", "N", "D"]
    num2 = ["M", "O", "R", "E"]
    res = ["M", "O", "N", "E", "Y"]
    # num1 + num2 = res

    problem.addConstraint(AllDifferentConstraint(), variables)
    problem.addConstraint(sum_of_edinici, ["D", "E", "Y"])
    problem.addConstraint(sum_of_desetki, ["D", "E", "N", "R", "E"])
    problem.addConstraint(sum_of_stotki, ["D", "E", "N", "R", "E", "O", "N"])
    problem.addConstraint(sum_of_iljdarki, ["D", "E", "N", "R", "E", "O", "S", "M", "O"])
    problem.addConstraint(valid, ["S", "M", "M"])

    # ----------------------------------------------------
    solution = problem.getSolution()
    print("{"+f"'D': {solution['D']}, 'E': {solution['E']}, 'M': {solution['M']}, 'N': {solution['N']}, "
              f"'O': {solution['O']}, 'R': {solution['R']}, 'S': {solution['S']}, 'Y': {solution['Y']}"+"}")
    # n1 = f' {solution["S"]}{solution["E"]}{solution["N"]}{solution["D"]}'
    # n2 = f' {solution["M"]}{solution["O"]}{solution["R"]}{solution["E"]}'
    # r = f'{solution["M"]}{solution["O"]}{solution["N"]}{solution["E"]}{solution["Y"]}'
    # print(n1)
    # print(n2)
    # print(r)


