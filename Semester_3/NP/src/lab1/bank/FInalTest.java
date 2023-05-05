//package lab1.bank;
//
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Random;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class BankTester {
//
//    public static void main(String[] args) {
//        Scanner jin = new Scanner(System.in);
//        String test_type = jin.nextLine();
//        switch (test_type) {
//            case "typical_usage":
//                testTypicalUsage(jin);
//                break;
//            case "equals":
//                testEquals();
//                break;
//        }
//        jin.close();
//    }
//
//    private static void testEquals() {
//        Account a1 = new Account("Andrej", "20.00$");
//        Account a2 = new Account("Andrej", "20.00$");
//        Account a3 = new Account("Andrej", "30.00$");
//        Account a4 = new Account("Gajduk", "20.00$");
//        List<Account> all = Arrays.asList(a1, a2, a3, a4);
//        if (!(a1.equals(a1)&&!a1.equals(a2)&&!a2.equals(a1) && !a3.equals(a1)
//                && !a4.equals(a1)
//                && !a1.equals(null))) {
//            System.out.println("Your account equals method does not work properly.");
//            return;
//        }
//        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
//        if (ids.size() != all.size()) {
//            System.out.println("Different accounts have the same IDS. This is not allowed");
//            return;
//        }
//        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
//        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, "20.00$", "10.00$");
//        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
//        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
//        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
//        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
//        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
//        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, "20.00$", 10);
//        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
//        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
//        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
//        if (fa1.equals(fa1) &&
//                !fa2.equals(null) &&
//                fa2.equals(fa1) &&
//                fa1.equals(fa2) &&
//                fa1.equals(fa3) &&
//                !fa1.equals(fa4) &&
//                !fa1.equals(fa5) &&
//                !fa1.equals(fp1) &&
//                fp1.equals(fp1) &&
//                !fp2.equals(null) &&
//                fp2.equals(fp1) &&
//                fp1.equals(fp2) &&
//                fp1.equals(fp3) &&
//                !fp1.equals(fp4) &&
//                !fp1.equals(fp5) &&
//                !fp1.equals(fp6)) {
//            System.out.println("Your transactions equals methods do not work properly.");
//            return;
//        }
//        Account accounts[] = new Account[]{a1, a2, a3, a4};
//        Account accounts1[] = new Account[]{a2, a1, a3, a4};
//        Account accounts2[] = new Account[]{a1, a2, a3};
//        Account accounts3[] = new Account[]{a1, a2, a3, a4};
//
//        Bank b1 = new Bank("Test", accounts);
//        Bank b2 = new Bank("Test", accounts1);
//        Bank b3 = new Bank("Test", accounts2);
//        Bank b4 = new Bank("Sample", accounts);
//        Bank b5 = new Bank("Test", accounts3);
//
//        if (!(b1.equals(b1) &&
//                !b1.equals(null) &&
//                !b1.equals(b2) &&
//                !b2.equals(b1) &&
//                !b1.equals(b3) &&
//                !b3.equals(b1) &&
//                !b1.equals(b4) &&
//                b1.equals(b5))) {
//            System.out.println("Your bank equals method do not work properly.");
//            return;
//        }
//        //accounts[2] = a1;
//        if (!b1.equals(b5)) {
//            System.out.println("Your bank equals method do not work properly.");
//            return;
//        }
//        long from_id = a2.getId();
//        long to_id = a3.getId();
//        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, "3.00$", "3.00$");
//        b1.makeTransaction(t);
//        if (b1.equals(b5)) {
//            System.out.println("Your bank equals method do not work properly.");
//            return;
//        }
//        b5.makeTransaction(t);
//        if (!b1.equals(b5)) {
//            System.out.println("Your bank equals method do not work properly.");
//            return;
//        }
//        System.out.println("All your equals methods work properly.");
//    }
//
//    private static void testTypicalUsage(Scanner jin) {
//        String bank_name = jin.nextLine();
//        int num_accounts = jin.nextInt();
//        jin.nextLine();
//        Account accounts[] = new Account[num_accounts];
//        for (int i = 0; i < num_accounts; ++i)
//            accounts[i] = new Account(jin.nextLine(), jin.nextLine());
//        Bank bank = new Bank(bank_name, accounts);
//        while (true) {
//            String line = jin.nextLine();
//            switch (line) {
//                case "stop":
//                    return;
//                case "transaction":
//                    String descrption = jin.nextLine();
//                    String amount = jin.nextLine();
//                    String parameter = jin.nextLine();
//                    int from_idx = jin.nextInt();
//                    int to_idx = jin.nextInt();
//                    jin.nextLine();
//                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
//                    System.out.println("Transaction amount: " + t.getAmount());
//                    System.out.println("Transaction description: " + t.getDescription());
//                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
//                    break;
//                case "print":
//                    System.out.println(bank.toString());
//                    System.out.println("Total provisions: " + bank.totalProvision());
//                    System.out.println("Total transfers: " + bank.totalTransfers());
//                    System.out.println();
//                    break;
//            }
//        }
//    }
//
//    private static Transaction getTransaction(String description, int from_idx, int to_idx, String amount, String o, Bank bank) {
//        switch (description) {
//            case "FlatAmount":
//                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
//                        bank.getAccounts()[to_idx].getId(), amount, o);
//            case "FlatPercent":
//                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
//                        bank.getAccounts()[to_idx].getId(), amount, Integer.parseInt(o));
//        }
//        return null;
//    }
//
//
//}
//
//
//class Account {
//    private String name;
//    private long id;
//    private String balance;
//
//    public Account(String name, String balance) {
//        this.name = name;
//        this.id = (long) (new Random().nextLong());
//        this.balance = balance.substring(0, balance.length()-1);
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
//        return "Name: " + name + "\n" + "Balance: " + String.format("%.2f$", Double.parseDouble(balance)) + "\n";
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
//
//abstract class Transaction{
//    private long fromId;
//    private long toId;
//    private String description;
//    private String amount;
//
//    public Transaction(long fromId, long toId, String description, String amount) {
//        this.fromId = fromId;
//        this.toId = toId;
//        this.description = description;
//        this.amount = amount;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public long getFromId() {
//        return fromId;
//    }
//
//    public long getToId() {
//        return toId;
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//    public abstract String getProvision();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Transaction that = (Transaction) o;
//        return Objects.equals(fromId, that.fromId) && Objects.equals(toId, that.toId) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fromId, toId, description, amount);
//    }
//}
//
//class FlatAmountProvisionTransaction extends Transaction{
//    String flatAmount;
//    public FlatAmountProvisionTransaction(long fromId, long toId,  String amount, String flatProvision) {
//        super(fromId, toId,"FlatAmount", amount);
//        this.flatAmount = flatProvision;
//    }
//    public String getProvision(){
//        return flatAmount.substring(0, flatAmount.length()-1);
//    }
//
//
//    public String getFlatAmount() {
//        return flatAmount;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FlatAmountProvisionTransaction that = (FlatAmountProvisionTransaction) o;
//        return Objects.equals(flatAmount, that.flatAmount);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(flatAmount);
//    }
//}
//
//class FlatPercentProvisionTransaction extends Transaction{
//    int percent;
//
//    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int percent) {
//        super(fromId, toId,"FlatPercent", amount);
//        this.percent = percent;
//    }
//
//    public String getProvision(){
//        return String.format("%f", Double.parseDouble(getAmount().substring(0, getAmount().length()-1)) * percent * 1.0 / 100) ;
//    }
//
//    public int getPercent() {
//        return percent;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
//        return percent == that.percent;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(percent);
//    }
//}
//
//
//class Bank {
//    private Account[] accounts;
//    private String name;
//    private double totalTransfer;
//    private double totalProvision;
//
//    public Bank(String name, Account[] accounts) {
//        this.accounts = Arrays.copyOf(accounts, accounts.length);
//        this.name = name;
//        totalTransfer = 0.00;
//        totalProvision = 0.00;
//    }
//
//    public boolean makeTransaction(Transaction t){
//        if(checkValidId(t.getFromId()) != null && checkValidId(t.getToId()) != null && checkForBalance(t)){
//            totalTransfer += (Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1)) );
//            totalProvision += (Double.parseDouble(t.getProvision()));
//            checkValidId(t.getFromId()).setBalance(String.valueOf((Double.parseDouble(checkValidId(t.getFromId()).getBalance()) - (Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1)) + Double.parseDouble(t.getProvision()))) ));
//            checkValidId(t.getToId()).setBalance(String.valueOf(Double.parseDouble(checkValidId(t.getToId()).getBalance()) + Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1))));
//            //System.out.println(String.valueOf(Integer.parseInt(t.getProvision())));
//            return true;
//        }
//        return false;
//    }
//
//    public String totalTransfers(){
//        return String.format("%.2f$", totalTransfer);
//    }
//
//    public String totalProvision(){
//        return String.format("%.2f$", totalProvision);
//    }
//
//    @Override
//    public String toString() {
//        return "Name: " + name + "\n\n" + printAllAccounts();
//    }
//
//    private String printAllAccounts(){
//        String returnValue = "";
//        for(int i=0; i<accounts.length; i++){
//            returnValue += accounts[i].toString();
//        }
//        return returnValue;
//    }
//
//    private Account checkValidId(long id){
//        for(int i=0; i<accounts.length; i++){
//            if(accounts[i].getId() == id)
//                return accounts[i];
//        }
//        return null;
//    }
//
//    private boolean checkForBalance(Transaction t){
//        Account senderAcc = checkValidId(t.getFromId());
//        if(senderAcc != null){
//            return(Double.parseDouble(senderAcc.getBalance()) >= (Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1)) + Double.parseDouble(t.getProvision())));
//        }
//        return false;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Bank bank = (Bank) o;
//        return Double.compare(bank.totalTransfer, totalTransfer) == 0 && Double.compare(bank.totalProvision, totalProvision) == 0 && Arrays.equals(accounts, bank.accounts) && Objects.equals(name, bank.name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(name, totalTransfer, totalProvision);
//        result = 31 * result + Arrays.hashCode(accounts);
//        return result;
//    }
//
//    public Account[] getAccounts() {
//        return accounts;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getTotalTransfer() {
//        return totalTransfer;
//    }
//
//    public double getTotalProvision() {
//        return totalProvision;
//    }
//}
