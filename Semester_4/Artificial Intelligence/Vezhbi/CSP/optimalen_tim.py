from constraint import *

global_max = 0


def max_sum(*variables):
    sum = 0
    for var in variables:
        sum += var[0]

    return sum <= 100.0


def maximum(solutions):
    max_elem = solutions[0]
    max = 0
    sum = 0
    for solution in solutions:
        sum = 0
        for elem in solution:
            sum += solution[elem][0]
        if sum > max:
            max = sum
            max_elem = solution
    return max_elem


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    # 1 team leader
    # 5 members
    num_members = int(input())
    members = []
    for i in range(num_members):
        line = input().split(" ")
        #print(line)
        members.append((float(line[0]), str(line[1])))

    num_leaders = int(input())
    leaders = []
    for i in range(num_leaders):
        line = input().split(" ")
        leaders.append((float(line[0]), str(line[1])))

    domain = members + leaders
    team_leader = ["Team leader"]
    participants = [f'Participant {i}' for i in range(1, 6)]
    variables =  team_leader + participants

    problem.addVariables(variables, domain)

    problem.addConstraint(max_sum, variables)
    problem.addConstraint(InSetConstraint(leaders), team_leader)
    problem.addConstraint(InSetConstraint(members), participants)
    problem.addConstraint(AllDifferentConstraint(), variables)
    # problem.addConstraint(maximum, variables)
    solution = maximum(problem.getSolutions())
    output = ''
    total = 0
    for key in solution:
        total += solution[key][0]
        output += f'{key}: {solution[key][1]}\n'

    print(f'Total score: {total}')
    print(output)
