package carrental.carrental_b.BD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
public class DatabaseInitializer //implements CommandLineRunner
{

//    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    @Transactional
    public void run(String... args) {

        List<String> carsqlQueries = List.of(
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (1, 'Kia', 'Sportage', 2020, 'Silver', 'SUV', 'Automatic', 'Petrol', '2.0L', 180, true, 'kia_sportage.png', 30000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (2, 'Audi', 'Q5', 2021, 'Black', 'SUV', 'Automatic', 'Diesel', '2.0L', 200, true, 'audi_q5.png', 50000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (3, 'Citroen', 'C5 Aircross', 2017, 'Blue', 'SUV', 'Automatic', 'Petrol', '1.6L', 180, true, 'citroen_c5_aircross.png', 35000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (4, 'Ford', 'Explorer', 2020, 'Red', 'SUV', 'Automatic', 'Petrol', '3.0L', 300, true, 'ford_explorer.png', 55000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (5, 'Honda', 'CR-V', 2016, 'White', 'SUV', 'Automatic', 'Petrol', '1.5L', 190, true, 'honda_cr_v.png', 40000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (6, 'Hyundai', 'Santa Fe', 2020, 'Grey', 'SUV', 'Automatic', 'Diesel', '2.2L', 200, true, 'hyundai_santa_fe.png', 42000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (7, 'Land Rover', 'Discovery', 2021, 'Green', 'SUV', 'Automatic', 'Diesel', '3.0L', 250, true, 'land_rover_discovery.png', 75000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (8, 'Lexus', 'RX', 2019, 'Silver', 'SUV', 'Automatic', 'Petrol', '3.5L', 290, true, 'lexus_rx.png', 60000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (9, 'Mazda', 'CX-5', 2015, 'Blue', 'SUV', 'Automatic', 'Petrol', '2.5L', 190, true, 'mazda_cx_5.png', 35000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (10, 'Mercedes-Benz', 'GLE', 2021, 'Black', 'SUV', 'Automatic', 'Petrol', '3.0L', 320, true, 'mercedes_gle.png', 80000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (11, 'Mini', 'Countryman', 2020, 'Red', 'SUV', 'Automatic', 'Petrol', '2.0L', 192, true, 'mini_countryman.png', 38000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (12, 'Toyota', 'Sienna', 2019, 'White', 'Minivan', 'Automatic', 'Petrol', '3.5L', 296, true, 'toyota_sienna.png', 35000.0, 'Minivan');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (13, 'Honda', 'Odyssey', 2010, 'Grey', 'Minivan', 'Automatic', 'Petrol', '3.5L', 280, true, 'honda_odyssey.png', 38000.0, 'Minivan');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (14, 'Chrysler', 'Pacifica', 2020, 'Blue', 'Minivan', 'Automatic', 'Petrol', '3.6L', 287, true, 'chrysler_pacifica.png', 32000.0, 'Minivan');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (15, 'Kia', 'Carnival', 2011, 'Silver', 'Minivan', 'Automatic', 'Diesel', '2.2L', 202, true, 'kia_carnival.png', 42000.0, 'Minivan');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (16, 'Suzuki', 'Swift', 2021, 'Blue', 'Hatchback', 'Manual', 'Petrol', '1.2L', 90, true, 'suzuki_swift.png', 20000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (17, 'Tesla', 'Model 3', 2017, 'Red', 'Sedan', 'Automatic', 'Electro', 'n/a', 450, true, 'tesla_model_3.png', 60000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (18, 'Toyota', 'Corolla', 2020, 'White', 'Sedan', 'Automatic', 'Petrol', '1.8L', 140, true, 'toyota_corolla.png', 25000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (19, 'Volkswagen', 'Golf', 2010, 'Silver', 'Hatchback', 'Manual', 'Petrol', '1.4L', 125, true, 'volkswagen_golf.png', 23000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (20, 'Kia', 'Ceed', 2021, 'Blue', 'Hatchback', 'Manual', 'Petrol', '1.6L', 120, true, 'kia_ceed.png', 22000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (21, 'Audi', 'A4', 2021, 'Silver', 'Sedan', 'Automatic', 'Diesel', '2.0L', 190, true, 'audi_a4.png', 45000.0, 'Business');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (22, 'Citroen', 'Berlingo', 2013, 'White', 'Minivan', 'Manual', 'Diesel', '1.5L', 100, true, 'citroen_berlingo.png', 30000.0, 'Commercial');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (23, 'Ford', 'Transit', 2009, 'White', 'Van', 'Manual', 'Diesel', '2.0L', 170, true, 'ford_transit.png', 40000.0, 'Commercial');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (24, 'Honda', 'Jazz', 2005, 'Yellow', 'Hatchback', 'Automatic', 'Petrol', '1.3L', 100, true, 'honda_jazz.png', 20000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (25, 'Hyundai', 'i10', 2021, 'Blue', 'Hatchback', 'Manual', 'Petrol', '1.0L', 67, true, 'hyundai_i10.png', 18000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (26, 'Land Rover', 'Defender', 2021, 'Green', 'SUV', 'Automatic', 'Diesel', '3.0L', 250, true, 'land_rover_defender.png', 80000.0, 'Premium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (27, 'Lexus', 'ES', 2020, 'Silver', 'Sedan', 'Automatic', 'Petrol', '3.5L', 302, true, 'lexus_es.png', 50000.0, 'Business');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (28, 'Mazda', '6', 2021, 'Red', 'Sedan', 'Automatic', 'Petrol', '2.5L', 187, true, 'mazda_6.png', 35000.0, 'Business');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (29, 'Mercedes-Benz', 'CLA', 2018, 'White', 'Coupe', 'Automatic', 'Petrol', '2.0L', 221, true, 'mercedes_cla.png', 55000.0, 'Premium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (30, 'Mini', 'Clubman', 2020, 'Green', 'Hatchback', 'Automatic', 'Petrol', '2.0L', 192, true, 'mini_clubman.png', 33000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (31, 'Ford', 'Ranger', 2016, 'Red', 'Pickup', 'Automatic', 'Diesel', '2.0L', 210, true, 'ford_ranger.png', 40000.0, 'Pickup');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (32, 'Toyota', 'Hilux', 2020, 'Black', 'Pickup', 'Manual', 'Diesel', '2.4L', 150, true, 'toyota_hilux.png', 35000.0, 'Pickup');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (33, 'Renault', 'Megane', 2001, 'Grey', 'Hatchback', 'Automatic', 'Petrol', '1.5L', 115, true, 'renault_megane.png', 25000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (34, 'Skoda', 'Fabia', 2021, 'Blue', 'Hatchback', 'Manual', 'Petrol', '1.0L', 95, true, 'skoda_fabia.png', 20000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (35, 'Suzuki', 'Swift', 2020, 'Red', 'Hatchback', 'Manual', 'Petrol', '1.2L', 90, true, 'suzuki_swift_red.png', 18000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (36, 'Tesla', 'Model 3', 2017, 'White', 'Sedan', 'Automatic', 'Electro', 'n/a', 450, true, 'tesla_model_3_white.png', 60000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (37, 'Toyota', 'Corolla', 2014, 'Black', 'Sedan', 'Automatic', 'Petrol', '1.8L', 140, true, 'toyota_corolla_black.png', 27000.0, 'Medium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (38, 'Volkswagen', 'Passat', 2020, 'Silver', 'Sedan', 'Automatic', 'Diesel', '2.0L', 190, true, 'volkswagen_passat.png', 40000.0, 'Business');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (39, 'Kia', 'Sorento', 2021, 'White', 'SUV', 'Automatic', 'Diesel', '2.2L', 202, true, 'kia_sorento.png', 42000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (40, 'Audi', 'A3 Cabriolet', 2021, 'Blue', 'Cabriolet', 'Automatic', 'Petrol', '1.5L', 150, true, 'audi_a3_cabriolet.png', 48000.0, 'Cabriolet');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (41, 'Citroen', 'C3 Pluriel', 2020, 'Yellow', 'Cabriolet', 'Manual', 'Petrol', '1.4L', 75, true, 'citroen_c3_pluriel.png', 18000.0, 'Cabriolet');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (42, 'Ford', 'Mustang', 2019, 'Red', 'Cabriolet', 'Automatic', 'Petrol', '5.0L', 450, true, 'ford_mustang.png', 55000.0, 'Cabriolet');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (43, 'Honda', 'S2000', 2020, 'Silver', 'Cabriolet', 'Manual', 'Petrol', '2.0L', 240, true, 'honda_s2000.png', 40000.0, 'Cabriolet');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (44, 'Hyundai', 'Veloster', 2021, 'Orange', 'Cabriolet', 'Automatic', 'Petrol', '1.6L', 147, true, 'hyundai_veloster.png', 30000.0, 'Cabriolet');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (45, 'Land Rover', 'Range Rover Evoque', 2003, 'Grey', 'SUV', 'Automatic', 'Diesel', '2.0L', 150, true, 'land_rover_range_rover_evoque.png', 62000.0, 'Offroad');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (46, 'Lexus', 'LC', 2021, 'Black', 'Coupe', 'Automatic', 'Petrol', '5.0L', 471, true, 'lexus_lc.png', 105000.0, 'Premium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (47, 'Mazda', 'MX-5', 2000, 'Red', 'Cabriolet', 'Manual', 'Petrol', '2.0L', 184, true, 'mazda_mx-5.png', 32000.0, 'Cabriolet');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (48, 'Mercedes-Benz', 'S-Class', 2021, 'White', 'Sedan', 'Automatic', 'Petrol', '4.0L', 603, true, 'mercedes_s-class.png', 130000.0, 'Premium');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (49, 'Mini', 'Cooper', 2024, 'Blue', 'Hatchback', 'Automatic', 'Petrol', '1.5L', 136, true, 'mini_cooper.png', 25000.0, 'Economy');",
                "INSERT INTO cars (car_id, brand, model, year, color, body_type, transmission_type, fuel_type, engine_capacity, horse_power, available, logo_photo_url, price, car_class) VALUES (50, 'Nissan', 'Leaf', 2024, 'Silver', 'Hatchback', 'Automatic', 'Electro', 'n/a', 147, true, 'nissan_leaf.png', 35000.0, 'Economy');"
        );

        for (String sqlQuery : carsqlQueries) {
            entityManager.createNativeQuery(sqlQuery).executeUpdate();
        }

        List<String> photoCarsqlQueries = List.of(
                "INSERT INTO photos (id, car_id, url) VALUES (1, 1, 'kia_sportage.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (2, 1, 'kia_sportage.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (3, 2, 'audi_q5.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (4, 2, 'audi_q5.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (5, 3, 'citroen_c5_aircross.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (6, 3, 'citroen_c5_aircross.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (7, 4, 'ford_explorer.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (8, 4, 'ford_explorer.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (9, 5, 'honda_cr_v.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (10, 5, 'honda_cr_v.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (11, 6, 'hyundai_santa_fe.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (12, 6, 'hyundai_santa_fe.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (13, 7, 'land_rover_discovery.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (14, 7, 'land_rover_discovery.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (15, 8, 'lexus_rx.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (16, 8, 'lexus_rx.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (17, 9, 'mazda_cx_5.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (18, 9, 'mazda_cx_5.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (19, 10, 'mercedes_gle.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (20, 10, 'mercedes_gle.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (21, 11, 'mini_countryman.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (22, 11, 'mini_countryman.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (23, 12, 'toyota_sienna.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (24, 12, 'toyota_sienna.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (25, 13, 'honda_odyssey.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (26, 13, 'honda_odyssey.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (27, 14, 'chrysler_pacifica.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (28, 14, 'chrysler_pacifica.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (29, 15, 'kia_carnival.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (30, 15, 'kia_carnival.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (31, 16, 'suzuki_swift.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (32, 16, 'suzuki_swift.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (33, 17, 'tesla_model_3.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (34, 17, 'tesla_model_3.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (35, 18, 'toyota_corolla.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (36, 18, 'toyota_corolla.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (37, 19, 'volkswagen_golf.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (38, 19, 'volkswagen_golf.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (39, 20, 'kia_ceed.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (40, 20, 'kia_ceed.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (41, 21, 'audi_a4.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (42, 21, 'audi_a4.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (43, 22, 'citroen_berlingo.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (44, 22, 'citroen_berlingo.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (45, 23, 'ford_transit.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (46, 23, 'ford_transit.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (47, 24, 'honda_jazz.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (48, 24, 'honda_jazz.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (49, 25, 'hyundai_i10.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (50, 25, 'hyundai_i10.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (51, 26, 'land_rover_defender.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (52, 26, 'land_rover_defender.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (53, 27, 'lexus_es.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (54, 27, 'lexus_es.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (55, 28, 'mazda_6.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (56, 28, 'mazda_6.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (57, 29, 'mercedes_cla.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (58, 29, 'mercedes_cla.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (59, 30, 'mini_clubman.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (60, 30, 'mini_clubman.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (61, 31, 'ford_ranger.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (62, 31, 'ford_ranger.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (63, 32, 'toyota_hilux.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (64, 32, 'toyota_hilux.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (65, 33, 'renault_megane.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (66, 33, 'renault_megane.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (67, 34, 'skoda_fabia.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (68, 34, 'skoda_fabia.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (69, 35, 'suzuki_swift_red.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (70, 35, 'suzuki_swift_red.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (71, 36, 'tesla_model_3_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (72, 36, 'tesla_model_3_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (73, 37, 'toyota_corolla_blue.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (74, 37, 'toyota_corolla_blue.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (75, 38, 'volkswagen_golf_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (76, 38, 'volkswagen_golf_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (77, 39, 'kia_ceed_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (78, 39, 'kia_ceed_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (79, 40, 'audi_a4_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (80, 40, 'audi_a4_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (81, 41, 'citroen_berlingo_blue.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (82, 41, 'citroen_berlingo_blue.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (83, 42, 'ford_transit_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (84, 42, 'ford_transit_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (85, 43, 'honda_jazz_red.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (86, 43, 'honda_jazz_red.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (87, 44, 'hyundai_i10_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (88, 44, 'hyundai_i10_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (89, 45, 'land_rover_defender_gray.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (90, 45, 'land_rover_defender_gray.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (91, 46, 'lexus_es_red.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (92, 46, 'lexus_es_red.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (93, 47, 'mazda_6_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (94, 47, 'mazda_6_white.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (95, 48, 'mercedes_cla_silver.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (96, 48, 'mercedes_cla_silver.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (97, 49, 'mini_clubman_green.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (98, 49, 'mini_clubman_green.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (99, 50, 'ford_ranger_black.png');",
                "INSERT INTO photos (id, car_id, url) VALUES (100, 50, 'ford_ranger_black.png');"
        );

        for (String sqlQuery : photoCarsqlQueries) {
            entityManager.createNativeQuery(sqlQuery).executeUpdate();
        }
    }
}

