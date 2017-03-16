package com.frame.exception;

public class TipRuntimeException extends RuntimeException {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6921989601227621001L;

	private VP vp;

	public TipRuntimeException() {
	}

	public TipRuntimeException(String message) {
		super(message);
	}

	public TipRuntimeException(VP vp) {
		this.vp = vp;
	}
	
	public VP getVp() {
		return vp;
	}

	public void setVp(VP vp) {
		this.vp = vp;
	}
}
