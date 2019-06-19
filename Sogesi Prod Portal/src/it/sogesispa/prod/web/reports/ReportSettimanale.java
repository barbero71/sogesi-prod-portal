package it.sogesispa.prod.web.reports;

import java.awt.Color;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
//import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
//import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import it.sogesispa.prod.web.dto.AttivitaDTO;

public class ReportSettimanale {
	static Font font = FontFactory.getFont(FontFactory.HELVETICA, 10f);
	SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat dfmt = new SimpleDateFormat("EEE, dd-MM-yyyy", Locale.ITALIAN);
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

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
			Phrase header = new Phrase("Report del: " + datatxt + " - Operatore:" + operatore + " - Stabilimento: "
					+ stabilimento + (headertxt != null ? (" - " + headertxt) : ""), font);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, header, document.leftMargin(), document.top() + 10, 0);

		}

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase footer = new Phrase(String.format("- %d -", writer.getPageNumber()), font);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer,
					(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
		}
	}

	public void createPdf(Collection<AttivitaDTO> lista, String stabilimento, String operatore, String header,
			Date dataStart, Date dataEnd, OutputStream out) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4.rotate(), 30, 30, 30, 30);

		PdfWriter writer = PdfWriter.getInstance(document, out);
		MyHeader event = new MyHeader(stabilimento, operatore, header);
		writer.setPageEvent(event);
		document.open();

		document.add(creaTabellaOggetti(lista, dataStart, dataEnd));

		document.close();
	}

	private PdfPTable creaTabellaOggetti(Collection<AttivitaDTO> lista, Date dataStart, Date dataEnd)
			throws DocumentException {

		PdfPTable table = new PdfPTable(8);
		String giorno1 = dfmt.format(dataStart);
		String giorno2 = dfmt.format(addDays(dataStart, 1));
		String giorno3 = dfmt.format(addDays(dataStart, 2));
		String giorno4 = dfmt.format(addDays(dataStart, 3));
		String giorno5 = dfmt.format(addDays(dataStart, 4));
		String giorno6 = dfmt.format(addDays(dataStart, 5));
		String giorno7 = dfmt.format(dataEnd);

		table.addCell(borderCellHeader("Macchina"));
		table.addCell(borderCellHeader(giorno1));
		table.addCell(borderCellHeader(giorno2));
		table.addCell(borderCellHeader(giorno3));

		table.addCell(borderCellHeader(giorno4));
		table.addCell(borderCellHeader(giorno5));
		table.addCell(borderCellHeader(giorno6));
		table.addCell(borderCellHeader(giorno7));
		table.setWidths(new float[] { 4, 4, 4, 4, 4, 4, 4, 4 });
		@SuppressWarnings("unused")
		boolean nuova = false;
		String currentMacchina = "empty";
		@SuppressWarnings("unused")
		String currentDtPrevista = "empty";
		Map<String, Integer> rows = new HashMap<String, Integer>();
		Map<String, Integer> cols = new HashMap<String, Integer>();
		cols.put(giorno1, 0);
		cols.put(giorno2, 1);
		cols.put(giorno3, 2);
		cols.put(giorno4, 3);
		cols.put(giorno5, 4);
		cols.put(giorno6, 5);
		cols.put(giorno7, 6);
		int i = 0;
		for (AttivitaDTO a : lista) {
			@SuppressWarnings("unused")
			String dtPrevista = dfmt.format(a.getDataPrevista());
			if (!currentMacchina.equals(a.getMacchina())) {
				rows.put(a.getMacchina(), i);
				i++;
				currentMacchina = a.getMacchina();
			}
		}
		@SuppressWarnings("unchecked")
		List<String>[][] arrobj = new ArrayList[rows.size()][cols.size()];
		for (AttivitaDTO a : lista) {
			String dtPrevista = dfmt.format(a.getDataPrevista());
			String macchina = a.getMacchina();
			List<String> x = arrobj[rows.get(macchina)][cols.get(dtPrevista)];
			if (x == null) {
				x = new ArrayList<>();
				arrobj[rows.get(macchina)][cols.get(dtPrevista)] = x;
			}
			x.add(a.getIntervento());
		}
		for (String j : rows.keySet()) {
			PdfPCell cellMach = noBorderCell(j);
			cellMach.setBackgroundColor(WebColors.getRGBColor("#f5f5f5"));
			cellMach.setBorderWidthBottom(0.5f);
			cellMach.setBorderWidthRight(0.5f);
			cellMach.setBorderWidthLeft(0.8f);
			table.addCell(cellMach);

			PdfPCell ccarr[] = new PdfPCell[7];
			ccarr[0] = noBorderCell("");
			ccarr[1] = noBorderCell("");
			ccarr[2] = noBorderCell("");
			ccarr[3] = noBorderCell("");
			ccarr[4] = noBorderCell("");
			ccarr[5] = noBorderCell("");
			ccarr[6] = noBorderCell("");
			for (String jj : cols.keySet()) {
				PdfPTable table2 = new PdfPTable(1);
				List<String> x = arrobj[rows.get(j)][cols.get(jj)];
				if (x != null) {
					boolean primo = true;
					for (String interv : x) {
						PdfPCell cella = noBorderCell(interv);
						if(!primo)
						{
						cella.setBorderWidthTop(0.4f);
						cella.setBorderColorTop(Color.GRAY);
						}
						primo = false;
						table2.addCell(cella);
					}
				} else {
					PdfPCell cella = noBorderCell("");
					table2.addCell(cella);
				}
				table2.setWidthPercentage(100);
				ccarr[cols.get(jj)] = noBorderCell(table2, 0);
				// table.addCell(table2);
			}
			for (PdfPCell pcel : ccarr) {
				pcel.setBorderWidthBottom(0.5f);
				pcel.setBorderWidthRight(0.5f);
				table.addCell(pcel);
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

	@SuppressWarnings("unused")
	private static PdfPCell borderCell(String arg0) {

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(arg0, font));
		return cell;
	}

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

	private PdfPCell noBorderCell(PdfPTable t, float paddingTop) {
		return noBorderCell(t, null, paddingTop);
	}

	private Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

}