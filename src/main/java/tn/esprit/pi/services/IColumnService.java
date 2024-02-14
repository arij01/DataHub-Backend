package tn.esprit.pi.services;

import tn.esprit.pi.entities.Column;

import java.util.List;

public interface IColumnService {
    public List<Column> getAllColumns();
    public Column saveComponent(Column c);
    public void deleteColumn(String id);
}
