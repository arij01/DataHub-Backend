package tn.esprit.pi.services;

import tn.esprit.pi.entities.Column;

import java.util.List;

public interface IColumnService {
    List<Column> getAllColumns();
    Column getColumnById(String id);
    Column saveColumn(Column c);
    void deleteColumn(String id);
    void updateColumn(Column c);
}