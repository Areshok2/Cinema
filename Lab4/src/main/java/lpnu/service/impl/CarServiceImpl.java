package lpnu.service.impl;

import lpnu.dto.CarDTO;
import lpnu.exception.ServiceException;
import lpnu.mapper.CarToCarDTOMapper;
import lpnu.repository.CarRepository;
import lpnu.repository.UserRepository;
import lpnu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarToCarDTOMapper carToCarDTOMapper;

    @Override
    public CarDTO getCarById(final Long id) {

        return carToCarDTOMapper.toDTO(carRepository.getCarById(id));
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.getAllCars().stream()
                .map(e -> carToCarDTOMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO createCar(final CarDTO carDTO, Long id) {
        if(carDTO.getId() != null){
            throw new ServiceException(400, "Car shouldn't have an id ", null);
        }
        if(!UserRepository.checkUserId(id)){
            throw new ServiceException(404, "User not found ", null);
        }
        return carToCarDTOMapper.toDTO(carRepository.createCar(carToCarDTOMapper.toEntity(carDTO)));
    }

    @Override
    public CarDTO updateCar(final CarDTO carDTO) {
        if(carDTO.getId() == null){
            throw new ServiceException(400, "Car have an id ", null);
        }
        return carToCarDTOMapper.toDTO(carRepository.updateCar(carToCarDTOMapper.toEntity(carDTO)));
    }

    @Override
    public void deleteCarById(final Long id) {
        carRepository.deleteCarById(id);
    }
}