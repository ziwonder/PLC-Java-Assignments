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
        /*File file = new File(apartmentsFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
                this.listOfApartments = new ArrayList<>();
            } catch (IOException e) {
                System.out.println("Error creating the file: " + e.getMessage());
                System.exit(1);
            }
        } else {*/
            try {
                FileInputStream inFile = new FileInputStream(apartmentsFile);
                ObjectInputStream in = new ObjectInputStream(inFile);
                listOfApartments = (List<Apartment>) in.readObject();
                in.close();
                inFile.close();
            } catch (FileNotFoundException e) {
                //System.out.println("File not found, starting with an empty list.");
                 e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Deserialization error: " + e.getMessage());
                System.exit(1);
            }

        // this.listOfApartments = new ArrayList<>();
    }

    public List<Apartment> getApartments() {
        // List<Apartment> listOfApartments = new ArrayList<>();
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
        // List<Apartment> listOfApartments = getApartments();
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

/*
public void deleteApartment(int id) {
        boolean apartmentFound = false;

        Iterator<Apartment> iterator = listOfApartments.iterator();
    while (iterator.hasNext()) {
        Apartment apartment = iterator.next();
        if (apartment.getId() == id) {
            iterator.remove();
            apartmentFound = true;
            //Apartment::setIds(getAllIds().remove(id));
            break;
        }
    }
    if (!apartmentFound) {
        throw new IllegalArgumentException("Error: Apartment not found. (id=" + id + ")");
    }
        try (FileOutputStream file = new FileOutputStream(apartmentsFile);
                ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(listOfApartments);
            out.close();
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
    }
*/