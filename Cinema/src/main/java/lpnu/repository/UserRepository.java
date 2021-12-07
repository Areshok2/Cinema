package lpnu.repository;

import lpnu.entity.User;
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
import java.util.stream.Collectors;

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

    public User getById(final Long id) {
        return userList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "User with id: " + id + " not found ", null));
    }

    public List<User> getAll() {
        return userList;
    }

    public User save(final User user) {
        if(user.getId() != null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }
        ++lastId;
        user.setId(lastId);
        userList.add(user);

        return user;
    }

    public User update(final User user) {

        if(user.getId() == null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }

        final User savedUser = getById(user.getId());
        savedUser.setFirstname(user.getFirstname());
        savedUser.setLastname(user.getLastname());
        savedUser.setEmail(user.getEmail());
        savedUser.setNumber(user.getNumber());
        savedUser.setRole(user.getRole());

        return savedUser;
    }

    public void deleteUserById(final Long id) {
        userList = userList.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }
}

