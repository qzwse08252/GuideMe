package com.favorite_product.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductVO;



public class FavoriteProductJDBCDAO implements FavoriteProductDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx";
	
	private static final String INSERT_STMT = 
			"INSERT INTO guideme.favorite_product (Member_No,Favorite_Product_No) VALUES (? ,?)";
		private static final String GET_ALL_STMT = 
			"SELECT Favorite_Product_No,Member_No FROM guideme.favorite_product  order by Favorite_Product_No";
		private static final String GET_ONE_STMT = 
			"SELECT Favorite_Product_No,Member_No FROM guideme.favorite_product where Favorite_Product_No = ?";
		private static final String DELETE = 
			"DELETE FROM guideme.favorite_product where Favorite_Product_No = ?";
		private static final String UPDATE = 
			"UPDATE guideme.favorite_product set Favorite_Product_No,Member_No, where Favorite_Product_No = ?";

	@Override
	public void insert(FavoriteProductVO favoriteProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, favoriteProductVO.getMemberNo());
			pstmt.setInt(2, favoriteProductVO.getFavoriteProductNo());
			
			

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
	public void update(FavoriteProductVO favoriteProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, favoriteProductVO.getMemberNo());
			pstmt.setInt(2, favoriteProductVO.getFavoriteProductNo());
			
			

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
	public FavoriteProductVO findByPrimaryKey(Integer favoriteProductNo) {
		FavoriteProductVO favoriteProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(2, favoriteProductNo);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				
				favoriteProductVO = new FavoriteProductVO();
				favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
				favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
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
		
		return favoriteProductVO;
	}

	@Override
	public List<FavoriteProductVO> getAll() {
		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteProductVO = null;

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
				favoriteProductVO = new FavoriteProductVO();
				favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
				favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
				
				list.add(favoriteProductVO); // Store the row in the list
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
		FavoriteProductJDBCDAO dao = new FavoriteProductJDBCDAO();
		List<FavoriteProductVO> list = dao.getAll();
		for (FavoriteProductVO aFavoriteProduct : list) {
			System.out.print(aFavoriteProduct.getMemberNo() + ",");
			System.out.print(aFavoriteProduct.getFavoriteProductNo() + ",");
			
			System.out.println();
		}
	}

	@Override
	public void delete(Integer memberNo, Integer favoriteProductNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FavoriteProductVO findByPrimaryKey1(Integer productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<FavoriteProductVO> findByPrimaryKey2(Integer productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoriteProductVO> findByPrimaryKey3(Integer memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO putIntoFavorite(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

}
