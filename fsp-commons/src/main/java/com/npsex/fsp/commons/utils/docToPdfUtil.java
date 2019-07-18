package com.npsex.fsp.commons.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * Created by songhuiping on 2017/7/17.
 */
public class docToPdfUtil {
    private static final Logger logger = LogManager.getLogger(docToPdfUtil.class);

    static final int wdFormatPDF = 17;// PDF 格式

    public static String wordToPDF(String sfileName, String toFileName) {

        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            // 设置word不可见
            app.setProperty("Visible", new Variant(false));
            // 打开word文件
            Dispatch docs = app.getProperty("Documents").toDispatch();
//          doc = Dispatch.call(docs,  "Open" , sourceFile).toDispatch();
            logger.debug("打开文档..." + sfileName);
            doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[]{
                    sfileName, new Variant(false), new Variant(true)}, new int[1]).toDispatch();

            logger.debug("转换文档到PDF..." + toFileName);
            File tofile = new File(toFileName);
            // System.err.println(getDocPageSize(new File(sfileName)));
            if (tofile.exists()) {
                tofile.delete();
            }else{
                File rootFile = tofile.getParentFile();
                rootFile.mkdirs();
            }
//          Dispatch.call(doc, "SaveAs",  destFile,  17);
            // 作为html格式保存到临时文件：：参数 new Variant(8)其中8表示word转html;7表示word转txt;44表示Excel转html;17表示word转成pdf。。
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{
                    toFileName, new Variant(wdFormatPDF)}, new int[1]);

        } catch (Exception e) {
            toFileName = "";
            e.printStackTrace();
            logger.error("========Error:文档转换失败：" + e.getMessage());
        } finally {
            // 关闭word
            Dispatch.call(doc, "Close", false);
//            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[]{});
            //如果没有这句话,winword.exe进程将不会关闭
            ComThread.Release();

        }
        return toFileName;
    }

    private static Document read(File xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(xmlFile);
    }

    public int getDocPageSize(String filePath) throws Exception {
        XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(filePath));
        int pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页数
        int wordCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();// 忽略空格的总字符数 另外还有getCharactersWithSpaces()方法获取带空格的总字数。
        System.out.println("pages=" + pages + " wordCount=" + wordCount);
        return pages;
    }

//    public static void main(String[] args) throws Exception {
//        docToPdf d = new docToPdf();
//        d.wordToPDF("D:\\temp\\doc\\testSyqrs.doc", "D:\\temp\\doc\\testSyqrs.pdf");
//    }
}
