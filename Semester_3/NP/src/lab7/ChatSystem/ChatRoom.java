package lab7.ChatSystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.stream.Collectors;

class NoSuchRoomException extends Exception{

    public NoSuchRoomException(String message) {
        super(String.format("The room %s does not exist", message));
    }
}
class NoSuchUserException extends Exception{
    public NoSuchUserException(String message) {
        super(String.format("The user %s does not exist", message));
    }
}

public class ChatRoom implements Comparable<ChatRoom> {
    String name;
    Set<String> users;

    public ChatRoom(String name) {
        this.name = name;
        users = new TreeSet<>();
    }

    void addUser(String username){
        users.add(username);
    }

    void removeUser(String username){
        users.remove(username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        int i=0;
        for(String user : users){
            sb.append(user).append("\n");

        }
        if(users.size() == 0){
            sb.append("EMPTY\n");
        }
        return sb.toString();
    }

    boolean hasUser(String username){
        return users.contains(username);
    }

    int numUsers(){
        return users.size();
    }

    @Override
    public int compareTo(ChatRoom o) {
        return name.compareTo(o.name);
    }
}

class ChatSystem {
    Set<String> allUsers;
    Map<String, ChatRoom> allRooms;
    Map<String, List<ChatRoom>> chatsPerUser;

    public ChatSystem() {
        allRooms = new TreeMap<>();
        allUsers = new HashSet<>();
        chatsPerUser = new HashMap<>();
    }

    void addRoom(String roomName){
        allRooms.putIfAbsent(roomName, new ChatRoom(roomName));
    }

    void removeRoom(String roomName){
        allRooms.remove(roomName);
    }

    ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if(allRooms.containsKey(roomName)){
            return allRooms.get(roomName);
        }

        throw new NoSuchRoomException(roomName);
    }

    void register(String userName){
        allUsers.add(userName);
        chatsPerUser.putIfAbsent(userName, new ArrayList<>());
        List<ChatRoom> sortedChatRooms = allRooms.values().stream()
                .sorted(Comparator
                        .comparing(ChatRoom::numUsers)
                        .thenComparing(Comparator.naturalOrder())
                )
                .collect(Collectors.toList());

        if(sortedChatRooms.size() > 0){
            sortedChatRooms.get(0).addUser(userName);
            chatsPerUser.get(userName).add(sortedChatRooms.get(0));
        }

    }

    void registerAndJoin(String userName, String roomName) throws NoSuchRoomException {
        allUsers.add(userName);
        chatsPerUser.putIfAbsent(userName, new ArrayList<>());
        joinRoom(userName, roomName);
    }

    void joinRoom(String userName, String roomName) throws NoSuchRoomException {
        if(!allRooms.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }

        allRooms.get(roomName).addUser(userName);
        chatsPerUser.get(userName).add(allRooms.get(roomName));
    }

    void leaveRoom(String username, String roomName) throws NoSuchRoomException {
        if(!allRooms.containsKey(roomName)){
            throw new NoSuchRoomException(roomName);
        }

        allRooms.get(roomName).removeUser(username);
        chatsPerUser.get(username).remove(allRooms.get(roomName));
    }

    void followFriend(String username, String friend_username) throws NoSuchUserException {
        if(!allUsers.contains(username)){
            throw new NoSuchUserException(username);
        }
        chatsPerUser.get(username).addAll(chatsPerUser.get(friend_username));
        chatsPerUser.get(friend_username).stream()
                .forEach(chatRoom -> chatRoom.addUser(username));
    }
}

 class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    try {
                        System.out.println(cs.getRoom(jin.next())+"\n");
                    }
                    catch (NoSuchRoomException e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs,params);
                    }
                }
            }
        }
    }

}
