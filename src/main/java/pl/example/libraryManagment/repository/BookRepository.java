package pl.example.libraryManagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.example.libraryManagment.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}