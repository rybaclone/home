package java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;


@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PrintWriter out;
	
    public ServletController() {
        super();
    }


    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	    
    	 out = response.getWriter();   	 
    	 response.setContentType("text/html;charset=UTF-8");
   	 
    	 Map params = request.getParameterMap();
    	    Iterator i = params.keySet().iterator();
    	    while ( i.hasNext() )
    	      {
    	        String key = (String) i.next();
    	        String value = ((String[]) params.get( key ))[ 0 ];
    	        if (value == null && value.isEmpty()) {
    	        	out.append(key + " value is empty or null");
    	        	out.write("n");
    	        }
    	      }
    	    out.close();
    	       	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		handleRequest(request, response);
				
		Order order = new Order();		
		order.setCustomerId(Long.parseLong(request.getParameter("customerId")));						
		order.setOrderUnits(Integer.parseInt(request.getParameter("units")));		
		order.setUnitPrice(Float.parseFloat(request.getParameter("price")));
		// order.setOrderDiscount(Integer.parseInt(request.getParameter("discount")));
		
		CalculateDiscountModel model = new CalculateDiscountModel();		
		float orderAmount = model.calculateTotal(order.getOrderUnits(), order.getUnitPrice(), order.getOrderDiscount());
		order.setOrderAmount(orderAmount);		
		order.setOrderTotal(model.getCalculationValue());
		order.setOrderDiscount(order.getOrderDiscount());
				
		request.setAttribute("order", order);
		getServletContext().getRequestDispatcher("/CalculatorView.jsp").forward(request, response);
				
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

	
}
