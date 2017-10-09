package com.sapphire.biz.stock.repository;

import java.util.List;

import com.sapphire.biz.stock.domain.IgnoreStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ethan on 2016/4/5.
 */
public interface IgnoreStockRepository extends CrudRepository<IgnoreStock, Long> {

    @Query("select i.code from IgnoreStock as i where i.flag = ?1")
    List<String> getCodeByType(int flag);
}
