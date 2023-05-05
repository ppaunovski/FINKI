package lab3.PhoneBook;

import java.util.Arrays;

public class ContactTest {
    public static void main(String[] args) {
        String []numbers = new String[4];
        String []numbers1 = new String[5];
        numbers[2] = "072111111";
        numbers[3] = "072222222";
        numbers[0] = "072333333";
        numbers[1] = "071444444";

        numbers1[0] = "075111111";
        numbers1[1] = "070111111";
        numbers1[2] = "071111111";
        numbers1[3] = "078111111";
        numbers1[4] = "076111111";

        Contact c;
        try {
            Contact contact = new Contact("Pavel", numbers1);
            Contact contact2 = new Contact("David", numbers);
            Contact contact3 = new Contact("Kiki", numbers);
            Contact contact4 = new Contact("Poppy", numbers);
            PhoneBook phoneBook = new PhoneBook();
            phoneBook.addContact(contact);
            phoneBook.addContact(contact2);
            phoneBook.addContact(contact3);
            phoneBook.addContact(contact4);
//            System.out.println(Arrays.toString(phoneBook.getContacts()));
//            System.out.println(phoneBook.numberOfContacts());
//            System.out.println(phoneBook.getContactForName("Pavel"));
//            System.out.println(phoneBook.removeContact("Pavel"));
//            System.out.println(phoneBook);
            System.out.println(Arrays.toString(phoneBook.getContactsForNumber("070")));
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        } catch (MaximumSizeExceddedException e) {
            System.out.println(e.getMessage());
        }

    }
}
