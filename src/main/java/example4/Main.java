package example4;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

public class Main
{
	public static void main(String[] args)throws Exception
	{
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setClassForTemplateLoading(new Main().getClass(), "/example4");
		configuration.setNumberFormat("0.##");
		configuration.setTagSyntax(2);// 使用方括号代表tag

		Template template = configuration.getTemplate("index.ftl");

		Map<String,Object> map1 =new HashMap<String, Object>();
		ObjectWrapper wrapper= ObjectWrapper.DEFAULT_WRAPPER;
		TemplateModel model = wrapper.wrap(map1);

		Writer out =new OutputStreamWriter(System.out);
		template.process(model, out);
	}
}
