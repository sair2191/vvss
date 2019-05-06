package repository;

import exception.DuplicateException;

import java.io.IOException;

public interface Repository<T>{
     void add(T obj) throws DuplicateException, IOException, DuplicateException;
     boolean exists(T obj);

}
