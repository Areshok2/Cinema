package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.util.Util;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private static Long lastId = 0L;
    private static List<User> savedUsers;

    @PostConstruct
    public void init(){

        final Path file = Paths.get("users.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            savedUsers = Util.deserialize(savedUsersAsString, new TypeReference<List<User>>() {});
        } catch (final Exception e){
            System.out.println("We have an issue user1");
            savedUsers = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("users.txt");
        try {
            Files.writeString(file, Util.serialize(savedUsers), StandardCharsets.UTF_16);

        } catch (final Exception e){
            System.out.println("We have an issue user2");
        }
    }

//    @PostConstruct
//    public void init(){
//        savedUsers = new ArrayList<>();
//    }
//
//    private static Long lastId = 0L;
//    private static List<User> savedUsers;

    public User getUserById(final Long id){
        return savedUsers.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "User with id: " + id + " not found ", null));
    }

    public List<User> getAllUsers(){
        return savedUsers;
    }

    public User createUser(final User user){
        if(user.getId() != null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }
        ++lastId;
        user.setId(lastId);
        savedUsers.add(user);
        return user;
    }

    public User updateUser(final User user){
        if(user.getId() == null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }
        final User savedUser = getUserById(user.getId());
        savedUser.setOrders(user.getOrders());
        savedUser.setDay(user.getDay());
        savedUser.setLastname(user.getLastname());
        savedUser.setFirstname(user.getFirstname());

        return savedUser;
    }

public void deleteUserById(final long id) {
    savedUsers = savedUsers.stream()
            .filter(e -> e.getId() != id)
            .collect(Collectors.toList());
}

    public static boolean checkUserId(Long id) {
        if(!savedUsers.isEmpty()) {
            for (User user: savedUsers) {
                if (user.getId().equals(id)) return true;
            }
        }
        return false;
    }
}