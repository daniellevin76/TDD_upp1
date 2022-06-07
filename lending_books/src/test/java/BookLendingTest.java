
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class BookLendingTest {

    Book theShining, frankenstein, theResurrectionist, animalFarm, annaKarenina, rescue, annaKareninaNotAvailable, rescueNotAvailable, it;


    ArrayList<String> rev1, rev2, rev3, rev4, rev5, rev6, rev7, rev8, rev9, rev10, rev11, rev12;
    DB mockDB = mock(DB.class);


    DBHandler dbHandler = new DBHandler(mockDB);
    ArrayList<Book> books = new ArrayList<>();


    @BeforeEach
    void setup(){
        theShining = new Book(1,"The Shining", "Stephen King", "Horror", 1977, 30.0, 100);
        it = new Book(2,"It", "Stephen King", "Horror", 1984, 15.0, 100);
        frankenstein = new Book(3,"Frankenstein", "Mary Shelley", "Horror", 1818, 25.0, 100);
        theResurrectionist = new Book(4,"The Resurrectionist", " E. B. Hudspeth", "Horror",2013, 35.0, 55 );
        animalFarm = new Book(5,"Animal Farm", "George Orwell", "Political Satire", 1945, 25, 99 );
        annaKarenina = new Book(6,"Anna Karenina", "Leo Tolstoy", "Realist Novel", 1978, 30.0, 1);
        rescue = new Book(7,"Rescue", "Jennifer Nielsen", "Detective", 2021, 10.0, 0);



        //review table ["comment", "rate", "rate_mean","bookId"]
        rev1 =  new ArrayList<>(Arrays.asList("It was an interesting read", "5", "4.0", "7"));

        rev2= new ArrayList<>(Arrays.asList("It was a crazy book", "4", "4.0", "7"));

        rev3 =new ArrayList<>(Arrays.asList("Strange book", "3", "4.0", "4"));

        rev4=new ArrayList<>(Arrays.asList("One of the scariest books ever", "3", "3.9", "1"));

        rev5  =new ArrayList<>(Arrays.asList("Not my cup of tea", "2", "3.7", "2"));

        rev6 =new ArrayList<>(Arrays.asList("It was better than the movie", "5", "3.9", "1"));

        rev7  = new ArrayList<>(Arrays.asList("A masterpiece", "5", "4.4", "6"));

        rev8 = new ArrayList<>(Arrays.asList("Very difficult read", "3", "4.4", "6"));

        rev9  =new ArrayList<>(Arrays.asList("Strange book", "3", "4.1", "5"));

        rev10 =new ArrayList<>(Arrays.asList("A must read", "4", "3.7", "3"));

        rev11 = new ArrayList<>(Arrays.asList("Orwell was a genius", "5", "4.1", "5"));

        rev12  = new ArrayList<>(Arrays.asList("So relevant today", "4", "4.1", "5"));

    }


    @Test
    void should_returnTheShining_when_searchByTitleTheShining(){

        String query = "SELECT * FROM BOOK_TABLE WHERE title like The Shining";
        books.add(theShining);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );

        assertEquals(books, dbHandler.searchBookByTitle("The Shining") );

    }


    @Test
    void should_returnTheShining_when_searchByTitleTHeShiningAllSmallLetters(){

        String query = "SELECT * FROM BOOK_TABLE WHERE title like the shining";
        ArrayList<Book> books = new ArrayList<>();
        books.add(theShining);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByTitle("the shining") );

    }

    @Test
    void should_returnTheShiningAndTheResurrectionist_when_searchByTitleThe(){

        ArrayList<Book> books = new ArrayList<>();
        books.add(theResurrectionist);
        books.add(theShining);
        String query = "SELECT * FROM BOOK_TABLE WHERE title like the";

        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByTitle("the") );

    }



    @Test
    void should_returnAnnaKarenina_when_searchByTitleAnnaKarenina(){
        ArrayList<Book> books = new ArrayList<>();
        books.add(annaKarenina);

        String query = "SELECT * FROM BOOK_TABLE WHERE title like Anna Karenina";

        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByTitle("Anna Karenina") );

    }

    @Test
    void should_returnFrankenstein_when_searchByAuthorMaryShelley(){

        String query = "SELECT * FROM BOOK_TABLE WHERE author like Mary Shelley";
        books.add(theShining);
        books.add(theResurrectionist);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByAuthor("Mary Shelley") );

    }

    @Test
    void should_returnTheShiningAndIt_when_searchByAuthorStephenKing(){

        String query = "SELECT * FROM BOOK_TABLE WHERE author like Stephen King";
        books.add(theShining);
        books.add(theResurrectionist);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByAuthor("Stephen King") );

    }

    @Test
    void should_returnRescue_when_searchByGenreDetective(){

        String query = "SELECT * FROM BOOK_TABLE WHERE genre like detective";
        books.add(rescue);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByGenre("detective") );

    }

    @Test
    void should_returnItTheShinFrankTheResurrectionist_when_searchByGenreHorror(){

        String query = "SELECT * FROM BOOK_TABLE WHERE genre like horror";
        books.add(it);
        books.add(theShining);
        books.add(theResurrectionist);
        books.add(frankenstein);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByGenre("horror") );

    }

    @Test
    void should_returnItTheShining_when_searchBookById1(){

        String query = "SELECT * FROM BOOK_TABLE WHERE bookId = 1";
        books.add(theShining);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookById("1") );

    }

    @Test
    void should_returnBookReviewForAnnaKarenina_when_searchBookReviewsForAnnaKarenina(){
        ArrayList<Book> books = new ArrayList<>();
        books.add(annaKarenina);
        ArrayList<ArrayList<String>> bookReviews = new ArrayList<>();
        bookReviews.add(rev7);
        bookReviews.add(rev8);
        String query1 = "SELECT * FROM BOOK_TABLE WHERE title like Anna Karenina";
        String query2 = "SELECT * FROM REVIEW_TABLE WHERE bookId = 6";

        when(mockDB.retrieveBooks(query1)).thenReturn(books);
        when(mockDB.retrieveBookReviews(query2)).thenReturn(bookReviews);

        ArrayList<Book> bk = dbHandler.searchBookByTitle("Anna Karenina");
        System.out.println("**********************************************************");
        System.out.println(bk.get(0).getBookId());
        assertEquals(bookReviews, dbHandler.searchBookReviewsByBookId(String.valueOf(bk.get(0).getBookId())));

    }

    @Test
    void should_returnBookReviewForRescue_when_searchBookReviewsForRescue(){

        ArrayList<ArrayList<String>> bookReviews = new ArrayList<>();
        bookReviews.add(rev1);
        bookReviews.add(rev2);
        bookReviews.add(rev10);
        String query = "SELECT * FROM REVIEW_TABLE WHERE bookId = 7";

        when(mockDB.retrieveBookReviews(query)).thenReturn(bookReviews);


        assertEquals(bookReviews, dbHandler.searchBookReviewsByBookId(String.valueOf(7)));

    }


    @Test
    void should_returnCommentsForTheBookRescue_when_displayCommentsForRescue(){

        ArrayList<ArrayList<String>> bookReviews = new ArrayList<>();
        String[] commentsList = { "It was an interesting read","It was a crazy book","Strange book" };
        ArrayList<String> comments = new ArrayList<>(List.of(commentsList));

        String query = "SELECT * FROM REVIEW_TABLE WHERE bookId = 0";

        when(mockDB.retrieveBookReviews(query)).thenReturn(
                bookReviews
        );

        ArrayList<ArrayList<String>> searchBookReview = dbHandler.searchBookReviewsByBookId(String.valueOf(0));
        System.out.println(searchBookReview);
        assertEquals(comments, dbHandler.displayComments(searchBookReview));
    }

    @Test
    void should_returnCommentsForAnimalFarm_when_displayCommentsForAnimalFarm(){

        ArrayList<ArrayList<String>> bookReviews = new ArrayList<>();
        String[] commentsList = { "Orwell was a genius","So relevant today"};
        ArrayList<String> comments = new ArrayList<>(List.of(commentsList));


        String query = "SELECT * FROM REVIEW_TABLE WHERE bookId = 5";

        when(mockDB.retrieveBookReviews(query)).thenReturn(
                bookReviews
        );

        ArrayList<ArrayList<String>> retrievedReviews = dbHandler.retrieveReviewsFromDB(query);

        assertEquals(comments, dbHandler.displayComments(retrievedReviews));
    }

    @Test
    void should_returnBooksWithHighestRatesInDescendingOrder_whenSearchByBestRating(){
        ArrayList<ArrayList<String>> bookReviews = new ArrayList<>();
        ArrayList<Object> books = new ArrayList<>();

        bookReviews.add(rev7);
        bookReviews.add(rev8);
        bookReviews.add(rev9);
        bookReviews.add(rev11);
        bookReviews.add(rev12);
        bookReviews.add(rev1);
        bookReviews.add(rev2);
        bookReviews.add(rev3);
        bookReviews.add(rev4);
        bookReviews.add(rev6);
        bookReviews.add(rev5);
        bookReviews.add(rev10);
        bookReviews.add(rev11);
        bookReviews.add(rev12);


        ArrayList<String> comments1 = new ArrayList<>();
        comments1.add(rev7.get(0));
        comments1.add(rev8.get(0));
        ArrayList<Object> bookReview1 = new ArrayList<>();
        bookReview1.add(annaKarenina.getTitle());
        bookReview1.add(annaKarenina.getAuthor());
        bookReview1.add(rev7.get(2));
        bookReview1.add(comments1);

        ArrayList<String> comments2 = new ArrayList<>();
        comments2.add(rev9.get(0));
        comments2.add(rev11.get(0));
        comments2.add(rev12.get(0));
        ArrayList<Object> bookReview2 = new ArrayList<>();
        bookReview2.add(animalFarm.getTitle());
        bookReview2.add(animalFarm.getAuthor());
        bookReview2.add(rev12.get(2));
        bookReview2.add(comments2);

        ArrayList<String> comments3 = new ArrayList<>();
        comments3.add(rev1.get(0));
        comments3.add(rev2.get(0));
        ArrayList<Object> bookReview3 = new ArrayList<>();
        bookReview3.add(rescue.getTitle());
        bookReview3.add(rescue.getAuthor());
        bookReview3.add(rev1.get(2));
        bookReview3.add(comments3);

        ArrayList<String> comments4 = new ArrayList<>();
        comments4.add(rev3.get(0));
        ArrayList<Object> bookReview4 = new ArrayList<>();
        bookReview4.add(theResurrectionist.getTitle());
        bookReview4.add(theResurrectionist.getAuthor());
        bookReview4.add(rev3.get(2));
        bookReview4.add(comments4);

        ArrayList<String> comments5 = new ArrayList<>();
        comments5.add(rev4.get(0));
        comments5.add(rev6.get(0));
        ArrayList<Object> bookReview5 = new ArrayList<>();
        bookReview5.add(theShining.getTitle());
        bookReview5.add(theShining.getAuthor());
        bookReview5.add(rev6.get(2));
        bookReview5.add(comments5);

        ArrayList<String> comments6 = new ArrayList<>();
        comments6.add(rev5.get(0));
        ArrayList<Object> bookReview6 = new ArrayList<>();
        bookReview6.add(it.getTitle());
        bookReview6.add(it.getAuthor());
        bookReview6.add(rev5.get(2));
        bookReview6.add(comments6);

        ArrayList<String> comments7 = new ArrayList<>();
        comments7.add(rev10.get(0));
        ArrayList<Object> bookReview7 = new ArrayList<>();
        bookReview7.add(frankenstein.getTitle());
        bookReview7.add(frankenstein.getAuthor());
        bookReview7.add(rev10.get(2));
        bookReview7.add(comments7);

        books.add(bookReview1);
        books.add(bookReview2);
        books.add(bookReview3);
        books.add(bookReview4);
        books.add(bookReview5);
        books.add(bookReview6);
        books.add(bookReview7);

        String query = "SELECT BOOK_TABLE.id, BOOK_TABLE.title, BOOK_TABLE.author," +
                " REVIEW_TABLE.comment, REVIEW_TABLE.rate, REVIEW_TABLE.rate_mean FROM BOOK_TABLE" +
                " \n INNER JOIN REVIEW_TABLE ON BOOK_TABLE.bookId=REVIEW_TABLE.bookId"+
                "ORDER BY rate_mean DESC";



        when(mockDB.retrieveBookReviewsTitleAuthor(query)).thenReturn(
                books
        );
        System.out.println("books" + books);

        assertEquals(books, dbHandler.retrieveReviewsWithHighestRates());
    }


    }


