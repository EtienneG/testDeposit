package entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import deposit.Gift;
import deposit.Meal;
import exception.InvalidDepositException;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import utils.DateManager;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    @Mock
    DateManager dateManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_ReturnGoodBalance_When_ClientHasInternBalance_AndADeposit() {
        Client client = new Client("CLient",1L, new BigDecimal(50));
        Meal mealDeposit = new Meal(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 02, 28));

        client.addDeposit(mealDeposit);
        Assert.assertEquals(new BigDecimal("100.25"), client.getBalance());
    }

    @Test
    public void should_ReturnGoodBalance_When_ClientHasInternBalance_AndAInvalidDeposit() {
        Client client = new Client("CLient",1L, new BigDecimal(50));
        Meal mealDeposit = new Meal(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 03, 01));

        client.addDeposit(mealDeposit);
        Assert.assertEquals(new BigDecimal("50"), client.getBalance());
    }

    @Test
    public void should_ReturnGoodBalance_When_ClientHasInternBalance_AndMixedDeposits() {
        Client client = new Client("CLient",1L, new BigDecimal(50));
        Meal mealDeposit = new Meal(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        Gift gift2Deposit = new Gift(2L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        gift2Deposit.setUsed(true);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 02, 28));

        client.addDeposit(mealDeposit);
        client.addDeposit(gift2Deposit);
        Assert.assertEquals(new BigDecimal("100.25"), client.getBalance());
    }

    @Test(expected = InvalidDepositException.class)
    public void should_CatchInvalidDepositException_When_AddingNullDeposit() {
        Client client = new Client("CLient",1L, new BigDecimal(50));
        Meal mealDeposit = null;

        client.addDeposit(mealDeposit);
    }
}
