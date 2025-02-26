import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Year;

public abstract class Apartment implements Serializable {
    private int id;
    private double area;
    private int numberOfRooms;
    private int floor;
    private int yearOfConstruction;
    private int postalCode;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    public Apartment(int id, double area, int numberOfRooms, int floor, int yearOfConstruction, int postalCode,
            String street, int houseNumber, int apartmentNumber) {
        if (id < 0 || area < 0 || floor < 0 || numberOfRooms < 0 || yearOfConstruction < 0 || postalCode < 0
                || street == null || houseNumber < 0 || apartmentNumber < 0) {
            throw new IllegalArgumentException("Error: Invalid parameter.");
        }
        if (yearOfConstruction > 2024)
            throw new IllegalArgumentException("Error:Invalid year of construction.");
        this.id = id;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.floor = floor;
        this.yearOfConstruction = yearOfConstruction;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    abstract double getTotalCost();

    public int getId() {
        return id;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getAge() {
        int year = Year.now().getValue();
        return year - yearOfConstruction;
    }

    double getArea() {
        return area;
    }

    public int getFloor() {
        return floor;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public static DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        return new DecimalFormat("0.00", dfs);
    }
}
