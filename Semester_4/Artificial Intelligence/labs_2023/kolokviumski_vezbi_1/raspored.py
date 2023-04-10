from constraint import *

days = ['Mon_', 'Tue_', 'Wed_', 'Thu_', 'Fri_']


def different_hour(*ml_variables):
    d = {}
    for var in ml_variables:
        parts = str(var).split('_')
        if d.get(parts[1]):
            return False
        else:
            d[parts[1]] = True
    return True


def time_duration(*variables):

    dictionary = {}
    for var in variables:
        parts = str(var).split('_')
        dictionary[var] = True

    for var in variables:
        parts = str(var).split('_')
        if dictionary.get(parts[0] + '_' + str(int(parts[1])+1)):
            return False
    # for i in range(8, 21):
    #     for day in days:
    #         if day + str(i) in variables and day + str(i + 1) in variables:
    #             return False
    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    casovi_AI = input()
    casovi_ML = input()
    casovi_R = input()
    casovi_BI = input()

    AI_predavanja_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_predavanja_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_predavanja_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                           "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_predavanja_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_vezbi_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_vezbi_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_vezbi_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    variables = []
    variables.extend(f'AI_cas_{i}' for i in range(1, int(casovi_AI)+1))
    variables.extend(f'R_cas_{i}' for i in range(1, int(casovi_R)+1))
    variables.extend(f'BI_cas_{i}' for i in range(1, int(casovi_BI)+1))
    variables.extend(f'ML_cas_{i}' for i in range(1, int(casovi_ML)+1))
    variables.append('ML_vezbi')
    variables.append('AI_vezbi')
    variables.append('BI_vezbi')

    # ---Tuka dodadete gi promenlivite--------------------
    problem.addVariable('ML_vezbi', ML_vezbi_domain)
    problem.addVariables([f'ML_cas_{i}' for i in range(1, int(casovi_ML)+1)], ML_predavanja_domain)

    problem.addVariable('BI_vezbi', BI_vezbi_domain)
    problem.addVariables([f'BI_cas_{i}' for i in range(1, int(casovi_BI)+1)], BI_predavanja_domain)

    problem.addVariable('AI_vezbi', AI_vezbi_domain)
    problem.addVariables([f'AI_cas_{i}' for i in range(1, int(casovi_AI)+1)], AI_predavanja_domain)

    problem.addVariables([f'R_cas_{i}' for i in range(1, int(casovi_R)+1)], R_predavanja_domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------
    ml_lec = [f'ML_cas_{i}' for i in range(1, int(casovi_ML)+1)]
    ml_lec.append('ML_vezbi')

    problem.addConstraint(AllDifferentConstraint())
    problem.addConstraint(different_hour, ml_lec)
    problem.addConstraint(time_duration, variables)

    # ----------------------------------------------------
    solution = problem.getSolution()

    # for key in solution:
    #     if not str(solution[key]).find('Mon'):
    #         print(f'{key} - {solution[key]}')
    #
    # for key in solution:
    #     if not str(solution[key]).find('Tue'):
    #         print(f'{key} - {solution[key]}')
    #
    # for key in solution:
    #     if not str(solution[key]).find('Wed'):
    #         print(f'{key} - {solution[key]}')
    #
    # for key in solution:
    #     if not str(solution[key]).find('Thu'):
    #         print(f'{key} - {solution[key]}')
    #
    # for key in solution:
    #     if not str(solution[key]).find('Fri'):
    #         print(f'{key} - {solution[key]}')

    print(solution)
    # flag = True
    # finki_solution = {'ML_vezbi': 'Thu_14', 'ML_cas_1': 'Fri_15', 'BI_vezbi': 'Thu_11', 'AI_vezbi': 'Tue_13', 'AI_cas_1': 'Fri_12', 'BI_cas_1': 'Fri_10', 'R_cas_1': 'Wed_15'}
    # for key in solution:
    #     if solution[key] != finki_solution[key]:
    #         flag = False
    #         print(f'{key} : {solution[key]} != {key} : {finki_solution[key]}')
    #
    # if flag:
    #     print('TOCNO')
