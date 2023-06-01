import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        List_of_auto list_of_auto = new List_of_auto("data_auto.txt");
        System.out.println("Задание 1: ");
        list_of_auto.get_equals_VIN();

        System.out.println("\nЗадание 2:");
        list_of_auto.get_equals_numbers_of_party();

        System.out.println("\nЗадание 3:");
        list_of_auto.avg_price_of_brand();

        System.out.println("\nЗадание 4:");
        list_of_auto.get_histogram_of_color();

        System.out.println("\nЗадание 5:");
        list_of_auto.max_min_price_and_year();

        System.out.println("\nЗадание 6:");
        Scanner input = new Scanner(System.in);
        boolean is_error = true;
        while (is_error) {
            System.out.print("Введите возраст или диапазон лет (год - год): ");
            String years = input.nextLine();
            System.out.print("Введите цвет или несколько цветов через запятую: ");
            String colors_str = input.nextLine().toLowerCase();
            ArrayList<String> colors = new ArrayList<>(List.of(colors_str.replace(" ", "").split(",")));
            System.out.print("Введите марку авто: ");
            String brand = input.nextLine().toLowerCase();
            System.out.print("Введите состояние авто (clean vehicle, salvage insurance): ");
            String condition = input.nextLine().toLowerCase();
            System.out.print("Сортировать по (по возрастанию цены, по убыванию цены, по возрастанию пробега, по убыванию пробега): ");
            String order_by = input.nextLine().toLowerCase();
            try {
                Selection selection = new Selection(years, colors, brand, condition, list_of_auto, order_by);
                System.out.println("\nРезультат подбора: ");
                selection.get_Selection();
                is_error = false;
            } catch (Exception e) {
                System.out.println("Ошибка ввода данных");
            }
        }

    }
}