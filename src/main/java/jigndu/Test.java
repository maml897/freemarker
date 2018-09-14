package jigndu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Test {
	public static void main(String[] args) {
		
		double d=162.535d;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2); //保留四位小数
		nf.setRoundingMode(RoundingMode.HALF_UP);//四舍五入
		String s2 = nf.format(d);
		System.out.println(s2);
		
		
		double c = 162.535;
	    String s=String.format("%.6f",c);
	    System.out.println("s:" + s);

	    
	    DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		String ss= decimalFormat.format(new BigDecimal(s));
		
		System.out.println(ss);
	}
}
