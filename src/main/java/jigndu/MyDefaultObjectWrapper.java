package jigndu;

import java.math.BigDecimal;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class MyDefaultObjectWrapper extends DefaultObjectWrapper
{
	@Override
	public TemplateModel wrap(Object obj) throws TemplateModelException
	{

		if (obj instanceof Number)
		{
			System.out.println("obj:" + obj);
			if (obj instanceof Float)
			{
				System.out.println("------");
				return new SimpleNumber(new BigDecimal(String.valueOf(obj)));
			}
			return new SimpleNumber((Number) obj);
		}
		return super.wrap(obj);
	}
}
