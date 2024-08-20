package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Book.Book;
import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;

public class UpdateBook implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        // Kitapları listele
        //System.out.println("Mevcut kitaplar:");
        new ViewBooks().actions(database, user);

        // Güncellemek istediğiniz kitabın ID'sini girin
        System.out.println("Güncellemek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine();  // Boşluk karakterini yakalamak için

        // Kitabı ID'ye göre bulma
        Book bookToUpdate = database.findBookById(bookId);

        if (bookToUpdate == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        // Kitap bilgilerini güncelleme
        System.out.println("Yeni kitabın adını girin (Mevcut: " + bookToUpdate.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            bookToUpdate.setName(newName);
        }

        System.out.println("Yeni kitabın yazarını girin (Mevcut: " + bookToUpdate.getAuthor() + "): ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.trim().isEmpty()) {
            bookToUpdate.setAuthor(newAuthor);
        }

        System.out.println("Yeni kitabın yayınevini girin (Mevcut: " + bookToUpdate.getPublisher() + "): ");
        String newPublisher = scanner.nextLine();
        if (!newPublisher.trim().isEmpty()) {
            bookToUpdate.setPublisher(newPublisher);
        }

        System.out.println("Yeni kitabın fiyatını girin (Mevcut: " + bookToUpdate.getPrice() + "): ");
        double newPrice = scanner.nextDouble();
        if (newPrice > 0) {
            bookToUpdate.setPrice(newPrice);
        }

        System.out.println("Kitap başarıyla güncellendi: " + bookToUpdate);
    }
}