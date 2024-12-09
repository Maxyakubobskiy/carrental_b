package carrental.carrental_b.criteria;

import lombok.Data;

import java.util.List;

@Data
public class FilterCriteria {
    private List<String> carClasses;
    private List<String> marks;
    private List<String> fuelTypes;
    private int yearFrom;
    private int yearTo;

    public boolean isEmpty() {
        return (isNullOrEmpty(fuelTypes) &&
                isNullOrEmpty(carClasses) &&
                isNullOrEmpty(marks) &&
                yearFrom == 0 &&
                yearTo == 0);
    }

    private boolean isNullOrEmpty(List<String> list) {
        return list == null || list.isEmpty();
    }
}

