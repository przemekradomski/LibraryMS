package pl.example.libraryManagment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.example.libraryManagment.entity.Author;
import pl.example.libraryManagment.entity.Book;
import pl.example.libraryManagment.repository.BookRepository;
import pl.example.libraryManagment.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book addBook(Book book){return bookRepository.save(book);}

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBook(String search){
        String searchLower = search.toLowerCase();
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchLower)
                        || book.getAuthors().stream()
                        .anyMatch(author ->
                                author.getFirstName().toLowerCase().contains(searchLower)
                                        || author.getLastName().toLowerCase().contains(searchLower)))
                .collect(Collectors.toList());
    }


    public List<Author> searchAuthors(String search){
        String searchLower = search.toLowerCase();
        return authorRepository.findAll()
                .stream()
                .filter(author -> author.getFirstName().toLowerCase().contains(searchLower)
                    || author.getLastName().toLowerCase().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
    }

    public List <Book> GetLastAddedBooks(){
        return bookRepository.findAll()
                .stream()
                .sorted((a, b) -> Long.compare(b.getId(), a.getId()))
                .limit(5)
                .collect(java.util.stream.Collectors.toList());

    }
}
