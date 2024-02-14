package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.repositories.ColumnRepository;

import java.util.List;

@Service
public class ColumnService implements IColumnService {
    @Autowired
    private ColumnRepository columnRepository;
    @Override
    public List<Column> getAllColumns() {
        return columnRepository.findAll();
    }

    @Override
    public Column saveComponent(Column c) {
        return columnRepository.save(c);
    }

    @Override
    public void deleteColumn(String id) {
        columnRepository.deleteById(id);
    }


}
