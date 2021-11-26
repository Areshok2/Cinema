package lpnu.repository;

import lpnu.dto.ItemDTO;
import lpnu.entity.Item;
import lpnu.exception.ServiceException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

//    private List<Item> savedItems;
//    private Long lastId = 0L;
//
//    @PostConstruct
//    public void init(){
//
//        final Path file = Paths.get("items.txt");
//        try {
//            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
//            savedItems = Util.deserialize(savedItemsAsString, new TypeReference<List<Item>>() {});
//        } catch (final Exception e){
//            System.out.println("We have an issue");
//            savedItems = new ArrayList<>();
//        }
//    }
//
//    @PreDestroy
//    public void preDestroy(){
//        final Path file = Paths.get("items.txt");
//        try {
//            Files.writeString(file, Util.serialize(savedItems), StandardCharsets.UTF_16);
//        } catch (final Exception e){
//            System.out.println("We have an issue");
//        }
//    }

    @PostConstruct
    public void init(){
        savedItems = new ArrayList<>();
    }

    private static Long lastId = 0L;
    private List<Item> savedItems;

    public Item getItemById(final Long id){
        return savedItems.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Item with id: " + id + " not found ", null));
    }

    public List<Item> getAllItems(){
        return savedItems;
    }

    public Item createItem(final Item item){
        if(item.getId() != null){
            throw new ServiceException(400, "Item shouldn't have an id ", null);
        }
        ++lastId;
        item.setId(lastId);
        savedItems.add(item);
        return item;
    }

    public Item updateItem(final Item item){
        if(item.getId() == null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }

        final Item savedItem = getItemById(item.getId());

        savedItem.setName(item.getName());
        savedItem.setPrice(item.getPrice());

        return savedItem;
    }

    public void deleteItemById(final Long id){
        if(id == null){
            throw new ServiceException(400, "Id isn't specified", null);
        }

        savedItems.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Item with id: " + id + " not found ", null));

        savedItems = savedItems.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());

    }

}
