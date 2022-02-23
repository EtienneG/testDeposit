package deposit.factory;

import deposit.Deposit;
import deposit.Gift;
import deposit.Meal;
import deposit.enums.DepositType;
import java.math.BigDecimal;
import java.time.LocalDate;
import utils.DateManager;

public class DepositFactory {

    /**
     * Design Pattern factory. For the context of the depositType, create a specific implementation
     * of a depositf. If the type don't exist return null object
     * @param depositType
     * @param id
     * @param startDate
     * @param amount
     * @return A specific deposit
     */
    public Deposit createDepositComponent(String depositType,long id, LocalDate startDate, BigDecimal amount) {
        if(DepositType.GIFT.toString().equals(depositType)){
            return new Gift(id, startDate,amount, new DateManager());
        } else if(DepositType.MEAL.toString().equals(depositType)){
            return new Meal(id, startDate, amount, new DateManager());
        } else {
            return null;
        }
    }
}
