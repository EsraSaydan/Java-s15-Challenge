package LibraryManagementSystem;

import LibraryManagementSystem.BookActions.Exit;
import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.Librarian;
import LibraryManagementSystem.userAccount.Member;
import LibraryManagementSystem.userAccount.MemberType;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);
    static Database database;

    public static void main(String[] args) {

        database = new Database();
        System.out.println("Welcome to Library Management System!");
        int num;
        do {
            System.out.println(
                    "0. Librarian Login\n1. Member Login\n2. Exit");

            num = s.nextInt();

            switch (num) {
                case 0:
                    librarianLogin();
                    break;
                case 1:
                    memberLogin();
                    break;

                case 2:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Error! Try again..");
                    break;
            }
        } while (num != 0);
    }

    public static void librarianLogin() { // Static method for direct invocation
        boolean found = false;
        Scanner scanner = new Scanner(System.in);

        while (!found) {
            System.out.println("Enter email:");
            String email = scanner.next().trim();  // Trim any extra spaces
            System.out.println("Enter password:");
            String password = scanner.next().trim();  // Trim any extra spaces

            // Check against hardcoded credentials first
            if (email.equals("esra@gmail.com") && password.equals("1234")) {
                System.out.println("Welcome Esra!");
                found = true;
                User librarian = new Librarian(email, "Esra", "05434762572", "1234");
                ((Librarian) librarian).list(database, librarian);  // Show librarian menu
            } else {
                // Verify librarian login from the database
                User user = database.getLibrarianByEmailAndPassword(email, password);
                if (user != null && user instanceof Librarian) {
                    Librarian librarian = (Librarian) user;
                    System.out.println("Hoşgeldin " + librarian.getName() + "!");
                    found = true;
                    librarian.list(database, librarian);  // Show librarian menu
                } else {
                    System.out.println("Invalid email or password.");
                }

                if (!found) {
                    System.out.println("1-Try again \n0-Exit");
                    int num = scanner.nextInt();
                    if (num == 1) {

                    } else if (num == 0) {
                        break;  // Exit the login loop
                    }
                }
            }
        }
    }

    private static void memberLogin() {
        boolean successfulLogin = false;

        while (!successfulLogin) {
            System.out.println("Lütfen seçim yapınız..");
            System.out.println("1- Student Login\n2- Faculty Login\n3- Other Login\n4- Exit");
            int memberTypeChoice = s.nextInt();
            s.nextLine();

            if (memberTypeChoice == 4) {
                new Exit().actions(database, null);
                return; // Çıkış yap
            }

            MemberType memberType;
            switch (memberTypeChoice) {
                case 1:
                    memberType = MemberType.STUDENT;
                    break;
                case 2:
                    memberType = MemberType.FACULTY;
                    break;
                case 3:
                    memberType = MemberType.OTHER;
                    break;
                default:
                    System.out.println("Invalid selection. Defaulting to OTHER.");
                    memberType = MemberType.OTHER;
            }

            System.out.println("Enter phone number:");
            String phoneNumber = s.next().trim();
            System.out.println("Enter email:");
            String email = s.next().trim();

            // Validate phone number and email
            int userId = database.login(phoneNumber, email);

            if (userId != -1) {
                User user = database.getUser(userId);  // getUser(int id) metodunu kullanıyoruz.
                if (user != null && user instanceof Member) {
                    Member member = (Member) user;
                    if (member.getMemberType() == memberType) {
                        System.out.println("Welcome " + member.getName() + "!");
                        member.list(database, member);  // Üyenin menüsünü göster
                        successfulLogin = true;
                    } else {
                        System.out.println("Login type does not match the member type.");
                    }
                } else {
                    System.out.println("User doesn't exist!");
                }
            } else {
                System.out.println("Invalid phone number or email.");
            }
        }
    }
}