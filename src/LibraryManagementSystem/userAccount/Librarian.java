package LibraryManagementSystem.userAccount;

import LibraryManagementSystem.BookActions.*;
import LibraryManagementSystem.Database.Database;

import java.util.Scanner;

public class Librarian extends User {
    private String password;

    public Librarian(String name) {
        super(name);
        this.action = new BookActions[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new ViewMember(),
                new AddMember(),
                new Exit(),
        };
    }

    public Librarian() {
    }

    public Librarian(String email, String name, String phoneNumber, String password) {
        super(email, name, phoneNumber);
        this.password = password;
        this.action = new BookActions[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new ViewMember(),
                new AddMember(),
                new Exit(),
        };
    }

    public String getPassword() {
        return password;
    }

    @Override
    public BookActions getAction(int num) {
        return switch (num) {
            case 0 -> new ViewBooks();
            case 1 -> new AddBook();
            case 2 -> new DeleteBook();
            case 3 -> new ViewMember();
            case 4 -> new AddMember();
            case 5 -> new UpdateBook();  // Kitapları güncelleme seçeneği eklendi
            case 6 -> new Exit();
            default -> {
                System.out.println("Error! Try again...");
                yield new Exit();
            }
        };
    }

    @Override
    public void list(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("0-Kitapları Görüntüle");
            System.out.println("1-Kitap Ekle");
            System.out.println("2-Kitap Sil");
            System.out.println("3-Üyeleri Görüntüle");
            System.out.println("4-Üye Ekle");
            System.out.println("5-Kitapları Güncelle");
            System.out.println("6-ÇIKIŞ");

            int n = scanner.nextInt();

            if (n == 6) { // Eğer kullanıcı ÇIKIŞ seçeneğini seçerse döngüden çık
                System.out.println("Çıkış yapılıyor...");
                break;
            }

            getAction(n).actions(database, user); // Seçilen işlemi uygula
        }
    }
}

