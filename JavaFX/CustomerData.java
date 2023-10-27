package JavaFX;

public class CustomerData {
    private  int Id;
    private String Name, MobileNo,Password;
    public CustomerData(int id) {
        Id = id;
    }


    public CustomerData(int id, String name, String mobileNo, String password) {
        Id = id;
        Name = name;
        MobileNo = mobileNo;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
