import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyManagementSerializationDAO implements PropertyManagementDAO {

    private String apartmentsFile;
    private List<Apartment> listOfApartments = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public PropertyManagementSerializationDAO(String apartmentsFile) {
        if (apartmentsFile == null)
            throw new IllegalArgumentException("Error: Invalid parameter.");
        this.apartmentsFile = apartmentsFile;
            try {
                FileInputStream inFile = new FileInputStream(apartmentsFile);
                ObjectInputStream in = new ObjectInputStream(inFile);
                listOfApartments = (List<Apartment>) in.readObject();
                in.close();
                inFile.close();
            } catch (FileNotFoundException e) {
                 e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Deserialization error: " + e.getMessage());
                System.exit(1);
            }

    }

    public List<Apartment> getApartments() {
        return listOfApartments;
    }

    public Apartment getApartmentById(int id) {
        List<Apartment> listOfApartments = getApartments();
        for (Apartment apartment : listOfApartments) {
            if (apartment.getId() == id) {
                return apartment;
            }
        }
        return null;
    }

    public void saveApartment(Apartment apartment) {
        if (getApartmentById(apartment.getId()) != null) {
            System.out.println("Error: Apartment already exists. (id=" + apartment.getId() + ")");
            return;
        }
        listOfApartments.add(apartment);
        try {
            FileOutputStream file = new FileOutputStream(apartmentsFile);
            ObjectOutputStream writer = new ObjectOutputStream(file);
            writer.writeObject(listOfApartments);
            writer.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
            System.exit(1);
        }
    }

    public void deleteApartment(int id) {
        Apartment apartment = getApartmentById(id);
        if (apartment == null) {
            throw new IllegalArgumentException("Error: Apartment not found. (id = " + id + ")");
        }
        listOfApartments.remove(apartment);
        try {
            FileOutputStream file = new FileOutputStream(apartmentsFile);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(listOfApartments);
            out.close();
    } catch (IOException e) {
        System.out.println("Serialization error: " + e.getMessage());
    }
    }

}