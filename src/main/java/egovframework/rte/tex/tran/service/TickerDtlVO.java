package egovframework.rte.tex.tran.service;

import egovframework.rte.tex.com.service.SearchVO;

public class TickerDtlVO extends SearchVO {
	Long last = null;
	Long bid = null;
	Long ask = null;
	Long low = null;
	Long high = null;
	Float volume = null;
	Long change = null;
	Float changePercent = null;	
	
	public Long getChange() {
		return change;
	}

	public void setChange(Long change) {
		this.change = change;
	}

	public Float getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(Float changePercent) {
		this.changePercent = changePercent;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public Long getAsk() {
		return ask;
	}

	public void setAsk(Long ask) {
		this.ask = ask;
	}

	public Long getLow() {
		return low;
	}

	public void setLow(Long low) {
		this.low = low;
	}

	public Long getHigh() {
		return high;
	}

	public void setHigh(Long high) {
		this.high = high;
	}

	public Float getVolume() {
		return volume;
	}

	public void setVolume(Float volume) {
		this.volume = volume;
	}

	public Long getLast() {
		return last;
	}

	public void setLast(Long last) {
		this.last = last;
	}

}
