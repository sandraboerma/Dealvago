package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import com.boerma.dealvago.domain.entity.OrderDetail;
import com.boerma.dealvago.domain.entity.Orderline;
import com.boerma.dealvago.domain.entity.User;
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

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createOrder(List<OrderlineDto> orderlineDtos) {


        List<Orderline> itemList = new ArrayList<>();
        for (OrderlineDto dto : orderlineDtos) {
            Orderline orderline = new Orderline();
            orderline.setProduct(productRepository.findById(dto.getProductDto().getId()).get());
            orderline.setQuantity(dto.getQuantity());
            orderline.setProductPrice(dto.getTotalPrice());
            itemList.add(orderline);
        }

        User user = userRepository.findById(1).get();

        OrderDetail order = new OrderDetail();
        order.setOrderlines(itemList);
        order.setCustomer(user);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("Pending");

        orderDetailRepository.save(order);
    }

}
