package deposit;

import java.math.BigDecimal;
import java.time.LocalDate;
import utils.DateManager;

public class Gift extends Deposit {
    public Gift(long id, LocalDate startDate, BigDecimal amount, DateManager dateManager) {
        this.id = id;
        this.amount = amount;
        this.startDate = startDate;
        this.dateManager = dateManager;
        this.isUsed = false;
    }

    /**
     * Check if Meal deposit is used and if the current is in it lifespan of 365 days
     * @return boolean
     */
    @Override
    public boolean isAvailable() {
        //cant use year because of bisextile etc 365 days of availability
        LocalDate availabilityDate = this.startDate.plusDays(365);
        LocalDate now = this.dateManager.getNow();
        // deposite is not currently use and today is in the availability date
        return !this.isUsed && (now.isBefore(availabilityDate) || now.isEqual(availabilityDate));
    }
}
