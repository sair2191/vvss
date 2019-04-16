package evaluator.repository;

import evaluator.exception.DuplicateException;

import java.io.IOException;

public interface Repository<T>{
     void add(T obj) throws DuplicateException, IOException;
     boolean exists(T obj);

}
