package br.edu.utfpr.pb.server.repository;

import br.edu.utfpr.pb.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
