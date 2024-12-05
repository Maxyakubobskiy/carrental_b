package carrental.carrental_b.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarNoAvailDto {
    private Long carId;
    private String brand;
    private String model;
    private String bodyType;
    private String engineCapacity;
    private String fuelType;
    private int year;
    private OrderDto order;

    public CarNoAvailDto(Long carId, String brand, String model, String bodyType, String engineCapacity, String fuelType, int year) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.year = year;
    }
}
