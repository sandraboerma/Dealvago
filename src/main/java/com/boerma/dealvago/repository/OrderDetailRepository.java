package com.boerma.dealvago.repository;

import com.boerma.dealvago.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
