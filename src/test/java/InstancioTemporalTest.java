import org.instancio.Gen;
import org.instancio.Instancio;
import org.instancio.Select;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.Seed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(InstancioExtension.class)
public class InstancioTemporalTest {

    @Seed(1)
    @Test
    void should_return_same_local_date_every_day() {
        TemporalObject temporalObject = Instancio.of(TemporalObject.class)
                .create();

        assertThat(temporalObject.localDate()).isEqualTo(LocalDate.of(2080, 12, 4));
    }

    @Seed(1)
    @Test
    void should_return_same_past_local_date_every_day() {
        TemporalObject temporalObject = Instancio.of(TemporalObject.class)
                .generate(Select.fields().ofType(LocalDate.class), gen -> gen.temporal().localDate().past())
                .create();

        assertThat(temporalObject.localDate()).isEqualTo(LocalDate.of(2001, 5, 19));
    }

    @Seed(1)
    @Test
    void should_return_same_past_local_date_every_day_using_Gen() {
        TemporalObject temporalObject = Instancio.of(TemporalObject.class)
                .generate(Select.fields().ofType(LocalDate.class), gen -> Gen.temporal().localDate().past())
                .create();

        assertThat(temporalObject.localDate()).isEqualTo(LocalDate.of(2001, 5, 19));
    }

    @Seed(1)
    @Test
    void should_generate_same_past_local_date_every_day_using_Gen() {
        LocalDate pastDate = Gen.temporal().localDate().past().get();

        assertThat(pastDate).isEqualTo(LocalDate.of(2001, 5, 19));
    }

    @Seed(1)
    @Test
    void should_return_same_date_every_day() {
        TemporalObject temporalObject = Instancio.of(TemporalObject.class)
                .create();

        LocalDateTime localDateTime = LocalDateTime.of(2079, 12, 3, 8, 45, 13);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        assertThat(Date.from(zonedDateTime.toInstant())).isInSameDayAs(temporalObject.date());
    }

    @Seed(1)
    @Test
    void should_return_same_past_date_every_day() {
        TemporalObject temporalObject = Instancio.of(TemporalObject.class)
                .generate(Select.fields().ofType(Date.class), gen -> gen.temporal().date().past())
                .create();

        LocalDateTime localDateTime = LocalDateTime.of(1984, 2, 19, 19, 44, 14);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        assertThat(Date.from(zonedDateTime.toInstant())).isInSameDayAs(temporalObject.date());
    }
}
