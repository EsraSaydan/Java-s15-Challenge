package LibraryManagementSystem.userAccount;

import LibraryManagementSystem.BookActions.*;
import LibraryManagementSystem.Database.Database;

import java.util.Scanner;



public class Member extends User{

    private MemberType memberType;
    private String address;

    public Member(int id, String email, String name, String phoneNumber, MemberType memberType, String address) {
        super(email, name, phoneNumber);
        this.setId(id);  // ID'yi User sınıfındaki id alanına set ediyoruz
        this.memberType = memberType;
        this.address = address;
        this.action = new BookActions[]{
                new BarrowBook(),
                new DetermineFine(),
                new ViewBarrowList(),
                new ReturnBook(),
                new ViewBooks(),  // Kitapları görüntüle
                new ViewBooksByAuthor(),  // Yazarın kitaplarını görüntüle
                new ViewBooksType(),
                new Exit(),
        };
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public BookActions getAction(int num) {
        switch (num) {
            case 0:
                return new BarrowBook();
            case 1:
                return new DetermineFine();

            case 2:
                return new ViewBarrowList();
            case 3:
                return new ReturnBook();
            case 4:
                return new ViewBooks();
            case 5:
                return new ViewBooksByAuthor();
            case 6:
                return new ViewBooksType();
            case 7:
                return new AddCommentAndRating();

            case 8:
                return new ViewCommentsAndRatings();
            case 9:
                return new Exit();
            default:
                System.out.println("Error! Try again...");
                return new Exit();
        }
    }

    @Override
    public void list(Database database, User user) {
        System.out.println("0-Kitap Ödünç Al");
        System.out.println("1-Cezayı Hesapla");
        System.out.println("2-Ödünç Alınan Kitapları Görüntüle");
        System.out.println("3-Kitabı İade Et");
        System.out.println("4-Kitapları Görüntüle");
        System.out.println("5-Yazarın Kitaplarını Görüntüle");
        System.out.println("6-Kitap Türlerine Göre Kitapları Görüntüle");
        System.out.println("7-Kitaba Yorum Yap ve Puan Ver!");
        System.out.println("8-Kitaplar Hakkında Yorum ve Puanları Gör!");
        System.out.println("9-ÇIKIŞ");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        getAction(n).actions(database, user);
    }
}