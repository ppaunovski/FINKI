package Kolokviumski2.FootballTable;

import java.util.*;
import java.util.stream.Collectors;


class Match{
    int homeGoals;
    int awayGoals;
    Team homeTeam;
    Team awayTeam;

    public Match(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;

        this.homeTeam.addGoalsConceded(awayGoals);
        this.awayTeam.addGoalsConceded(homeGoals);

        this.awayTeam.addGoalsScored(awayGoals);
        this.homeTeam.addGoalsScored(homeGoals);

        if(winner() != null){
            winner().addWin();
            loser().addLose();
        }
        else{
            this.homeTeam.addDraw();
            this.awayTeam.addDraw();
        }
    }

    public Team winner(){
        if(homeGoals > awayGoals)
            return homeTeam;
        if(awayGoals > homeGoals)
            return awayTeam;
        return null;
    }

    public Team loser(){
        if(homeGoals > awayGoals)
            return awayTeam;
        if(awayGoals > homeGoals)
            return homeTeam;
        return null;
    }
}
class Team {
    String name;
    int wins;
    int loses;
    int draws;
    int goalsScored;
    int goalsConceded;
    List<Match> matchesPlayed;

    public Team(String name) {
        this.name = name;
        wins = 0;
        loses = 0;
        draws = 0;
        goalsConceded = 0;
        goalsScored = 0;
        matchesPlayed = new ArrayList<>();
    }

    void addGoalsScored(int goals){
        this.goalsScored += goals;
    }

    void addGoalsConceded(int goals){
        this.goalsConceded += goals;
    }
    void addWin(){
        wins++;
    }
    void addLose(){
        loses++;
    }
    void addDraw(){
        draws++;
    }
    public void addMatch(Match match){
        matchesPlayed.add(match);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return wins == team.wins && loses == team.loses && draws == team.draws && goalsScored == team.goalsScored && goalsConceded == team.goalsConceded && Objects.equals(name, team.name) && Objects.equals(matchesPlayed, team.matchesPlayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wins, loses, draws, goalsScored, goalsConceded, matchesPlayed);
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d", name, matchesPlayed.size(), wins, draws, loses, points());
    }

    public Integer points() {
        return wins*3 + draws;
    }

    public Integer goalDiff(){
        return goalsScored - goalsConceded;
    }

    public String getName() {
        return name;
    }
}

public class FootballTable {
    Map<String, Team> teamsByName;

    public FootballTable() {
        teamsByName = new HashMap<>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals){
        Team home = new Team(homeTeam);
        Team away = new Team(awayTeam);
        teamsByName.putIfAbsent(homeTeam, home);
        teamsByName.putIfAbsent(awayTeam, away);

        Match match = new Match(teamsByName.get(homeTeam), teamsByName.get(awayTeam), homeGoals, awayGoals);

        teamsByName.get(homeTeam).addMatch(match);

        teamsByName.get(awayTeam).addMatch(match);
    }

    public void printTable(){
        Comparator<Team> comparator = Comparator.comparing(Team::points).thenComparing(Team::goalDiff).reversed().thenComparing(Team::getName);
        Map<Team, Integer> placeByTeam = new TreeMap<>(comparator);
        for(Team value  : teamsByName.values()){
            placeByTeam.putIfAbsent(value, 0);
        }
        //teamsByName.values().stream().sorted(comparator).collect(Collectors.toList());
        //placeByTeam.entrySet().stream().sequential().forEach(teamIntegerEntry -> System.out.println(String.format("%2d. %s", teamIntegerEntry.getValue(), teamIntegerEntry.getKey())));
        Iterator<Team> iterator = placeByTeam.keySet().iterator();
        for(int i=0; i<placeByTeam.size(); i++){
            System.out.println(String.format("%2d. %s", i+1, iterator.next()));
        }
    }
}
