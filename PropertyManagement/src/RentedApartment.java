import java.text.DecimalFormat;

public class RentedApartment extends Apartment {
    private double monthlyRent;
    private int numberOfTenants;

    public RentedApartment(int id, double area, int numberOfRooms, int floor, int yearOfConstruction,
            int postalCode, String street, int houseNumber, int apartmentNumber,
            double monthlyRent, int numberOfTenants) {
        super(id, area, numberOfRooms, floor, yearOfConstruction, postalCode, street, houseNumber, apartmentNumber);
        if (monthlyRent < 0 || numberOfTenants < 0)
            throw new IllegalArgumentException("Error: Invalid parameter.");
        this.monthlyRent = monthlyRent;
        this.numberOfTenants = numberOfTenants;
    }

    @Override
    double getTotalCost() {
        double cost = monthlyRent * getArea();
        double surcharge = (numberOfTenants - 1) * 0.025;
        if (surcharge > cost * 0.1)
            surcharge = cost * 0.1;
        return cost + surcharge;
    }

    @Override
    public String toString() {
        DecimalFormat df = Apartment.getDecimalFormat();
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
                "Type:", "RA",
                "Id:", getId(),
                "Area:", df.format(getArea()),
                "Rooms:", getNumberOfRooms(),
                "Floor:", getFloor(),
                "Year Built:", getYearOfConstruction(),
                "Postal Code:", getPostalCode(),
                "Street:", getStreet(),
                "House Number:", getHouseNumber(),
                "Apartment:", getApartmentNumber(),
                //getAddress().toString(),
                "Rent/m2:", df.format(monthlyRent),
                "Number of Tenants:", numberOfTenants);
    }
}
