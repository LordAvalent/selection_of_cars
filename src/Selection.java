import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Selection{
    private String year;
    private ArrayList<String> color;
    private String brand;
    private String condition;
    private List_of_auto all_auto;
    private String order_by;

    public Selection(String year, ArrayList<String> color, String brand, String condition, List_of_auto all_auto, String order_by) {
        this.year = year;
        this.color = color;
        this.brand = brand;
        this.condition = condition;
        this.all_auto = all_auto;
        this.order_by = order_by;
    }

    public Selection() {
        this.year = "";
        this.color = new ArrayList<>();
        this.brand = "";
        this.condition = "";
        this.all_auto = new List_of_auto();
        this.order_by = "";
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List_of_auto getAll_auto() {
        return all_auto;
    }

    public void setAll_auto(List_of_auto all_auto) {
        this.all_auto = all_auto;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public boolean is_color(Auto auto) {
        boolean is_color = false;
        String color_of_auto = auto.getColor();
        for (String s: this.color) {
            if (color_of_auto.contains(s)) {
                is_color = true;
                break;
            }
        }
        return is_color;
    }

    public void get_Selection() throws Exception {
        ArrayList<Auto> selection = new ArrayList<>();
        String[] years = this.year.replaceAll(" ","").split("-");
        if (years.length == 1) {
            for (Auto c : all_auto.getAll_auto()) {
                if (c.getYear() == Integer.parseInt(years[0]) & c.getBrand().contains(this.brand) & c.getCondition().contains(this.condition) & is_color(c)) {
                    selection.add(c);
                }
            }
        } else {
            for(Auto c: all_auto.getAll_auto()) {
                if (c.getYear() >= Integer.parseInt(years[0]) & c.getYear() <= Integer.parseInt(years[1]) & c.getBrand().contains(this.brand) & c.getCondition().contains(this.condition) & is_color(c)) {
                    selection.add(c);
                }
            }
        }
        switch (this.order_by) {
            case "по возрастанию цены" -> selection.sort(Comparator.comparing(Auto::getPrice));
            case "по убыванию цены" -> {
                selection.sort(Comparator.comparing(Auto::getPrice));
                Collections.reverse(selection);
            }
            case "по возрастанию пробега" -> selection.sort(Comparator.comparing(Auto::getMileage));
            case "по убыванию пробега" -> {
                selection.sort(Comparator.comparing(Auto::getMileage));
                Collections.reverse(selection);
            }
            default -> {
                System.out.println("Выбрана не правильная сортировка");
                throw new Exception("Ошибка ввода");
            }
        }
        if (selection.size() > 0) {
            try (PrintWriter pw = new PrintWriter("selection.txt")) {
                pw.println("Параметры: " + this.year + ", " + this.color + ", " + this.brand + ", " + this.condition + ", " + this.order_by);
                pw.println("Найдено: " + selection.size() + "\n");
                System.out.println("Найдено: " + selection.size() + "\n");
                selection.forEach(pw::println);
            } catch (IOException exc) {
                System.out.println("Ошибка");
            }
            selection.forEach(System.out::println);
        } else {
            System.out.println("По заданным параметрам результатов не найдено");
            try (PrintWriter pw = new PrintWriter("selection.txt")) {
                pw.println("По заданным параметрам результатов не найдено");
            } catch (IOException exc) {
                System.out.println("Ошибка");
            }
        }


    }
}
