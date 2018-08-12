package jigndu;

import java.math.BigDecimal;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

public class MyBeanWraper extends BeansWrapper
{
	protected ModelFactory getModelFactory(Class clazz)
	{
		if (Float.class.isAssignableFrom(clazz) || Double.class.isAssignableFrom(clazz))
		{
			return BigDecimalModel.FACTORY;
		}

		return super.getModelFactory(clazz);
	}

	private final static class BigDecimalModel extends BeanModel implements TemplateNumberModel
	{
		static final ModelFactory FACTORY = new ModelFactory()
		{
			public TemplateModel create(Object object, ObjectWrapper wrapper)
			{
				return new BigDecimalModel(object, (BeansWrapper) wrapper);
			}
		};

		public BigDecimalModel(Object bg, BeansWrapper wrapper)
		{
			super(bg, wrapper);
		}

		@Override
		public Number getAsNumber() throws TemplateModelException
		{
			return new BigDecimal(String.valueOf(object));
		}
	}
}
