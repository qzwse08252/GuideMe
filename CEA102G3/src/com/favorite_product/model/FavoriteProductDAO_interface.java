package com.favorite_product.model;

import java.util.List;

import com.product.model.ProductVO;

public interface FavoriteProductDAO_interface {
	public void insert(FavoriteProductVO favoriteProductVO);
    public void update(FavoriteProductVO favoriteProductVO);
    public void delete(Integer memberNo,Integer favoriteProductNo);
    public FavoriteProductVO findByPrimaryKey(Integer memberNo);
    public FavoriteProductVO findByPrimaryKey1(Integer productNo);
	public ProductVO putIntoFavorite(String productName);

    public List<FavoriteProductVO> findByPrimaryKey2(Integer favoriteProductNo);
    public List<FavoriteProductVO> findByPrimaryKey3(Integer memberNo);
    public List<FavoriteProductVO> getAll();
  

}
