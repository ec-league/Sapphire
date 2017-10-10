package com.sapphire.common.dal.stock.repository;

import com.sapphire.common.dal.stock.domain.IgnoreStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ethan on 2016/4/5.
 */
public interface IgnoreStockRepository extends CrudRepository<IgnoreStock, Long> {

    @Query("select i.code from IgnoreStock as i where i.flag = ?1")
    List<String> getCodeByType(int flag);
}
