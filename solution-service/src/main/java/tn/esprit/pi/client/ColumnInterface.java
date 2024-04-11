package tn.esprit.pi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.pi.model.Column;

import java.util.List;

@FeignClient(name="CHAMPS-SERVICE")
public interface ColumnInterface {
    @GetMapping("/columns")
    List<Column> getAllColumns();
    @GetMapping("/columns/getById/{id}")
    ResponseEntity<Column> getColumnById(@PathVariable String id);
}
