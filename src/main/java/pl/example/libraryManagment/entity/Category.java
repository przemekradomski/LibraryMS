package pl.example.libraryManagment.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "kategorie")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nazwa", nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "nadkategoria_id")
    private Category parentCategory;

    // Removed books mapping as there is no explicit join table in the provided schema.
    // If a many-to-many relationship exists, a join table needs to be defined in the schema.

    public Category() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Category getParentCategory() { return parentCategory; }
    public void setParentCategory(Category parentCategory) { this.parentCategory = parentCategory; }
    // Removed get/set for books
}
