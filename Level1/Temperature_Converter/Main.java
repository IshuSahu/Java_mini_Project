package Temperature_Converter;
import java.util.*;
class Main {
    public static void main(String[] args) {
        double Tempreture_convert;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the Tempreture  Value: ");
        double temperature = s.nextDouble();
        System.out.print("Enter unit of measurement (for Celcius(C) or for Fahrenheit(F)): ");
        char input = s.next().charAt(0);

        if (input == 'C' || input == 'c') {
            Tempreture_convert = (temperature * 9/5) + 32;
            System.out.println("tempreture " +temperature + " °C Converted temperature in Fahrenheit: " + Tempreture_convert + " °F");
        } else if (input == 'F' || input == 'f') {
            Tempreture_convert = (temperature - 32) * 5/9;
            System.out.println("tempreture " +temperature + " °F Converted temperature in Celcius: " + Tempreture_convert + " °C");
        } else {
            System.out.println("Invalid Input");
        }
    }
    
}