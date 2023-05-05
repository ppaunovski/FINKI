package lab3.PhoneBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contact {
    private static final int MAX_NUMBERS = 5;
    String name;
    List<String> phoneNumbers;

    public Contact(String name, String... phoneNumbers) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException  {
        if(name.length() < 4 || name.length() > 10) throw new InvalidNameException();
        else this.name = name;
        this.phoneNumbers = new ArrayList<>();
        for(String phoneNumber: phoneNumbers){
            //if(phoneNumbers.length > 5) throw new InvalidNumberException();
            addNumber(phoneNumber);
        }

    }

    public String getName() {
        return name;
    }

    public String[] getNumbers() {
        List<String> sorted = new ArrayList<>(phoneNumbers);
        Collections.sort(sorted);

        return sorted.toArray(new String[0]);
    }

    public void addNumber(String phoneNumber) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException{
        if(phoneNumber.length() != 9)   throw new InvalidNumberException();
        if(!phoneNumber.substring(0,3).equals("070") && !phoneNumber.substring(0,3).equals("071") && !phoneNumber.substring(0,3).equals("072") && !phoneNumber.substring(0,3).equals("075") && !phoneNumber.substring(0,3).equals("076")
                && !phoneNumber.substring(0,3).equals("077") && !phoneNumber.substring(0,3).equals("078"))
            throw new InvalidNumberException();
        if(this.phoneNumbers.size() == 5) throw new MaximumSizeExceddedException();
        //this.phoneNumbers = Arrays.copyOf(this.phoneNumbers, this.phoneNumbers.size()+1);
        this.phoneNumbers.add(phoneNumber);
    }

    @Override
    public String toString() {
        String[] sortedList = getNumbers();
        StringBuilder returnString = new StringBuilder();
        returnString.append(name).append("\n").append(phoneNumbers.size()).append("\n");
        for(String phoneNumber : sortedList){
            returnString.append(phoneNumber).append("\n");
        }
        return returnString.toString();
    }

    public static Contact valueOf(String s) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        return new Contact("123", "070000000");
    }

    public boolean hasNumberWithPrefix(String prefix){
        for(String number : phoneNumbers){
            if(number.startsWith(prefix))
                return true;
        }
        return false;
    }
}
