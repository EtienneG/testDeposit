package utils;

import java.time.LocalDate;

/**
 * Class create to make mockito test easier as LocalDate.now() is static
 */
public class DateManager {

    /**
     *
     * @return LocalDate current day
     */
    public LocalDate getNow() {
        return LocalDate.now();
    }

}
