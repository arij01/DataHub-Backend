package tn.esprit.pi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tn.esprit.pi.entities.Column;

import java.util.List;

@FeignClient(name = "champs-service", url = "${application.config.champs-url}")
public interface ColumnClient {
    @GetMapping
    public List<Column> getAllColumns() ;
}
