package example3;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import example1.User;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

public class Main
{
	public static void main(String[] args)throws Exception
	{
		Configuration configuration =new Configuration();
		configuration.setClassForTemplateLoading(new Main().getClass(), "/example3");
		Template template = configuration.getTemplate("index.ftl");
		configuration.setNumberFormat("0.##");
		configuration.setTagSyntax(0);
		

		Map<String,Object> map1 =new HashMap<String, Object>();
		map1.put("bb", new User());
		
		ObjectWrapper wrapper= ObjectWrapper.DEFAULT_WRAPPER;
		
		
		TemplateModel model = wrapper.wrap(map1);
		
		Writer out =new OutputStreamWriter(System.out);
		template.process(model, out);
	}
}
