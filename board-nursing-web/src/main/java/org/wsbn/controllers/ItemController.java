package org.wsbn.controllers;
 
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.wsbn.dto.OrderDto;
 
@ManagedBean(name = "item")
@SessionScoped
public class ItemController implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String item;
    private Integer qty;
    private Double price;
    OrderDto orderDto;
 
    public String getItem() {
        return item;
    }
 
    public void setItem(String item) {
        this.item = item;
    }
 
    public Double getPrice() {
        return price;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }
 
    public Integer getQty() {
        return qty;
    }
 
    public void setQty(Integer qty) {
        this.qty = qty;
    }
 
    public OrderDto getOrder() {
        return orderDto;
    }
 
    public void setOrder(OrderDto order) {
        this.orderDto = order;
    }
    private static final ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();
 
    public ArrayList<OrderDto> getOrderList() {
        return orderList;
    }
 
    public String addAction() {
        OrderDto orderitem = new OrderDto(this.item, this.qty, this.price);
        orderList.add(orderitem);
 
        item = "";
        qty = 0;
        price = 0.0;
        return null;
    }
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Edited",((OrderDto) event.getObject()).getItem());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Item Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        orderList.remove((OrderBean) event.getObject());
    }  
}