import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChapterTest {
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenAStringIsNotAdded(){
        String name = "First Chapter, Section 2";
        Double number = 1.3;

        number.equals("1.3");
    }
    @Test(expected = NullPointerException.class)
    public void exceptionIsThrownWhenStringIsNull(){
        String name = null;
        String number = "1.2";



    }

    @Test(expected = IllegalArgumentException.class)
    public void numberStringIsLongerThanTwoLevels(){
        String name = "Chapter 1, Level 2";
        String number = "1.23";

        number.equals("1.2");
    }

    @Test
    public void mustAddUniqueChapterToBook(){
        Chapter chapter = new Chapter( "New 1", "1.1");
        Chapter chapter2 = new Chapter( "New 2", "1.2");

        List<Book> bookList = new ArrayList<>();
        bookList.add( new Book("New Book1", "New Author", chapter));
        bookList.add( new Book("New Book2", "New Author", chapter2));
        assertEquals(2, bookList.size());
    }


}
