package pl.uzi.springweb_crud_tutorial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pl.uzi.springweb_crud_tutorial.manager.VideoCassetteManager;
import pl.uzi.springweb_crud_tutorial.model.VideoCassette;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/cassettes")
public class VideoCassetteApi {

    private VideoCassetteManager videoCassetteManager;

    @Autowired
    public VideoCassetteApi(VideoCassetteManager videoCassetteManager) {
        this.videoCassetteManager = videoCassetteManager;
    }

    @GetMapping("/all")
    public Iterable<VideoCassette> getAll(){
        return videoCassetteManager.findAll();
    }

    @GetMapping
    public VideoCassette getById(@RequestParam Long index){
//        Optional<VideoCassette> first = videoCassettes.stream()
//                .filter(elem -> Objects.equals(elem.getId(), index))
//                .findFirst();
        return videoCassetteManager.find(index).orElseThrow();
    }

    @PostMapping
    public VideoCassette addVideoCassettes(@RequestBody VideoCassette videoCassette){
        return videoCassetteManager.save(videoCassette);
    }

    @PutMapping
    public VideoCassette updateVideoCassettes(@RequestParam Long index,@RequestBody VideoCassette videoCassette){
        VideoCassette videoCassette1 = new VideoCassette();
        videoCassette1.setId(index);
        videoCassette1.setProductionYear(videoCassette.getProductionYear());
        videoCassette1.setTitle(videoCassette.getTitle());
        return videoCassetteManager.save(videoCassette1);
    }

    @PatchMapping
    public boolean patchVideoCassettes(@RequestParam Long index,@RequestBody VideoCassette videoCassette){

        try {
            VideoCassette videoCassette1 = videoCassetteManager.find(index).orElseThrow(ChangeSetPersister.NotFoundException::new);

            boolean needUpdate = false;

            if(StringUtils.hasLength(videoCassette.getTitle())){
                videoCassette1.setTitle(videoCassette.getTitle());
                needUpdate = true;
            }

            if(StringUtils.hasLength(String.valueOf(videoCassette.getProductionYear()))){
                videoCassette1.setProductionYear(videoCassette.getProductionYear());
                needUpdate=true;
            }

            if(needUpdate) {
                videoCassetteManager.save(videoCassette1);
                return true;
            }
            return false;
        } catch (ChangeSetPersister.NotFoundException e) {
            System.err.println("No such object in the db!");
        }
        return false;
    }

    @DeleteMapping
    public void deleteVideo(@RequestParam Long index){
        videoCassetteManager.deleteById(index);
    }
}
