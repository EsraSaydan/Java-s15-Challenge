package LibraryManagementSystem.userAccount;

import LibraryManagementSystem.BookActions.BookActions;
import LibraryManagementSystem.Database.Database;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    protected BookActions[] action;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String email, String name, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    abstract public void list(Database database, User user);

    public abstract BookActions getAction(int num);



    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
