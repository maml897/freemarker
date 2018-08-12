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
		if (Float.class.isAssignableFrom(clazz))
		{
			return FloatModel.FACTORY;
		}

		return super.getModelFactory(clazz);
	}

	private final static class FloatModel extends BeanModel implements TemplateNumberModel
	{
		static final ModelFactory FACTORY = new ModelFactory()
		{
			public TemplateModel create(Object object, ObjectWrapper wrapper)
			{
				return new FloatModel(object, (BeansWrapper) wrapper);
			}
		};

		public FloatModel(Object bg, BeansWrapper wrapper)
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
