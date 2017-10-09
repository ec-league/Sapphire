package com.sapphire.manage.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.manage.domain.Ticket;
import com.sapphire.manage.repository.TicketRepository;
import com.sapphire.manage.service.TicketService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTicketsByReportUserId(long userId) {
        List<Ticket> tickets = ticketRepository.getTicketsByReportUserId(userId);
        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByAssignUserId(long userId) {
        List<Ticket> tickets = ticketRepository.getTicketsByAssignUserId(userId);
        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByProjectId(long projectId) {
        List<Ticket> tickets = ticketRepository.getTicketsByProjectId(projectId);
        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }
        return tickets;
    }

    @Override
    public long saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket).getUidPk();
    }

    @Override
    public Ticket getTicketById(long uidPk) {
        Ticket ticket = ticketRepository.findOne(uidPk);
        if (ticket == null) {
            throw new EntityNotFoundException(String.format("Ticket : \"%d\" not found.", uidPk));
        }
        return ticket;
    }
}
