package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class JavaToPdfHtmlFreeMarker
{
	private static final String DEST = "target/HelloWorld_CN_HTML_FREEMARKER.pdf";

	private static final String HTML = "template_freemarker.html";

	private static final String FONT = "simhei.ttf";

	private static Configuration freemarkerCfg = null;

	static
	{
		freemarkerCfg = new Configuration();
		// freemarker的模板目录
		try
		{
			freemarkerCfg.setDirectoryForTemplateLoading(new File("d://"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, DocumentException
	{
		Map<String, Object> data = new HashMap();
		data.put("name", "鲁家宁");
		String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(data, HTML);
		JavaToPdfHtmlFreeMarker.createPdf(content, DEST);
	}

	public static void createPdf(String content, String dest) throws IOException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(DEST));
		document.open();
		BaseFont bfChinese = BaseFont.createFont(BaseFont.COURIER, "utf8", BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
		Paragraph t = new Paragraph(content, FontChinese);
		document.add(t);
		document.close();

	}

	/**
	 * freemarker渲染html
	 */
	public static String freeMarkerRender(Map<String, Object> data, String htmlTmp)
	{
		Writer out = new StringWriter();
		try
		{
			// 获取模板,并设置编码方式
			Template template = freemarkerCfg.getTemplate(htmlTmp);
			template.setEncoding("UTF-8");
			// 合并数据模型与模板
			template.process(data, out); // 将合并后的数据和模板写入到流中，这里使用的字符流
			out.flush();
			return out.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				out.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return null;
	}
}
