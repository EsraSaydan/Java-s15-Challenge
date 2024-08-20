package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.Main;
import LibraryManagementSystem.userAccount.User;
import LibraryManagementSystem.Book.Book;

import java.util.List;
import java.util.Scanner;

public class ViewBarrowList implements BookActions {
    @Override
    public void actions(Database database, User user) {
        List<Book> borrowedBooks = database.getBorrowedBooks(user.getId());

        if (borrowedBooks.isEmpty()) {
            System.out.println("Şu anda ödünç aldığınız bir kitap yok.");
        } else {
            System.out.println("Ödünç Aldığınız Kitaplar:");
            for (Book book : borrowedBooks) {
                String borrowerName = user.getName();
                String borrowedDate = database.getBarrowDate(book);  // Tarihi alıyoruz
                if (borrowedDate == null) {
                    borrowedDate = "Tarih bulunamadı";
                }
                System.out.println("BookID: " + book.getBookID() + ", Kitap Adı: " + book.getName() +
                        ", Yazar: " + book.getAuthor() + ", Yayıncı: " + book.getPublisher() +
                        ", Durum: " + book.getStatus() +
                        ", Ödünç Alan: " + borrowerName + ", Ödünç Alınma Tarihi: " + borrowedDate);
            }
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1- Başka bir kitap ödünç al");
            System.out.println("2- Ödünç alınan kitapları tekrar görüntüle");
            System.out.println("3- Ana menüye dön");
            System.out.println("4- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Boşluk karakterini yakalamak için

            switch (choice) {
                case 1:
                    new BarrowBook().actions(database, user);  // Kitap ödünç alma işlemi
                    break;
                case 2:
                    actions(database, user);  // Ödünç alınan kitapları tekrar görüntüle
                    break;
                case 3:
                    user.list(database, user);
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