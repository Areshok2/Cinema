package lpnu.service;

import lpnu.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO getItemById(Long id);
    List<ItemDTO> getAllItems();
    ItemDTO createItem(ItemDTO itemDTO, Long id);
    ItemDTO updateItem(ItemDTO itemDTO);
    void deleteItemById(Long id);
}