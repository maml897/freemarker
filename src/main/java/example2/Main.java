package example2;

import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

public class Main
{
	static MyFreemarkerManager manager;
	protected static ObjectWrapper wrapper;
	protected static Configuration configuration;
	public static void main(String[] args) throws Exception
	{
		manager =new MyFreemarkerManager();
		configuration= manager.getConfiguration();
		wrapper = getObjectWrapper();
		
		Template template = configuration.getTemplate("index.ftl");
        TemplateModel model = createModel();
		
		Writer out =new OutputStreamWriter(System.out);
		template.process(model, out);
	}
	
	protected static TemplateModel createModel() {
        return manager.buildTemplateModel(wrapper);
    }
	protected static ObjectWrapper getObjectWrapper()
	{
		return configuration.getObjectWrapper();
	}
}
