import org.junit.Test;

public class BookTest {
    @Test(expected = IllegalArgumentException.class)
    public void argumentIsAnEmptyString(){
        String name = "";
        String author = "Johny Bravo";

        //testBook(name, author);
    }

    @Test(expected = NullPointerException.class)
    public void argumentIsANull(){
        String name = null;
        String author = "Johny Bravo";

        //testBook(name, author);
    }
}
