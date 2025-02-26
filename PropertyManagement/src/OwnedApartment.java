import java.text.DecimalFormat;

public class OwnedApartment extends Apartment {
    private double operatingCost;
    private double maintenanceReserve;

    public OwnedApartment(int id, double area, int numberOfRooms, int floor, int yearOfConstruction,
            int postalCode, String street, int houseNumber, int apartmentNumber,
            double operatingCost, double maintenanceReserve) {
        super(id, area, numberOfRooms, floor, yearOfConstruction, postalCode, street, houseNumber, apartmentNumber);
        if (operatingCost < 0 || maintenanceReserve < 0) {
            throw new IllegalArgumentException("Error: Invalid parameter.");
        }
        this.operatingCost = operatingCost;
        this.maintenanceReserve = maintenanceReserve;
    }

    @Override
    double getTotalCost() {
        double cost = operatingCost * getArea() + maintenanceReserve * getArea();
        return cost + (0.02 * getFloor()) * cost;
    }

    @Override
    public String toString() {
        DecimalFormat df = Apartment.getDecimalFormat(); // "%-19s%s%n" String.format
        return String.format(
                "%-19s%s%n" +
                        "%-19s%d%n" +
                        "%-19s%s%n" +
                        "%-19s%d%n" +
                        "%-19s%d%n" +
                        "%-19s%d%n" +
                        "%-19s%d%n" +
                        "%-19s%s%n" +
                        "%-19s%d%n" +
                        "%-19s%d%n" +
                        "%-19s%s%n" +
                        "%-19s%s%n",
                "Type:", "OA",
                "Id:", getId(),
                "Area:", df.format(getArea()),
                "Rooms:", getNumberOfRooms(),
                "Floor:", getFloor(),
                "Year Built:", getYearOfConstruction(),
                "Postal Code:", getPostalCode(),
                "Street:", getStreet(),
                "House Number:", getHouseNumber(),
                "Apartment:", getApartmentNumber(),
                "Operating Costs:", df.format(operatingCost),
                "Reserve Fund:", df.format(maintenanceReserve));

    }
}
