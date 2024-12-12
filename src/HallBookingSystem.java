import java.util.*;
import java.text.SimpleDateFormat;

public class HallBookingSystem {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = 0, col = 0;
        String[][] h = null;
        String[] bookingHistory = new String[100];
        int bookingCount = 0;

        while (true) {
            System.out.println("==================================================");
            System.out.println("              Hall Booking System            ");
            System.out.println("--------------------------------------------------");
            System.out.println(RED + "[1]" + RESET + " Set up hall");
            System.out.println(RED + "[2]" + RESET + " List all halls");
            System.out.println(RED + "[3]" + RESET + " Start Booking");
            System.out.println(RED + "[4]" + RESET + " Cancel Booking");
            System.out.println(RED + "[5]" + RESET + " View Booking History");
            System.out.println(RED + "[0]" + RESET + " Exit");
            System.out.println("--------------------------------------------------");
            System.out.print(GREEN + "[+]" + RESET + " Insert option: ");

            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    break;
                }
                System.out.println("[!] Invalid input. Please enter a valid option.");
                sc.nextLine();
            }

            switch (choice) {
                case 1: {
                    System.out.println("==========> Set up hall <==========");
                    while (true) {
                        System.out.print("[+] Enter number of rows (1-26): ");
                        if (sc.hasNextInt()) {
                            row = sc.nextInt();
                            if (row > 0 && row <= 26) break;
                        }
                        System.out.println("[!] Invalid input. Please enter a number between 1 and 26.");
                        sc.nextLine();
                    }
                    while (true) {
                        System.out.print("[+] Enter number of columns (1-50): ");
                        if (sc.hasNextInt()) {
                            col = sc.nextInt();
                            if (col > 0 && col <= 50) break;
                        }
                        System.out.println("[!] Invalid input. Please enter a number between 1 and 50.");
                        sc.nextLine();
                    }

                    h = new String[row][col];
                    char prefix = 'A';
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            h[i][j] = prefix + "-" + (j + 1) + ": AV";
                        }
                        prefix++;
                    }

                    System.out.println("[!] Hall setup complete.");
                    break;
                }

                case 2: {
                    System.out.println("==========> List All Halls <==========");
                    if (h == null || row == 0 || col == 0) {
                        System.out.println("[!] No halls available. Please set up a hall first.");
                    } else {
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < col; j++) {
                                System.out.print(" | " + h[i][j]);
                            }
                            System.out.println(" |");
                        }
                    }
                    break;
                }

                case 3: {
                    if (h == null || row == 0 || col == 0) {
                        System.out.println("[!] No halls available. Please set up a hall first.");
                        break;
                    }

                    System.out.println("==========> Booking <==========");
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            System.out.print(" | " + h[i][j]);
                        }
                        System.out.println(" |");
                    }

                    System.out.print("[+] Enter seat code (e.g., A-1, B-1): ");
                    String seat = sc.next();
                    boolean booked = false;

                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (h[i][j].startsWith(seat)) {
                                if (h[i][j].endsWith(": BO")) {
                                    System.out.println("[!] Seat already booked.");
                                } else {
                                    System.out.println("[!] Booking successful for seat: " + seat);
                                    h[i][j] = seat + ": BO";
                                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                    bookingHistory[bookingCount++] = "Seat: " + seat + " | Date: " + time;
                                }
                                booked = true;
                                break;
                            }
                        }
                        if (booked) break;
                    }

                    if (!booked) {
                        System.out.println("[!] Seat not found.");
                    }
                    break;
                }

                case 4: {
                    if (h == null || row == 0 || col == 0) {
                        System.out.println("[!] No halls available. Please set up a hall first.");
                        break;
                    }

                    System.out.println("==========> Cancel Booking <==========");
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            System.out.print(" | " + h[i][j]);
                        }
                        System.out.println(" |");
                    }

                    System.out.print("[+] Enter seat code to cancel booking (e.g., A-1, B-1): ");
                    String seat = sc.next();
                    boolean canceled = false;

                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (h[i][j].startsWith(seat)) {
                                if (h[i][j].endsWith(": AV")) {
                                    System.out.println("[!] Seat is not booked.");
                                } else {
                                    System.out.println("[!] Booking canceled for seat: " + seat);
                                    h[i][j] = seat + ": AV";
                                }
                                canceled = true;
                                break;
                            }
                        }
                        if (canceled) break;
                    }

                    if (!canceled) {
                        System.out.println("[!] Seat not found.");
                    }
                    break;
                }

                case 5: {
                    System.out.println("==========> Booking History <==========");
                    if (bookingCount == 0) {
                        System.out.println("[!] No bookings made yet.");
                    } else {
                        for (int i = 0; i < bookingCount; i++) {
                            System.out.println(bookingHistory[i]);
                        }
                        System.out.println("Total booked seats: " + bookingCount);
                    }
                    break;
                }

                case 0: {
                    System.out.println(RED + "[*] System Closed. Goodbye!");
                    sc.close();
                    System.exit(0);
                }

                default:
                    System.out.println(RED + "[!] Invalid option. Please choose a valid option.");
            }
        }
    }
}

