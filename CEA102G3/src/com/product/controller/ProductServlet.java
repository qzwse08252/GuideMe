package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.product.model.*;
import com.product_rate.model.*;
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action ======"+action);
		

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
			String productName = req.getParameter("productName");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.add("請輸入商品名稱");
			} else if(!productName.trim().matches(productNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字 和_, 且長度必需在2到50之間");
            }
			

			Integer listPrice = null;
			try {
				listPrice = new Integer(req.getParameter("listPrice").trim());
			} catch (NumberFormatException e) {
				listPrice = 0;
				errorMsgs.add("請輸入定價");
			}
			

			String descr = req.getParameter("descr").trim();
			String descrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
			if (descr == null || descr.trim().length() == 0) {
				errorMsgs.add("請輸入商品敘述");
			}else if(!descr.trim().matches(descrReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商品敘述: 只能是中、英文字母、數字和_ , 且長度必需在2到1000之間");
            }
			

			Part part1 = req.getPart("productPic1");
			byte[] productPic1 = null;
			if (part1 == null || part1.getSize() == 0) {
				errorMsgs.add("請上傳商品圖片");
			} else {
				InputStream in = part1.getInputStream();
				productPic1 = new byte[in.available()];
				in.read(productPic1);
			}
			
//          -----------------錯誤處理使用的VO物件  當使用者無輸入或是輸入不符合規定時部用在全部重新輸入 ---------------
			ProductVO productVO = new ProductVO();   

			productVO.setProductName(productName);
			productVO.setListPrice(listPrice);
			productVO.setDescr(descr);
			productVO.setProductPic1(productPic1);
//          --------------------------------------------------------------------------------
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/addProduct_full.jsp");
				failureView.forward(req, res);
				return;                              // 錯誤處理後的VO return remember
			}
			

			/*************************** 2.開始新增資料 ***************************************/
			ProductService productSvc = new ProductService();
			productVO = productSvc.addProduct(productName, listPrice, descr, productPic1);
			

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			int whichPage = productVO.getProductNo()/5+1;
			String url = "/back-end/product/listAllProduct_full.jsp?whichPage="+whichPage+"&productNo="+productVO.getProductNo();
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Product/addProduct.jsp");
				failureView.forward(req, res);
				
			}
			}
		
		if ("delete".equals(action)) { // 來自listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer productNo = new Integer(req.getParameter("productDelete"));
				
				/***************************2.開始刪除資料***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(productNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/Product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Prduct/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String productNoParameter = req.getParameter("productNo");
				if (productNoParameter == null || (productNoParameter.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer productNo = null;
				try {
					productNo =  Integer.parseInt(productNoParameter);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
			
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();

				ProductVO productVO = productSvc.getOneProduct(productNo);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
				String url = "/back-end/Product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("getOne_For_Display1".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String productName1 = req.getParameter("productName");
//				if (productName1 == null || (productName1.trim()).length() == 0) {
//					errorMsgs.add("請勾選商品名稱");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				
//			
//				/***************************2.開始查詢資料*****************************************/
//				ProductService productSvc = new ProductService();
//
//				ProductVO productVO = productSvc.getOneProductByName(productName1);
//				if (productVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
//				String url = "/back-end/Product/listOneProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/Product/ProductSelect_Page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer productNo = Integer.parseInt(req.getParameter("productNo"));

				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productNo);

				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的productVO物件,存入req
				String url = "/back-end/product/update_Product_input_full.jsp";
				

				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Product_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/listAllProduct_full.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_Product_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
				Integer productNo = new Integer(req.getParameter("productNo").trim());

				String productName = req.getParameter("productName");
				
				String productNameReg = "^[(\u4e00-\u9fff)(a-zA-Z0-9_)]{2,1000}$";
				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!productName.trim().matches(productNameReg)) { 
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字 , 且長度必需在2到30之間");
	            }
				
				
				Integer listPrice = new Integer(req.getParameter("listPrice").trim());
				
				String descr = req.getParameter("descr");
				System.out.println("descr: "+descr);
				if (descr == null || descr.trim().length() == 0) {
					errorMsgs.add("商品敘述: 請勿空白");
				}
				
				Boolean productStatus = Boolean.parseBoolean(req.getParameter("productStatus"));
				System.out.println("productStatus : "+productStatus);
				
				Part part1 = req.getPart("productPic1");
				byte[] productPic1 = null;
				if (part1 == null || part1.getSize() == 0) {
					ProductService productSvc = new ProductService();
					ProductVO productVO = productSvc.getOneProduct(productNo);
					productPic1 = productVO.getProductPic1();
				} else {
					InputStream in = part1.getInputStream();
					productPic1 = new byte[in.available()];
					in.read(productPic1);
				}
				

				ProductVO productVO= new ProductVO();
				productVO.setProductNo(productNo);
				productVO.setProductName(productName);
				productVO.setListPrice(listPrice);
				productVO.setDescr(descr);
				productVO.setProductStatus(productStatus);
				productVO.setProductPic1(productPic1);
			

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/update_Product_input_full.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(productNo,productName, listPrice,descr, productStatus,productPic1);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的productVO物件,存入req
				String whichPage = req.getParameter("whichPage");
				String url = "/back-end/product/listAllProduct_full.jsp?whichPage="+whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/update_Product_input_full.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		if ("getOne_For_Status".equals(action)) { // 來自listAllProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer productNo = new Integer(req.getParameter("productNo").trim());

				String productStatusStr = req.getParameter("productStatus");
				Boolean productStatus =null;
				
				if(productStatusStr.equals("1")) {
					productStatus = true;
				}else {
					productStatus = false;
				}

				/***************************2.開始更新資料*****************************************/
				ProductService productSvc = new ProductService();

				productSvc.getChangeProductStatus( productStatus,productNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Product/listAllProduct.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
				String url = "/back-end/Product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllProduct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("updateRate".equals(action)) {
			
			int productNo = new Integer(req.getParameter("productNo"));
			int rate = new Integer(req.getParameter("rate"));
			
			ProductService productSvc = new ProductService();
			productSvc.updateProductRate(productNo, rate);
			
			res.setContentType("text/plain; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.write("感謝您的評分");
			out.flush();
			out.close();
		}
		
	}

}
