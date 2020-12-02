package com.cashlez.demo.service;

import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Category;
import com.cashlez.demo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public GeneralResponse getAllCategory(){
        GeneralResponse generalResponse = new GeneralResponse();
        Iterable<Category> data = categoryRepository.findAll();
        generalResponse.success("200", "List Of Category", data);
        return generalResponse;
    }

    public GeneralResponse addNewCategory (Category category){
        GeneralResponse generalResponse = new GeneralResponse();

        Category data = categoryRepository.save(category);
        generalResponse.success("201", "Success created category !", data);
        return generalResponse;
    }

    public GeneralResponse updateCategory (Category category){

        Date date = new Date();
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());

        GeneralResponse generalResponse = new GeneralResponse();

        Category newCategory = new Category();
        if (categoryOptional.isPresent()){
            newCategory = categoryOptional.get();
            newCategory.setModifiedDate(date);
            if (category.getName() != null){
                newCategory.setName(category.getName());
            }
            if (category.getCategoryImage() != null){
                newCategory.setCategoryImage(category.getCategoryImage());
            }
            if (category.getDescription() != null){
                newCategory.setDescription(category.getDescription());
            }
            if (category.getCreatedBy() != null){
                newCategory.setCreatedBy(category.getCreatedBy());
            }
            categoryRepository.save(newCategory);
            generalResponse.success("200","Success Updated category !", newCategory);
        }else {
            generalResponse.fail("404", "Category is not found !");
        }
        return generalResponse;
    }

    public GeneralResponse deleteCategery (long categoryId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(categoryId);
            generalResponse.success("00", "Success delete Category !", null);
        }else {
            generalResponse.fail("01", "Category not found !");
        }
        return generalResponse;
    }

    public GeneralResponse getOneCategory (long categoryId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            generalResponse.success("200", "Detail Of Category", categoryOptional);
        }else {
            generalResponse.fail("404", "Category not found !");
        }
        return generalResponse;
    }
}
