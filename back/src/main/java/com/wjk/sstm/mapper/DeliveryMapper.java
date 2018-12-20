package com.wjk.sstm.mapper;

import com.wjk.sstm.model.Delivery;
import java.util.List;

public interface DeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Delivery record);

    Delivery selectByPrimaryKey(Integer id);

    List<Delivery> selectAll();

    int updateByPrimaryKey(Delivery record);
}
