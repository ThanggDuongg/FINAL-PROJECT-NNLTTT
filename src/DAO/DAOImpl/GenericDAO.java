package DAO.DAOImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {
    List<T> getAll();
    Boolean update(T DTO);
    Boolean insert(T DTO);
    T findById(Integer Id);
    boolean delete(Integer Id);
}
