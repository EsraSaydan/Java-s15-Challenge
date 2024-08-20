package LibraryManagementSystem.StaticData;

import LibraryManagementSystem.Book.Book;
import LibraryManagementSystem.Book.BookType;

import java.util.HashMap;
import java.util.Map;

public class StaticBookData {
    public static final Map<Long, Book> books = new HashMap<>();

    static {
        books.put(1L, new Book(1L, "Küçük Prens", "Antoine de Saint-Exupéry", "Rafta", "Yayınevi 1", 25.0, BookType.STUDY_BOOK));
        books.put(2L, new Book(2L, "Simyacı", "Paulo Coelho", "Rafta", "Yayınevi 2", 30.0, BookType.STUDY_BOOK));
        books.put(3L, new Book(3L, "1984", "George Orwell", "Rafta", "Yayınevi 3", 35.0, BookType.STUDY_BOOK));
        books.put(4L, new Book(4L, "Hayvan Çiftliği", "George Orwell", "Rafta", "Yayınevi 3", 20.0, BookType.STUDY_BOOK));
        books.put(5L, new Book(5L, "Sefiller", "Victor Hugo", "Rafta", "Yayınevi 4", 40.0, BookType.STUDY_BOOK));
        books.put(6L, new Book(6L, "Yeraltından Notlar", "Fyodor Dostoyevski", "Rafta", "Yayınevi 5", 22.5, BookType.JOURNAL));
        books.put(7L, new Book(7L, "Beyaz Geceler", "Fyodor Dostoyevski", "Rafta", "Yayınevi 5", 18.0, BookType.JOURNAL));
        books.put(8L, new Book(8L, "Suç ve Ceza", "Fyodor Dostoyevski", "Rafta", "Yayınevi 5", 45.0, BookType.STUDY_BOOK));
        books.put(9L, new Book(9L, "Anna Karenina", "Lev Tolstoy", "Rafta", "Yayınevi 6", 50.0, BookType.STUDY_BOOK));
        books.put(10L, new Book(10L, "Savaş ve Barış", "Lev Tolstoy", "Rafta", "Yayınevi 6", 55.0, BookType.STUDY_BOOK));


        books.put(11L, new Book(11L, "National Geographic", "Çeşitli Yazarlar", "Rafta", "NatGeo Yayınevi", 15.0, BookType.MAGAZINE));
        books.put(12L, new Book(12L, "Time", "Çeşitli Yazarlar", "Rafta", "Time Yayınevi", 20.0, BookType.MAGAZINE));
        books.put(13L, new Book(13L, "Forbes", "Çeşitli Yazarlar", "Rafta", "Forbes Yayınevi", 25.0, BookType.MAGAZINE));
        books.put(14L, new Book(14L, "Scientific American", "Çeşitli Yazarlar", "Rafta", "SciAm Yayınevi", 22.0, BookType.MAGAZINE));
    }
}