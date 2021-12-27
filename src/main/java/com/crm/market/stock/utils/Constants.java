package com.crm.market.stock.utils;

public class Constants {

    public static final String API_ROOT = "crm/stock/v2";
    public static final String API_ARTICLE = API_ROOT + "/article";
    public static final String API_CATEGORY = API_ROOT + "/category";
    public static final String API_CLIENT = API_ROOT + "/client";
    public static final String API_USER = API_ROOT + "/user";
    public static final String API_PROVIDER = API_ROOT + "/provider";
    public static final String API_BUSINESS = API_ROOT + "/business";
    public static final String API_ORDER_CLIENT = API_ROOT + "/client/order";
    public static final String API_ORDER_PROVIDER = API_ROOT + "/provider/order";
    public static final String API_SALES = API_ROOT + "/sales";

    public static final String STOCK_SECRET_KEY = "crm-stock-market-be4f";
    public static final int STOCK_EXPIRATION_TOKEN = 1000 * 60 * 60 * 10;
    public static final String STOCK_AUTHORIZATION = "Authorization";
    public static final String STOCK_HEADER = "Bearer ";
}
