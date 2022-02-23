package deposit;

import java.math.BigDecimal;
import java.time.LocalDate;
import utils.DateManager;

public abstract class Deposit {
    protected long id;
    protected LocalDate startDate;
    protected BigDecimal amount;
    protected DateManager dateManager;
    protected boolean isUsed;

    public abstract boolean isAvailable();

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
