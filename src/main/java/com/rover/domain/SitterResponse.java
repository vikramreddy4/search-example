package com.rover.domain;

import java.util.List;

public class SitterResponse {

	private List<SitterRank> tx;
	
	public SitterResponse() {
		// TODO Auto-generated constructor stub
	}

	public SitterResponse(List<SitterRank> tx) {
		this.tx = tx;
	}

	/**
	 * @return the tx
	 */
	public List<SitterRank> getTx() {
		return tx;
	}

	/**
	 * @param tx the tx to set
	 */
	public void setTx(List<SitterRank> tx) {
		this.tx = tx;
	}


}
