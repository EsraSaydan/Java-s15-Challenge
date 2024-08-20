package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;
import java.util.Scanner;

public class DeleteBook implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        // Tüm kitapları göster
        System.out.println("Kütüphanedeki kitaplar:");
        database.getBooks();

        // Silinecek kitabın ID'sini al
        System.out.println("Silmek istediğiniz kitabın ID numarasını giriniz:");
        long bookId = scanner.nextLong();
        scanner.nextLine(); // Boşluk karakterini yakalamak için

        // Kitabı sil
        database.deleteBook(bookId);

        // Menü yapısı
        int choice;
        do {
            System.out.println("\n1- Başka bir kitap sil");
            System.out.println("2- Kitapları görüntüle");
            System.out.println("3- Ana menüye dön");
            System.out.println("4- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Boşluk karakterini yakalamak için

            switch (choice) {
                case 1:
                    actions(database, user);  // Tekrar kitap silme işlemi
                    break;
                case 2:
                    new ViewBooks().actions(database, user);  // Kitapları görüntüle
                    break;
                case 3:
                    return;  // Ana menüye dön
                case 4:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);  // Programdan çık
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        } while (choice != 3);
    }
}
