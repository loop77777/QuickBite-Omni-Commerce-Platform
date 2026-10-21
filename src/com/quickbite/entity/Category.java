package com.quickbite.entity;

// File: com/quickbite/entity/Category.java

import java.util.Objects;

public class Category implements Comparable<Category> {
    private int categoryId;
    private String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public int compareTo(Category o) {
        return Integer.compare(this.categoryId, o.categoryId);
    }

    @Override
    public String toString() {
        return categoryName;
    }
}