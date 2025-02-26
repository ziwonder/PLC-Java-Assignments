//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the
        // highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        OwnedApartment myHouse = new OwnedApartment(1, 200, 4, 1, 1999,
                1070, "Burgasse", 22, 213,
                30, 20);
        // System.out.printf(myHouse.toString());

        PropertyManagement p1 = new PropertyManagement(new PropertyManagementSerializationDAO("fil.dat"));
        p1.addApartment(myHouse);
        System.out.println(p1.getApartments());
    }
}
