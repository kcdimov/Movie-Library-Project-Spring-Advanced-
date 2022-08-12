package bg.softuni.movies.services;

import bg.softuni.movies.models.entity.Statistic;
import bg.softuni.movies.models.view.StatisticViewModel;
import bg.softuni.movies.repository.StatisticRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final ModelMapper modelMapper;

    public StatisticService(StatisticRepository statisticRepository, ModelMapper modelMapper) {
        this.statisticRepository = statisticRepository;
        this.modelMapper = modelMapper;
    }

    public Statistic saveVisitationsInDataBase(String ip) {
        Statistic statistic = new Statistic();

        statistic.setIpAddress(ip);
        statistic.setLocalDateTime(LocalDateTime.now());
        return this.statisticRepository.saveAndFlush(statistic);
    }

    public List<StatisticViewModel> getStatistic() {
        List<Statistic> statisticList = this.statisticRepository.findAll();
        return statisticList.stream().
                map(s -> this.modelMapper.map(s, StatisticViewModel.class)).
                collect(Collectors.toList());
    }


    public void dropTable() {
        this.statisticRepository.dropTable();
    }
}
