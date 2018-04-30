package model;
/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/

//@Entity
//@Table(name = "order")
public class Order {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "order_id")
	private Long 	orderId;
	
//	@Column(name = "units")
	private int 	orderUnits;
	
//	@Column(name = "price")
	private float 	unitPrice;
	
//	@Column(name = "amount")
	private float 	orderAmount;
	
//	@Column(name="discount")
	private int orderDiscount;
	
//	@Column(name = "total")
	private float 	orderTotal;	

//	@Column(name = "customer_id")
	private Long 	customerId;
	
	public Order() {	
	}
	
	public Long getOrderId() {
		return orderId;
	}

	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getOrderUnits() {
		return orderUnits;
	}

	public void setOrderUnits(int orderUnits) {
		this.orderUnits = orderUnits;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	
	
}
