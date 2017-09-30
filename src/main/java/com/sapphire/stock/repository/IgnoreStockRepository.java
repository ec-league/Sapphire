package com.sapphire.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapphire.stock.domain.IgnoreStock;

/**
 * Created by Ethan on 2016/4/5.
 */
public interface IgnoreStockRepository extends CrudRepository<IgnoreStock, Long> {

    @Query("select i.code from IgnoreStock as i where i.flag = ?1")
    List<String> getCodeByType(int flag);
}
