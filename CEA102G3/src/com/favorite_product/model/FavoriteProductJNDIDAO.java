package com.favorite_product.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class FavoriteProductJNDIDAO implements FavoriteProductDAO_interface{
	
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
			"INSERT INTO guideme.favorite_product (Member_No,Favorite_Product_No) VALUES (? ,?)";
		private static final String GET_ALL_STMT = 
			"SELECT Favorite_Product_No,Member_No FROM guideme.favorite_product  order by Favorite_Product_No";
		private static final String GET_ONE_STMT = 
			"SELECT Favorite_Product_No,Member_No FROM guideme.favorite_product where Member_No =?";
		private static final String GET_ONE_Product = 
			"SELECT Member_No,Favorite_Product_No FROM guideme.favorite_product where Favorite_Product_No =?";
		private static final String GET_ONE_Member = 
			"SELECT Member_No,Favorite_Product_No FROM guideme.favorite_product where Member_No =?";
		private static final String DELETE = 
			"DELETE FROM guideme.favorite_product where member_No=? and Favorite_Product_No = ?";
		private static final String UPDATE = 
			"UPDATE guideme.favorite_product set Favorite_Product_No=? where Member_No=? ";
		private static final String PUT_INTO_FAVORITE = 
				"UPDATE guideme.Product set Product_Name=?,list_Price=?,Descr=? where Product_No = ?";
	
	
	
	
		@Override
		public void insert(FavoriteProductVO favoriteProductVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, favoriteProductVO.getMemberNo());
				pstmt.setInt(2, favoriteProductVO.getFavoriteProductNo());
				
				

				pstmt.executeUpdate();

				
			
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

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(2, favoriteProductVO.getMemberNo());
				pstmt.setInt(1, favoriteProductVO.getFavoriteProductNo());
				
				

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
		public void delete(Integer memberNo,Integer favoriteProductNo) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1,memberNo);
				pstmt.setInt(2, favoriteProductNo);

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
		public FavoriteProductVO findByPrimaryKey(Integer memberNo) {
			FavoriteProductVO favoriteProductVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, memberNo);
				

				rs = pstmt.executeQuery();
				

				while (rs.next()) {
					
					favoriteProductVO = new FavoriteProductVO();
					favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
					favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
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

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					favoriteProductVO = new FavoriteProductVO();
					favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
					favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
					
					list.add(favoriteProductVO); // Store the row in the list
				}

			
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

		@Override
		public FavoriteProductVO findByPrimaryKey1(Integer favoriteProductNo) {
			FavoriteProductVO favoriteProductVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_Product);

				pstmt.setInt(1, favoriteProductNo);
				

				rs = pstmt.executeQuery();
				

				while (rs.next()) {
					System.out.println();
					favoriteProductVO = new FavoriteProductVO();
					favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
					favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
					}
				System.out.println(favoriteProductVO.getMemberNo());
				
			
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
		public List<FavoriteProductVO> findByPrimaryKey2(Integer favoriteProductNo) {
			List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
			FavoriteProductVO favoriteProductVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ONE_Product);
				pstmt.setInt(1, favoriteProductNo);

				rs = pstmt.executeQuery();
				

				while (rs.next()) {
					
					favoriteProductVO = new FavoriteProductVO();
					favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
					favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
					
					list.add(favoriteProductVO); // Store the row in the list
				}

			
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

		@Override
		public List<FavoriteProductVO> findByPrimaryKey3(Integer memberNo) {
			List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
			FavoriteProductVO favoriteProductVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ONE_Member);
				pstmt.setInt(1, memberNo);

				rs = pstmt.executeQuery();
				

				while (rs.next()) {
					
					favoriteProductVO = new FavoriteProductVO();
					favoriteProductVO.setMemberNo(rs.getInt("Member_No"));
					favoriteProductVO.setFavoriteProductNo(rs.getInt("Favorite_Product_No"));
					
					list.add(favoriteProductVO); // Store the row in the list
				}

			
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

		@Override
		public ProductVO putIntoFavorite(String productName) {
			
			return null;
		}

}
