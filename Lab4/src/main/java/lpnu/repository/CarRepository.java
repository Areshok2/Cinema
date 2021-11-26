package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Car;
import lpnu.entity.Item;
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
public class CarRepository {

    private static Long lastId = 0L;
    private static List<Car> savedCars;

    @PostConstruct
    public void init(){

        final Path file = Paths.get("cars.txt");
        try {
            final String savedCarsAsString = Files.readString(file, StandardCharsets.UTF_16);
            savedCars = Util.deserialize(savedCarsAsString, new TypeReference<List<Car>>() {});
        } catch (final Exception e){
            System.out.println("We have an issue car1");
            savedCars = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("cars.txt");
        try {
            Files.writeString(file, Util.serialize(savedCars), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue car2");
        }
    }
//
//    @PostConstruct
//    public void init(){
//        savedCars = new ArrayList<>();
//    }
//
//    private static Long lastId = 0L;
//    private static List<Car> savedCars;

    public Car getCarById(final Long id){
        return savedCars.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Car with id: " + id + " not found ", null));
    }

    public List<Car> getAllCars(){
        return savedCars;
    }

    public Car createCar(final Car car){
        if(car.getId() != null){
            throw new ServiceException(400, "Car shouldn't have an id ", null);
        }

        if(car.getYear() < 2000){
            throw new ServiceException(400,"We don't service machines until 2000",null);
        }

        ++lastId;
        car.setId(lastId);
        savedCars.add(car);
        return car;
    }

    public Car updateCar(final Car car){
        if(car.getId() == null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }

        final Car savedCar = getCarById(car.getId());

        savedCar.setType(car.getType());
        savedCar.setName(car.getName());
        savedCar.setYear(car.getYear());

        return savedCar;
    }

    public void deleteCarById(final Long id){
        if(id == null){
            throw new ServiceException(400, "Id isn't specified", null);
        }

        savedCars.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "Car with id: " + id + " not found ", null));

        savedCars = savedCars.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public static boolean checkCarId(Long id) {
        if(!savedCars.isEmpty()) {
            for (Car car: savedCars) {
                if (car.getId().equals(id)) return true;
            }
        }
        return false;
    }
}
