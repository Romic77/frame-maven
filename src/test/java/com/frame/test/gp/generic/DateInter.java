package com.frame.test.gp.generic;

import java.util.Date;

class DateInter extends Pair<Date> {
	public DateInter(){

	}

	public DateInter(Date value) {
		super(value);
	}

	@Override
    public void setValue(Date value) {  
        super.setValue(value);  
    }  
    @Override  
    public Date getValue() {  
        return super.getValue();  
    }

	public static void main(String[] args) {
		DateInter dateInter=new DateInter();
		dateInter.setValue(new Date());
		System.out.println(dateInter.getValue());
		//dateInter.setValue(new Object());//编译错误
	}
}  