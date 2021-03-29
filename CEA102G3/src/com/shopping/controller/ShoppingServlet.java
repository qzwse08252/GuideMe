package com.shopping.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itineItem.model.ItineItemVO;
import com.order_item.model.OrderItemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;





public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShoppingServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();
		
		
		HttpSession session = req.getSession();
		Vector<OrderItemVO> buylist = (Vector<OrderItemVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		System.out.println("action === "+ action);

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				for(OrderItemVO orderItemVO: buylist) {
					if(orderItemVO.getProductVO().getProductNo()==d) {
						buylist.remove(orderItemVO);
						return;
					}
				}
			
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				boolean match = false;

				// 取得後來新增的書籍
				 OrderItemVO orderItemVO = getOrderItemVO(req);

				// 新增第一本書籍至購物車時
				if (buylist == null) {
					buylist = new Vector<OrderItemVO>();
					buylist.add(orderItemVO);
					System.out.println("add first product");
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						OrderItemVO orderItemVOin = buylist.get(i);

						// 假若新增的書籍和購物車的書籍一樣時
						if (orderItemVOin.getProductVO().getProductNo().equals(orderItemVO.getProductVO().getProductNo())) {
							orderItemVOin.setProductCount(orderItemVO.getProductCount()
									+ orderItemVOin.getProductCount());
							orderItemVOin.setSellingPrice(orderItemVO.getSellingPrice()+orderItemVOin.getSellingPrice());
							buylist.setElementAt(orderItemVOin, i);
							match = true;
							System.out.println("add same product");
						} // end of if name matches
					} // end of for

					// 假若新增的書籍和購物車的書籍不一樣時
					if (!match) {
						buylist.add(orderItemVO);
						System.out.println("add different product");
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			System.out.println("setAttribute to session");
//			String url = "/EShop.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				OrderItemVO orderItemVO = buylist.get(i);

				int sellingPrice = orderItemVO.getSellingPrice();
				total += sellingPrice;
			}

			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			String url = "/front-end/product/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}
	
	private OrderItemVO getOrderItemVO(HttpServletRequest req) {

		int productNo = new Integer(req.getParameter("productNo"));
		int productCount = new Integer(req.getParameter("productCount"));
		
		OrderItemVO orderItemVO = new OrderItemVO();
		ProductService productSvc = new ProductService();
		ProductVO productVO = productSvc.getOneProduct(productNo);
		int sellingPrice = productVO.getListPrice()*productCount;
		
		orderItemVO.setProductVO(productVO);
		orderItemVO.setProductCount(productCount);
		orderItemVO.setSellingPrice(sellingPrice);
		
		return orderItemVO;
	}

}
