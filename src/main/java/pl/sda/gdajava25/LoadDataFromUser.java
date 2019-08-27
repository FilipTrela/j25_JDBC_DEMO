package pl.sda.gdajava25;

import java.util.Scanner;

public class LoadDataFromUser {
    private final static Scanner SCANNER = new Scanner(System.in);

    public String askCommend() {
        System.out.println("Podaj komende [q to exit] : ");
        return SCANNER.nextLine();
    }


    public Long askId() {
        System.out.println("Podaj ID : ");
        return Long.parseLong(SCANNER.nextLine());
    }

    public String askName() {
        System.out.println("Podaj imie : ");
        return SCANNER.nextLine();
    }

    public int askAge() {
        System.out.println("Podaj wiek : ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public double askAverage() {
        System.out.println("Podaj średnią : ");
        return Double.parseDouble(SCANNER.nextLine());
    }

    public boolean askIsAlive() {
        System.out.println("Czy żyje? : [y/n]");
        String isAlive = null;
        do {
            isAlive = SCANNER.nextLine();
            if (isAlive.equalsIgnoreCase("y")) {
                return true;
            } else if (isAlive.equalsIgnoreCase("n")) {
                return false;
            }
        } while (!isAlive.equalsIgnoreCase("y") || !isAlive.equalsIgnoreCase("n"));
        return false;
    }
}
