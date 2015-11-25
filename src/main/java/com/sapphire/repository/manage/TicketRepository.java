package com.sapphire.repository.manage;

import com.sapphire.domain.manage.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
   @Query("select t from Ticket as t where t.project.uidPk = :projectId")
   List<Ticket> getTicketsByProjectId(@Param("projectId") long projectId);

   @Query("select t from Ticket as t where t.assignUser.uidPk = :userId")
   List<Ticket> getTicketsByUserId(@Param("userId") long userId);
}
