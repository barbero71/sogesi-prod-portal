package it.sogesispa.prod.web.reports;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import it.sogesispa.prod.web.dto.AttivitaDTO;
import it.sogesispa.prod.web.dto.MacchinaImpiantoInterventoDTO;
import it.sogesispa.prod.web.dto.MacchinaImpiantoInterventoDTO.ImpiantoInterventoDTO;

public class ReportMacchina {
	static Font font = FontFactory.getFont(FontFactory.HELVETICA, 10f);
	static Font boldfont = FontFactory.getFont(FontFactory.HELVETICA, 10f, Font.BOLD);

	SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	String righe = "______________";

	/**
	 * Creates a PDF with information about the movies
	 * 
	 * @param filename
	 *            the name of the PDF file that will be created.
	 * @throws DocumentException
	 * @throws IOException
	 */
	class MyHeader extends PdfPageEventHelper {
		String nomeMacchina = null;
		String datatxt = "";
		String headertxt = null;
		String stabilimento = null;

		public MyHeader(Date data) {
			super();
			this.datatxt = fmt.format(data);
		}

		public void onStartPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase header = new Phrase(
					"Stabilimento: " + stabilimento + " - Nome macchina: " + nomeMacchina + " - Data: " + datatxt,
					boldfont);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, header, document.leftMargin(), document.top() + 10, 0);

		}

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase pagina = new Phrase(String.format("Pagina: %d", writer.getPageNumber()), font);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, pagina, document.leftMargin(), document.bottom() - 10,
					0);
			Phrase footer = new Phrase("FIRMA: ________________________________________________", font);
			ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer,
					(document.right() - document.left()) + document.leftMargin(), document.bottom() - 10, 0);

		}
	}

	public void createPdf(Collection<MacchinaImpiantoInterventoDTO> lista, Date data,
			OutputStream out) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4, 30, 30, 30, 30);

		PdfWriter writer = PdfWriter.getInstance(document, out);
		MyHeader event = null;
		
		if(lista.size()==0)
		{
			document.open();
			Phrase emptyf = new Phrase("Per la data "+fmt.format(data)+" non sono previsti interventi", font);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, emptyf,
					(document.right() - document.left()) /2, document.top() + 50, 0);
			document.add(emptyf);
		}
		else
		{
			event = new MyHeader(data);
			writer.setPageEvent(event);
		}
		for(MacchinaImpiantoInterventoDTO m:lista)
		{
			event.nomeMacchina = m.getNomeMacchina();
			event.stabilimento = m.getNomeStabilimento();
			if(!document.isOpen()){
				document.open();
			}
			else
			{
				document.newPage();
			}
			document.add(creaTabellaOggetti(m.getLista()));
			
		}
		
		if(document.isOpen())document.close();
	}

	private PdfPTable creaTabellaOggetti(Collection<ImpiantoInterventoDTO> lista) throws DocumentException {

		PdfPTable table = new PdfPTable(1);

		for (ImpiantoInterventoDTO i : lista) {

			table.addCell(noBorderCell("Impianto:"+i.getDescrizioneImpianto()));

			for (AttivitaDTO a : i.getList()) {
				PdfPTable table2 = new PdfPTable(1);
				table2.addCell(borderCell("Nome intervento: "+a.getNomeIntervento()));
				PdfPTable table3 = new PdfPTable(1);

				table3.addCell(noBorderCell("Descrizione intervento: "+a.getIntervento()));
				table3.addCell(noBorderCell(a.isEseguita() ? "Eseguito" : "Da eseguire"));
				table3.addCell(noBorderCell(
						"Data esecuzione: " + (a.getDataEsecuzione() != null ? fmt.format(a.getDataEsecuzione()) : righe)
								+ " ore: " + (a.getOreImpiegate() != null ? a.getOreImpiegate() : righe) + " costi: "
								+ (a.getCosti() != null ? a.getCosti() : righe) + " operatore: "
								+ (a.getNomeOperatore() != null ? a.getNomeOperatore() : righe)));
				table3.addCell(noBorderCell("Note:"));
				table3.addCell(borderCell(a.getNote() != null ? "\n"+a.getNote()+"\n\n" : "\n\n\n"));
				table3.setWidthPercentage(100);
				
				table2.addCell(table3);
				table2.setWidthPercentage(100);
				table.addCell(table2);
			}

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

	@SuppressWarnings("unused")
	private static PdfPCell borderCellHeader(String arg0) {

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(arg0, font));
		cell.setBackgroundColor(WebColors.getRGBColor("#ddd"));
		return cell;
	}

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