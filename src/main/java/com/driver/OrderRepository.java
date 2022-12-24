package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    private HashMap<String,List<String>> partnerOrderMap=new HashMap<>();
    private HashMap<String,Order> orderMap=new HashMap<>();
    private HashMap<String,DeliveryPartner> deliveryPartnerMap=new HashMap<>();
    private List<String> assignedOrder=new ArrayList<>();

    public void addOrder(Order order){
        orderMap.put(order.getId(),order);
    }

    public void addPartner(String partnerId){
        deliveryPartnerMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public Order getOrder(String orderId){
        return orderMap.get(orderId);
    }

    public DeliveryPartner getPartner(String partnerId){
        return deliveryPartnerMap.get(partnerId);
    }

    public void orderPartner(String order,String partner){
        if(orderMap.containsKey(order) && deliveryPartnerMap.containsKey(partner)){
            List<String> currOrder=new ArrayList<>();
            if(partnerOrderMap.containsKey(partner)){
                currOrder=partnerOrderMap.get(partner);
            }
            currOrder.add(order);
            partnerOrderMap.put(partner,currOrder);
        }
        assignedOrder.add(order);
    }

    public int noOfOrders(String partnerId){
        List<String> orders=new ArrayList<>();
        orders=partnerOrderMap.get(partnerId);
        return orders.size();
    }

    public List<String> orderList(String partnerId){
        List<String> orderList=new ArrayList<>();
        orderList=partnerOrderMap.get(partnerId);
        return orderList;
    }

    public List<String> allOrders(){
        List<String> orders=new ArrayList<>();
       for(String order:orderMap.keySet()){
           orders.add(order);
       }
       return orders;
    }

    public int countOfUnassignedOrders(){
        int c=orderMap.size();
        int c1=assignedOrder.size();
//        for(String partner : partnerOrderMap.keySet()){
//            List<String> orderlist=new ArrayList<>();
//            orderlist=partnerOrderMap.get(partner);
//            c1+=orderlist.size();
//            orderlist.clear();
//        }
        return c-c1;
    }

    public int ordersLeftAfterGivenTime(int time,String partnerId){
        int count = 0;
        List<String> orderList = partnerOrderMap.get(partnerId);

        for(int i=0;i<orderList.size();i++){
            Order curOrder = orderMap.get(orderList.get(i));
            if(curOrder.getDeliveryTime()>time){
                count++;
            }
        }
        return count;
    }


    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int lastTime = 0;
        List<String> orderList = partnerOrderMap.get(partnerId);

        for(int i=0;i<orderList.size();i++){
            Order curOrder = orderMap.get(orderList.get(i));
            lastTime = Math.max(lastTime,curOrder.getDeliveryTime());
        }

        int hour = lastTime/60;
        int min = lastTime%60;

        String timeInHHMM = "";

        if(hour<=9){
            timeInHHMM += "0" + hour;
        }else if(hour>9){
            timeInHHMM+= hour + "";
        }

        timeInHHMM += ":";

        if(min<=9){
            timeInHHMM += "0" + min;
        }else if(hour>9){
            timeInHHMM+= min + "";
        }

        return timeInHHMM;
    }

    public void deletePartnerById(String partnerId){
        deliveryPartnerMap.remove(partnerId);
        List<String> list = partnerOrderMap.get(partnerId);
        partnerOrderMap.remove(partnerId);

        for(int i=0;i<list.size();i++){
            assignedOrder.remove(list.get(i));
        }

    }

    public void deleteOrderById(String orderId){
        orderMap.remove(orderId);

        for(String partnerId:partnerOrderMap.keySet()){
            List<String> curOrder = partnerOrderMap.get(partnerId);
            for(int i=0;i<curOrder.size();i++){
                if(curOrder.get(i).equals(orderId))
                    curOrder.remove(orderId);
            }
        }

        assignedOrder.remove(orderId);
    }



}
