package com.miket.repository;

import com.miket.model.Author;
import com.miket.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>{
    @Query("{ 'title' : ?0 }")
    List<Book> findBooksByTitle(String title);

    @Query("{ 'year' : { $gt: ?0, $lt: ?1 } }")
    List<Book> findBooksByYearBetween(int yearGT, int yearLT);

    @Query("{ 'title' : { $regex: ?0 } }")
    List<Book> findBooksByRegexpTitle(String regexp);

    List<Book> findByTitle(String title);

    List<Book> findByTitleLikeOrderByYearAsc(String title);

    List<Book> findByYearBetween(int yearGT, int yearLT);

    List<Book> findByTitleStartingWith(String regexp);

    List<Book> findByTitleEndingWith(String regexp);

    List<Book> findByAuthors(ArrayList<Author> authors);

    @Query(value = "{}", fields = "{title : 1}")
    List<Book> findTitleAndId();

    @Query(value = "{}", fields = "{_id : 0}")
    List<Book> findTitleAndYearExcludeId();
}