package com.insurance.model;

/**
 * A sub category that belongs to a Category,
 * e.g. Category "Vehicle Insurance" -> SubCategory "Two Wheeler", "Four Wheeler".
 */
public class SubCategory {

    private int id;
    private String name;
    private String description;
    private int categoryId; // links back to the parent Category

    public SubCategory(int id, String name, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "SubCategoryID: " + id + " | Name: " + name + " | Description: " + description
                + " | CategoryID: " + categoryId;
    }
}
