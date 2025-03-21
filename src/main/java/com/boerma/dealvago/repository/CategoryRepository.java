package com.boerma.dealvago.repository;

import com.boerma.dealvago.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
