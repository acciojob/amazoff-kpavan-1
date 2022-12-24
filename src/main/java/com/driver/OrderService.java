package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId){
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.orderPartner(orderId,partnerId);
    }

    public Order getOrder(String orderId){
        return orderRepository.getOrder(orderId);
    }

    public DeliveryPartner getPartner(String partnerId){
        return orderRepository.getPartner(partnerId);
    }

    public int noOfOrders(String partnerId){
        return orderRepository.noOfOrders(partnerId);
    }

    public List<String> ordersList(String partnerId){
        return orderRepository.orderList(partnerId);
    }

    public List<String> getAllOrder(){
        return orderRepository.allOrders();
    }

    public int countOfUnassigned(){
        return orderRepository.countOfUnassignedOrders();
    }

    public int ordersLeftAfterGivenTime(int time,String partnerId){
        return orderRepository.ordersLeftAfterGivenTime(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
