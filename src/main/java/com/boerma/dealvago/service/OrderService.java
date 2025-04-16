package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import com.boerma.dealvago.domain.entity.OrderDetail;
import com.boerma.dealvago.domain.entity.Orderline;
import com.boerma.dealvago.repository.OrderDetailRepository;
import com.boerma.dealvago.repository.ProductRepository;
import com.boerma.dealvago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderDetailRepository orderDetailRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderService(OrderDetailRepository orderDetailRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void createOrder(List<OrderlineDto> orderlineDtos, Integer userId) {

        List<Orderline> itemList = new ArrayList<>();
        for (OrderlineDto dto : orderlineDtos) {
            Orderline orderline = new Orderline();
            orderline.setProduct(productRepository.findById(dto.getProductDto().getId()).get());
            orderline.setQuantity(dto.getQuantity());
            orderline.setProductPrice(dto.getTotalPrice());
            itemList.add(orderline);
        }

        OrderDetail order = new OrderDetail();
        order.setOrderlines(itemList);
        order.setCustomer(userRepository.findById(userId).get());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("Pending");

        orderDetailRepository.save(order);
    }

}
