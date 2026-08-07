package com.insurance.service;

import com.insurance.model.Category;
import com.insurance.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private List<Category> categoryList = new ArrayList<>();
    private List<SubCategory> subCategoryList = new ArrayList<>();

    private int categoryIdCounter = 1;
    private int subCategoryIdCounter = 1;

    public Category addCategory(String name, String description) {
        Category category = new Category(categoryIdCounter++, name, description);
        categoryList.add(category);
        return category;
    }

    public SubCategory addSubCategory(String name, String description, int categoryId) {
        // Make sure the parent category actually exists before adding.
        if (findCategoryById(categoryId) == null) {
            System.out.println("Cannot add sub-category. Category ID " + categoryId + " does not exist.");
            return null;
        }
        SubCategory subCategory = new SubCategory(subCategoryIdCounter++, name, description, categoryId);
        subCategoryList.add(subCategory);
        return subCategory;
    }

    public Category findCategoryById(int categoryId) {
        for (Category category : categoryList) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    public SubCategory findSubCategoryById(int subCategoryId) {
        for (SubCategory subCategory : subCategoryList) {
            if (subCategory.getId() == subCategoryId) {
                return subCategory;
            }
        }
        return null;
    }

    public List<Category> getAllCategories() {
        return categoryList;
    }

    public List<SubCategory> getAllSubCategories() {
        return subCategoryList;
    }

    public List<SubCategory> getSubCategoriesByCategory(int categoryId) {
        List<SubCategory> result = new ArrayList<>();
        for (SubCategory subCategory : subCategoryList) {
            if (subCategory.getCategoryId() == categoryId) {
                result.add(subCategory);
            }
        }
        return result;
    }

    public void printAllCategories() {
        if (categoryList.isEmpty()) {
            System.out.println("No categories added yet.");
            return;
        }
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

    public void printAllSubCategories() {
        if (subCategoryList.isEmpty()) {
            System.out.println("No sub-categories added yet.");
            return;
        }
        for (SubCategory subCategory : subCategoryList) {
            System.out.println(subCategory);
        }
    }
}
