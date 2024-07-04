package com.example.MinorProject.DigitalLibrary.Repository;

import com.example.MinorProject.DigitalLibrary.DTO.BookAll;
import com.example.MinorProject.DigitalLibrary.DTO.SearchBookRequest;
import com.example.MinorProject.DigitalLibrary.Entity.Book;
import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import com.example.MinorProject.DigitalLibrary.Entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.name =:name and b.student is null")
    List<Book> findByNameAndStudentIsNull(String name);

    List<Book> findByName(String name);

    List<Book> findByGenre(Genre genre);
    @Query("SELECT new com.example.MinorProject.DigitalLibrary.DTO.BookAll(b.name, b.pages, b.genre, a.authorName) " +
            "FROM Book b JOIN b.my_author a " +
            "WHERE b.name = :name")
    List<BookAll> findByName2(@Param("name")String name);


    @Query("SELECT new com.example.MinorProject.DigitalLibrary.DTO.BookAll(b.name, b.pages, b.genre, a.authorName) " +
            "FROM Book b JOIN b.my_author a " +
            "WHERE b.genre = :genre")
    List<BookAll> findByGenre2(@Param("genre")Genre genre);


    @Modifying // for DML support
    @Transactional // for updating any data
    @Query("update Book b set b.student = ?2,b.dueDate = ?3 where b.id = ?1 and b.student is null")
    void assignBookToStudent(int bookId, Student student, Date DueDate);

    @Modifying // for DML support
    @Transactional // for updating any data
    @Query("update Book b set b.student = null,b.dueDate = null where b.id = ?1")
    void unassignBookToStudent(int bookId);

    @Query("SELECT s.email FROM Book t JOIN t.student s WHERE t.dueDate between :startTime AND :endTime")
    List<String> getEmailAddress(Date startTime,Date endTime);

    @Query("SELECT DISTINCT b.name AS bookName, a.authorName AS name FROM Book b JOIN b.my_author a WHERE b.genre = :genre")
    List<String> getBooks(Genre genre);

    @Query("SELECT DISTINCT b.name AS bookName, a.authorName AS name FROM Book b JOIN b.my_author a")
    List<String> getAllBooksInside();


//    //to do
//    @Query("select distinct a.authorName from Book b join b.my_author a where a.id = 1")
//    String getAuthorName();

    @Query("SELECT new com.example.MinorProject.DigitalLibrary.DTO.BookAll(b.name, b.pages, b.genre, a.authorName) " +
            "FROM Book b JOIN b.my_author a")
    List<BookAll> getAllBooks();


}