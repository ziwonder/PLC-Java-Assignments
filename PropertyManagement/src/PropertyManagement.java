import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyManagement {
    private PropertyManagementDAO propertyManagementDAO;

    public PropertyManagement(PropertyManagementDAO propertyManagementDAO) {
        this.propertyManagementDAO = propertyManagementDAO;
    }

    public List<Apartment> getApartments() {
        return propertyManagementDAO.getApartments();
    }

    public Apartment getApartment(int id) {
        return propertyManagementDAO.getApartmentById(id);
    }

    public void addApartment(Apartment apartment) {
        propertyManagementDAO.saveApartment(apartment);
    }

    public void deleteApartment(int id) {
        propertyManagementDAO.deleteApartment(id);
    }

    public int totalNumberOfAllApartments() {
        return getApartments().size();
    }

    public long totalNumberOfOwnedApartments() {
        return getApartments().stream()
                .filter(apartment -> apartment instanceof OwnedApartment)
                .count();
    }

    public long totalNumberOfRentedApartments() {
        return getApartments().stream()
                .filter(apartment -> apartment instanceof RentedApartment)
                .count();
    }

    public double averageMonthlyTotalCost() {
        double totalCost = 0;
        for (Apartment apartment : getApartments()) {
            totalCost += apartment.getTotalCost();
        }
        return totalCost / getApartments().size();
    }

    public List<Integer> oldestApartmentId() {
        List<Apartment> apartments = getApartments();
        if (apartments.isEmpty()) {
            return new ArrayList<>();
        }
        int oldest = getApartments().getFirst().getAge();
        for (int i = 0; i < getApartments().size(); i++) {
            if (oldest < getApartments().get(i).getAge()) {
                oldest = getApartments().get(i).getAge();
            }
        }
        int finalOldest = oldest;
        return apartments.stream()
                .filter(a -> a.getAge() == finalOldest)  // Filter apartments with the oldest age
                .map(Apartment::getId)  // Map to apartment IDs
                .collect(Collectors.toList());

    }
}