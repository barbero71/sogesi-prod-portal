package it.sogesispa.prod.web.reports;

//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

//import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
//import com.lowagie.text.Rectangle;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


import it.sogesispa.prod.web.dto.AttivitaDTO;

public class ReportElencoAttivita {
	static Font font = FontFactory.getFont(FontFactory.HELVETICA, 10f);
	static Font boldfont = FontFactory.getFont(FontFactory.HELVETICA, 10f, Font.BOLD);
	SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Creates a PDF with information about the movies
	 * 
	 * @param filename
	 *            the name of the PDF file that will be created.
	 * @throws DocumentException
	 * @throws IOException
	 */
	class MyHeader extends PdfPageEventHelper {
		String stabilimento = null;
		String operatore = null;
		String datatxt = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String headertxt = null;
		public MyHeader(String stabilimento, String operatore, String headertxt) {
			super();
			this.stabilimento = stabilimento;
			this.operatore = operatore;
			this.datatxt = sdf.format(new Date());
			this.headertxt = headertxt;
		}

		public void onStartPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase header = new Phrase(
					"Report del: " + datatxt + " - Operatore:" + operatore + " - Stabilimento: " + stabilimento +(headertxt!=null?(" - "+headertxt):""),
					font);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, header, document.leftMargin(), document.top() + 10, 0);

		}

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase footer = new Phrase(String.format("- %d -", writer.getPageNumber()), font);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer, (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
		}
	}

	public void createPdf(Collection<AttivitaDTO> lista, String stabilimento, String operatore, String header, OutputStream out)
			throws IOException, DocumentException {
		Document document = new Document(PageSize.A4.rotate(), 30, 30, 30, 30);

		PdfWriter writer = PdfWriter.getInstance(document, out);
		MyHeader event = new MyHeader(stabilimento, operatore, header);
		writer.setPageEvent(event);
		document.open();

		document.add(creaTabellaOggetti(lista));

		document.close();
	}

	private PdfPTable creaTabellaOggetti(Collection<AttivitaDTO> lista) throws DocumentException {

		PdfPTable table = new PdfPTable(8);
		table.addCell(borderCellHeader("Data prevista"));
		table.addCell(borderCellHeader("Area"));
		table.addCell(borderCellHeader("Macchina"));
		table.addCell(borderCellHeader("Impianto"));
		
		table.addCell(borderCellHeader("Priorit\u00e0"));
		table.addCell(borderCellHeader("Tipo pianificazione"));
		table.addCell(borderCellHeader("Intervento"));
		table.addCell(borderCellHeader("Eseguita"));
		table.setWidths(new float[] { 2, 1.5f, 3.5f, 2, 1.5f, 2, 7, 1.5f });

		for (AttivitaDTO a : lista) {
			table.addCell(borderCell(fmt.format(a.getDataPrevista())));
			table.addCell(borderCell(a.getLinea()));
			table.addCell(borderCellBold(a.getMacchina()));
			table.addCell(borderCell(a.getImpianto()));
			
			table.addCell(borderCell(a.getPriorita()));
			table.addCell(borderCell(a.getTipoPianificazione()));
			table.addCell(borderCellBold(a.getIntervento()));
			table.addCell(borderCell(a.getDataEsecuzione() != null ? fmt.format(a.getDataEsecuzione()) : " - "));
		}

		table.setWidthPercentage(100);

		return table;
	}

	/**
	 * Creates our first table
	 * 
	 * @return our first table
	 * @throws DocumentException
	 */
	public static PdfPTable creaIntestazione(String stabilimento) throws DocumentException {

		PdfPTable table = new PdfPTable(2);
		PdfPCell cell = new PdfPCell();
		cell.setBorder(0);
		cell.setPhrase(new Phrase("Stabilimento:", font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(0);
		cell.setPhrase(new Phrase(stabilimento, font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		table.setWidthPercentage(40);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setWidths(new float[] { 10, 30 });
		return table;

	}

	private static PdfPCell borderCell(String arg0) {

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(arg0, font));
		return cell;
	}
	private static PdfPCell borderCellBold(String arg0) {

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(arg0, boldfont));
		return cell;
	}

	private static PdfPCell borderCellHeader(String arg0) {

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(arg0, font));
	    cell.setBackgroundColor( WebColors.getRGBColor("#ddd"));
		return cell;
	}

	@SuppressWarnings("unused")
	private static PdfPCell noBorderCell(String arg0) {

		PdfPCell cell = new PdfPCell();
		cell.setBorder(0);
		cell.setPhrase(new Phrase(arg0, font));
		return cell;
	}

	@SuppressWarnings("unused")
	private static PdfPCell noBorderCell(String arg0, Integer align) {

		PdfPCell cell = new PdfPCell();
		cell.setBorder(0);
		cell.setPhrase(new Phrase(arg0, font));
		cell.setHorizontalAlignment(align);
		return cell;
	}

	private PdfPCell noBorderCell(PdfPTable t, Integer align, float paddingTop) {
		PdfPCell cell = new PdfPCell();

		cell.setPaddingTop(paddingTop);
		cell.setBorder(0);
		cell.addElement(t);
		cell.setPaddingLeft(0);
		if (align != null) {
			cell.setHorizontalAlignment(align);
		}

		return cell;
	}

	@SuppressWarnings("unused")
	private PdfPCell noBorderCell(PdfPTable t, float paddingTop) {
		return noBorderCell(t, null, paddingTop);
	}

}