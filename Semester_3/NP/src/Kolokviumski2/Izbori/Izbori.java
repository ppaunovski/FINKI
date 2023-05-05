package Kolokviumski2.Izbori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Subscriber{
    void updateVotes(int unit, String pollId, String party, int votes, int totalVotersPerPoll, int totalVotersPerUnit);

}

class VotersTurnoutApp implements Subscriber{
    //Izlezenost po izborna edinica, glasale, vkupno, izlezenost vo %

    Map<Integer, Integer> castedVotesPerUnit;
    Map<Integer, Integer> registeredVotesPerUnit;

    public VotersTurnoutApp() {
        castedVotesPerUnit = new HashMap<>();
        registeredVotesPerUnit = new HashMap<>();
    }

    @Override
    public void updateVotes(int unit, String pollId, String party, int votes, int totalVotersPerPoll, int totalVotersPerUnit) {
        registeredVotesPerUnit.put(unit, totalVotersPerUnit);
        castedVotesPerUnit.putIfAbsent(unit, 0);

        castedVotesPerUnit.computeIfPresent(unit, (k, v) -> {v+=votes;return v;});
    }

    void printStatistics(){
        System.out.println("Unit: Casted: Voters: Turnout:");
        castedVotesPerUnit.keySet().forEach(unit -> System.out.println(String.format("%10d%7d%7d%7.2f%%", unit, castedVotesPerUnit.get(unit), registeredVotesPerUnit.get(unit), (double)castedVotesPerUnit.get(unit) / registeredVotesPerUnit.get(unit) *100 )));
    }
}

class SeatsApp implements Subscriber{

    @Override
    public void updateVotes(int unit, String pollId, String party, int votes, int totalVotersPerPoll, int totalVotersPerUnit) {

    }
}

class ElectionUnit{
    int unit;
    Map<String, Integer> registeredVotesByPoll;
    List<Subscriber> subscribers;

    ElectionUnit(int unit, Map<String, Integer> votesPerPoll){
        this.unit = unit;
        this.registeredVotesByPoll = votesPerPoll;
        subscribers = new ArrayList<>();
    }
    void addVotes(String pollId, String party, int votes){
        subscribers.forEach(subscriber -> subscriber.updateVotes(
                unit,
                pollId,
                party,
                votes,
                registeredVotesByPoll.get(pollId),
                totalVoterPerUnit()
        ));

    }

    private int totalVoterPerUnit() {
        return registeredVotesByPoll.values().stream().mapToInt(i -> i).sum();
    }

    void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    void unsubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
    }
}

class VotesController{
    List<String> parties;
    Map<String, Integer> unitPerPoll;
    Map<Integer, ElectionUnit> electionUnitMap;
    VotesController(List<String> parties, Map<String, Integer> unitPerPoll){
        this.parties = parties;
        this.unitPerPoll = unitPerPoll;
        electionUnitMap = new HashMap<>();
    }

    void addElectionUnit(ElectionUnit electionUnit){
        electionUnitMap.put(electionUnit.unit, electionUnit);
    }

    void addVotes(String pollId, String party, int votes){
        electionUnitMap.get(unitPerPoll.get(pollId)).addVotes(pollId, party, votes);
    }
}

public class Izbori {
}
