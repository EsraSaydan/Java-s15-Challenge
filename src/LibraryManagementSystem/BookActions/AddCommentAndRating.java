package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.CommentLike.Comment;
import LibraryManagementSystem.CommentLike.Rating;
import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

import java.util.Scanner;

public class AddCommentAndRating implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        // Kitap ID'sini al
        System.out.println("Yorum yapmak ve puan vermek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine(); // Boşluk karakterini yakalamak için

        // Yorum yap
        System.out.println("Yorumunuzu yazın:");
        String commentText = scanner.nextLine();

        // Puan gir
        int ratingValue;
        do {
            System.out.println("1 ile 5 arasında bir puan girin:");
            ratingValue = scanner.nextInt();
            scanner.nextLine(); // Boşluk karakterini yakalamak için

            if (ratingValue < 1 || ratingValue > 5) {
                System.out.println("Geçersiz puan. Lütfen 1 ile 5 arasında bir puan girin:");
            }
        } while (ratingValue < 1 || ratingValue > 5);

        // Yorum ve Puan nesnelerini oluştur ve veritabanına ekle
        Comment comment = new Comment(commentText, user.getName(), bookId);
        Rating rating = new Rating(ratingValue, user.getName(), bookId);

        database.addComment(bookId, comment);
        database.addRating(bookId, rating);

        System.out.println("Yorum ve puan başarıyla eklendi.");


        System.out.println("Ana menüye dönmek ister misin?");
        user.list(database, user);
    }
}