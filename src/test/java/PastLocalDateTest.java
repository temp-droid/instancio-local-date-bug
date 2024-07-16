import org.instancio.internal.util.Constants;
import org.instancio.junit.InstancioExtension;
import org.instancio.support.DefaultRandom;
import org.instancio.support.Seeds;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(InstancioExtension.class)
public class PastLocalDateTest {

    @Test
    void test_past_today() {
        DefaultRandom random = new DefaultRandom(1, Seeds.Source.MANUAL);
        LocalDate min = Constants.DEFAULT_MIN.toLocalDate();
        LocalDate max = LocalDate.now().minusDays(1L);

        LocalDate generated = LocalDate.ofEpochDay(random.longRange(min.getLong(ChronoField.EPOCH_DAY), max.getLong(ChronoField.EPOCH_DAY)));

        assertThat(generated).isEqualTo(LocalDate.of(2003, 10, 20));
    }

    @Test
    void test_past_tomorrow() {
        DefaultRandom random = new DefaultRandom(1, Seeds.Source.MANUAL);
        LocalDate min = Constants.DEFAULT_MIN.toLocalDate();
        LocalDate max = LocalDate.now().plusDays(1).minusDays(1L);

        LocalDate generated = LocalDate.ofEpochDay(random.longRange(min.getLong(ChronoField.EPOCH_DAY), max.getLong(ChronoField.EPOCH_DAY)));

        assertThat(generated).isEqualTo(LocalDate.of(2003, 10, 20));
    }
}
