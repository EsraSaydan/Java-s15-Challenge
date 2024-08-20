package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;


import java.util.Scanner;

public class ReturnBook implements BookActions {

    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("İade etmek istediğiniz kitabın ID numarasını girin:");
        long bookId = scanner.nextLong();

        // Kitabı veritabanından al ve iade işlemini başlat
        boolean isReturned = database.returnBook(user, bookId);

        if (!isReturned) {
            System.out.println("Kitap iadesi başarısız oldu. Lütfen geçerli bir kitap ID'si girin.");
        }

        // Menüye geri dönme veya çıkış
        int choice;
        do {
            System.out.println("\n1- Başka bir kitap iade et");
            System.out.println("2- Ana menüye dön");
            System.out.println("3- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    actions(database, user);  // Tekrar kitap iade etme işlemi
                    break;
                case 2:
                    user.list(database, user);
                    return;  // Ana menüye dön
                case 3:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);  // Programdan çık
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        } while (choice != 2);
    }
}