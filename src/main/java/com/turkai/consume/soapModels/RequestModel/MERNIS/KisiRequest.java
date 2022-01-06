package com.turkai.consume.soapModels.RequestModel.MERNIS;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;

public class KisiRequest {

	private String tcNo;
	private KullaniciBilgi kullaniciBilgi;

	@JsonGetter("tc")
	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}

	@JsonGetter("auth")
	public KullaniciBilgi getKullaniciBilgi() {
		return kullaniciBilgi;
	}

	public void setKullaniciBilgi(KullaniciBilgi kullaniciBilgi) {
		this.kullaniciBilgi = kullaniciBilgi;
	}

}
