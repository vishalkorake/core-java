import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OwnerDAO ownerDAO = new OwnerDAO();
        MessDAO messDAO = new MessDAO();
        MenuDAO menuDAO = new MenuDAO();

        int ownerId = -1;
        boolean loggedIn = false;

        System.out.println("=== Mess Management System ===");
        System.out.println("1. Register as Owner");
        System.out.println("2. Login as Owner");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1:
                Owner owner = new Owner();
                System.out.print("Enter Name: ");
                owner.setName(sc.nextLine());
                System.out.print("Enter Email: ");
                owner.setEmail(sc.nextLine());
                System.out.print("Enter Password: ");
                owner.setPassword(sc.nextLine());

                if (ownerDAO.registerOwner(owner)) {
                    System.out.println("✅ Registered successfully!");
                } else {
                    System.out.println("❌ Registration failed.");
                }
                break;

            case 2:
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                ownerId = ownerDAO.getOwnerId(email, password);
                if (ownerId != -1) {
                    System.out.println("✅ Login successful!");
                    loggedIn = true;
                } else {
                    System.out.println("❌ Invalid credentials.");
                }
                break;

            default:
                System.out.println("❌ Invalid option.");
        }

        
        if (loggedIn) {
            System.out.println("\n--- Owner Menu ---");
            System.out.println("1. Add New Mess");
            System.out.println("2. Update Today’s Menu");
            System.out.print("Choose an action: ");
            int action = sc.nextInt();
            sc.nextLine(); 

            switch (action) {
                case 1:
                    Mess mess = new Mess();
                    mess.setOwnerId(ownerId);
                    System.out.print("Enter Mess Name: ");
                    mess.setName(sc.nextLine());
                    System.out.print("Enter Mess Address: ");
                    mess.setAddress(sc.nextLine());

                    if (messDAO.addMess(mess)) {
                        System.out.println("✅ Mess added successfully with ID: " + mess.getMessId());
                    } else {
                        System.out.println("❌ Could not add mess.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Mess ID: ");
                    int messId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Breakfast: ");
                    String breakfast = sc.nextLine();
                    System.out.print("Enter Lunch: ");
                    String lunch = sc.nextLine();
                    System.out.print("Enter Dinner: ");
                    String dinner = sc.nextLine();

                    if (menuDAO.updateTodayMenu(messId, breakfast, lunch, dinner)) {
                        System.out.println("✅ Menu updated successfully!");
                    } else {
                        System.out.println("❌ Menu update failed.");
                    }
                    break;

                default:
                    System.out.println("❌ Invalid action.");
            }
        }

        sc.close();
    }
}
