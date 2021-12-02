package com.bellpro.delivery.repository;

import com.bellpro.delivery.domain.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordering, Long> {
}
