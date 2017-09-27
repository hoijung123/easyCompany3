package egovframework.rte.tex.util;

import egovframework.rte.tex.tran.service.AccessTokenVO;

public class Constants {

	public final static String currency = "krw";
	public final static String oauthPath = "https://api.korbit.co.kr/v1/oauth2/access_token";
	public final static String invoicePath = "https://api.korbit.co.kr/v1/user/balances";
	public final static String balancePath = "https://api.korbit.co.kr/v1/user/balances";
	public final static String ordersOpenPath = "https://api.korbit.co.kr/v1/user/orders/open";
	public final static String ordersBuyPath = "https://api.korbit.co.kr/v1/user/orders/buy";
	public final static String ordersCancelPath = "https://api.korbit.co.kr/v1/user/orders/cancel";
	public final static String ordersSellPath = "https://api.korbit.co.kr/v1/user/orders/sell";
	public final static String ordersPath = "https://api.korbit.co.kr/v1/user/orders";
	public final static String tickerPath = "https://api.korbit.co.kr/v1/ticker";
	public final static String tickerDtlPath = "https://api.korbit.co.kr/v1/ticker/detailed";
	public final static String transactionsPath = "https://api.korbit.co.kr/v1/user/transactions";
	
	// BTC, ETH, DASH, LTC, ETC, XRP, BCH
	public final static String CURRENCY_LTC = "LTC";
	public final static String CURRENCY_BTC = "BTC";
	public final static String ETH_KRW = "eth_krw";
	public final static String CURRENCY_DASH = "DASH";
	public final static String ETC_KRW = "etc_krw";
	public final static String XRP_KRW = "xrp_krw";
	public final static String CURRENCY_BCH = "BCH";
	public final static String CURRENCY_XMR = "XMR";	
	
	public final static String TRAN_TYPE_LIMIT = "limit";
	public final static String TRAN_TYPE_MARKET = "market";
	
	public static AccessTokenVO ACCESS_TOKEN_VO = null;
	
	public static String SUCCESS = "success";
}
