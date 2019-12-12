package com.zeng.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础类
 * @author zengZhanLiang
 * @since 2019-09-05
 */
public class BaseController<M extends IService<E>,E extends BaseEntity> {

    @Autowired
    protected M service;

    /**
     * 查询
     * @param id
     * @return
     */
    public Object getById(int id){
        return service.getById( id );
    }

    /**
     * 查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Object getPage(int pageNum, int pageSize){

        Page<E> page = new Page<>( pageNum, pageSize );
        IPage<E> responseEntity = service.page( page );
        return responseEntity.getRecords();
    }


    /**
     * 增加
     * @param e
     * @return
     */
    public Object create(E e){
        return service.save( e );
    }

    /**
     * 更新
     * @param e
     * @return
     */
    public Object update(E e){

        E old = service.getById( e.getId() );

        //用新对象中的非空值覆盖旧对象的值
        BeanUtil.copyProperties( e, old, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        return service.updateById(old);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Object removeById(int id){
        return service.removeById( id );
    }
}
