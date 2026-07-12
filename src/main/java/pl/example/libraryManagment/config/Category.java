package pl.example.libraryManagment.config;

public class Category {
    private long categoryId;
    private String nameOfCategory;
    private Long superCategoryId;

    public Category() {
    }

    public Category(long categoryId, String nameOfCategory, Long superCategoryId) {
        this.categoryId = categoryId;
        this.nameOfCategory = nameOfCategory;
        this.superCategoryId = superCategoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public Long getSuperCategoryId() {
        return superCategoryId;
    }

    public void setSuperCategoryId(Long superCategoryId) {
        this.superCategoryId = superCategoryId;
    }
}
