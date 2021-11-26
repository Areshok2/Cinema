package lpnu.service.impl;

import lpnu.dto.ItemDTO;
import lpnu.exception.ServiceException;
import lpnu.mapper.ItemToItemDTOMapper;
import lpnu.repository.CarRepository;
import lpnu.repository.ItemRepository;
import lpnu.repository.UserRepository;
import lpnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemToItemDTOMapper itemToItemDTOMapper;

    @Override
    public ItemDTO getItemById(final Long id) {
        return itemToItemDTOMapper.toDTO(itemRepository.getItemById(id));
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.getAllItems().stream()
                .map(e -> itemToItemDTOMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO createItem(final ItemDTO itemDTO, Long id) {
        if(itemDTO.getId() != null) {
            throw new ServiceException(400, "Item shouldn't have an id ", null);
        }

        if(UserRepository.checkUserId(id)){
            if(!CarRepository.checkCarId(id)){
                throw new ServiceException(404, "User not found ", null);
            }
        }
        else { throw new ServiceException(404, "User not found ", null);}

        return itemToItemDTOMapper.toDTO(itemRepository.createItem(itemToItemDTOMapper.toEntity(itemDTO)));
    }

    @Override
    public ItemDTO updateItem(final ItemDTO itemDTO) {
        if(itemDTO.getId() == null){
            throw new ServiceException(400, "Item doesn't have an id ", null);
        }
        return itemToItemDTOMapper.toDTO(itemRepository.updateItem(itemToItemDTOMapper.toEntity(itemDTO)));
    }

    @Override
    public void deleteItemById(final Long id) {
        itemRepository.deleteItemById(id);
    }

}


