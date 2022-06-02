import javax.naming.InsufficientResourcesException;

public class Book {


   private String title, author, genre;
    private int bookId, year, copies;
   private double cost;

    public Book(int bookId, String title, String author, String genre, int year, double cost, int copies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.cost = cost;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }


    public int getYear() {
        return year;
    }

    public double getCost() {
        return cost;
    }

    public int getBookId() {
        return bookId;
    }

    public int getCopies() {
        return copies;
    }

    public boolean equals(Object obj){
        Book book = (Book)obj;
        return book.title.equals(title)&&book.author.equals(author) ;
    }

    public String toString(){
        return (title + ", " + author);
    }

    public void lend(int quantity) {

        if(copies < quantity){
            System.out.println("Book not in Stock");
        }
        copies -= quantity;
    }
}
