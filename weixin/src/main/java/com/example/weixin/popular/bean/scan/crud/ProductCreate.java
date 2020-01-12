package com.example.weixin.popular.bean.scan.crud;


import com.example.weixin.popular.bean.scan.base.ProductGet;
import com.example.weixin.popular.bean.scan.info.BrandInfo;

public class ProductCreate extends ProductGet {
    private BrandInfo brand_info;

    public BrandInfo getBrand_info() {
        return brand_info;
    }

    public void setBrand_info(BrandInfo brand_info) {
        this.brand_info = brand_info;
    }
}
