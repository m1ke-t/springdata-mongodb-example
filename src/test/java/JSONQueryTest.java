/**
 * Created by Mike S. on 05.08.2018.
 */
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.miket.configuration.MongoConfig;
import com.miket.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class JSONQueryTest extends BaseQueryTest {


    @Test
    public void givenBooksExist_whenFindingBooksByTitle_thenBooksAreFound() {
        Book book = new Book();
        book.setTitle("BookTitle1");
        book.setYear(1945);
        book.setAuthors(authors);
        mongoOps.insert(book);
        book = new Book();
        book.setTitle("BookTitle2");
        book.setYear(1955);
        mongoOps.insert(book);

        List<Book> users = bookRepository.findBooksByTitle("BookTitle1");
        assertThat(users.size(), is(1));
    }

    @Test
    public void givenBooksExist_whenFindingBooksWithYearGreaterThanAndLessThan_thenBooksAreFound() {
        Book book = new Book();
        book.setYear(2020);
        book.setTitle("BookTitle3");
        mongoOps.insert(book);

        book = new Book();
        book.setYear(2002);
        book.setTitle("BookTitle4");
        mongoOps.insert(book);

        book = new Book();
        book.setYear(2033);
        book.setTitle("BookTitle5");
        mongoOps.insert(book);

        List<Book> books = bookRepository.findBooksByYearBetween(2025, 2040);
        assertThat(books.size(), is(1));
    }

    @Test
    public void givenBooksExist_whenFindingBookWithNameStartWithB_thenBooksAreFound() {
        Book book = new Book();
        book.setTitle("BookTitle6");
        book.setYear(1995);
        mongoOps.insert(book);

        book = new Book();
        book.setTitle("zBookTitle7");
        book.setYear(2015);
        mongoOps.insert(book);

        book = new Book();
        book.setTitle("BookTitle8");
        book.setYear(2018);
        mongoOps.insert(book);

        List<Book> books = bookRepository.findBooksByRegexpTitle("^B");

        assertThat(books.size(), is(2));
    }

    @Test
    public void givenBooksExist_whenFindingBookWithNameEndWith11_thenBooksAreFound() {
        Book book = new Book();
        book.setTitle("BookTitle9");
        book.setYear(1999);
        mongoOps.insert(book);

        book = new Book();
        book.setTitle("BookTitle10");
        book.setYear(2010);
        mongoOps.insert(book);

        book = new Book();
        book.setTitle("BookTitle11");
        book.setYear(2013);
        mongoOps.insert(book);

        List<Book> books = bookRepository.findBooksByRegexpTitle("11$");

        assertThat(books.size(), is(1));
    }
}
