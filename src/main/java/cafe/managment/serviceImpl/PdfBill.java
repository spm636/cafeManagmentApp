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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PdfBill {
    private OrderDto orderDto;
    private List<Cart> carts;
   private Double grandTotel;
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        com.lowagie.text.Document document = new Document(PageSize.A5);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        fontTiltle.setSize(20);
        Font newFont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        newFont.setSize(12);

        Paragraph paragraph = new Paragraph("New Hot Cafe", newFont);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Chunk("Kochi, Ernakulam, 67154",newFont));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Chunk("Mobile: 9497018669",newFont));

        document.add(paragraph);

        Font fontTiltle3 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle3.setSize(12);

        Paragraph paragraph3=new Paragraph("Customer Name:"+String.valueOf(orderDto.getCustomerName()),fontTiltle3);
         paragraph3.setAlignment(Element.ALIGN_BASELINE);
        paragraph3.setIndentationLeft(35);
        paragraph3.add(Chunk.NEWLINE);
        paragraph3.add(new Chunk("Mobile: " + String.valueOf(orderDto.getMobile()), fontTiltle3));
        paragraph3.add(Chunk.NEWLINE);
        paragraph3.add(new Chunk("Place: " + String.valueOf(orderDto.getPlace()), fontTiltle3));
        document.add(paragraph3);


        LocalDate localDate=LocalDate.now();
        LocalTime time=LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = localDate.format(formatter);
        Paragraph paragraph1=new Paragraph("Date: "+formattedDate,fontTiltle3);
        DateTimeFormatter formatTime=DateTimeFormatter.ofPattern("HH:mm:ss");
        String formatedTime=time.format(formatTime);
        paragraph1.add(Chunk.NEWLINE);
        paragraph1.add(new Chunk("Time: " + formatedTime, fontTiltle3));
        paragraph1.setAlignment(Element.ALIGN_RIGHT);

        document.add(paragraph1);



        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(80f);
        table.setWidths(new int[]{1,4, 3,3,3});
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
        cell.setPhrase(new Phrase("price", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Totel", font));
        table.addCell(cell);
        int sl=1;
        for(Cart c:carts){
        table.addCell(String.valueOf(sl));
        table.addCell(String.valueOf(c.getProduct().getName()));
        table.addCell(String.valueOf(c.getProduct().getSalePrice()));
        table.addCell(String.valueOf(c.getQuanity()));
        table.addCell(String.valueOf(c.getTotel()));
        sl++;
        }

        PdfPCell totalLabelCell = new PdfPCell(new Phrase("Totel Amount"));
        totalLabelCell.setColspan(4);
        table.addCell(totalLabelCell);
        PdfPCell totalAmountCell = new PdfPCell(new Phrase(String.valueOf(grandTotel)));
        table.addCell(totalAmountCell);
        document.add(table);

        document.close();
    }
}
