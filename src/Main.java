/*
Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные пробелом:
Фамилия Имя Отчество номертелефона

Форматы данных:
фамилия, имя, отчество - строки
номертелефона - целое беззнаковое число без форматирования

Ввод всех элементов через пробел

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки,
обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают,
нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано,
пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><номер_телефона>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в формате: Фамилия Имя Отчество Номер Телефона");
        String input = scanner.nextLine();

        String[] inputData = input.split(" ");

        if (inputData.length != 4) {
            System.out.println("Ошибка: неверное количество данных.");
            return;
        }

        String lastName = inputData[0];
        String firstName = inputData[1];
        String middleName = inputData[2];
        String phoneNumberStr = inputData[3];

        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(phoneNumberStr);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: номер телефона должен быть целым числом.");
            return;
        }

        try {
            FileWriter fileWriter = new FileWriter(lastName + ".txt", true);
            fileWriter.write(lastName + " " +  firstName + " " + middleName + " " +  phoneNumber + "\n");
            fileWriter.close();
            System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
