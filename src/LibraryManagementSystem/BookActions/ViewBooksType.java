package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Book.Book;
import LibraryManagementSystem.Book.BookType;
import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class ViewBooksType implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        // Kitap türlerini listele
        System.out.println("Lütfen görüntülemek istediğiniz kitap türünü seçin:");
        for (BookType type : BookType.values()) {
            System.out.println(type.ordinal() + " - " + type.name());
        }

        int typeIndex = scanner.nextInt();
        if (typeIndex < 0 || typeIndex >= BookType.values().length) {
            System.out.println("Geçersiz kitap türü seçimi.");
            return;
        }

        BookType selectedType = BookType.values()[typeIndex];
        List<Book> booksByType = database.getBooksByType(selectedType);

        if (booksByType.isEmpty()) {
            System.out.println("Bu türde hiçbir kitap bulunamadı.");
        } else {
            booksByType.forEach(System.out::println);
        }

        // İşlem tamamlandıktan sonra tekrar ana menüye dön
        System.out.println("Başka seçmek istediğiniz bir şey var mı ? İşlem yapmak için devam edebilirsiniz..");
        user.list(database, user);
    }
}