package lpnu.repository;

import lpnu.entity.Ticket;

import lpnu.exception.ServiceException;
import lpnu.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    private List<Ticket> ticketList;
    private Long lastId = 0L;

    @PostConstruct
    public void init(){

        final Path file = Paths.get("ticket.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            ticketList = Util.deserialize(savedUsersAsString, new TypeReference<List<Ticket>>() {});


            if (ticketList == null) {
                ticketList = new ArrayList<>();
                return;
            }

            final long maxId = ticketList.stream().mapToLong(Ticket::getId).max().orElse(1);

            lastId = maxId + 1;

        } catch (final Exception e){
            System.out.println("We have an issue");
            ticketList = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("ticket.txt");
        try {
            Files.writeString(file, Util.serialize(ticketList), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public List<Ticket> getAll(){
        return ticketList;
    }

    public Ticket getById(final Long id){
        final Ticket ticket = ticketList.stream()
                .filter((e -> e.getId().equals(id)))
                .findFirst()
                .orElseThrow(()-> new ServiceException(400,"Ticket not found",null));

        return ticket;
    }

    public Ticket save(final Ticket ticket){
        ++lastId;
        ticket.setId(lastId);
        ticketList.add(ticket);
        return ticket;
    }

    public Ticket update(final Ticket ticket) {
        final Ticket oldTicket = getById(ticket.getId());
        if (oldTicket==null){
            throw new ServiceException(400,"Ticket not found", null);
        }
        ticketList.remove(oldTicket);
        ticketList.add(ticket);

        return ticket;
    }

    public  List<Ticket> delete(final Long id){
        final Ticket ticket = getById(id);
        if(ticket==null){
            throw new ServiceException(400, "Ticket not found", null);
        }
        ticketList.remove(ticket);

        return ticketList;
    }

}
