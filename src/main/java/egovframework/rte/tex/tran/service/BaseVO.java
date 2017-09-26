package egovframework.rte.tex.tran.service;

import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.util.Utils;

public class BaseVO  extends SearchVO{
	String timestamp = "";
	String dateTime = "";

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	String currencyPair = "";

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDateTime() {
		return Utils.timeStamp2Date(this.timestamp);
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
