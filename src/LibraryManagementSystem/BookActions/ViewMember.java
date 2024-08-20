package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.Member;
import LibraryManagementSystem.userAccount.User;

public class ViewMember implements BookActions {
    @Override
    public void actions(Database database, User user) {
        System.out.println("Tüm Üyeler:");

        for (User member : database.getAllUsers()) {
            if (member instanceof Member) {  // Eğer kullanıcı bir Member ise
                Member actualMember = (Member) member;
                System.out.println("ID: " + actualMember.getId() +
                        ", İsim: " + actualMember.getName() +
                        ", Eposta: " + actualMember.getEmail() +
                        ", Telefon: " + actualMember.getPhoneNumber() +
                        ", Adres: " + actualMember.getAddress() +
                        ", Üye Türü: " + actualMember.getMemberType());
            } else {
                System.out.println("ID: " + member.getId() + ", İsim: " + member.getName() +
                        ", Eposta: " + member.getEmail() + ", Telefon: " + member.getPhoneNumber());
            }
        }
    }
}