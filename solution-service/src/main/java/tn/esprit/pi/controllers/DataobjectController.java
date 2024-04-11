package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Dataobject;
import tn.esprit.pi.services.DataobjectService;

import java.util.List;

@RestController
@RequestMapping("/api/dataobjects")
public class DataobjectController {
    @Autowired
    private DataobjectService dataobjectService;

    @GetMapping
    public List<Dataobject> getAllDataobjects() {
        return dataobjectService.getAllDataobjects();
    }

    @PostMapping
    public Dataobject saveDataobject(@RequestBody Dataobject dataobject) {
        return dataobjectService.saveDataobject(dataobject);
    }

    @GetMapping("get/{id}")
    public Dataobject getDataobjectById(@PathVariable String id) {
        return dataobjectService.getDataobjectById(id);
    }

    //    @PutMapping("/update")
//    public void updateDataobject(@RequestBody Dataobject dataobject) {
//        dataobjectService.updateDataobject(dataobject);
//    }
    @PutMapping("/update/{id}")
    public void updateDocById(@PathVariable String id,@RequestBody Dataobject dataobject) {
        dataobjectService.updateDocById(id,dataobject);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDataobject(@PathVariable String id) {
        dataobjectService.deleteDataobject(id);
        return ResponseEntity.ok().build();
    }
}
