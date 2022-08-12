package bg.softuni.movies.services;

import bg.softuni.movies.models.entity.Actor;
import bg.softuni.movies.models.service.ActorServiceModel;
import bg.softuni.movies.repository.ActorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final ModelMapper modelMapper;


    public ActorService(ActorRepository actorRepository, ModelMapper modelMapper) {
        this.actorRepository = actorRepository;
        this.modelMapper = modelMapper;

    }

    public ActorServiceModel findActorsByNames(String actorName) {

        Actor name = this.actorRepository.findByName(actorName);
        //TODO find actor by name
        ActorServiceModel map = this.modelMapper.map(name, ActorServiceModel.class);
        return map;
    }

    public List<Actor> getAllActors() {
        return this.actorRepository.findAll();
    }

    public List<String> findAllActorsNames() {
        return this.actorRepository.findAllActorsNames();
    }

    public ActorServiceModel addActor (ActorServiceModel actorServiceModel) {
        this.actorRepository.saveAndFlush(this.modelMapper.map(actorServiceModel, Actor.class));
        return actorServiceModel;
    }

    public ActorServiceModel findById (Long id) {
        Actor actor = this.actorRepository.findById(id).orElse(null);

        if (actor != null) {
            return this.modelMapper.map(actor, ActorServiceModel.class);
        } else {
            return null;
        }

    }


}
