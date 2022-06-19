import java.util.Scanner;

public class Main {
    public static void mainMenu() {
        Library library = new Library();
        library.loadBooks();

        int choice;

        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("============================");
            System.out.print("Choose an action(0-3)");
            System.out.print("\n0 - EXIT");
            System.out.print("\n1 - ADD BOOK");
            System.out.print("\n2 - REMOVE BOOK");
            System.out.print("\n3 - DISPLAY BOOKS");
            System.out.print("\nEnter: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 0: return;
                case 1: library.addBook();
                        break;
                case 2: library.removeBook();
                        break;
                case 3: library.displayLibrary();
                        break;
                default: System.out.println("\nYou chose a wrong option. Try again!");
            }
        } while(choice != 0);
    }


    public static void main(String[] args) {
        mainMenu();

    }
}