package java;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnectionDb;

public class CalculateDiscountModel {

	float calculationValue;

	// number of units
	private int BIG_PURCHASE_STEP = 10;
	private int LARGE_PURCHASE_STEP = 100;

	// % discount
	private int BIG_PURCHASE_DISCOUNT = 10;
	private int LARGE_PURCHASE_DISCOUNT = 20;

	private int NEW_CUSTOMER_DISCOUNT = 30;

	/*
	 * Given an input of customer, the price of the purchase, number of units and a
	 * type of a discount
	 * 
	 * New customers discount: If this is customers first purchase apply 30%
	 * discount else 10%
	 */

	public float calculateAmount(int units, float price) {
		return units * price;
	}

	public float calculateTotal(int units, float price, int rate) {

		float amount = calculateAmount(units, price);

		float discount = (float) amount * rate / 100;

		return amount - discount;
	}

	public boolean isCustomerFirstOrder(int customerId) {

		boolean returnValue = false;

		ConnectionDb conn = new ConnectionDb();

		String sql = "select customer_id " + "from `test`.customer where customer_id not in"
				+ " (select customer_id from `test`.order )";

		try {
			ResultSet rs = conn.ConnectDb(sql);
			while (rs.next()) {
				if (rs.getInt("customer_id") == customerId) {
					returnValue = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;

	}

	/*
	 * Big purchase discount: If customer orders more than 10 units apply 10%
	 * discount, if more than 100 units 20% discount.
	 */
	public int getBigDiscountRate(int units) {

		int rate = 1;

		if (units > LARGE_PURCHASE_STEP) {
			rate = LARGE_PURCHASE_DISCOUNT;
		} else if (units > BIG_PURCHASE_STEP) {
			rate = BIG_PURCHASE_DISCOUNT;
		}

		return rate;
	}

	public void calculateCustomerDiscount(int price, int units, int customerId) {

		int rate = 1;
		if (isCustomerFirstOrder(customerId)) {
			rate = NEW_CUSTOMER_DISCOUNT;
		} else {
			rate = getBigDiscountRate(units);
		}
		calculationValue = calculateTotal(units, price, rate);
	}

	public float getCalculationValue() {
		return calculationValue;

	}
}
