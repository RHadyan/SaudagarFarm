package com.saudagarFarmer.kelompokArio.Repository;

import com.saudagarFarmer.kelompokArio.Models.ProductModels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModels,Integer> {
}
