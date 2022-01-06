package com.turkai.consume.soapModels.ResponseModel.MERNISResponse;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.turkai.consume.DTO.Mernis.MERNISKisiFotografDTO;

public class KisiFotografResponse {
	
	private String hataDetay;
    private String hataKodu;
    private MERNISKisiFotografDTO mernisKisiFotografDTO;
    
    @JsonGetter("hataDetay")
	public String getHataDetay() {
		return hataDetay;
	}
	public void setHataDetay(String hataDetay) {
		this.hataDetay = hataDetay;
	}
	
	 @JsonGetter("hataKod")
	public String getHataKodu() {
		return hataKodu;
	}
	public void setHataKodu(String hataKodu) {
		this.hataKodu = hataKodu;
	}
	public MERNISKisiFotografDTO getMernisKisiFotografDTO() {
		return mernisKisiFotografDTO;
	}
	public void setMernisKisiFotografDTO(MERNISKisiFotografDTO mernisKisiFotografDTO) {
		this.mernisKisiFotografDTO = mernisKisiFotografDTO;
	}
    
    
    

}
