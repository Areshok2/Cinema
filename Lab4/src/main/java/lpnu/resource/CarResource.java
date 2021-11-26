package lpnu.resource;

import lpnu.dto.CarDTO;
import lpnu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarResource {
    @Autowired
    private CarService carService;

    @GetMapping("/users/{id}/cars/{id}")
    public CarDTO getCarById(final @PathVariable Long id){
        return carService.getCarById(id);
    }

    @GetMapping("/users/{id}/cars")
    public List<CarDTO> getAllCar(){
        return carService.getAllCars();
    }

    @PostMapping("/users/{id}/cars")
    public CarDTO createCar(final @RequestBody CarDTO carDTO, @PathVariable Long id){
        return carService.createCar(carDTO, id);
    }

    @PutMapping("/users/{id}/cars")
    public CarDTO updateCar(final @RequestBody CarDTO carDTO){
        return carService.updateCar(carDTO);
    }

    @DeleteMapping("/users/{id}/cars/{id}")
    public void deleteCarById(final @PathVariable Long id){
        carService.deleteCarById(id);
    }
}