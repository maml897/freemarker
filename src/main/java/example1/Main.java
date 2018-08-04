package example1;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
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
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(new Main().getClass(), "/example1");
		configuration.setSharedVariable("share", "全局共享");
		configuration.setNumberFormat("0.####");
		
		Template template = configuration.getTemplate("index.ftl");
		Map<Object,Object> root =new HashMap<Object, Object>();
		root.put("name", "china");
		root.put("user", new User());
		root.put("A strange name!", "一个特殊的名字");//改名字 有空格等特殊符号，于是这样取得${.vars['A strange name!']}
		root.put("list", Arrays.asList(1.256, 12.87455, 1.18, 1.257));
		
		Writer out =new OutputStreamWriter(System.out);
		template.process(root, out);
		
		
		
	}
}
