package carrental.carrental_b.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Order> orders;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Photo> photos;

    public boolean isAvailable() {
        return available != null && available;
    }
}
