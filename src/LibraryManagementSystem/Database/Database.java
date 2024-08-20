package LibraryManagementSystem.Database;

import LibraryManagementSystem.Book.BookType;
import LibraryManagementSystem.CommentLike.Comment;
import LibraryManagementSystem.CommentLike.Rating;
import LibraryManagementSystem.StaticData.StaticBookData;
import LibraryManagementSystem.StaticData.StaticMemberData;
import LibraryManagementSystem.userAccount.Librarian;
import LibraryManagementSystem.userAccount.User;
import LibraryManagementSystem.Book.Book;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Database {
    private Map<Integer, User> users = new HashMap<>();  // Kullanıcıları ID ile saklayan Map
    private Map<Long, Book> books = new HashMap<>();     // Kitapları ID ile saklayan Map
    private Map<Book, User> borrowedBooks = new HashMap<>(); // Kitap ve kullanıcı ilişkisini tutar
    private Map<Book, String> borrowedDates = new HashMap<>(); // Kitap ve ödünç alma tarihini tutar
//*****************************************************************************
private Map<Long, List<Comment>> bookComments = new HashMap<>();
    private Map<Long, List<Rating>> bookRatings = new HashMap<>();



    public Database() {
        this.users = StaticMemberData.users;
        this.books = StaticBookData.books;
    }

    public void addUser(int id, User user) {
        users.put(id, user);
    }

    public int login(String phoneNumber, String email) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user.getPhoneNumber().equals(phoneNumber) && user.getEmail().equals(email)) {
                return entry.getKey(); // Kullanıcı ID'sini döndür
            }
        }
        return -1; // Kullanıcı bulunamadıysa -1 döndür
    }

    public User getUser(int id) {
        return users.get(id); // Kullanıcı ID'si ile kullanıcıyı döndür
    }

    public User getLibrarianByEmailAndPassword(String email, String password) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user instanceof Librarian) {
                Librarian librarian = (Librarian) user;
                if (librarian.getEmail().equals(email) && librarian.getPassword().equals(password)) {
                    return librarian;
                }
            }
        }
        return null; // Kütüphaneci bulunamadıysa null döndür
    }


    public void deleteBook(long id) {
        if (books.containsKey(id)) {
            books.remove(id);
            System.out.println("Kitap başarıyla silindi.");
        } else {
            System.out.println("Kitap bulunamadı...");
        }
    }

    public void getBooks() {
        for (Map.Entry<Long, Book> entry : books.entrySet()) {
            System.out.println(entry.getValue()); // Kitapları ekrana yazdır
        }
    }

    public int getBorrowedBooksCount(int userId) {
        return (int) borrowedBooks.values().stream()
                .filter(user -> user.getId() == userId)
                .count();
    }

    public void setBorrowedDate(Book book, String date) {
        borrowedDates.put(book, date);
    }

    public String getBorrowedDate(Book book) {
        return borrowedDates.get(book);
    }



    public boolean returnBook(User user, long bookId) {
        Book returnedBook = books.get(bookId);

        if (returnedBook != null && returnedBook.getBorrowerId() == user.getId()) {
            String borrowedDateStr = borrowedDates.get(returnedBook);

            if (borrowedDateStr == null) {
                System.out.println("Bu kitap daha önce ödünç alınmamış.");
                return false;
            }

            // Clear borrower information
            returnedBook.setStatus("Rafta");
            returnedBook.setBorrowerId(-1);
            borrowedDates.remove(returnedBook);

            // Process refund
            processRefund(user, returnedBook);

            // Print return confirmation
            System.out.println(returnedBook.getName() + " başarıyla iade edildi.");
            return true;
        } else {
            System.out.println("Bu kitap size ait değil veya mevcut değil.");
            return false;
        }
    }
    public void processRefund(User user, Book book) {
        double refundAmount = book.getPrice();
        System.out.println("Kullanıcı " + user.getName() + " için " + book.getName() + " kitabı iade edildi ve " + refundAmount + " TL geri ödendi.");
    }



    public void generateInvoice(User user, Book book) {
        // Fatura kesme işlemi burada yapılır
        double price = book.getPrice();
        System.out.println("Kullanıcı " + user.getName() + " için " + book.getName() + " kitabı için " + price + " TL fatura kesildi.");
    }



    public Book getBook(long bookId) {
        return books.get(bookId);
    }

    public void barrowBook(User user, long id) {
        Book barrowedBook = books.get(id);
        if (barrowedBook != null && barrowedBook.getStatus().equalsIgnoreCase("Rafta")) {
            String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            barrowedBook.updateStatus("Ödünç Alındı");
            barrowedBook.setBorrowerId(user.getId());
            setBorrowedDate(barrowedBook, today);  // Tarihi kaydediyoruz
            borrowedBooks.put(barrowedBook, user);
            System.out.println(barrowedBook.getName() + " isimli kitap " + user.getName() + " tarafından " + today + " tarihinde ödünç alındı.");  // Tarih bilgisi eklendi
            generateInvoice(user, barrowedBook);  // Fatura kesiliyor
        } else if (barrowedBook != null && barrowedBook.getStatus().equalsIgnoreCase("Alındı")) {
            User borrower = borrowedBooks.get(barrowedBook);
            String borrowedDate = getBorrowedDate(barrowedBook);  // Ödünç alınma tarihini alıyoruz
            System.out.println("Kitap şu anda mevcut değil. " + borrower.getName() + " isimli üyemizden " + borrowedDate + " tarihinde ödünç alındı.");
        } else {
            System.out.println("Girilen id'de kitap bulunamadı...");
        }
    }






    public String getBarrowDate(Book book) {
        return borrowedDates.get(book);
    }



    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Book> getBorrowedBooks(int userId) {
        return books.values().stream()
                .filter(book -> book.getBorrowerId() == userId)
                .collect(Collectors.toList());
    }

    public Book findBookById(long bookId) {
        return StaticBookData.books.get(bookId);
    }

    public boolean getBooksByAuthor(String author) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
                found = true;
            }
        }
        return found;
    }


    // Kitap türüne göre listeleme
    public List<Book> getBooksByType(BookType type) {
        return books.values().stream()
                .filter(book -> book.getType() == type)
                .collect(Collectors.toList());
    }
    ///////////////////Comment

    public void addComment(long bookId, Comment comment) {
        // Kitap için yorum listesini al, eğer yoksa yeni bir ArrayList oluştur ve ekle
        List<Comment> comments = bookComments.computeIfAbsent(bookId, k -> new ArrayList<>());
        comments.add(comment);
    }

    public void addRating(long bookId, Rating rating) {
        // Kitap için puan listesini al, eğer yoksa yeni bir ArrayList oluştur ve ekle
        List<Rating> ratings = bookRatings.computeIfAbsent(bookId, k -> new ArrayList<>());
        ratings.add(rating);
    }

    public List<Comment> getComments(long bookId) {
        return bookComments.getOrDefault(bookId, new ArrayList<>());
    }

    public List<Rating> getRatings(long bookId) {
        return bookRatings.getOrDefault(bookId, new ArrayList<>());
    }

}