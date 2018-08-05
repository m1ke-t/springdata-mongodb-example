import com.miket.model.Author;
import com.miket.model.Book;
import com.miket.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;

public class BaseQueryTest {
    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected MongoOperations mongoOps;

    ArrayList<Author> authors = new ArrayList<>();
    private Author author1 = new Author();
    private Author author2 = new Author();


    @Before
    public void testSetup() {
        if (!mongoOps.collectionExists(Book.class)) {
            mongoOps.createCollection(Book.class);
        }
        author1.setName("Author_1_name");
        author1.setBio("Author_1_bio");
        author1.setId("1");
        author2.setName("Author_2_name");
        author2.setBio("Author_2_bio");
        author2.setId("2");
        authors.add(author1);
        authors.add(author2);
    }

    @After
    public void tearDown() {
       mongoOps.dropCollection(Book.class);
    }
}
