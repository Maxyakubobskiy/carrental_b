package carrental.carrental_b.DTO;

import carrental.carrental_b.models.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long carId;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String bodyType;
    private String transmissionType;
    private String fuelType;
    private String engineCapacity;
    private String horsePower;
    private Boolean available;
    private Double price;
    private String carClass;
    private String logoPhotoUrl;
    private List<ReviewNewDto> reviews;
    private List<Photo> photos;
}
