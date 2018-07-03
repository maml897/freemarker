package example2;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class MyScopesHashModel extends SimpleHash implements TemplateModel
{

	public MyScopesHashModel(ObjectWrapper objectWrapper)
	{
		super(objectWrapper);
	}

	public TemplateModel get(String key) throws TemplateModelException
	{
		// 现在model中找，然后再在值栈中找等等

		Map<String, String> map = new HashMap<String, String>();
		map.put("hello", "world");
		return wrap(map);
		// 如果最后 为null的话 则由 全局变量查找
	}

	
}
