package srent.senai.com.srent.data;

import java.util.List;

public interface DAO<T, P> {

    void insert(T obj);

    void update(T obj);

    void delete(P id);

    List<T> searchAll();

    T search(P id);

}
