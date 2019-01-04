package com.gift.futurestrading.member.vo;

public class ConsumerSignDetailVo {
	private String signPk;						/* 체결번호(PK) */
	private String fkOrderBuySign;				/* 매수주문번호 */
	private String fkSellerSign;				/* 판매자아이디 */
	private String signSellerName;				/* 판매자이름 */
	private int signPerPrice;					/* 체결가격(1계약당) */
	private int signAmount;						/* 체결수량 */
	private int signTotalPrice;					/* 전체체결가 */
	private int signInitialMargin;				/* 체결증거금 */
	private String signDate;					/* 체결날짜 */
	private String signFuturesTradeBeginDate;	/* 선물거래기간(시작일) */
	private String signFuturesTradeEndDate;		/* 선물거래기간(만기일) */
	
	public String getSignPk() {
		return signPk;
	}
	public void setSignPk(String signPk) {
		this.signPk = signPk;
		System.out.println(signPk + "<----- setSignPk");
	}
	public String getFkOrderBuySign() {
		return fkOrderBuySign;
	}
	public void setFkOrderBuySign(String fkOrderBuySign) {
		this.fkOrderBuySign = fkOrderBuySign;
		System.out.println(fkOrderBuySign + "<----- setFkOrderBuySign");
	}
	public String getFkSellerSign() {
		return fkSellerSign;
	}
	public void setFkSellerSign(String fkSellerSign) {
		this.fkSellerSign = fkSellerSign;
		System.out.println(fkSellerSign + "<----- setFkSellerSign");
	}
	public String getSignSellerName() {
		return signSellerName;
	}
	public void setSignSellerName(String signSellerName) {
		this.signSellerName = signSellerName;
		System.out.println(signSellerName + "<----- setSignSellerName");
	}
	public int getSignPerPrice() {
		return signPerPrice;
	}
	public void setSignPerPrice(int signPerPrice) {
		this.signPerPrice = signPerPrice;
		System.out.println(signPerPrice + "<----- setSignPerPrice");
	}
	public int getSignAmount() {
		return signAmount;
	}
	public void setSignAmount(int signAmount) {
		this.signAmount = signAmount;
		System.out.println(signAmount + "<----- setSignAmount");
	}
	public int getSignTotalPrice() {
		return signTotalPrice;
	}
	public void setSignTotalPrice(int signTotalPrice) {
		this.signTotalPrice = signTotalPrice;
		System.out.println(signTotalPrice + "<----- setSignTotalPrice");
	}
	public int getSignInitialMargin() {
		return signInitialMargin;
	}
	public void setSignInitialMargin(int signInitialMargin) {
		this.signInitialMargin = signInitialMargin;
		System.out.println(signInitialMargin + "<----- setSignInitialMargin");
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
		System.out.println(signDate + "<----- setSignDate");
	}
	public String getSignFuturesTradeBeginDate() {
		return signFuturesTradeBeginDate;
	}
	public void setSignFuturesTradeBeginDate(String signFuturesTradeBeginDate) {
		this.signFuturesTradeBeginDate = signFuturesTradeBeginDate;
		System.out.println(signFuturesTradeBeginDate + "<----- setSignFuturesTradeBeginDate");
	}
	public String getSignFuturesTradeEndDate() {
		return signFuturesTradeEndDate;
	}
	public void setSignFuturesTradeEndDate(String signFuturesTradeEndDate) {
		this.signFuturesTradeEndDate = signFuturesTradeEndDate;
		System.out.println(signFuturesTradeEndDate + "<----- setSignFuturesTradeEndDate");
	}
	@Override
	public String toString() {
		return "ConsumerSignDetailVo [signPk=" + signPk + ", fkOrderBuySign=" + fkOrderBuySign + ", fkSellerSign="
				+ fkSellerSign + ", signSellerName=" + signSellerName + ", signPerPrice=" + signPerPrice
				+ ", signAmount=" + signAmount + ", signTotalPrice=" + signTotalPrice + ", signInitialMargin="
				+ signInitialMargin + ", signDate=" + signDate + ", signFuturesTradeBeginDate="
				+ signFuturesTradeBeginDate + ", signFuturesTradeEndDate=" + signFuturesTradeEndDate + "]";
	}
}
