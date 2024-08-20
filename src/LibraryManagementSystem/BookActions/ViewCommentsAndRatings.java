package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;
import LibraryManagementSystem.CommentLike.Comment;  // Yorum sınıfı için doğru paketi import edin
import LibraryManagementSystem.CommentLike.Rating;

import java.util.List;
import java.util.Scanner;

public class ViewCommentsAndRatings implements BookActions {

    @Override
    public void actions(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Görmek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();

        // Yorumları göster
        List<Comment> comments = database.getComments(bookId);
        if (comments != null && !comments.isEmpty()) {
            System.out.println("Yorumlar:");
            for (Comment comment : comments) {
                System.out.println(comment);
            }
        } else {
            System.out.println("Bu kitap için henüz yorum yapılmamış.");
        }

        // Puanları göster
        List<Rating> ratings = database.getRatings(bookId);
        if (ratings != null && !ratings.isEmpty()) {
            System.out.println("Puanlar:");
            for (Rating rating : ratings) {
                System.out.println(rating);
            }
        } else {
            System.out.println("Bu kitap için henüz puan verilmemiş.");
        }
        System.out.println("Ana menüye dönmek ister misin?");
        user.list(database, user);
    }

}