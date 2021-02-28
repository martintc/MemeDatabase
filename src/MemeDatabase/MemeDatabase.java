package MemeDatabase;

import java.util.Scanner;

public class MemeDatabase {

    private Account account;
    private MySQLServer server;
    private Scanner scan;

    public MemeDatabase (MySQLServer server) {
        this.server = server;
        scan = new Scanner(System.in);
    }

    public static void main (String[] args) {

        MemeDatabase prog = new MemeDatabase(new MySQLServer(args[0], args[1], args[2]));

        String username = "";
        String password = "";
        String email = "";
        System.out.println("Welcome to Meme Database!");
        if (getInput("Do you have an account? [y/n] ").equals("y")) { // if the user has an account
            username = getInput("Username");
            password = getInput("Password");
            boolean isLoggedIn = false;
            while (!isLoggedIn) {
                isLoggedIn = logIn(username, password);
            }
        } else { // if the user does not have an account and needs to register an account
            boolean isCreated = false;
            while (!isCreated) {
                createAccount(username, password, email);
                isCreated = doCreateAccount(username, password, email);
            }
        }
    }

    public static boolean logIn (String username, String password) { // verify that the log in is correct using a query against the databse
        // TODO
        // CHECK THAT USER EXISTS
        return true; // test stub
    }

    // actuall creates the account
    public static boolean doCreateAccount (String username, String password, String email) { // make a query to DB to insert a new account
        // TODO
        // NEED TO DO SOME CHECKING TO MAKE SURE THAT USERNAME OR EMAIL DOES NOT EXIT
        return true; // test stub
    }

    public static String getInput (String question) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        System.out.print(question + ": ");
        input = scan.nextLine();

        return input;
    }

    // get details for new account
    private static void createAccount (String username, String password, String email) {
        String confirm = "";
        while (!confirm.equals("y")) {
            username = getInput("Enter a username you want ");
            password = getInput("Enter a password you want ");
            email = getInput("Enter your email addres: ");
            System.out.println("\n============================\n");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Email: " + email);
            confirm = getInput("Does this information look correct? [y/n]: ");
        }
    }

    public void run () {
        while (true) {
            String option = menu();
            switch (option) {
                case "1" :
                    searchMemeByTitle();
                    break;
                case "2" :
                    searchMemeByCategory();
                    break;
                case "3" :
                    favoriteMeme();
                    break;
                case "4" :
                    viewMeme();
                    break;
                case "5" :
                    createMeme();
                    break;
                case "6" :
                    deleteMeme();
                    break;
                default :
                    System.out.println("Wrong input");
                    continue;
            }
        }
    }

    public String menu () {
        System.out.println("Menu:");
        System.out.println("\t1. Search for meme by title");
        System.out.println("\t1. Search for meme by category");
        System.out.println("\t3. Add a meme to favorite");
        System.out.println("\t4. View a meme");
        System.out.println("\t5. Add a new meme");
        System.out.println("\t6. Delete a meme");
        System.out.print(">> ");
        return scan.nextLine();
    }

    public void searchMemeByTitle () {
        System.out.print("Enter a title to search by: ");
        String title = scan.nextLine();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            rs = stmt("SELECT * FROM meme, (SELECT contains.meme_id FROM contains WHERE contains.picture_title =" +
                    title + ") as B WHERE meme.meme_id = B.meme_id;);
            //need to pass the title to open or find
        } catch (SQLExeption sq1) {
           sq1.printStackTrace(); 
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("System failed to close connection safely");
            }
        }
    }

    public void searchMemeByCategory () {
        System.out.print("Enter a category to search by: ");
        String title = scan.nextLine();
        // initiate query;
    }

    public void favoriteMeme () {
        System.out.print("Enter a title to favorite: ");
        String title = scan.nextLine();
        // initiate query;
    }

    public void viewMeme () {
        System.out.print("Enter a title view: ");
        String title = scan.nextLine();
        // initiate query;
    }

    public void createMeme () {
        System.out.print("Enter a title for new meme: ");
        String title = scan.nextLine();
        // initiate insert;
    }

    public void deleteMeme () {
        System.out.print("Enter a title to delete: ");
        String title = scan.nextLine();
        // initiate deletion;
        // required to be author
    }
}
