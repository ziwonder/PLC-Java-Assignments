import java.util.List;

public class PropertyManagementClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Invalid parameter.");
            return;
        }

        String filename = args[0];
        String parameter = args[1];

        PropertyManagement propertyManagement = new PropertyManagement(
                new PropertyManagementSerializationDAO(filename));
        switch (parameter) {

            case "list":
                if (args.length == 2)
                    listApartments(propertyManagement);
                else if  (args.length == 3) //how to check args[2]
                    listApartment(propertyManagement, args[2]);
                else
                    System.out.println("Invalid parameter.");
                break;

            case "add":
                if (args.length == 14) {
                    if ("OA".equals(args[2])) {
                        addOwnedApartment(propertyManagement, args);
                    }
                    if ("RA".equals(args[2])) {
                        addRentedApartment(propertyManagement, args);
                    }
                }
                else
                    System.out.println("Error: Invalid parameter.");
                break;

            case "delete":
                if (args.length == 3) {
                    deleteApartment(propertyManagement, args[2]);
                }
                else
                    System.out.println("Error: Invalid parameter.");
                break;

            case "count":
                if (args.length == 2)
                    totalNumber(propertyManagement);
                else if (args.length == 3 && args[2].equals("RA"))
                    totalNumberRA(propertyManagement);
                else if (args.length == 3 && args[2].equals("OA")) {
                    totalNumberOA(propertyManagement);
                } else
                    System.out.println("Error: Invalid parameter.");
                break;

            case "meancosts":
                if (args.length == 2)
                    meanCosts(propertyManagement);
                else
                    System.out.println("Error: Invalid parameter.");
                break;

            case "oldest":
                if (args.length == 2)
                    oldest(propertyManagement);
                else
                    System.out.println("Error: Invalid parameter.");
                break;

            default:
                System.out.println("Error: Invalid parameter.");
        }
    }

    public static void listApartments(PropertyManagement propertyManagement) {
        List<Apartment> listOfApartments = propertyManagement.getApartments();
        for (Apartment apartment : listOfApartments) {
            System.out.println(apartment.toString());
        }
    }

    public static void addOwnedApartment(PropertyManagement propertyManagement, String[] args) { // valueOf
        try {
            int id = Integer.parseInt(args[3]);
            double area = Double.parseDouble(args[4]);
            int rooms = Integer.parseInt(args[5]);
            int floor = Integer.parseInt(args[6]);
            int yearOfConstruction = Integer.parseInt(args[7]);
            int postalCode = Integer.parseInt(args[8]);
            String street = args[9];
            int houseNumber = Integer.parseInt(args[10]);
            int apartmentNumber = Integer.parseInt(args[11]);
            double operatingCosts = Double.parseDouble(args[12]);
            double reserveFund = Double.parseDouble(args[13]);
            Apartment apartment = new OwnedApartment(id, area, rooms, floor, yearOfConstruction,
                    postalCode, street, houseNumber, apartmentNumber,
                    operatingCosts, reserveFund);
            propertyManagement.addApartment(apartment);
            System.out.println("Info: Apartment " + args[3] + " added.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid parameter.");
            return;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    public static void addRentedApartment (PropertyManagement propertyManagement, String[] args) {
        try {
            int id = Integer.parseInt(args[3]);
            double area = Double.parseDouble(args[4]);
            int rooms = Integer.parseInt(args[5]);
            int floor = Integer.parseInt(args[6]);
            int yearOfConstruction = Integer.parseInt(args[7]);
            int postalCode = Integer.parseInt(args[8]);
            String street = args[9];
            int houseNumber = Integer.parseInt(args[10]);
            int apartmentNumber = Integer.parseInt(args[11]);
            double monthlyRent = Double.parseDouble(args[12]);
            int NumberOfTenants = Integer.parseInt(args[13]);
            Apartment apartment = new RentedApartment(id, area, rooms, floor, yearOfConstruction,
                    postalCode, street, houseNumber, apartmentNumber,
                    monthlyRent, NumberOfTenants);
            propertyManagement.addApartment(apartment);
            System.out.println("Info: Apartment " + args[3] + " added.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid parameter.");
            return;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public static void listApartment(PropertyManagement propertyManagement, String args) {
        if (propertyManagement.getApartment(Integer.parseInt(args)) == null)
            return;
        System.out.println(propertyManagement.getApartment(Integer.parseInt(args)));
    }

    public static void deleteApartment (PropertyManagement propertyManagement, String args) {
        try {
            propertyManagement.deleteApartment(Integer.parseInt(args));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Info: Apartment " + args + " deleted.");
    }

    public static void totalNumber(PropertyManagement propertyManagement) {
        System.out.println(propertyManagement.totalNumberOfAllApartments());
    }

    public static void totalNumberRA(PropertyManagement propertyManagement) {
        System.out.println(propertyManagement.totalNumberOfRentedApartments());
    }

    public static void totalNumberOA(PropertyManagement propertyManagement) {
        System.out.println(propertyManagement.totalNumberOfOwnedApartments());
    }

    public static void meanCosts(PropertyManagement propertyManagement) {
        System.out.println(propertyManagement.averageMonthlyTotalCost());
    }

    public static void oldest(PropertyManagement propertyManagement) {
        for (Integer id : propertyManagement.oldestApartmentId())
            System.out.println("Id: " + id);
    }
}