package com.fileee.payroll.payroll

import com.fileee.payroll.entities.Payroll
import com.lowagie.text.*
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import java.awt.Color
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table

import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets
import javax.servlet.http.HttpServletResponse

import com.lowagie.text.pdf.PdfWriter


class PayrollDocumentExporter(val payroll: Payroll) {
    fun writeTableHeader(table: PdfPTable) {
        var cell = PdfPCell()
        cell.backgroundColor = Color.LIGHT_GRAY
        cell.setPadding(5F)

        val font: Font = FontFactory.getFont(FontFactory.HELVETICA)
        font.color = Color.WHITE

        cell.phrase = Phrase("Member", font)
        table.addCell(cell)

        cell.phrase = Phrase("Payroll Type", font)
        table.addCell(cell)

        cell.phrase = Phrase("From", font)
        table.addCell(cell)

        cell.phrase = Phrase("To", font)
        table.addCell(cell)

        cell.phrase = Phrase("Amount to Pay", font)
        table.addCell(cell)
    }

    fun export(response: HttpServletResponse) {
        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, response.outputStream)

        document.open()
        val font = FontFactory.getFont(FontFactory.HELVETICA_BOLD)
        font.size = 18f
        font.color = Color.DARK_GRAY

        val p = Paragraph("Payroll", font)
        p.alignment = Paragraph.ALIGN_CENTER

        document.add(p)

        val table = PdfPTable(5)
        table.widthPercentage = 100f
        table.setWidths(floatArrayOf(1.5f, 2.5f, 2.5f, 2.5f, 2.5f))
        table.setSpacingBefore(10f)

        writeTableHeader(table)
        table.addCell(payroll.member.name);
        table.addCell(payroll.member.payrollType.toString());
        table.addCell(payroll.from.toString());
        table.addCell(payroll.to.toString());
        table.addCell(payroll.paymentAmount.toString());

        document.add(table)
        document.close()
    }
}