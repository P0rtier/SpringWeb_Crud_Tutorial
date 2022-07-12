package pl.uzi.springweb_crud_tutorial.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.uzi.springweb_crud_tutorial.model.VideoCassette;
import pl.uzi.springweb_crud_tutorial.repositories.VideoCassetteRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VideoCassetteManager {

    private VideoCassetteRepository videoCassetteRepository;

    @Autowired
    public VideoCassetteManager(VideoCassetteRepository videoCassetteRepository) {
        this.videoCassetteRepository = videoCassetteRepository;
    }

    public Optional<VideoCassette> find(Long id){
        return videoCassetteRepository.findById(id);
    }

    public Iterable<VideoCassette> findAll(){
        return videoCassetteRepository.findAll();
    }

    public VideoCassette save(VideoCassette videoCassette){
        return videoCassetteRepository.save(videoCassette);
    }

    public void deleteById(Long id){
        videoCassetteRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new VideoCassette(1L, "Titanic", LocalDate.of(1995, 1, 1)));
        save(new VideoCassette(2L, "Pulp Fiction", LocalDate.of(1997, 2, 1)));
    }
}
