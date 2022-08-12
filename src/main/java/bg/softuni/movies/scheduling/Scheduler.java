package bg.softuni.movies.scheduling;

import bg.softuni.movies.services.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private final StatisticService statisticService;

    public Scheduler(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Scheduled(cron = "0 23 * * * *")
    public void dropVisitation() {
//        this.statisticService.dropTable();
        LOGGER.info("The Statistic was successful restart");
    }

    @Scheduled(fixedRate = 10000)
    public void logCurrentTime() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
//        LOGGER.info("The time is {}", date.format(localDateTime));
    }
}
