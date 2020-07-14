@DbTable(name = "store")
public class BookStore {
    @DbId
    private long id;

    @DbColumn
    private String name;

    @DbColumn
    private String author;

    @DbColumn
    private Integer price;

    public BookStore(){}

    public BookStore(long id, String name, String author, Integer price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Book: "+id+" "+name+" "+author+" "+price;
    }
}
