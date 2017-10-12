package com.sapphire.common.dal.manage.repository;

import java.util.List;

import com.sapphire.common.dal.manage.domain.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Query("select t from Ticket as t where t.project.uidPk = :projectId")
    List<Ticket> getTicketsByProjectId(@Param("projectId") long projectId);

    @Query("select t from Ticket as t where t.assignUser.uidPk = :userId")
    List<Ticket> getTicketsByAssignUserId(@Param("userId") long userId);

    @Query("select t from Ticket as t where t.createUser.uidPk = :userId")
    List<Ticket> getTicketsByReportUserId(@Param("userId") long userId);
}
