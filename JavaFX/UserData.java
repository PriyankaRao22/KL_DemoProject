package JavaFX;

public class UserData {
    private  int id;
    private String userName,password,role;

    public UserData(int id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserData(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserData() {
        super();
    }

    public UserData(String userName)
    {
     this.userName=userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
