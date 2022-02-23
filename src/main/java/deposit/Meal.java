package deposit;

import java.math.BigDecimal;
import java.time.LocalDate;
import utils.DateManager;

public class Meal extends Deposit {
    public Meal(long id, LocalDate startDate, BigDecimal amount, DateManager dateManager) {
        this.id = id;
        this.startDate = startDate;
        this.amount = amount;
        this.dateManager = dateManager;
        this.isUsed = false;
    }

    /**
     * Check if Meal deposit is used and if the current day is before March
     * of the next year of the starting date
     * @return boolean
     */
    @Override
    public boolean isAvailable() {
        // last day of february of the next month of the next year
        LocalDate availabilityDate = this.startDate.plusYears(1).withMonth(3).withDayOfMonth(1).minusDays(1);
        LocalDate now = this.dateManager.getNow();
        // deposite is not currently use and today is in the availability date
        return !this.isUsed && (now.isBefore(availabilityDate) || now.isEqual(availabilityDate));
    }
}
