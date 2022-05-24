package br.edu.utfpr.pb.server.controller;

import br.edu.utfpr.pb.server.model.Category;
import br.edu.utfpr.pb.server.service.CategoryService;
import br.edu.utfpr.pb.server.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryController
        extends CrudController<Category, Long> {

    @Autowired
    private CategoryService categoryService;

    @Override
    protected CrudService<Category, Long> getService() {
        return this.categoryService;
    }

}
