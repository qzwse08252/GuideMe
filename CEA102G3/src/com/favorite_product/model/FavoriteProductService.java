package com.favorite_product.model;
import java.util.List;

public class FavoriteProductService {
	
	private FavoriteProductDAO_interface dao;
	
	public FavoriteProductService() {
		dao = new FavoriteProductJNDIDAO();
	}
	
	public FavoriteProductVO addFavoriteProduct(Integer memberNo,Integer favoriteProductNo) {
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		
		favoriteProductVO.setMemberNo(memberNo);
		favoriteProductVO.setFavoriteProductNo(favoriteProductNo);
		dao.insert(favoriteProductVO);
		return favoriteProductVO;
	}

	public FavoriteProductVO updateFavoriteProduct(Integer memberNo,Integer favoriteProductNo) {
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		
		favoriteProductVO.setMemberNo(memberNo);
		favoriteProductVO.setFavoriteProductNo(favoriteProductNo);
		dao.update(favoriteProductVO);
		
		return favoriteProductVO;
	}
		public void deleteFavoriteProduct(Integer memberNo,Integer favoriteProductNo) {
			dao.delete( memberNo,favoriteProductNo);
		}
		
		public FavoriteProductVO getOneFavoriteProduct(Integer memberNo) {
			return dao.findByPrimaryKey(memberNo);
		}
		public FavoriteProductVO getOneFavoriteProductByproductNo(Integer favoriteProductNo) {
			return dao.findByPrimaryKey1(favoriteProductNo);
		}
		public List<FavoriteProductVO> getAll(){
			return dao.getAll();
			}
		public List<FavoriteProductVO>getOneFavoriteProductByproductNoAll (Integer favoriteProductNo){
			return dao.findByPrimaryKey2(favoriteProductNo);
			}
		public List<FavoriteProductVO>getOneFavoriteProductByMemberNoAll(Integer memberNo){
			return dao.findByPrimaryKey3(memberNo);
			}
		public List<FavoriteProductVO> getAllbyProduct(){
				return dao.getAll();
		
		
		
		
	}
}
