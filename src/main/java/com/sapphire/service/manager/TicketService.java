package com.sapphire.service.manager;

import com.sapphire.domain.manage.Ticket;

import java.util.List;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/24.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface TicketService {
   List<Ticket> getTicketsByUserId(long userId);

   List<Ticket> getTicketsByProjectId(long projectId);

   long saveTicket(Ticket ticket);
}
