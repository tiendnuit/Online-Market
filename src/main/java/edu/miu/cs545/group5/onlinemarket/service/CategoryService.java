package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAllCategory();
    public Category saveCategory(Category category);
}
