//package lab1.bank;
//
//import java.util.Objects;
//import java.util.Random;
//
//class Account {
//    private String name;
//    private long id;
//    private String balance;
//
//    public Account(String name, String balance) {
//        this.name = name;
//        this.id = (long) (new Random().nextLong(1000000000));
//        this.balance = balance;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getBalance() {
//        return balance;
//    }
//
//    public void setBalance(String balance) {
//        this.balance = balance;
//    }
//
//    @Override
//    public String toString() {
//        return "Name:" + name + "\n" + "Balance:" + balance + "\n";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return Objects.equals(name, account.name) && Objects.equals(id, account.id) && Objects.equals(balance, account.balance);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, id, balance);
//    }
//
//}
//
//public class AccountTester {
//    public static void main(String[] args) {
//        Account acc = new Account("Pavel Paunovski", "1000$");
//        System.out.println(acc.toString());
//        acc.setBalance("10$");
//        System.out.println(acc.toString());
//        System.out.println(acc.getName() + acc.getBalance() + acc.getId());
//    }
//}
