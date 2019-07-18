package com.npsex.fsp.commons.utils;

import com.itextpdf.text.pdf.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by songhuiping on 2017/8/1.
 */
public class PDFUtils {
    private static final Logger logger = LogManager.getLogger(PDFUtils.class);

    /**
     * 由pdf模板创建pdf
     *
     * @param modelPath  模板路径
     * @param toFilePath 新生成的文件路径
     * @param map        模板内填充的数据
     * @return
     */
    public static boolean createPdf(String modelPath, String toFilePath, Map<String, String> map) {
        boolean flag = false;
        try {
            PdfReader reader = new PdfReader(modelPath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* 将要生成的目标PDF文件名称 */
            PdfStamper ps = new PdfStamper(reader, bos);
            PdfContentByte under = ps.getUnderContent(1);

          /* 使用中文字体 */
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);


        /* 取出报表模板中的所有字段 */
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
//            fillData(fields, data());
            for (String key : map.keySet()) {
                String value = map.get(key);
                fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的
            }

        /* 必须要调用这个，否则文档不会生成的 */
            ps.setFormFlattening(true);
            ps.close();
            File file = new File(toFilePath);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            OutputStream fos = new FileOutputStream(toFilePath);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bos.close();
            flag = true;
        } catch (Exception e) {
            logger.error("创建PDF文件失败：" + e.toString());
        }
        return flag;
    }
}
