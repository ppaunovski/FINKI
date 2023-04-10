from constraint import *


def marija_available(marija, vreme):
    if marija == 1 and (vreme == 14 or vreme == 15 or vreme == 18):
        return True
    return False


def simona_available(simona, vreme):
    if simona == 1 and (vreme == 13 or vreme == 14 or vreme == 16 or vreme == 19):
        return True
    return False


def petar_available(petar, vreme):
    if petar == 1 and (vreme == 12 or vreme == 13 or vreme == 16 or vreme == 17 or vreme == 18 or vreme == 19):
        return True
    return False


def valid(petar, marija, simona, vreme):
    if simona == 0 or not simona_available(simona, vreme):
        return False
    elif petar == 1 and petar_available(petar, vreme):
        if marija == 1 and not marija_available(marija, vreme):
            return False
        return True
    elif marija == 1 and marija_available(marija, vreme):
        if petar == 1 and not petar_available(petar, vreme):
            return False
        return True
    return False


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # ---Dadeni se promenlivite, dodadete gi domenite-----
    problem.addVariable("Simona_prisustvo", [0, 1])
    problem.addVariable("Marija_prisustvo", [0, 1])
    problem.addVariable("Petar_prisustvo", [0, 1])
    problem.addVariable("vreme_sostanok", [i for i in range(12, 21)])

    # ----------------------------------------------------

    # ---Tuka dodadete gi ogranichuvanjata----------------

    # problem.addConstraint(marija_available, ["Marija_prisustvo", "vreme_sostanok"])
    # problem.addConstraint(simona_available, ["Simona_prisustvo", "vreme_sostanok"])
    # problem.addConstraint(petar_available, ["Petar_prisustvo", "vreme_sostanok"])
    problem.addConstraint(valid, ["Petar_prisustvo", "Marija_prisustvo", "Simona_prisustvo", "vreme_sostanok"])
    # ----------------------------------------------------

    #[print(solution) for solution in problem.getSolutions()]
    solutions = problem.getSolutions()
    for solution in solutions:
        print("{'Simona_prisustvo': " + str(solution["Simona_prisustvo"]) + ", 'Marija_prisustvo': "
              + str(solution["Marija_prisustvo"]) + ", 'Petar_prisustvo': " + str(solution["Petar_prisustvo"])
              + ", 'vreme_sostanok': " + str(solution["vreme_sostanok"]) + "}")
