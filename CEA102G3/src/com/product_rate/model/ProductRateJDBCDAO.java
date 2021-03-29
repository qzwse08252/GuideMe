package com.product_rate.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*; 

public class ProductRateJDBCDAO implements ProductRateDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO guideme.Product_Rate (Member_No,Product_No,Rate,Comment) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT Member_No,Product_No,Rate,Comment FROM guideme.Product_Rate  order by Product_No";
		private static final String GET_ONE_STMT = 
			"SELECT Member_No,Product_No,Rate,Comment, FROM guideme.Product_Rate where Product_No = ?";
		private static final String DELETE = 
			"DELETE FROM guideme.Product_Rate where Product_No = ?";
		private static final String UPDATE = 
			"UPDATE guideme.Product_Rate set Member_No=?, Product_No=?, Rate=?, Comment=? where Product_No = ?";
		@Override
		public void insert(ProductRateVO productRateVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, productRateVO.getMemberNo());
				pstmt.setInt(2, productRateVO.getProductNo());
				pstmt.setInt(3, productRateVO.getRate());
				pstmt.setString(4, productRateVO.getComment());
				
				

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
		}
		@Override
		public void update(ProductRateVO productRateVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, productRateVO.getMemberNo());
				pstmt.setInt(2, productRateVO.getProductNo());
				pstmt.setInt(3, productRateVO.getRate());
				pstmt.setString(4, productRateVO.getComment());
				
				

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			
			}
			
			
		}
//		@Override
//		public void delete(Integer memeberNo,Integer productNo) {
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(DELETE);
//
//				pstmt.setInt(1, productNo);
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//			
//		}
//		@Override
//		public ProductRateVO findByPrimaryKey(Integer productRateNo) {
//			ProductRateVO productRateVO = null;
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(GET_ONE_STMT);
//
//				pstmt.setInt(2, productRateNo);
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// empVo 也稱為 Domain objects
//					productRateVO = new ProductRateVO();
//					productRateVO.setMemberNo(rs.getInt("Member_No"));
//					productRateVO.setProductNo(rs.getInt("Product_No"));
//					productRateVO.setRate(rs.getInt("Rate"));
//					productRateVO.setComment(rs.getString("Comment"));
//					
//				}
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return productRateVO;
//		}
		@Override
		public List<ProductRateVO> getAll() {
			List<ProductRateVO> list = new ArrayList<ProductRateVO>();
			ProductRateVO productRateVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					productRateVO = new ProductRateVO();
					productRateVO.setMemberNo(rs.getInt("Member_No"));
					productRateVO.setProductNo(rs.getInt("Product_No"));
					productRateVO.setRate(rs.getInt("Rate"));
					productRateVO.setComment(rs.getString("Comment"));
					
					list.add(productRateVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
		
		public static void main(String[] args) {
			
			ProductRateJDBCDAO dao = new ProductRateJDBCDAO();
			
			List<ProductRateVO> list = dao.getAll();
			for (ProductRateVO aProductRate : list) {
				System.out.print(aProductRate.getMemberNo() + ",");
				System.out.print(aProductRate.getProductNo() + ",");
				System.out.print(aProductRate.getRate() + ",");
				System.out.print(aProductRate.getComment() + ",");
				
				System.out.println();
				}
			
			
		}
		@Override
		public ProductRateVO findByPrimaryNo(Integer productNo) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ProductRateVO findByPrimaryRate(Integer rate) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void delete(Integer memberNo, Integer productNo) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public ProductRateVO findByPrimaryKey(Integer memberNo) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Integer getByRate(Integer rate) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Integer getByRateCount(Integer productNo) {
			// TODO Auto-generated method stub
			return null;
		}


}
