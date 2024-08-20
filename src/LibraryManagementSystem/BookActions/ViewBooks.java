package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

public class ViewBooks implements BookActions{


    @Override
    public void actions(Database database, User user) {
        System.out.println("Library Books:");
        database.getBooks();


    }
}
