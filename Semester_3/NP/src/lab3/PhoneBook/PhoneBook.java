package lab3.PhoneBook;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private List<Contact> contacts;
    private static int MAX_CONTACTS = 250;
    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) throws InvalidNameException, MaximumSizeExceddedException, InvalidNumberException {
        if(contacts.size() == MAX_CONTACTS) throw new MaximumSizeExceddedException();
        if(isNameTaken(contact.getName()) != null)    throw new InvalidNameException();
        //contacts = Arrays.copyOf(contacts, contacts.length+1);

        contacts.add(new Contact(contact.getName(), contact.getNumbers()));

    }

    private Contact isNameTaken(String name) {
        for(Contact c : contacts){
            if(c.name.equals(name))
                return c;
        }
        return null;
    }

    public Contact getContactForName(String name){
        return isNameTaken(name);
    }

    public int numberOfContacts(){
        return contacts.size();
    }

    public Contact[] getContacts(){
        List<Contact> sorted = contacts.stream()
                .sorted(Comparator.comparing(contact -> contact.getName()))
                .collect(Collectors.toList());
        return sorted.toArray(new Contact[0]);
    }

    public boolean removeContact(String name){
        if(isNameTaken(name) == null)
            return false;
        contacts.remove(isNameTaken(name));
        return true;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(Contact contact : contacts){
            returnString.append(contact.toString()).append("\n");
        }
        return returnString.toString();
    }

    public static boolean saveAsTextFile(PhoneBook phoneBook, String path){
        return true;
    }

    public static PhoneBook loadFromTextFile(String path) throws InvalidFormatException{
        return null;
    }

    public Contact[] getContactsForNumber(String number_prefix){
        ArrayList<Contact> contactsForNumber = (ArrayList<Contact>) contacts.stream()
                .filter(contact -> contact.hasNumberWithPrefix(number_prefix))
                .sorted(Comparator.comparing(contact -> contact.getName()))
                .collect(Collectors.toList());
        return contactsForNumber.toArray(new Contact[0]);
    }
}
