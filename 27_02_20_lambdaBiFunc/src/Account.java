public class Account {
    private  String number;
    private Long balance;
    private boolean isLocked;

    public Account() {
    }

    public Account(String number, Long balance, boolean isLocked) {
        this.number = number;
        this.balance = balance;
        this.isLocked = isLocked;
    }

    public String getNumber() {
        return number;
    }

    public Long getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", isLocked=" + isLocked +
                '}';
    }
}
