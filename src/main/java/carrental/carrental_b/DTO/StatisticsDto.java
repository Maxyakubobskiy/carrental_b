package carrental.carrental_b.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatisticsDto {
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
