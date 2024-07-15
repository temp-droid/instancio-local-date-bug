import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record TemporalObject(LocalDate localDate, LocalDateTime localDateTime, Date date) {
}
