package lab8.MP3Player;

import java.util.ArrayList;
import java.util.List;

class Song{
    String title;
    String artist;
    boolean isStopped;

    public Song(String title, String singer) {
        this.title = title;
        this.artist = singer;
        isStopped = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title=" + title  +
                ", artist=" + artist  +
                '}';
    }
}

class MP3Player{
    List<Song> listSongs;
    int currentSong;

    public MP3Player(List<Song> listSongs) {
        this.listSongs = listSongs;
        currentSong = 0;
    }

    public void pressPlay() {
        if(!listSongs.get(currentSong).isStopped()){
            System.out.println("Song is already playing");
            return;
        }
        System.out.println("Song " + currentSong +" is playing");
        listSongs.get(currentSong).setStopped(false);
    }

    public void pressStop() {
        if(listSongs.get(currentSong).isStopped()){
            System.out.println("Songs are stopped");
            currentSong = 0;
            return;
        }
        System.out.println("Song " + currentSong +" is paused");
        listSongs.get(currentSong).setStopped(true);
    }

    public void pressFWD() {
        listSongs.get(currentSong).setStopped(true);
        currentSong++;
        if(currentSong >= listSongs.size())
            currentSong = 0;

        listSongs.get(currentSong).setStopped(false);
        System.out.println("Forward...");
    }

    public void pressREW() {
        listSongs.get(currentSong).setStopped(true);
        currentSong--;
        if(currentSong < 0)
            currentSong = listSongs.size()-1;

        listSongs.get(currentSong).setStopped(false);
        System.out.println("Reward...");
    }

    public void printCurrentSong() {
        System.out.println(listSongs.get(currentSong));
    }

    @Override
    public String toString() {
        return "MP3Player{" +
                "currentSong = " + currentSong +
                ", songList = " + listSongs +
                '}';
    }
}

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

