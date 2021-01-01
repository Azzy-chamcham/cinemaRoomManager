package cinema;

import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scan.nextInt();
        char[][] Seats = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Seats[i][j] = 'S';
            }
        }
        int nbrPurchased = 0;
        int currIncome = 0;
        menu(Seats, rows, columns, nbrPurchased, currIncome);
    }

    public static void menu(char[][] Seats, int rows, int columns, int nbrPurchased, int currIncome) {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                showSeats(Seats, rows, columns, nbrPurchased, currIncome);
            case 2:
                buyTicket(rows, columns, Seats, nbrPurchased, currIncome);
            case 3:
                statistics(Seats, rows, columns, nbrPurchased, currIncome);
            case 0:
                return;
        }
    }


    public static void showSeats(char[][] Seats, int rows, int seatsRow, int nbrPurchased, int currIncome) {
        System.out.print("Cinema:\n");
        System.out.print("  ");
        for (int i = 1; i <= seatsRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatsRow; j++) {
                System.out.print(Seats[i][j] + " ");
            }
            System.out.print("\n");
        }
        menu(Seats, rows, seatsRow, nbrPurchased, currIncome);
    }

    public static int buyTicket(int rows, int columns, char[][] Seats, int nbrPurchased, int currIncome) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int yourRow = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int yourColumn = scan.nextInt();
        if (yourRow > rows || yourColumn > columns) {
            System.out.println("Wrong input!\n");
            return buyTicket(rows, columns, Seats, nbrPurchased, currIncome);
        } else if (Seats[yourRow - 1][yourColumn - 1] == 'B') {
            System.out.println("That ticket has already been purchased!\n");
            return buyTicket(rows, columns, Seats, nbrPurchased, currIncome);
        } else {
            System.out.println("Ticket price:" + " $" + ticketPrice(rows, columns, yourRow) + "\n");
            Seats[yourRow - 1][yourColumn - 1] = 'B';
            nbrPurchased = nbrPurchased + 1;
            currIncome = currIncome + ticketPrice(rows, columns, yourRow);
            menu(Seats, rows, columns, nbrPurchased, currIncome);
            return nbrPurchased;
        }
    }

    public static void totalIncome(int rows, int columns) {
        int totalIncome;
        if (rows * columns <= 60) {
            totalIncome = 10 * rows * columns;
        } else {
            totalIncome = rows / 2 * columns * 10 + (rows - rows / 2) * columns * 8;
        }
        System.out.println("Total income: $" + totalIncome);

    }

    public static int ticketPrice(int rows, int columns, int yourRow) {
        if (rows * columns <= 60) {
            return 10;
        } else if (yourRow <= rows / 2) {
            return 10;
        } else {
            return 8;
        }

    }

    private static DecimalFormat df = new DecimalFormat("0.00");
    private static void statistics(char Seats[][], int rows, int columns, int nbrPurchased, int currIncome) {
        System.out.println("Number of purchased tickets: " + nbrPurchased);
        double percentage;
        percentage = (double) (nbrPurchased * 100) / (rows * columns) ;
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println("Percentage: " + df.format(percentage) + "%");
        System.out.println("Current income: $" + currIncome);
        totalIncome(rows, columns);
        menu(Seats, rows, columns, nbrPurchased, currIncome);
    }


}
