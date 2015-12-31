package com.sapphire.manage.service;

import com.sapphire.manage.domain.Ticket;

import java.util.List;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/24.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface TicketService {
   List<Ticket> getTicketsByReportUserId(long userId);

   List<Ticket> getTicketsByAssignUserId(long userId);

   List<Ticket> getTicketsByProjectId(long projectId);

   long saveTicket(Ticket ticket);

   Ticket getTicketById(long uidPk);
}
