package com.shawf.service.impl;

import com.shawf.dao.TypeDao;
import com.shawf.entity.Type;
import com.shawf.exception.NotFoundException;
import com.shawf.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/5/29 15:16
 * Content:
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }


    @Override
    public List<Type> listType() {
        return typeDao.findAll() ;
    }

    @Override
    public List<Type> listType(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogList.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return typeDao.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type  type1=typeDao.findById(id).orElse(null);
        if(type1==null){
            throw new NotFoundException("该类型不存在！");
        }
        BeanUtils.copyProperties(type,type1);
        return typeDao.save(type1);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }


}
