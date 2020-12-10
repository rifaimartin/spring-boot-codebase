package com.cashlez.demo.controller;

import com.cashlez.demo.dto.SearchByCategoryId;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Category;
import com.cashlez.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> getAllMerchant() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @PostMapping("/get_one_category")
    public ResponseEntity<GeneralResponse> getOneMerchant (@RequestBody SearchByCategoryId searchByCategoryId){
        return new ResponseEntity<>(categoryService.getOneCategory(searchByCategoryId.getCategoryId()), HttpStatus.OK);
    }

    @PostMapping("/add_category")
    public ResponseEntity<GeneralResponse> addCategoryController(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.addNewCategory(category), HttpStatus.OK);
    }

    @PostMapping("/update_category")
    public ResponseEntity<GeneralResponse> updateCategoryController(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
    }

    @PostMapping("/delete_category")
    public ResponseEntity<GeneralResponse> deleteMerchantController (@RequestBody SearchByCategoryId searchByCategoryId){
        return new ResponseEntity<>(categoryService.deleteCategery(searchByCategoryId.getCategoryId()), HttpStatus.OK);
    }
}
