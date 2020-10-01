import java.util.Objects;

public class Book {
    private String name, author;
    Chapter chapter;

    public Book(String n, String a, Chapter ch){
       this.setName(n);
       this.setAuthor(a);
       chapter = chapter;
    }

    public void addChapter(Chapter chapter){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.equals(" ")){
            this.name = name;
        }
        else{this.name = "Default name";}
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author != null && !author.equals(" ")){
            this.author = author;
        }
        else{this.author = "Default author";}
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, chapter);
    }
}
