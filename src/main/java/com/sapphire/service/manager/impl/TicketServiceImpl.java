package com.sapphire.service.manager.impl;

import com.sapphire.domain.manage.Ticket;
import com.sapphire.repository.manage.TicketRepository;
import com.sapphire.service.manager.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {
   @Autowired
   private TicketRepository ticketRepository;

   public List<Ticket> getTicketsByReportUserId(long userId) {
      List<Ticket> tickets = ticketRepository.getTicketsByReportUserId(userId);
      if (tickets == null || tickets.isEmpty()) {
         return Collections.emptyList();
      }
      return tickets;
   }

   public List<Ticket> getTicketsByAssignUserId(long userId) {
      List<Ticket> tickets = ticketRepository.getTicketsByAssignUserId(userId);
      if (tickets == null || tickets.isEmpty()) {
         return Collections.emptyList();
      }
      return tickets;
   }

   public List<Ticket> getTicketsByProjectId(long projectId) {
      List<Ticket> tickets = ticketRepository.getTicketsByProjectId(projectId);
      if (tickets == null || tickets.isEmpty()) {
         return Collections.emptyList();
      }
      return tickets;
   }

   public long saveTicket(Ticket ticket) {
      return ticketRepository.save(ticket).getUidPk();
   }

   public Ticket getTicketById(long uidPk) {
      Ticket ticket = ticketRepository.findOne(uidPk);
      if (ticket == null) {
         throw new EntityNotFoundException(String.format(
               "Ticket : \"%d\" not found.", uidPk));
      }
      return ticket;
   }
}
