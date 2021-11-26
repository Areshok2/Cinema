package lpnu.resource;

import lpnu.dto.CarDTO;
import lpnu.dto.ItemDTO;
import lpnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ItemResource {
    @Autowired
    private ItemService itemService;

    @GetMapping("/user/{id1}/cars/{id}/items/{id}")
    public ItemDTO getItemById(final @PathVariable Long id){
        return itemService.getItemById(id);
    }

    @GetMapping("/user/{id1}/cars/{id}/items")
    public List<ItemDTO> getAllItem(){
        return itemService.getAllItems();
    }

    @PostMapping("/user/{id1}/cars/{id}/items")
    public ItemDTO createItem(final @RequestBody ItemDTO itemDTO, @PathVariable Long id){ return itemService.createItem(itemDTO, id); }

    @PutMapping("/user/{id1}/cars/{id}/items")
    public ItemDTO updateItem(final @RequestBody ItemDTO itemDTO){
        return itemService.updateItem(itemDTO);
    }

    @DeleteMapping("/user/{id1}/cars/{id}/items/{id}")
    public void deleteItemById(final @PathVariable Long id){
        itemService.deleteItemById(id);
    }
}

