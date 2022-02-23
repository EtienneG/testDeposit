package deposit.factory;

import static org.junit.Assert.*;

import deposit.Deposit;
import deposit.Gift;
import deposit.Meal;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class DepositFactoryTest {

    @Test
    public void should_ReturnGiftObject_When_DepositTypeIsGift() {
        DepositFactory depositFactory = new DepositFactory();
        Deposit deposit = depositFactory.createDepositComponent("GIFT", 1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"));
        Assert.assertEquals(Gift.class, deposit.getClass());
    }

    @Test
    public void should_ReturnMealObject_When_DepositTypeIsMeal() {
        DepositFactory depositFactory = new DepositFactory();
        Deposit deposit = depositFactory.createDepositComponent("MEAL", 1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"));
        Assert.assertEquals(Meal.class, deposit.getClass());
    }

    @Test
    public void should_ReturnNull_When_DepositTypeDontExist() {
        DepositFactory depositFactory = new DepositFactory();
        Deposit deposit = depositFactory.createDepositComponent("test", 1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"));
        Assert.assertNull(deposit);
    }

}
