package com.exemple.cinema.repository;

import com.exemple.cinema.entity.User;
import com.exemple.cinema.exception.ServiceException;
import com.exemple.cinema.util.Util;
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
public class UserRepository {

    private List<User> userList;
    public Long lastId =0L;


    @PostConstruct
    public void init(){

        final Path file = Paths.get("users.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            userList = Util.deserialize(savedUsersAsString, new TypeReference<List<User>>() {});


            if (userList == null) {
                userList = new ArrayList<>();
                return;
            }

            final long maxId = userList.stream().mapToLong(User::getId).max().orElse(1);

            lastId = maxId + 1;

        } catch (final Exception e){
            System.out.println("We have an issue");
            userList = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("users.txt");
        try {
            Files.writeString(file, Util.serialize(userList), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public List<User> getAll() {
        return userList;
    }

    public User save(final User user) {
        ++lastId;
        user.setId(lastId);
        userList.add(user);

        return user;
    }

    public User getById(final Long id) {
        final User user = userList.stream()
                .filter((e -> e.getId().equals(id)))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Usser not found", null));

        return user;
    }

    public User update(final User user) {
        final User oldTicket = getById(user.getId());
        if (oldTicket==null){
            throw new ServiceException(400,"User not found", null);
        }
        userList.remove(oldTicket);
        userList.add(user);

        return user;
    }

    public  List<User> delete(final Long id){
        final User user = getById(id);
        if(user==null){
            throw new ServiceException(400, "User not found", null);
        }
        userList.remove(user);
        return userList;
    }
}
