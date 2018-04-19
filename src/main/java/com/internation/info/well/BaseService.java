package com.internation.info.well;

import java.util.List;

public interface BaseService<T> {

    T get(Object o);

    int save(T o);

    int update(T o);
    
    int delete(T o);
    
    int deleteByPrimaryKey(Object id);
    
    List<T> getAllList();

    List<T> getByIds(Class<T> object, String property, List<?> ids);
}
