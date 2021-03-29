package com.product_rate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductRateJNDIDAO implements ProductRateDAO_interface{
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO guideme.Product_Rate (Member_No,Product_No,Rate,Comment) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT Member_No,Product_No,Rate,Comment FROM guideme.Product_Rate  order by Member_No";
		private static final String GET_ONE_STMT = 
			"SELECT Member_No,Product_No,Rate,Comment FROM guideme.Product_Rate where Member_No =?";
		private static final String DELETE = 
			"DELETE FROM guideme.Product_Rate where Member_No = ?&& product_No=?";
		private static final String UPDATE = 
			"UPDATE guideme.product_rate set Rate=?, Comment=? where member_No=? && product_No=?";
		private static final String GET_ONE_PRODUCTNO =
			"select Member_No,Product_No,Rate,Comment from guideme.product_rate where product_no=?";
		private static final String GET_ONE_RATE =
			"select Member_No,Product_No,Rate,Comment from guideme.product_rate where rate =?";
		private static final String GET_THE_AVGRATE =
			"SELECT sum(rate)/count(rate) FROM guideme.product_rate where product_no = ?" ;
		private static final String GET_THE_COUNTRATE =
			"SELECT count(rate) FROM guideme.product_rate where product_no = ?";
	
	
	
	
	
	
	
	@Override
	public void insert(ProductRateVO productRateVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productRateVO.getMemberNo());
			pstmt.setInt(2, productRateVO.getProductNo());
			pstmt.setInt(3, productRateVO.getRate());
			pstmt.setString(4, productRateVO.getComment());
			
			

			pstmt.executeUpdate();

		
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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			

			pstmt.setInt(3, productRateVO.getMemberNo());
			pstmt.setInt(4, productRateVO.getProductNo());
			pstmt.setInt(1, productRateVO.getRate());
			pstmt.setString(2, productRateVO.getComment());
			
			
			
			

			pstmt.executeUpdate();

			
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
	public void delete(Integer memberNo,Integer productNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, productNo);
			

			pstmt.executeUpdate();

			
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
	public ProductRateVO findByPrimaryKey(Integer memberNo) {
		
		ProductRateVO productRateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productRateVO = new ProductRateVO();
				productRateVO.setMemberNo(rs.getInt("Member_No"));
				productRateVO.setProductNo(rs.getInt("Product_No"));
				productRateVO.setRate(rs.getInt("Rate"));
				productRateVO.setComment(rs.getString("Comment"));
				
			}

			
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
		
		return productRateVO;
	}

	@Override
	public List<ProductRateVO> getAll() {
		
		List<ProductRateVO> list = new ArrayList<ProductRateVO>();
		ProductRateVO productRateVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productRateVO = new ProductRateVO();
				productRateVO.setMemberNo(rs.getInt("Member_No"));
				productRateVO.setProductNo(rs.getInt("Product_No"));
				productRateVO.setRate(rs.getInt("Rate"));
				productRateVO.setComment(rs.getString("Comment"));
				
				list.add(productRateVO); // Store the row in the list
			}

	
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

	@Override
	public ProductRateVO findByPrimaryNo(Integer productNo) {
		ProductRateVO productRateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PRODUCTNO);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productRateVO = new ProductRateVO();
				productRateVO.setMemberNo(rs.getInt("Member_No"));
				productRateVO.setProductNo(rs.getInt("Product_No"));
				productRateVO.setRate(rs.getInt("Rate"));
				productRateVO.setComment(rs.getString("Comment"));
				
			}

			
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
		
		return productRateVO;
	}

	@Override
	public ProductRateVO findByPrimaryRate(Integer rate) {
		ProductRateVO productRateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_RATE);

			pstmt.setInt(1, rate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productRateVO = new ProductRateVO();
				productRateVO.setMemberNo(rs.getInt("Member_No"));
				productRateVO.setProductNo(rs.getInt("Product_No"));
				productRateVO.setRate(rs.getInt("Rate"));
				productRateVO.setComment(rs.getString("Comment"));
				
			}

			
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
		
		return productRateVO;
	}

	@Override
	public Integer getByRate(Integer productNo) {
		ProductRateVO productRateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRate = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_THE_AVGRATE);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				totalRate = (rs.getInt(1));
				
				
			}

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
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
		return totalRate;
	}

	@Override
	public Integer getByRateCount(Integer productNo) {
		ProductRateVO productRateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRateCount = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_THE_COUNTRATE);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productRateVO = new ProductRateVO();  //因為只是要將值傳到product的total_rate表格所以不須將其他欄位值放入
				
				totalRateCount = (rs.getInt(1));
				
				
			}

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
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
		return totalRateCount;
	}

		
	

		
	
	

}
