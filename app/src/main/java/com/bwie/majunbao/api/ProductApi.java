package com.bwie.majunbao.api;

import com.bwie.majunbao.entity.CartEntity;
import com.bwie.majunbao.entity.ProductEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductApi {
    /*首页广告（轮播图+京东秒杀+最底部的为你推荐）*/
    @POST("home/getHome")
    Observable<ProductEntity> showProduct();
    /*查询购物车*/
    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<CartEntity> showCart(@Field("uid") String uid);
}