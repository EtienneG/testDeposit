package entity;

import deposit.enums.DepositType;
import exception.InvalidDepositException;
import exception.UnsufficientBalanceException;
import exception.InvalidClientException;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class CompanyTest {

    @Test
    public void should_ClientHaveADeposit_When_AddingValidDeposit() {
        Client client = new Client("CLient",1L, new BigDecimal(0));
        Company company = new Company("Compagny", 2L, new BigDecimal(100));
        company.giveDeposit(DepositType.MEAL.toString(), 1L, LocalDate.of(2020, 1, 1), new BigDecimal(50), client);
        Assert.assertTrue(client.hasDeposit());
    }

    @Test(expected = UnsufficientBalanceException.class)
    public void should_CatchUnsufficientBalanceException_When_BalanceIsNotEnough() {
        Client client = new Client("CLient",1L, new BigDecimal(0));
        Company company = new Company("Compagny", 2L, new BigDecimal(25));
        company.giveDeposit(DepositType.MEAL.toString(), 1L, LocalDate.of(2020, 1, 1), new BigDecimal(50), client);
    }

    @Test(expected = InvalidDepositException.class)
    public void should_CatchInvalidDepositException_When_DepositInfoInvalid() {
        Client client = new Client("CLient",1L, new BigDecimal(0));
        Company company = new Company("Compagny", 2L, new BigDecimal(25));
        company.giveDeposit(DepositType.MEAL.toString(), 1L, null, new BigDecimal(50), client);
    }

    @Test(expected = InvalidClientException.class)
    public void should_CatchInvalidClientException_When_ClientIsNull() {
        Client client = null;
        Company company = new Company("Compagny", 2L, new BigDecimal(25));
        company.giveDeposit(DepositType.MEAL.toString(), 1L, LocalDate.of(2020, 1, 1), new BigDecimal(50), client);
    }
}
