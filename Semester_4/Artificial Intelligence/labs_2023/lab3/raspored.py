from constraint import *


def raspredelba(*papers):
    #print(papers)
    if len(papers) <= 4:
        #print(papers)
        if papers.count('T1') == len(papers) or papers.count('T2') == len(papers) or papers.count('T3') == len(papers) \
                or papers.count('T4') == len(papers):
            #print(papers)
            return True
        else:
            return False
    else:
        if papers.count('T1') == len(papers) or papers.count('T2') == len(papers) or papers.count('T3') == len(papers) \
                or papers.count('T4') == len(papers):
            return False
    #print(papers)
    return True


def fer_raspredelba(*papers):
    if papers.count('T1') > 4 or papers.count('T2') > 4 or papers.count('T3') > 4 or papers.count('T4') > 4:
        return False
    #print(papers)
    return True


if __name__ == '__main__':
    num = int(input())

    papers = dict()

    paper_info = input()
    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        papers[title] = topic
        paper_info = input()

    # Tuka definirajte gi promenlivite
    variables = []
    for key in papers:
        variables.append((key, papers[key]))

    ai_var = []
    ml_var = []
    nlp_var = []
    for var in variables:
        if var[1] == 'AI':
            ai_var.append(var)
        if var[1] == 'ML':
            ml_var.append(var)
        if var[1] == 'NLP':
            nlp_var.append(var)

    # print(ai_var)
    # print(ml_var)
    # print(nlp_var)

    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())

    # Dokolku vi e potrebno moze da go promenite delot za dodavanje na promenlivite
    problem.addVariables(variables, domain)

    # Tuka dodadete gi ogranichuvanjata

    problem.addConstraint(raspredelba, [var for var in ai_var])
    problem.addConstraint(raspredelba, [var for var in ml_var])
    problem.addConstraint(raspredelba, [var for var in nlp_var])
    problem.addConstraint(fer_raspredelba, [var for var in variables])

    result = problem.getSolution()

    # Tuka dodadete go kodot za pechatenje
    print_arr = []
    p10_paper = ''
    p10_category = ''
    p10_appointment = ''
    for key in result:
        if key[0] != 'Paper10':
            print_arr.append(key[0] + " (" + key[1] + "): " + result[key])
        else:
            p10_paper = key[0]
            p10_category = key[1]
            p10_appointment = result[key]

    print_arr.append(p10_paper + " (" + p10_category + "): " + p10_appointment)

    for string in print_arr:
        print(string)