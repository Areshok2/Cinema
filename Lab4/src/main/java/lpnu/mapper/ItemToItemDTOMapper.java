package lpnu.mapper;

import lpnu.dto.ItemDTO;
import lpnu.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDTOMapper {
    public Item toEntity(final ItemDTO itemDTO){
        final Item item = new Item();

        item.setId(itemDTO.getId());
        item.setPrice(itemDTO.getPrice());
        item.setName(itemDTO.getTitle());

        return item;
    }

    public ItemDTO toDTO(final Item item){
        final ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setTitle(item.getName());

        return itemDTO;
    }
}