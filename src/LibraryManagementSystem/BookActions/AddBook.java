package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Book.Book;
import LibraryManagementSystem.Book.BookType;
import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.StaticData.StaticBookData;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;

public class AddBook implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Yeni kitabın ID'sini girin: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Boşluk karakterini yakalamak için

        System.out.println("Yeni kitabın adını girin: ");
        String name = scanner.nextLine();

        System.out.println("Yeni kitabın yazarını girin: ");
        String author = scanner.nextLine();

        System.out.println("Yeni kitabın durumunu girin (Rafta/Alındı): ");
        String status = scanner.nextLine();

        System.out.println("Yeni kitabın yayınevini girin: ");
        String publisher = scanner.nextLine();

        System.out.println("Yeni kitabın fiyatını girin: ");
        Double price = scanner.nextDouble();
        scanner.nextLine(); // Boşluk karakterini yakalamak için

        System.out.println("Yeni kitabın türünü seçin (1-Study, 2-Magazine, 3-Journal): ");
        int bookTypeChoice = scanner.nextInt();
        BookType bookType = null;

        switch (bookTypeChoice) {
            case 1 -> bookType = BookType.STUDY_BOOK;
            case 2 -> bookType = BookType.MAGAZINE;
            case 3 -> bookType = BookType.JOURNAL;
            default -> System.out.println("Geçersiz seçim!");
        }

        // Yeni bir Book (kitap) nesnesi oluştur ve veritabanına ekle
        Book newBook = new Book(id, name, author, status, publisher, price, bookType);
        StaticBookData.books.put(id, newBook);

        System.out.println("Yeni kitap başarıyla eklendi: " + name);

        // Menü yapısı
        int choice;
        do {
            System.out.println("\n1- Başka bir kitap ekle");
            System.out.println("2- Kitapları görüntüle");
            System.out.println("3- Ana menüye dön");
            System.out.println("4- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Boşluk karakterini yakalamak için

            switch (choice) {
                case 1:
                    actions(database, user);  // Tekrar kitap ekleme işlemi
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