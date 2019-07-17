//package com.webside.docToPdf;
//
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by songhuiping on 2017/7/17.
// */
//public class testParseXml {
//    /**
//     * 解析xml
//     * 填入doc模板数据
//     */
//    @Test
//    public void test() {
//        DocUtil docUtil = new DocUtil();
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("Name", "Joanna");
//        dataMap.put("GRSQSTime", "20170717124533");
//        docUtil.createDoc(dataMap, "E:\\testDoc\\test.doc");
//    }
//
//    /**
//     * doc生成pdf
//     * @throws Exception
//     */
//    @Test
//    public void test2() throws Exception {
//        docToPdf d = new docToPdf();
//        System.err.println(d.getDocPageSize("E:\\doc\\授权书.docx"));
//        d.wordToPDF("E:\\testDoc\\test.doc", "E:\\testDoc\\law.pdf");
//    }
//
//}
