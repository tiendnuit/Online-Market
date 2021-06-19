package edu.miu.cs545.group5.onlinemarket.config;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import edu.miu.cs545.group5.onlinemarket.domain.Order;
import edu.miu.cs545.group5.onlinemarket.domain.OrderLine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfUtil {

    public static ByteArrayInputStream orderReport(Order order) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfDiv divHeader = new PdfDiv();
            divHeader.addElement(new Phrase("Order Receipt"));
            divHeader.addElement(new Phrase("Order Id: " + order.getId()));
            divHeader.addElement(new Phrase("Customer name: " + order.getBuyer().getFirstName() + " " + order.getBuyer().getLastName()));
            divHeader.addElement(new Phrase("Seller name: " + order.getSeller().getFirstName() + " " + order.getSeller().getLastName()));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 5, 1, 1, 2});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Quantity", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Total", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (OrderLine line : order.getOrderLines()) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(line.getProduct().getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(line.getProduct().getName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(line.getQuantity().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(line.getProduct().getPrice())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(line.getTotalPrice())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfDiv divFooter = new PdfDiv();
            divFooter.addElement(new Phrase("Total Price (USD): " + order.getTotalPrice()));
            divFooter.addElement(new Phrase("Reward Point: -" + (order.getTotalPrice() - order.getTotal())));
            divFooter.addElement(new Phrase("Total Payment (USD): " + order.getTotal()));
            divFooter.addElement(new Phrase("Shipping Address"));
            divFooter.addElement(new Phrase("Street: " + order.getShippingAddress().getStreet()));
            divFooter.addElement(new Phrase("City: " + order.getShippingAddress().getCity()));
            divFooter.addElement(new Phrase("State: " + order.getShippingAddress().getState()));
            divFooter.addElement(new Phrase("Zipcode: " + order.getShippingAddress().getZipCode()));

            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle("Order_" + order.getId());
            document.add(divHeader);
            document.add(table);
            document.add(divFooter);

            document.close();

        } catch (DocumentException ex) {
            System.out.println("Error");
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
