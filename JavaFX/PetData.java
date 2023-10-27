package JavaFX;

public class PetData {
    private int id,age,availability;
    private double price;
    private String type,breed;

    public PetData() {
        super();
    }

    public PetData(int id) {
        this.id = id;
    }

    public PetData(int id, int age, double price, int availability, String type, String breed) {
        this.id = id;
        this.age = age;
        this.price = price;
        this.availability = availability;
        this.type = type;
        this.breed = breed;
    }
    public PetData( int age, double price, int availability, String type, String breed) {

        this.age = age;
        this.price = price;
        this.availability = availability;
        this.type = type;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
