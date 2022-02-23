package entity;

import deposit.Deposit;
import exception.InvalidDepositException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Client extends Entity {
    private Map<Long, Deposit> deposits;

    public Client(String name, Long id, BigDecimal balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        deposits = new HashMap<>();
    }

    public Client(String name, Long id) {
        this.name = name;
        this.id = id;
        this.balance = new BigDecimal(0);
    }

    /**
     * Add a deposit to his HashMap of deposits
     * @param deposit deposit to add
     */
    public void addDeposit(Deposit deposit){
        if(deposit == null){
            throw new InvalidDepositException("Invalid information for deposit: null");
        }
        this.deposits.put(deposit.getId(), deposit);
    }

    /**
     * Filter his available deposits, then sum their balances and finally add
     * his intern balance
     * @return BigDecimal
     */
    public BigDecimal getBalance() {
        //get the list of amount of available deposit
        List<BigDecimal> availableDposits = this.deposits.values().stream().filter(Deposit::isAvailable).map(Deposit::getAmount).collect(Collectors.toList());
        //get the sum of the availables amounts
        BigDecimal depositBalance = availableDposits.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        //add personnal balance of the user
        return depositBalance.add(this.balance);
    }

    public boolean hasDeposit() {
        return this.deposits.size() > 0;
    }
}
