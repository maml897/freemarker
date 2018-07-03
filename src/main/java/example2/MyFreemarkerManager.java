package example2;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;

public class MyFreemarkerManager
{
	protected Configuration config;
	
	public MyScopesHashModel buildTemplateModel(ObjectWrapper wrapper)
	{
		MyScopesHashModel model =new MyScopesHashModel(wrapper);
		model.put("a", "aaa");
		return model;
	}
	
	
	 public synchronized Configuration getConfiguration() throws TemplateException {
	      
	       init();
	       return config;
	   }

	 private void init() throws TemplateException
	 {
		 config = createConfiguration();
		 config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		 config.setClassForTemplateLoading(new Main().getClass(), "/example2");
		 config.setObjectWrapper(createObjectWrapper());
		 
		 loadSettings();
	 }
	 
	 private ObjectWrapper createObjectWrapper()
	 {
		 MyBeanWrapper wrapper = new MyBeanWrapper(false);
		 return wrapper;
	 }
	
	 public Configuration createConfiguration() throws TemplateException {
		 Configuration configuration = new Configuration();
		 configuration.setWhitespaceStripping(true);
		 
		 
		 return configuration;
	 }
	 
	 //可以用子类重写该方法，实现自己的 全局变量等 内容
	 private void loadSettings() throws TemplateModelException
	 {
		 //读取freemarker配置文件 ，设置全局变量等等
		 config.setSharedVariable("quanju", "我是全局变量哈哈");
	 }
}
