package method;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setClassForTemplateLoading(new Main().getClass(), "/method");
		
		Template template = configuration.getTemplate("index.ftl");
		Map<Object,Object> root =new HashMap<Object, Object>();
		
		root.put("list",FreeMarkerUtils.method((a)->a.size()));
		Writer out =new OutputStreamWriter(System.out);
		template.process(root, out);
	}
}
