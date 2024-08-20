package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;

public class ViewBooksByAuthor implements BookActions {


    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Yazarın adını girin:");
        String authorName = scanner.nextLine().trim();  // Boşlukları temizle

        boolean found = database.getBooksByAuthor(authorName);  // Kitaplar bulunursa true, bulunamazsa false döner

        if (!found) {
            System.out.println("Yazar " + authorName + " için kitap bulunamadı.");
        }

        // Menüye tekrar geri döner
        user.list(database, user);
    }
}