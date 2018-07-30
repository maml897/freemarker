package pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		File file = new File("E:\\NewFile.html");// 定义一个file对象，用来初始化FileReader
		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
		BufferedReader bReader = new BufferedReader(reader);// new一个BufferedReader对象，将文件内容读取到缓存
		StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
		String s = "";
		while ((s = bReader.readLine()) != null)
		{
			sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
		}
		bReader.close();
		String str = sb.toString();
		System.out.println(str);
		
		
		htmlCodeComeString(str, "E://a.pdf");
	}

	public static void htmlCodeComeString(String htmlCode, String pdfPath)
	{
		Document doc = new Document(PageSize.A4);
		try
		{
			PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
			doc.open();
			// 解决中文问题
			BaseFont bfChinese = BaseFont.createFont("simfang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
			Paragraph t = new Paragraph("okok", FontChinese);
			doc.add(t);

			Image image = Image.getInstance("E:\\okok\\a.jpg");
			image.scaleAbsoluteWidth(120);
			image.scaleAbsoluteHeight(110);
			image.setAbsolutePosition(100, 500);


			doc.add(image);
			doc.close();
			System.out.println("文档创建成功");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
