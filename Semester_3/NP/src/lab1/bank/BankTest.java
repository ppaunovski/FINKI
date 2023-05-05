//package lab1.bank;
//
//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.Objects;
//
//public class BankTest{
//    public static void main(String[] args) {
//        Account[] accounts = new Account[2];
//        accounts[0] = new Account("Pavel", "1000$");
//        accounts[1] = new Account("David", "300$");
//        Bank bank = new Bank("Nadodna banka", accounts);
//
//        Transaction transaction = new FlatAmountProvisionTransaction(accounts[0].getId(), accounts[1].getId(), "200$", "10$");
//        Transaction transaction1 = new FlatPercentProvisionTransaction(accounts[0].getId(), accounts[1].getId(), "200$", 10);
//        boolean validTrans = bank.makeTransaction(transaction);
//
//        System.out.println(validTrans);
//
//        System.out.println(bank.totalProvision());
//        System.out.println(bank.totalTransfers());
//        System.out.println(bank.toString());
//    }
//}
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
//            checkValidId(t.getFromId()).setBalance(String.format("%.2f$", (Double.parseDouble(checkValidId(t.getFromId()).getBalance().substring(0,checkValidId(t.getFromId()).getBalance().length()-1)) - (Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1)) + Double.parseDouble(t.getProvision()))) ));
//            checkValidId(t.getToId()).setBalance(String.format("%.2f$", Double.parseDouble(checkValidId(t.getToId()).getBalance().substring(0,checkValidId(t.getToId()).getBalance().length()-1)) + Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1))));
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
//            return(Double.parseDouble(senderAcc.getBalance().substring(0, senderAcc.getBalance().length() -1 )) >= (Double.parseDouble(t.getAmount().substring(0, t.getAmount().length()-1)) + Double.parseDouble(t.getProvision().substring(0, t.getProvision().length()-1))));
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
