//package lab1.bank;
//
//import java.util.Objects;
//
//public class TransactionTester {
//
//    public static void main(String[] args) {
//        Account from = new Account("Pavel", "1000$");
//        Account to = new Account("David", "300");
//        Transaction flat = new FlatAmountProvisionTransaction(from.getId(), to.getId(), "500$", "10$");
//        Transaction percent = new FlatPercentProvisionTransaction(from.getId(), to.getId(), "500$", 10);
//
//        System.out.println("Polymorphism test--------------");
//        System.out.println(flat.getProvision());
//        System.out.println(percent.getProvision());
//    }
//}
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
//        super(fromId, toId,"FlatAAmount", amount);
//        this.flatAmount = flatProvision;
//    }
//    public String getProvision(){
//        return flatAmount;
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
