package pl.example.libraryManagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.example.libraryManagment.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
