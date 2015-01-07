package com.scanchex.database;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TradeProperties implements Serializable {

	private int tradeId;
	private String symbol;
	private float buyPrice;
	private String buyDateTime;
	private int quantity;
	private String buyComment;
	private String saleComment;
	private float salePrice;
	private String sellDateTime;
	private float rating;
	private String buyImage;
	private String saleImage;
	private boolean isActiveTrade;
	private boolean selected;
	private double totalAmount;
	private float stop;

	private float winOrLoss;
	private float percentage;
	private long tradeDays;

	public TradeProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TradeProperties(int tradeId, String symbol, float buyPrice,
			String buyImage, String buyComment, String buyDateTime,
			int quantity, float salePrice, String saleImage,
			String saleComment, String sellDateTime, float rating,
			boolean isActiveTrade, double totalAmount) {
		super();

		this.tradeId = tradeId;
		this.symbol = symbol;
		this.buyPrice = buyPrice;
		this.buyImage = buyImage;
		this.buyComment = buyComment;
		this.buyDateTime = buyDateTime;
		this.quantity = quantity;
		this.salePrice = salePrice;
		this.saleImage = saleImage;
		this.saleComment = saleComment;
		this.sellDateTime = sellDateTime;
		this.rating = rating;
		this.isActiveTrade = isActiveTrade;
		this.totalAmount = totalAmount;

	}

	public TradeProperties(String symbol, float buyPrice, String buyImage,
			String buyComment, String buyDateTime, int quantity,
			float salePrice, String saleImage, String saleComment,
			String sellDateTime, float rating, boolean isActiveTrade,
			double totalAmount, float stop) {
		super();

		this.symbol = symbol;
		this.buyPrice = buyPrice;
		this.buyImage = buyImage;
		this.buyComment = buyComment;
		this.buyDateTime = buyDateTime;
		this.quantity = quantity;
		this.salePrice = salePrice;
		this.saleImage = saleImage;
		this.saleComment = saleComment;
		this.sellDateTime = sellDateTime;
		this.rating = rating;
		this.isActiveTrade = isActiveTrade;
		this.totalAmount = totalAmount;
		this.stop = stop;

	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getBuyDateTime() {
		return buyDateTime;
	}

	public void setBuyDateTime(String buyDateTime) {
		this.buyDateTime = buyDateTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBuyComment() {
		return buyComment;
	}

	public void setBuyComment(String buyComment) {
		this.buyComment = buyComment;
	}

	public String getSaleComment() {
		return saleComment;
	}

	public void setSaleComment(String saleComment) {
		this.saleComment = saleComment;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public String getSellDateTime() {
		return sellDateTime;
	}

	public void setSellDateTime(String sellDateTime) {
		this.sellDateTime = sellDateTime;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getBuyImage() {
		return buyImage;
	}

	public void setBuyImage(String buyImage) {
		this.buyImage = buyImage;
	}

	public String getSaleImage() {
		return saleImage;
	}

	public void setSaleImage(String saleImage) {
		this.saleImage = saleImage;
	}

	public boolean isActiveTrade() {
		return isActiveTrade;
	}

	public void setIsActiveTrade(boolean isActiveTrade) {
		this.isActiveTrade = isActiveTrade;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setActiveTrade(boolean isActiveTrade) {
		this.isActiveTrade = isActiveTrade;
	}

	public float getWinOrLoss() {
		return winOrLoss;
	}

	public void setWinOrLoss(float winOrLoss) {
		this.winOrLoss = winOrLoss;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public long getTradeDays() {
		return tradeDays;
	}

	public void setTradeDays(long tradeDays) {
		this.tradeDays = tradeDays;
	}

	public float getStop() {
		return stop;
	}

	public void setStop(float stop) {
		this.stop = stop;
	}

}
