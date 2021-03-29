package com.favorite_product.model;

public class FavoriteProductVO implements java.io.Serializable {
	private Integer memberNo;
	private Integer favoriteProductNo;
	
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getFavoriteProductNo() {
		return favoriteProductNo;
	}
	public void setFavoriteProductNo(Integer favoriteProductNo) {
		this.favoriteProductNo = favoriteProductNo;
	}
	

}
