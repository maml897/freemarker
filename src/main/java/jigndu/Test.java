package jigndu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test {
	public static void main(String[] args) {
		java.text.DecimalFormat d=new DecimalFormat("0.##");
		d.setRoundingMode(RoundingMode.HALF_UP);
		
		System.out.println(d.format(128.525f));
		System.out.println(d.format(127.525f));
		System.out.println("======================================");
		System.out.println(d.format(new BigDecimal(128.525f)));
		System.out.println(d.format(new BigDecimal(127.525f)));
		System.out.println("======================================");

		// freemarker采用的就是这种方式的处理方式，但是只处理了${128.525?string("0.##")}
		// 没有处理${f?}
		System.out.println(d.format(new BigDecimal("127.525")));
		System.out.println(d.format(new BigDecimal("128.525")));

	}
}
