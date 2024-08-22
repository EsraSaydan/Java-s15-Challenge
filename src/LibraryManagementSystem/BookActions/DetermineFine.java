package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Book.Book;
import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class DetermineFine implements BookActions{
    private static final double FINE_PER_DAY = 10; // Gün başına ceza miktarı

    @Override
    public void actions(Database database, User user) {
        List<Book> borrowedBooks = database.getBorrowedBooks(user.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        double totalFine = 0.0;

        // kullanıcının ödünç aldığı her kitap için işlem yapar. ChronoUnit-->java.time
        for (Book book : borrowedBooks) {
            String borrowedDateString = database.getBorrowedDate(book);
            if (borrowedDateString != null && !borrowedDateString.isEmpty()) {
                LocalDate borrowedDate = LocalDate.parse(borrowedDateString, formatter);
                long daysBetween = ChronoUnit.DAYS.between(borrowedDate, today);
                long remainingDays = 90 - daysBetween; //Kitabın iade edilmesine kaç gün kaldığını hesaplar.

                if (daysBetween > 90) { // 90 gün aşılmışsa ceza hesapla
                    long overdueDays = daysBetween - 90;
                    double fine = overdueDays * FINE_PER_DAY;
                    totalFine += fine;
                    System.out.println("Kitap: " + book.getName() + " - Gecikme Günleri: " + overdueDays + " - Ceza: " + fine + " TL");
                } else {
                    System.out.println("Kitabı iade etmenize " + remainingDays + " gün kaldı. İade etmezseniz günlük 10 TL ceza uygulanacaktır.");
                }
            } else {
                System.out.println("Kitap: " + book.getName() + " için ödünç alınma tarihi bulunamadı.");
            }
        }

        if (totalFine > 0) {
            System.out.println("Toplam Ceza: " + totalFine + " TL");
        } else {
            System.out.println("Hiçbir kitap için ceza bulunmamaktadır.");
        }
    }


}