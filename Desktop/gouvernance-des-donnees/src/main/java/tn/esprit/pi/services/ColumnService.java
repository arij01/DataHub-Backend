package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.repositories.ColumnRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ColumnService implements IColumnService {
    @Autowired
    private ColumnRepository columnRepository;
    @Override
    public List<Column> getAllColumns() {
        return columnRepository.findAll();
    }
    @Override
    public Column getColumnById(String id) {
        Optional<Column> column = columnRepository.findById(id);
        return column.orElse(null);
    }


    @Override
    public Column saveColumn(Column c) {
        return columnRepository.save(c);
    }

    @Override
    public void deleteColumn(String id) {
        columnRepository.deleteById(id);
    }
    @Override
    public void updateColumn(Column c) {
        Column savedColumn = columnRepository.findById(c.getId())
                .orElseThrow(() -> new RuntimeException(String.format("cannot find column by ID %d", c.getId())));
        savedColumn.setColumn_name(c.getColumn_name());
        savedColumn.setSynonym(c.getSynonym());
        savedColumn.setData_type(c.getData_type());
        savedColumn.setLabel(c.getLabel());
        savedColumn.setDoc(c.getDoc());
        columnRepository.save(savedColumn);
    }


}
