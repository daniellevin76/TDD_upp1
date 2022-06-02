import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

public class DBHandler {


    private DB db;

    public DBHandler(DB db) {
        this.db = db;
    }

    //This method only handles book data retrieval from database
    //Assuming data for each book is added to Book object which in turn is added to
    //arrayList of Book object
    public ArrayList<Book> retrieveBooksFromDB(String keyWord, String searchWord) {

        String query = "SELECT * FROM BOOK_TABLE WHERE " + keyWord + " like " + searchWord;

        ArrayList<Book> retrievedBooks = db.retrieveBooks(query);
        if (retrievedBooks.isEmpty()) {
            displayMessage();
        }
        return retrievedBooks;
    }

    //This method only handles book review retrieval from database
    //Assuming data for each review is added to BookReview object which in turn is added to
    //arrayList of BookReview object

    public ArrayList<String> retrieveReviewsFromDB(String keyWord, String searchWord) {

        String query = "SELECT * FROM REVIEW_TABLE WHERE " + keyWord + " = " + searchWord;

        ArrayList<String> retrievedReviews = db.retrieveBookReviews(query);
        System.out.println(query);

        return retrievedReviews;
    }


    public ArrayList<Book> searchBookByTitle(String title) {

        ArrayList<Book> retrieveBooks = retrieveBooksFromDB("title", title);


        return retrieveBooks;
    }

    private void displayMessage() {
        System.out.println("Search did not yield any result");
    }

    public ArrayList<Book> searchBookByAuthor(String author) {
        return retrieveBooksFromDB("author", author);

    }

    public ArrayList<Book> searchBookByGenre(String genre) {
        return retrieveBooksFromDB("genre", genre);
    }


    //Retrieve customers reviews (comments) for a specific book, reached by bookId
    public String retrieveReviews(String bookId) {


        ArrayList<String> retrieveReviews = retrieveReviewsFromDB("bookId", bookId);
        // Use this builder to construct a Gson instance when you need to set configuration options other than the default.
        GsonBuilder gsonBuilder = new GsonBuilder();

        // This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it.
        // Gson instances are Thread-safe so you can reuse them freely across multiple threads.
        Gson gson = gsonBuilder.create();

        String JSONObject = gson.toJson(retrieveReviews);

        //for(int i = 0; i < retrieveReviews.size())
        System.out.println(retrieveReviews);

        return JSONObject;
    }
}
