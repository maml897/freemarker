package utils;

import java.util.Arrays;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FreeMarkerUtils
{
	// FreeMarkerUtils.get(x -> StatisticsUtils.getSegment(Float.parseFloat(x.get(0).toString()), Float.parseFloat(x.get(1).toString())))
	public static TemplateMethodModelEx get(TemplateMethodModelEx t)
	{
		return t;
	}

	public static void main(String[] args) throws TemplateModelException
	{
		TemplateMethodModelEx result = get(list -> {
			int total = 0;
			for (Object o : list)
			{
				total = total + Integer.parseInt(o.toString());
			}
			return total;
		});

		Object result1 = result.exec(Arrays.asList(1, 2, 5));
		System.out.println(result1);
	}
}
