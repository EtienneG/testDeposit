package deposit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import utils.DateManager;

@RunWith(MockitoJUnitRunner.class)
public class MealTest {
    @Mock
    DateManager dateManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_ReturnTrue_When_MealIs_Not_Used_And_DateIsBeforeMarchOfNextYear() {
        Meal mealDeposit = new Meal(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 02, 28));
        assertTrue(mealDeposit.isAvailable());
    }

    @Test
    public void should_ReturnFalse_When_MealIs_Not_Used_And_DateIs_Not_BeforeMarchOfNextYear() {
        Meal mealDeposit = new Meal(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 03, 01));
        assertFalse(mealDeposit.isAvailable());
    }

    @Test
    public void should_ReturnFalse_When_MealIsUsed_And_DateIsBeforeMarchOfNextYear() {
        Meal mealDeposit = new Meal(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        mealDeposit.setUsed(true);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 02, 28));
        assertFalse(mealDeposit.isAvailable());
    }
}
