package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;
import LibraryManagementSystem.Book.Book;
import java.util.Scanner;

public class BarrowBook implements BookActions {
    private static final int BOOK_LIMIT = 5;  // Kitap limiti

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        int borrowedBooksCount = database.getBorrowedBooksCount(user.getId());

        if (borrowedBooksCount >= BOOK_LIMIT) {
            System.out.println("Kitap limitinize ulaştınız! Daha fazla kitap ödünç alamazsınız.");
            return;
        }


        System.out.println("Ödünç almak istediğiniz kitabın ID numarasını girin:");
        long bookId = scanner.nextLong();

        Book book = database.getBook(bookId);

        if (book != null && book.getStatus().equalsIgnoreCase("Rafta")) {
            database.barrowBook(user, bookId);  // Kitap ödünç alma işlemi
        } else if (book == null) {
            System.out.println("Bu ID'ye sahip bir kitap bulunamadı.");
        } else if (book != null && book.getStatus().equalsIgnoreCase("Alındı")) {
            User borrower = database.getUser(book.getBorrowerId());
            if (borrower != null) {
                String borrowedDate = database.getBarrowDate(book);
                System.out.println("Kitap şu anda mevcut değil. Kitap " + borrower.getName() + " tarafından " + borrowedDate + " tarihinde ödünç alınmış.");
            }
        }
        // Menüye geri dönme veya çıkış
        int choice;
        do {
            System.out.println("\n1- Başka bir kitap ödünç al");
            System.out.println("2- Ödünç alınan kitapları görüntüle");
            System.out.println("3- Ana menüye dön");
            System.out.println("4- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    actions(database, user);  // Tekrar kitap ödünç alma işlemi
                    break;
                case 2:
                    new ViewBarrowList().actions(database, user);  // Ödünç alınan kitapları görüntüle
                    break;
                case 3:
                    user.list(database, user);
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