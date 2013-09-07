/**
 * Copyright (c) 2013, jamiesun, All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *     Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 * 
 *     Redistributions in binary form must reproduce the above copyright notice, this
 *     list of conditions and the following disclaimer in the documentation and/or
 *     other materials provided with the distribution.
 * 
 *     Neither the name of the {organization} nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.toughradius.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.xlightweb.BodyDataSink;
import org.xlightweb.HttpResponseHeader;
import org.xlightweb.IHttpExchange;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 报表导出工具类
 */
public class ExportUtil {
    public static int A3 = 3;
    public static int A4 = 4;
    public static int Horizontal = 1;//横向
    public static int Vertical = 2;
    
    /**
     * 导出EXCEL
     * 
     * @param response
     * @param fileName 文件名称
     * @param title 标题
     * @param header 标头数组
     * @param widths 标头大小比例
     * @param dataList 数据列表，每个为一个数组，数组内数据个数和标头相同
     * @param footer 标脚,为导出提供说明
     * @throws Exception
     */
    public static void exportExcel(IHttpExchange http, String fileName, String title, String[] header, int[] widths, List<String[]> dataList, String footer) throws Exception
    {
        if(dataList.size()>(255*255))
        {
            throw new Exception("数据太大超过限制,数据总记录不能超过"+(255*255));
        }
        BodyDataSink sink = null;
        ByteArrayOutputStream os = null;
        try{
            HttpResponseHeader headers = new HttpResponseHeader("application/msexcel,charset=GBK");
            headers.addHeader("Content-Disposition","attachment;filename="+fileName);
            sink = http.send(headers);
            os = new ByteArrayOutputStream();
            WritableWorkbook wwb = Workbook.createWorkbook(os); 
            WritableSheet ws = wwb.createSheet(title, 0); 

            //设置标题
            WritableFont titleWf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD); 
            WritableCellFormat titleWcfF = new WritableCellFormat(titleWf); 
            titleWcfF.setBorder(Border.ALL, BorderLineStyle.THIN);
            Label titleLab = new Label(0, 0, title,titleWcfF); 
            ws.addCell(titleLab); 
            ws.mergeCells(0, 0, header.length-1, 0);
            
            //表格栏目的格式   
            WritableFont font4 =  new  WritableFont(WritableFont.TIMES, 10 ,WritableFont.NO_BOLD);   
            WritableCellFormat hwcf = new  WritableCellFormat(font4);    
            hwcf.setAlignment(Alignment.LEFT); //水平对齐   
            hwcf.setVerticalAlignment(VerticalAlignment.CENTRE); //垂直对齐   
            hwcf.setBorder(Border.ALL,BorderLineStyle.THIN); //设置边框   
            hwcf.setBackground(Colour.GRAY_25);
            hwcf.setWrap(true);
            
            for (int i = 0; i < header.length; i++)
            {        
                ws.setColumnView(i, widths[i]);
                Label lable = new Label(i, 1, header[i],hwcf); 
                ws.addCell(lable);
            }
            
            //数据栏目的格式   
            WritableFont font5 =  new  WritableFont(WritableFont.TIMES, 10 ,WritableFont.NO_BOLD);   
            WritableCellFormat dwcf = new  WritableCellFormat(font5);    
            dwcf.setAlignment(Alignment.LEFT); //水平对齐   
            dwcf.setVerticalAlignment(VerticalAlignment.CENTRE); //垂直对齐   
            dwcf.setBorder(Border.ALL,BorderLineStyle.THIN); //设置边框   
            dwcf.setWrap(true);
            
            for (int i = 0; i <  header.length; i++)
            {
                if(dataList==null)
                    break;
                for (int j = 0; j < dataList.size(); j++)
                {
                    String[] dcol = (String[]) dataList.get(j);
                    Label lable = new Label(i, j+2, dcol[i],dwcf); 
                    ws.addCell(lable);
                }
            }
            
            
            // 页脚的格式
            if (footer != null)
            {
                WritableFont font6 = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD);
                WritableCellFormat fwcf = new WritableCellFormat(font6);
                fwcf.setAlignment(Alignment.LEFT); // 水平对齐
                fwcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
                fwcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框
                fwcf.setWrap(true);

                Label footLable = new Label(0, dataList.size() + 2, footer, fwcf);
                ws.addCell(footLable);
                ws.mergeCells(0, dataList.size() + 2, header.length - 1, dataList.size() + 2);
            }
            // 写入文件
            wwb.write(); 
            wwb.close();
            sink.transferFrom(new ByteArrayInputStream(os.toByteArray()));
        }
        finally
        {
            if (os != null) os.close();
            if (sink != null) sink.close();
        }
   
    }
    
    /**
     * 导出PDF，默认A4纸和纵向
     * 
     * @param response
     * @param fileName 文件名称
     * @param title 标题
     * @param header 标头数组
     * @param widths 标头大小比例
     * @param dataList 数据列表，每个为一个数组，数组内数据个数和标头相同
     * @param footer 标脚,为导出提供说明
     * @throws Exception
     */
    public static void exportPdf(IHttpExchange http,String fileName, String title, String[] header, int[] widths, List<String[]> dataList, String footer) throws Exception
    {
        exportPdf(http, fileName, title, header, widths, dataList, footer, false, false);//默认A4,纵向
    }
    
    /**
     * 导出PDF，需提供是否A3/A4，是否横纵向
     * 
     * @param response
     * @param fileName 文件名称
     * @param title 标题
     * @param header 标头数组
     * @param widths 标头大小比例
     * @param dataList 数据列表，每个为一个数组，数组内数据个数和标头相同
     * @param footer 标脚,为导出提供说明
     * @param isA3 支持A3,A4两种，=true表示A3，=false表示A4
     * @param isHorizontal 是否横向,=true表示横向，=false表示纵向
     * @throws Exception
     */
    public static void exportPdf(IHttpExchange http,String fileName, String title, String[] header, int[] widths, List<String[]> dataList, String footer, boolean isA3, boolean isHorizontal) throws Exception
    {
        BodyDataSink sink = null;
        ByteArrayOutputStream os = null;
        try {
            HttpResponseHeader headers = new HttpResponseHeader("application/pdf,charset=GBK");
            headers.addHeader("Content-Disposition","attachment;filename="+fileName);
            sink = http.send(headers);
            
            Document document = new Document();  
            if (isA3)
                document.setPageSize(PageSize.A3);
            else
                document.setPageSize(PageSize.A4);
            
            if (isHorizontal)
                document.setPageSize(document.getPageSize().rotate());
            
            PdfWriter.getInstance(document,os);
            document.open(); 
            
            //中文
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL); 
            //英文
            BaseFont bfen = BaseFont.createFont(); 
            Font Fonten = new Font(bfen, 12, Font.NORMAL); 


            Font fTitle = new Font(bfChinese, 16, Font.BOLD); 
            Paragraph pTitle = new Paragraph(title, fTitle);
            pTitle.setSpacingAfter(18);
            pTitle.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(pTitle);
            
            PdfPTable  table = new PdfPTable (header.length);
            table.setWidthPercentage(100);//占页面百分率
            table.setWidths(widths);

            
            //表头
            for (int i = 0; i < header.length; i++)
            {
                String [] heards = header;
                PdfPCell hcell = new PdfPCell(new Paragraph(heards[i],FontChinese)); 
                hcell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
                hcell.setGrayFill(0.75f);
                hcell.setPadding(3f);
                hcell.setNoWrap(false);
                table.addCell(hcell); 
            }
            
            //数据
            if (dataList != null)
            {
                int n = dataList.size();
                for (int i = 0; i < n; i++)
                {
                    String[] dcol = (String[]) dataList.get(i);
                    for (int j = 0; j < dcol.length; j++)
                    {
                        if(dcol[j]==null)
                            dcol[j]="";
                        PdfPCell dcell = null;
                        if(dcol[j].getBytes().length>dcol[j].length())
                            dcell= new PdfPCell(new Paragraph(dcol[j],FontChinese)); 
                        else
                            dcell= new PdfPCell(new Paragraph(dcol[j],Fonten)); 
                        dcell.setPadding(3f);
                        dcell.setNoWrap(false);
                        table.addCell(dcell); 
                    }

                }
            }
            
            //页脚
            if (footer != null)
            {
                PdfPCell fcell = new PdfPCell(new Paragraph(footer, FontChinese));
                fcell.setColspan(header.length);
                fcell.setPadding(3f);
                fcell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                table.addCell(fcell);
            }
            document.add(table);

            document.close(); 
            sink.transferFrom(new ByteArrayInputStream(os.toByteArray()));
        }
        finally{
            if (os != null) os.close();
            if (sink != null) sink.close();
        }
    }
}
