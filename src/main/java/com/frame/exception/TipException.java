package com.frame.exception;

public class TipException extends Exception{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5910509866530523106L;
	private VP vp;
	public VP getVp() {
		return vp;
	}
	public void setVp(VP vp) {
		this.vp = vp;
	}
	
	public TipException(){super();}
	
	public TipException(String message){super(message);}
	
	public TipException(VP vp){this.vp=vp;}
}
