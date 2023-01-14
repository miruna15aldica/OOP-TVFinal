package data.entities;

public class Credentials {
    private String name;
    private String password;
    private AccountType accountType;
    private String country;
    private int balance;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }

    public final AccountType getAccountType() {
        return accountType;
    }

    public final void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }

    public final String getCountry() {
        return country;
    }

    public final void setCountry(final String country) {
        this.country = country;
    }

    public final int getBalance() {
        return balance;
    }

    public final void setBalance(final int balance) {
        this.balance = balance;
    }

    public Credentials(final String name, final String password, final AccountType accountType,
                       final String country, final int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    @Override
    public final String toString() {
        return "Credentials{"
                + "name='" + name + '\''
                + ", password='" + password + '\''
                + ", accountType=" + accountType
                + ", country='" + country + '\''
                + ", balance=" + balance
                + '}';
    }

    /**
     * salvarea unei copii pentru Credentials
     * @return
     */
    public final Credentials copy() {
        return new Credentials(name, password, accountType, country, balance);
    }
}
