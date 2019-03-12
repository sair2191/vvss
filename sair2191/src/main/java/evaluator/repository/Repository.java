package evaluator.repository;

import evaluator.exception.DuplicateException;

public interface Repository<T>{
     void add(T obj) throws DuplicateException;
     boolean exists(T obj);

}
