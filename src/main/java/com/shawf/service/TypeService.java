package com.shawf.service;

import com.shawf.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/5/29 15:13
 * Content:
 */
public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listType(Integer size);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
