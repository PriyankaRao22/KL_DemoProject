package JavaFX;

public class SalesData {
    private  int id,items,amount;
    private  String date,customer;

    public SalesData() {
        super();
    }

    public SalesData(int id) {
        this.id = id;
    }

    public SalesData(int id, int items, int amount, String date, String customer) {
        this.id = id;
        this.items = items;
        this.amount = amount;
        this.date = date;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
