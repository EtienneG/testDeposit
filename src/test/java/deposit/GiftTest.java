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
public class GiftTest {
    @Mock
    DateManager dateManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_ReturnTrue_IfGiftNotUsed_And_DateIn365LifeSpan_EdgeCase() {
        Gift giftDeposit = new Gift(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2020, 12, 31));
        assertTrue(giftDeposit.isAvailable());
    }

    @Test
    public void should_ReturnFalse_IfGiftNotUsed_And_Date_Not_In365LifeSpan_EdgeCase() {
        Gift giftDeposit = new Gift(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2021, 1, 1));
        assertFalse(giftDeposit.isAvailable());
    }

    @Test
    public void should_ReturnFalse_IfGiftUsed_And_DateIn365LifeSpan_EdgeCase() {
        Gift giftDeposit = new Gift(1L, LocalDate.of(2020, 1, 1), new BigDecimal("50.25"), dateManager);
        giftDeposit.setUsed(true);
        when(dateManager.getNow()).thenReturn(LocalDate.of(2020, 12, 31));
        assertFalse(giftDeposit.isAvailable());
    }
}
