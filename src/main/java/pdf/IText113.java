package pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
public class IText113
{
	private static final String DEST = "E://HelloWorld_CN_HTML.pdf";
	public static void main(String[] args) throws DocumentException, IOException
	{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// PdfWriter pdf = PdfWriter.getInstance(document, baos);

		PdfWriter pdf = PdfWriter.getInstance(document, new FileOutputStream(DEST));
		// 文档加密
		pdf.setEncryption(null, "123456".getBytes(), PdfWriter.AllowPrinting, PdfWriter.STRENGTH40BITS);
		document.open();
		BaseFont bfChinese = BaseFont.createFont("simfang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bfChinese, 12, Font.NORMAL);
		document.add(new Paragraph("<span>html</span>", font));
		document.close();
	}
}
