package com.order.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;
import com.order.model.OrderService;
import com.order_item.model.OrderItemVO;

/**
 * Servlet implementation class OrderServlet1
 */
public class OrderServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println("action ====="+ action);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		Vector<OrderItemVO> buylist = (Vector<OrderItemVO>) session.getAttribute("shoppingcart");
		
		if("confirmCheckOut".equals(action)) {
			
			Integer amount =new Integer(request.getParameter("amount"));
			System.out.println("amount: "+ amount);
			String creditCardNo = request.getParameter("creditCardNo");
			System.out.println("creditCardNo: "+creditCardNo);
			Set<OrderItemVO> orderItems = new HashSet<OrderItemVO>(buylist); 
			OrderService orderSvc = new OrderService();
			orderSvc.addOrder(memberVO.getMemberNo(), amount, creditCardNo,orderItems);
			
			System.out.println("新增訂單成功");
			Vector<OrderItemVO> emptyBuylist = new Vector<OrderItemVO>();
			session.setAttribute("shoppingcart", emptyBuylist);
			
			String url = "/front-end/order/listMemberAllOrder.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
