package lab2.Contact;

import java.util.Arrays;

public class Student {
    private Contact []contacts;
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;
    private int size;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        contacts = new Contact[50];
        this.size = 0;
    }

    private void resize(){
        Arrays.copyOf(contacts, size*2);
    }

    public void addEmailContact(String date, String email){
        if(size == contacts.length){
            resize();
        }
        contacts[size++] = new EmailContact(date, email);
    }

    public void addPhoneContact(String date, String phone){
        if(size == contacts.length){
            resize();
        }
        contacts[size++] = new PhoneContact(date, phone);
    }

    public Contact[] getEmailContacts(){
        Contact []emailContacts = new EmailContact[0];

        int index = 0;
        for(int i=0; i<size; i++){
            if(contacts[i].getType().equals("Email")){
                emailContacts = Arrays.copyOf(emailContacts, emailContacts.length + 1);
                emailContacts[index++] = new EmailContact((EmailContact) contacts[i]);
            }
        }
        return (index == 0 ? null : emailContacts);
    }

    public Contact[] getPhoneContacts(){
        Contact []phoneContacts = new PhoneContact[0];
        int index = 0;
        for(int i=0; i<size; i++){
            if(contacts[i].getType().equals("Phone")){
                phoneContacts = Arrays.copyOf(phoneContacts, phoneContacts.length + 1);
                phoneContacts[index++] = new PhoneContact((PhoneContact) contacts[i]);
            }
        }
        return (index == 0 ? null : phoneContacts);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getCity() {
        return city;
    }

    public long getIndex() {
        return index;
    }

    public Contact getLatestContact(){
        Contact latestContact = contacts[0];
        for(int i=0; i<size; i++){
            if(!latestContact.isNewerThan(contacts[i])){
                latestContact = contacts[i];
            }
        }

        return latestContact;
    }

    @Override
    public String toString() {
        StringBuilder phoneContactsString = new StringBuilder();
        StringBuilder emailContactsString = new StringBuilder();

//        boolean f = true;
//        for(int i=0; i<size; i++){
//            if(f){
//                f=false;
//                if(contacts[i].getType().equals("Email")){
//                    emailContactsString.append(contacts[i].toString());
//                }
//                if(contacts[i].getType().equals("Phone")){
//                    phoneContactsString.append(contacts[i].toString());
//                }
//            }
//            if(contacts[i].getType().equals("Email")){
//                emailContactsString.append(", " + contacts[i].toString());
//            }
//            if(contacts[i].getType().equals("Phone")){
//                phoneContactsString.append(", " + contacts[i].toString());
//            }
//        }
        Contact []emailContacts = getEmailContacts();
        Contact []phoneContacts = getPhoneContacts();
        extractString(emailContactsString, emailContacts);
        extractString(phoneContactsString, phoneContacts);
        phoneContactsString.append("]");
        emailContactsString.append("]");

        return "{\"ime\":" + "\"" + firstName + "\""  + ", \"prezime\":" + "\"" + lastName + "\"" + ", \"vozrast\":" + age + ", \"grad\":" + "\"" + city + "\"" + ", \"indeks\":" + index +
                ", \"telefonskiKontakti\":" + phoneContactsString.toString() + ", \"emailKontakti\":" + emailContactsString.toString() + "}";
    }

    private void extractString(StringBuilder string, Contact[] contact) {
        string.append("[");
        if(contact.length > 0 && contact[0] != null){
            string.append(contact[0]);
        }
        for(int i=1; i<contact.length; i++){
            string.append(", ");
            string.append(contact[i].toString());
        }
        string.append("]");
    }

    public int getNumberOfContacts(){
        return size;
    }
}
