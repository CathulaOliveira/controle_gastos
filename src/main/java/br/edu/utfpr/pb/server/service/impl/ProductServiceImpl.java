package br.edu.utfpr.pb.server.service.impl;

import br.edu.utfpr.pb.server.model.Product;
import br.edu.utfpr.pb.server.repository.ProductRepository;
import br.edu.utfpr.pb.server.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CrudServiceImpl<Product, Long>
    implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }
}
