package br.edu.utfpr.pb.server.controller;

import br.edu.utfpr.pb.server.model.Product;
import br.edu.utfpr.pb.server.service.CrudService;
import br.edu.utfpr.pb.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController extends CrudController<Product, Long> {

    @Autowired
    private ProductService productService;

    @Override
    protected CrudService<Product, Long> getService() {
        return this.productService;
    }
    
}
