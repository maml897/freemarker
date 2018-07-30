package pdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class JavaToPdfHtml
{
	private static final String DEST = "E://HelloWorld_CN_HTML.pdf";

	private static final String HTML = "e://okok/template.html";


	public static void main(String[] args) throws IOException, DocumentException
	{
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
		document.open();

		XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML), null, Charset.forName("UTF-8"), fontImp);
		document.close();

		org.bouncycastle.asn1.ASN1Primitive b;
	}
}
