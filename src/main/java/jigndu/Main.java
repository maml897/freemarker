package jigndu;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 通过class类路径来加载模板的实例
 * @author maml
 *
 */
public class Main
{
	public static void main(String[] args) throws IOException, TemplateException
	{
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setClassForTemplateLoading(new Main().getClass(), "/jigndu");
		Template template = configuration.getTemplate("index.ftl");

		Map<Object,Object> root =new HashMap<Object, Object>();
		root.put("float", 172.525f);
		Writer out =new OutputStreamWriter(System.out);
		template.process(root, out);

		float f = 128.525f;
		double d = new BigDecimal(String.valueOf(f)).doubleValue();
		System.out.println(d);
	}
}
