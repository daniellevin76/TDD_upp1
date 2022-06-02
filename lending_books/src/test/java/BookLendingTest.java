import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class BookLendingTest {

    Book theShining, frankenstein, theResurrectionist, animalFarm, annaKarenina, rescue, annaKareninaNotAvailable, rescueNotAvailable, it;
    DB mockDB = mock(DB.class);

    BookLending bookLending = new BookLending();
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

        rescue = new Book(7,"Rescue", "Jennifer Nielsen", "Detective", 2021, 10.0, 2);

        annaKareninaNotAvailable = new Book(6,"Anna Karenina", "Leo Tolstoy", "Realist Novel", 1978, 30.0, 3);

        rescueNotAvailable = new Book(7,"Rescue", "Jennifer Nielsen", "Detective", 2021, 10.0, 0);

        ArrayList<Book> books = new ArrayList<>();
    }


    @Test
    void bookTheShiningIsInTheStock(){

   theShining.lend(1);
   assertEquals(99, theShining.getCopies());
    }



    @Test
    void should_returnTheShining_when_searchByTitleTheShining(){


        String query = "SELECT * FROM BOOK_TABLE WHERE title like the shining";
        books.add(theShining);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByTitle("the shining") );

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
    void should_returnTheShiningAndTheResurrectionist_when_searchByTitleTHe(){

        String query = "SELECT * FROM BOOK_TABLE WHERE title like the";
        books.add(theShining);
        books.add(theResurrectionist);
        when(mockDB.retrieveBooks(query)).thenReturn(
                books
        );
        assertEquals(books, dbHandler.searchBookByTitle("the") );

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
    void should_returnItTheShinFrankTheResur_when_searchByGenreHorror(){

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
    void should_returnBookReviews_when_RecueBookReviewRetrievedFromDB(){
        ArrayList<String> comments = new ArrayList<>();
        comments.add("It was an interesting read");
        comments.add("it was a nice read");
        comments.add("Strange book");

        System.out.println("comments in test " + comments);



        String query = "SELECT * FROM REVIEW_TABLE WHERE bookId = 0";

        when(mockDB.retrieveBookReviews(query)).thenReturn(
                comments
        );

        System.out.println("dbhandler " + dbHandler.retrieveReviews(String.valueOf(rescue.getBookId())));


        assertEquals(comments, dbHandler.retrieveReviews(String.valueOf(rescue.getBookId())));
    }



    }


