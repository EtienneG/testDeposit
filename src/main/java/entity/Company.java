package entity;

import deposit.Deposit;
import deposit.factory.DepositFactory;
import exception.InvalidClientException;
import exception.InvalidDepositException;
import exception.UnsufficientBalanceException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Company extends Entity{
    public Company(String name, Long id, BigDecimal balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public Company(String name, Long id) {
        this.name = name;
        this.id = id;
        this.balance = new BigDecimal(0);
    }

    /**
     * Check function variables, check if balance is valid and then add a deposit to a client
     * @param depositType
     * @param id
     * @param startDate
     * @param amount
     * @param client
     */
    public void giveDeposit(String depositType, long id, LocalDate startDate, BigDecimal amount, Client client){
        if(client == null){
            throw new InvalidClientException("Invalid client: null");
        }
        StringBuilder invalidElement = new StringBuilder();
        invalidElement.append(id == 0 ? "id " : "").append(startDate == null ? "startDate " : "").append(depositType == null ? "depositType ": "");
        if(invalidElement.length() > 0){
            throw new InvalidDepositException("Invalid information for deposit: " + invalidElement.toString());
        }
        DepositFactory depositFactory = new DepositFactory();
        if(this.balance.compareTo(amount) > -1) {
            Deposit deposit = depositFactory.createDepositComponent(depositType, id, startDate, amount);
            client.addDeposit(deposit);
        } else {
            throw new UnsufficientBalanceException(
                  "UnsufficientBalance company: " + this.name + " balance: " + this.balance + "€");
        }
    }
}
