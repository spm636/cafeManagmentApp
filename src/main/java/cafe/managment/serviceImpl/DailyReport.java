package cafe.managment.serviceImpl;

import cafe.managment.dto.OrderDto;
import cafe.managment.model.Cart;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyReport {
    private OrderDto orderDto;
    private List<cafe.managment.dto.DailyReport> dailyReports;
    private Double profit;
    private int customer;
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        fontTiltle.setSize(20);


        Paragraph paragraph = new Paragraph("Daily Report", fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        Font newFont1=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        newFont1.setSize(13);

        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Chunk("Date: "+String.valueOf(orderDto.getLocalDate()),newFont1));
        document.add(paragraph);

        Font newFont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        newFont.setSize(10);

        Paragraph paragraph1 = new Paragraph("New Hot Cafe", newFont);
        paragraph1.setAlignment(Paragraph.ALIGN_BASELINE);
        paragraph1.add(Chunk.NEWLINE);
        paragraph1.add(new Chunk("Kochi, Ernakulam, 67154",newFont));
        paragraph1.add(Chunk.NEWLINE);
        paragraph1.add(new Chunk("Mobile: 9497018669",newFont));

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(80f);
        table.setWidths(new int[]{1,4, 3,3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.DARK_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("sl", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("product", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("profit", font));
        table.addCell(cell);
        int sl=1;
        for(cafe.managment.dto.DailyReport c:dailyReports){
            table.addCell(String.valueOf(sl));
            table.addCell(String.valueOf(c.getProduct()));

            table.addCell(String.valueOf(c.getQuantity()));
            table.addCell(String.valueOf(c.getTotelPrice()));
            sl++;
        }


        document.add(table);
        Font newFont2=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        newFont2.setSize(20);
        Paragraph paragraph3=new Paragraph("Totel Profit:"+String.valueOf(profit),newFont2);
        paragraph3.setAlignment(Element.ALIGN_BASELINE);
        paragraph3.setIndentationLeft(40);

       document.add(paragraph3);
        Font newFont3=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        newFont3.setSize(15);
       Paragraph paragraph2=new Paragraph("Number of customer visited: "+customer,newFont3);
       document.add(paragraph2);
        document.close();
    }
}
