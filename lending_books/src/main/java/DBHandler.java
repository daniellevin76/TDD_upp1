import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class DBHandler {


    private DB db;

    public DBHandler(DB db) {
        this.db = db;
    }

    //This method only handles book data retrieval from database
    //Assuming data for each book is added to Book object which in turn is added to
    //arrayList of Book object
    public ArrayList<Book> retrieveBooksFromDB(String query) {

        ArrayList<Book> retrievedBooks = db.retrieveBooks(query);
        if (retrievedBooks.isEmpty()) {
            displayMessage();
        }
        return retrievedBooks;
    }

    //This method only handles book review retrieval from database
    //Assuming data for each review is added to BookReview object which in turn is added to
    //arrayList of BookReview object

    public ArrayList<ArrayList<String>> retrieveReviewsFromDB(String query) {

        ArrayList<ArrayList<String>> retrievedReviews = db.retrieveBookReviews(query);
        if (retrievedReviews.isEmpty()) {
            displayMessage();
        }
        return retrievedReviews;
    }


    public ArrayList<Book> searchBookByTitle(String title) {
        System.out.println("SELECT * FROM BOOK_TABLE WHERE title like " + title);
        return retrieveBooksFromDB("SELECT * FROM BOOK_TABLE WHERE title like " + title);
    }

    public ArrayList<Book> searchBookByAuthor(String author) {
        return retrieveBooksFromDB( "SELECT * FROM BOOK_TABLE WHERE author like " + author);
    }

    public ArrayList<Book> searchBookByGenre(String genre) {
        return retrieveBooksFromDB("SELECT * FROM BOOK_TABLE WHERE genre like " + genre) ;
    }

    public ArrayList<Book> searchBookById(String bookId) {
        return retrieveBooksFromDB("SELECT * FROM BOOK_TABLE WHERE bookId = " + bookId);
    }


    //Retrieve customers reviews (comments) for a specific book, reached by bookId
    public ArrayList<ArrayList<String>> searchBookReviewsByBookId(String bookId) {
         return db.retrieveBookReviews("SELECT * FROM REVIEW_TABLE WHERE bookId = " + bookId);
    }

    public ArrayList<String> displayComments(ArrayList<ArrayList<String>> retrievedReviews) {
        ArrayList<String> comments =new ArrayList<>();

        for(int i = 0; i < retrievedReviews.size();i++){
            comments.add(retrievedReviews.get(i).get(0));
        }

        return comments;
    }


    //Retrieve Reviews with highest rates from Data base
    public ArrayList<Object> retrieveReviewsWithHighestRates() {

        String query = "SELECT BOOK_TABLE.id, BOOK_TABLE.title, BOOK_TABLE.author," +
                " REVIEW_TABLE.comment, REVIEW_TABLE.rate, REVIEW_TABLE.rate_mean FROM BOOK_TABLE" +
                " \n INNER JOIN REVIEW_TABLE ON BOOK_TABLE.bookId=REVIEW_TABLE.bookId"+
                "ORDER BY rate_mean DESC";

        ArrayList<Object> retrievedReviewsFromDB = db.retrieveBookReviewsTitleAuthor(query);
     
        return retrievedReviewsFromDB;
    }

    //Display books title, author mean rate and comments with the highest rates




    private void displayMessage() {
        System.out.println("Search did not yield any result");
    }
}
