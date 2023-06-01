import java.io.*;
import java.util.*;

public class List_of_auto {
    private ArrayList<Auto> all_auto = new ArrayList<>();

    public List_of_auto(String s) {
        File file = new File(s);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                int id = Integer.parseInt(line);
                int price = Integer.parseInt(br.readLine());
                String brand = br.readLine();
                String name = br.readLine();
                int year = Integer.parseInt(br.readLine());
                String condition = br.readLine();
                double mileage = Double.parseDouble(br.readLine());
                String color = br.readLine();
                String vin = br.readLine();
                int number_of_party = Integer.parseInt(br.readLine());
                if (vin.length() == 17 & (condition.equals("clean vehicle") | condition.equals("salvage insurance"))) {
                    Auto new_auto = new Auto(id, price, brand, name, year, condition, mileage, color, vin, number_of_party);
                    add_auto(new_auto);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List_of_auto() {
        this.all_auto = new ArrayList<>();
    }

    public ArrayList<Auto> getAll_auto() {
        return all_auto;
    }

    public void setAll_auto(ArrayList<Auto> all_auto) {
        this.all_auto = all_auto;
    }

    public void add_auto(Auto c) {
        this.all_auto.add(c);
    }

    public void get_equals_VIN() {
        for (int i = 0; i < this.all_auto.size(); i++) {
            if (i != this.all_auto.size() - 1) {
                for (int g = i + 1; g < this.all_auto.size(); g++) {
                    if (this.all_auto.get(i).is_VIN_equals(this.all_auto.get(g))) {
                        System.out.println("Идентификационные номера " + this.all_auto.get(i).getId() + " и " + this.all_auto.get(g).getId() + " совпадают");
                    }
                }
            }

        }
    }

    public void get_equals_numbers_of_party() {

        boolean is_party_equals = false;
        for (int i = 0; i < this.all_auto.size(); i++) {
            if (i != this.all_auto.size() - 1) {
                for (int g = i + 1; g < this.all_auto.size(); g++) {
                    if (this.all_auto.get(i).is_number_of_party_equals(this.all_auto.get(g)) & !this.all_auto.get(i).is_VIN_equals(this.all_auto.get(g))) {
                        is_party_equals = true;
                        System.out.println("Авто " + this.all_auto.get(i).getId() + " и " + this.all_auto.get(g).getId() + " выпущены в одной партии: " + this.all_auto.get(i).getNumber_of_party());
                        try (PrintWriter pw = new PrintWriter("equals_number_of_party.txt")) {
                            pw.println("Авто " + this.all_auto.get(i).getId() + " и " + this.all_auto.get(g).getId() + " выпущены в одной партии: " + this.all_auto.get(i).getNumber_of_party());
                            ;
                        } catch (IOException exc) {
                            System.out.println("Ошибка");
                        }
                    }
                }
            }
        }
        if (!is_party_equals) {
            System.out.println("Авто с одниковыми партиями не найдены");
            try (PrintWriter pw = new PrintWriter("equals_number_of_party.txt")) {
                pw.println("Авто с одниковыми партиями не найдены");
            } catch (IOException exc) {
                System.out.println("Ошибка");
            }
        }
    }

    public double get_avg_price_of_brand(String brand) {
        int count = 0;
        double avg = 0;
        for (Auto auto : this.all_auto) {
            if (auto.getBrand().equals(brand)) {
                count++;
                avg += auto.getPrice();
            }
        }
        if (count == 0) {
            return 0.0;
        } else {
            return Math.round(avg / count * 100.0) / 100.0;
        }

    }

    public void avg_price_of_brand() {
        HashMap<String, Double> all_brand = new HashMap<>();

        for (Auto auto : this.all_auto) {
            if (all_brand.get(auto.getBrand()) == null) {
                double avg = get_avg_price_of_brand(auto.getBrand());
                all_brand.put(auto.getBrand(), avg);
            }
        }
        Object[][] result = new Object[all_brand.size()][2];
        Object[] keys = all_brand.keySet().toArray();
        Object[] values = all_brand.values().toArray();
        for (int i = 0; i < keys.length; i++) {
            result[i][0] = keys[i];
            result[i][1] = values[i];
        }

        Arrays.sort(result, Comparator.comparingDouble(o -> (double) o[1]));
        try (PrintWriter pw = new PrintWriter("avg_price_auto.txt")) {
            System.out.println("Список марок автомобилей по возрастанию средней цены: ");
            pw.println("Список марок автомобилей по возрастанию средней цены: ");
            for (Object[] objects : result) {
                System.out.println(objects[0] + ": " + objects[1]);
                pw.println(objects[0] + ": " + objects[1]);
            }
        } catch (IOException exc) {
            System.out.println("Ошибка");
        }
    }

    public void get_histogram_of_color() {
        HashSet<String> all_colors = new HashSet<>();

        for (Auto i : this.all_auto) {
            if (!i.getColor().contains(" ") & !i.getColor().contains(":")) {
                all_colors.add(i.getColor());
            }

        }
        try (PrintWriter pw = new PrintWriter("histogram_of_colors.txt")) {
            for (String i : all_colors) {
                HashMap<String, Integer> colors_of_brand = new HashMap<>();
                for (Auto g : all_auto) {
                    if (g.getColor().contains(i)) {
                        colors_of_brand.merge(g.getBrand(), 1, Integer::sum);
                    }
                }

                System.out.println(i + ":");
                pw.println(i + ":");
                Object[][] result = new Object[colors_of_brand.size()][2];
                Object[] keys = colors_of_brand.keySet().toArray();
                Object[] values = colors_of_brand.values().toArray();
                for (int j = 0; j < keys.length; j++) {
                    result[j][0] = keys[j];
                    result[j][1] = values[j];
                }

                Arrays.sort(result, Comparator.comparingInt(o -> (int) o[1]));
                for (Object[] objects : result) {
                    System.out.println(objects[0] + " ".repeat(15 - objects[0].toString().length()) + "■".repeat(Integer.parseInt(objects[1].toString())));
                    pw.println(objects[0] + " ".repeat(15 - objects[0].toString().length()) + "■".repeat(Integer.parseInt(objects[1].toString())));
                }
                System.out.println();
                pw.println();


            }
        } catch (IOException exc) {
            System.out.println("Ошибка");
        }
    }

    public Object[][] get_year_mileage(String brand) {
        HashMap<Integer, Double> year_mileage = new HashMap<>();

        for (Auto i : all_auto) {
            if (i.getBrand().equals(brand)) {
                year_mileage.computeIfAbsent(i.getYear(), k -> i.getMileage());
            }

        }
        Object[][] result = new Object[year_mileage.size()][2];
        Object[] keys = year_mileage.keySet().toArray();
        Object[] values = year_mileage.values().toArray();
        for (int j = 0; j < keys.length; j++) {
            result[j][0] = keys[j];
            result[j][1] = values[j];
        }
        return result;
    }

    public int[] get_max_min_of_brand(String brand) {
        int max_price = 0;
        int min_price = 0;
        for (Auto i : all_auto) {
            if (i.getBrand().equals(brand)) {
                if (min_price == 0 | min_price > i.getPrice()) {
                    min_price = i.getPrice();
                }
                if (i.getPrice() > max_price) {
                    max_price = i.getPrice();
                }
            }
        }
        return new int[]{max_price, min_price, (int) get_avg_price_of_brand(brand)};
    }

    public ArrayList<String> get_all_brand() {
        HashSet<String> all_brand = new HashSet<>();

        for (Auto i : all_auto) {
            all_brand.add(i.getBrand());
        }

        return new ArrayList<>(all_brand);
    }

    public void max_min_price_and_year() {
        ArrayList<String> all_brands = get_all_brand();

        try (PrintWriter pw = new PrintWriter("price_of_brands_and_mileage.txt")) {
            for (String s : all_brands) {
                int[] max_min = get_max_min_of_brand(s);
                Object[][] year_mileage = get_year_mileage(s);
                Arrays.sort(year_mileage, Comparator.comparingInt(o -> (int) o[0]));
                System.out.println("—".repeat(25) + s.toUpperCase() + "—".repeat(25));
                System.out.println("_".repeat(23));
                System.out.printf("%-16s%7s%n", "| " + s.toUpperCase(), "price |");
                System.out.println("—".repeat(23));
                System.out.printf("%-16s%7s%n", "| max", max_min[0] + " |");
                System.out.println("—".repeat(23));
                System.out.printf("%-16s%7s%n", "| min", max_min[1] + " |");
                System.out.println("—".repeat(23));
                System.out.printf("%-16s%7s%n", "| avg", max_min[2] + " |");
                System.out.println("—".repeat(23));
                System.out.println();
                System.out.println("_".repeat(22));
                System.out.printf("%-7s%15s%n", "| year ", "mileage |");

                pw.println("—".repeat(25) + s.toUpperCase() + "—".repeat(25));
                pw.println("_".repeat(23));
                pw.printf("%-16s%7s%n", "| " + s.toUpperCase(), "price |");
                pw.println("—".repeat(23));
                pw.printf("%-16s%7s%n", "| max", max_min[0] + " |");
                pw.println("—".repeat(23));
                pw.printf("%-16s%7s%n", "| min", max_min[1] + " |");
                pw.println("—".repeat(23));
                pw.printf("%-16s%7s%n", "| avg", max_min[2] + " |");
                pw.println("—".repeat(23));
                pw.println();
                pw.println("_".repeat(22));
                pw.printf("%-7s%15s%n", "| year ", "mileage |");
                for (Object[] objects : year_mileage) {
                    System.out.println("—".repeat(22));
                    System.out.printf("%-7s%15s%n", "| " + objects[0], objects[1] + " |");

                    pw.println("—".repeat(22));
                    pw.printf("%-7s%15s%n", "| " + objects[0], objects[1] + " |");

                }
                System.out.println("—".repeat(22));
                System.out.println("—".repeat(50 + s.length()) + "\n");

                pw.println("—".repeat(22));
                pw.println("—".repeat(50 + s.length()) + "\n");
            }
        } catch (IOException exc) {
            System.out.println("Ошибка");
        }

    }


}
