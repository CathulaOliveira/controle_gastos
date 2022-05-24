package br.edu.utfpr.pb.server.repository;

import br.edu.utfpr.pb.server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
