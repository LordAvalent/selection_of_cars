public class Auto {
    private int id;
    private int price;
    private String brand;
    private String name;
    private int year;
    private String condition;
    private double mileage;
    private String color;
    private String vin;
    private int number_of_party;

    public Auto(int id, int price, String brand, String name, int year, String condition, double mileage, String color, String vin, int number_of_party) {
        this.id = id;
        this.price = price;
        this.brand = brand;
        this.name = name;
        this.year = year;
        this.condition = condition;
        this.mileage = mileage;
        this.color = color;
        this.vin = vin;
        this.number_of_party = number_of_party;
    }

    public Auto() {
        this.id = 0;
        this.price = 0;
        this.brand = "none";
        this.name = "none";
        this.year = 0;
        this.condition = "none";
        this.mileage = 0;
        this.color = "none";
        this.vin = "none";
        this.number_of_party = 0;
    }

    public Auto(Auto c) {
        this.id = c.getId();
        this.price = c.getPrice();
        this.brand = c.getBrand();
        this.name = c.getName();
        this.year = c.getYear();
        this.condition = c.getCondition();
        this.mileage = c.getMileage();
        this.color = c.getColor();
        this.vin = c.getVin();
        this.number_of_party = c.getNumber_of_party();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getNumber_of_party() {
        return number_of_party;
    }

    public void setNumber_of_party(int number_of_party) {
        this.number_of_party = number_of_party;
    }

    @Override
    public String toString() {
        return "Индекс: " + this.id + ", Марка: " + this.brand + ", Модель: " + this.name + ", Год: " + this.year + ", Цвет: " + this.color + ", Состояние: " + this.condition + " VIN: " + this.vin.toUpperCase() + ", Цена: " + this.price + ", Пробег: " + this.mileage;
    }

    public boolean is_VIN_equals(Auto c) {
        return this.vin.equals(c.getVin());
    }

    public boolean is_number_of_party_equals(Auto c) {
        return this.number_of_party == c.getNumber_of_party();
    }

}
