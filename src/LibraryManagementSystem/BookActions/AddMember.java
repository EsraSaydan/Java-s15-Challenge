package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.Member;
import LibraryManagementSystem.userAccount.MemberType;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;

public class AddMember implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Yeni üyenin ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Boşluk karakterini yakalamak için

        System.out.println("Yeni üyenin adını girin: ");
        String name = scanner.nextLine();

        System.out.println("Yeni üyenin e-posta adresini girin: ");
        String email = scanner.nextLine();

        System.out.println("Yeni üyenin telefon numarasını girin: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Yeni üyenin adresini girin: ");
        String address = scanner.nextLine();

        System.out.println("Üye tipini seçin (1: STUDENT, 2: FACULTY, 3: OTHER): ");
        int memberTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Boşluk karakterini yakalamak için

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
                System.out.println("Geçersiz seçim. Varsayılan olarak OTHER seçildi.");
                memberType = MemberType.OTHER;
        }

        // Yeni bir Member (üye) nesnesi oluştur ve veritabanına ekle
        Member newMember = new Member(id, email, name, phoneNumber, memberType, address);
        database.addUser(id, newMember);

        System.out.println("Yeni üye başarıyla eklendi: " + name);

        // Menü yapısı
        int choice;
        do {
            System.out.println("\n1- Başka bir üye ekle");
            System.out.println("2- Üyeleri görüntüle");
            System.out.println("3- Ana menüye dön");
            System.out.println("4- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Boşluk karakterini yakalamak için

            switch (choice) {
                case 1:
                    actions(database, user);  // Tekrar üye ekleme işlemi
                    break;
                case 2:
                    new ViewMember().actions(database, user);  // Üyeleri görüntüle
                    break;
                case 3:
                    return;
                // Ana menüye dön
                case 4:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);  // Programdan çık
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        } while (choice != 3);
    }
}