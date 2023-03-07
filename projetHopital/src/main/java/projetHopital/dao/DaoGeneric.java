package projetHopital.dao;

import java.util.List;

public interface DaoGeneric<T, K> {
  void insert(T obj);
  T findByKey(K key);
  void update(T obj);
  void delete(T obj);
  void deleteByKey(K key);
  List<T> findAll();
}
