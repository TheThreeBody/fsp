package com.zhengtong.fsp.manager.service.job.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhengtong.fsp.commons.core.enums.StatusEnum;
import com.zhengtong.fsp.commons.utils.FTPUtils;
import com.zhengtong.fsp.commons.utils.HttpClientUtil;
import com.zhengtong.fsp.commons.utils.PDFUtils;
import com.zhengtong.fsp.manager.mapper.job.dict.DictItemMapper;
import com.zhengtong.fsp.manager.mapper.job.syqrs.SequenceMapper;
import com.zhengtong.fsp.manager.mapper.job.syqrs.SyqrsGenerStatusMapper;
import com.zhengtong.fsp.manager.mapper.job.syqrs.SyqrsParamsMapper;
import com.zhengtong.fsp.manager.mapper.job.syqrs.SyqrsRecodeMapper;
import com.zhengtong.fsp.manager.pojo.job.dict.DictItemEntity;
import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsGenerStatusEntity;
import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsParamsEntity;
import com.zhengtong.fsp.manager.pojo.job.syqrs.SyqrsRecodeEntity;
import com.zhengtong.fsp.manager.pojo.common.FtpConstant;
import com.zhengtong.fsp.manager.pojo.common.HttpApiConstant;
import com.zhengtong.fsp.manager.service.job.GenerateSyqrsService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by songhuiping on 2017/7/17.
 */
@Service("generateSyqrsService")
public class GenerateSyqrsServiceImpl implements GenerateSyqrsService {

    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    private static final Logger logger = LogManager.getLogger(GenerateSyqrsServiceImpl.class);


    @Autowired
    private SyqrsParamsMapper syqrsParamsMapper;

    @Autowired
    private SyqrsRecodeMapper syqrsRecodeMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private SyqrsGenerStatusMapper syqrsGenerStatusMapper;

    /**
     * 字典
     */
    @Autowired
    private DictItemMapper dictItemMapper;

    /**
     */
    private String con_no = "001020017010";

    /**
     * 补充合同后缀
     */
    private String suppConSuffix = "_51_01";

    /**
     * 生成合同
     *
     * @param consume_time 消费时间
     */
    @Override
    public void insertSyqrs(String consume_time) {
        //模板文件路径，名字，以及临时存放路径等数据
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("modelPath", "/model/syqrs.pdf");
//        urlMap.put("modelName", "syqrs.pdf");
        //product_type产品类型从字典获取
        Map<String, String> maparams = new HashMap<>();
        maparams.put("dictName", "product_type");
        maparams.put("dictItemCode", "1");
        DictItemEntity productDict = dictItemMapper.selectDict(maparams);
        productDict.setDictItemCode(productDict.getDictItemCode());
        productDict.setDictItemName(productDict.getDictItemName());
        String product_type = productDict.getDictItemName();
        //消费时间不能为空
        if (consume_time != null && !consume_time.equals("")) {
            List<SyqrsParamsEntity> syqrsParamsEntityList = syqrsParamsMapper.findSyqrsDataByParams(consume_time);
            //存在需要生成合同的数据
            if (syqrsParamsEntityList != null && syqrsParamsEntityList.size() > 0) {
                //循环遍历集合中数据
                for (SyqrsParamsEntity syqrsParamsEntity : syqrsParamsEntityList) {
                    String dateName = consume_time.replaceAll("-", "");
                    //临时doc存放目录
//                    String tempDocName = "/temp/doc/" + dateName + "/temp" + syqrsParamsEntity.getCustId() + ".doc";
//                    urlMap.put("docSavePath", tempDocName);
                    //临时pdf存放目录
                    String tempPdfName = "/temp/pdf/" + dateName + "/temp" + syqrsParamsEntity.getCustId() + ".pdf";
                    urlMap.put("pdfSavePath", tempPdfName);
                    //生成借据合同的数据，调用生成合同的接口
                    Map<String, String> map = new HashMap<>();
                    //编号，按规则生成
                    map.put("contract_no", getContractNo());
                    //本次消费金额
                    map.put("consum_amount", syqrsParamsEntity.getLoanAmount());
                    //借款人姓名
                    map.put("cust_name", syqrsParamsEntity.getCustName());
                    //身份证号
                    map.put("cust_id_no", syqrsParamsEntity.getCustIdNo());
                    //贷款方名称从字典获取
                    Map<String, String> params = new HashMap<>();
                    params.put("dictName", "credit_side");
                    params.put("dictItemCode", syqrsParamsEntity.getCreditSide().toString());
                    DictItemEntity dictItemEntity = dictItemMapper.selectDict(params);
                    dictItemEntity.setDictItemCode(dictItemEntity.getDictItemCode());
                    dictItemEntity.setDictItemName(dictItemEntity.getDictItemName());
                    String credit_side = dictItemEntity.getDictItemName();
                    map.put("credit_side", credit_side);
                    params.clear();

                    //担保方从字典获取
                    params.put("dictName", "guarantor");
                    params.put("dictItemCode", syqrsParamsEntity.getGuarantor().toString());
                    dictItemEntity = dictItemMapper.selectDict(params);
                    dictItemEntity.setDictItemCode(dictItemEntity.getDictItemCode());
                    dictItemEntity.setDictItemName(dictItemEntity.getDictItemName());
                    String guarantor = dictItemEntity.getDictItemName();
                    map.put("guarantor", guarantor);
                    params.clear();
                    //贷款金额
                    map.put("loan_amount", syqrsParamsEntity.getLoanAmount());
                    //最后还款日
                    map.put("final_repayment_date", syqrsParamsEntity.getFinalRepaymentDate().toString());
                    //户名1
                    map.put("repayment_cust_name1", syqrsParamsEntity.getRepaymentCustName());
                    //账号1
                    map.put("cust_mobile", syqrsParamsEntity.getCustMobile());
                    //户名2
                    map.put("repayment_cust_name2", syqrsParamsEntity.getRepaymentCustName());
                    //账号2
                    map.put("repayment_account", syqrsParamsEntity.getRepaymentAccount());

                    //担保费率
                    map.put("guarantee_fee_rate", syqrsParamsEntity.getGuaranteeFeeRate());
                    //最低担保费
                    map.put("gurantee_fee", syqrsParamsEntity.getGuranteeFee().toString());
                    //罚息
                    map.put("penalty_rate", syqrsParamsEntity.getOverduePayment());
                    //消费日期
                    String consum_date = syqrsParamsEntity.getConsumeTime();
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = simpleDateFormat.parse(consum_date);
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        consum_date = simpleDateFormat.format(date);
                    } catch (ParseException e) {
                        logger.error("消费日期解析错误：" + e.toString());
                    }
                    String str = consume_time.replaceAll("-", "");
                    map.put("consum_date", consum_date);
                    //ftp的filePath
                    urlMap.put("filePath", product_type.toLowerCase() + "/" + str + "/syqrs");
                    //ftp的保存名字
                    String fileName = product_type.toLowerCase() + "-" + syqrsParamsEntity.getCustId() + "-" + getFileName() + ".pdf";
                    urlMap.put("filename", fileName);
                    //生成pdf并上传ftp
                    String fileUrl = createPdf(map, urlMap);
                    //保存文件成功
                    if (fileUrl != null && fileUrl != "") {
                        //保存成功后t_syqrs_recode数据表保存数据
                        SyqrsRecodeEntity syqrsRecodeEntity = new SyqrsRecodeEntity();
                        syqrsRecodeEntity.setFileName(fileName);
                        syqrsRecodeEntity.setFileUrl(fileUrl);
                        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                        syqrsRecodeEntity.setSyqrsUuid(uuid);
                        syqrsRecodeEntity.setFileType("SYQRS");
                        //字典取值
                        syqrsRecodeEntity.setProductType(Byte.parseByte(productDict.getDictItemCode()));
                        syqrsRecodeEntity.setCustIdNo(syqrsParamsEntity.getCustIdNo());
                        syqrsRecodeEntity.setCreateTime(new Date());
                        syqrsRecodeEntity.setUpdateTime(new Date());
                        syqrsRecodeMapper.recodeInsert(syqrsRecodeEntity);
                        //t_syqrs_gener_status更改状态记录
                        SyqrsGenerStatusEntity syqrsGenerStatusEntity = new SyqrsGenerStatusEntity();
                        //使用授权书参数表uuid对应状态表uuid
                        syqrsGenerStatusEntity.setSyqrsUuid(syqrsParamsEntity.getSyqrsUuid());
                        //'状态0失败 1成功 2初始化
                        syqrsGenerStatusEntity.setGenerateStatus(Byte.parseByte(StatusEnum.SUCCESS.getCode()));
                        syqrsGenerStatusEntity.setUpdateTime(new Date());
                        syqrsGenerStatusMapper.updateStatus(syqrsGenerStatusEntity);
                    } else {
                        //t_syqrs_gener_status更改状态记录
                        SyqrsGenerStatusEntity syqrsGenerStatusEntity = new SyqrsGenerStatusEntity();
                        //使用授权书参数表uuid对应状态表uuid
                        syqrsGenerStatusEntity.setSyqrsUuid(syqrsParamsEntity.getSyqrsUuid());
                        //'状态0失败 1成功 2初始化
                        syqrsGenerStatusEntity.setGenerateStatus(Byte.parseByte(StatusEnum.FAIL.getCode()));
                        syqrsGenerStatusEntity.setUpdateTime(new Date());
                        syqrsGenerStatusMapper.updateStatus(syqrsGenerStatusEntity);
                    }

                }
            }
        }
    }

    /**
     * 生成pdf，并盖章保存文件
     *
     * @param dataMap 合同所需数据
     * @param urlMap  合同路径数据
     * @return pdf在ftp上的保存路径
     */
    public String createPdf(Map<String, String> dataMap, Map<String, String> urlMap) {
        //模板存放路径
        String modelPath = urlMap.get("modelPath");
        //临时存放路径
        String tempPdfSavepath = urlMap.get("pdfSavePath");
        //生成Pdf
        //获取根路径
        String rootPath = "";
        rootPath = System.getProperty("user.dir");
        int i = rootPath.indexOf("\\");
        rootPath = rootPath.substring(0, i);
        tempPdfSavepath = rootPath + tempPdfSavepath;

        boolean flag = PDFUtils.createPdf(modelPath, tempPdfSavepath, dataMap);
        //ftp文件保存路径
        String filePath = "";
        String filename = "";
        if (flag) {
            //生成成功，上传ftp
            //pdf文件字节流，base64编码，调贷前用
            String PdfBufferString = getPDFBinary(new File(tempPdfSavepath));
            //pdf上传到ftp
            filePath = urlMap.get("filePath") + "/seal";
            filename = urlMap.get("filename");
            try {
                InputStream input = new FileInputStream(tempPdfSavepath);
                //获取ftp连接
                FTPClient ftp = FTPUtils.connectFtp(FtpConstant.FTP_IP, Integer.parseInt(FtpConstant.FTP_PORT), FtpConstant.FTP_USERNAME, FtpConstant.FTP_PWD);
                //上传文件
                FTPUtils.uploadFile(ftp, filename, input, filePath);
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("盖章前ftp文件保存失败：" + e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //贷前盖章接口
            Map<String, Object> param = new HashMap<>();
            param.put("FileName", filename);
//            param.put("idNo", dataMap.get("cust_id_no"));
            param.put("PdfBufferString", PdfBufferString);
            //返回的文件字节流，带base64编码
            String FileBufferString = outCompoundSeal(param);
            try {
                if (!FileBufferString.equals("") && FileBufferString != null) {

                    String tempSaveUrl = rootPath + "/temp/temp.pdf";
                    //解码转文件
                    base64StringToPDF(FileBufferString, tempSaveUrl);
//                    byte[] outFileBytes = decoder.decodeBuffer(FileBufferString);
//                    InputStream input = new ByteArrayInputStream(outFileBytes);
                    InputStream input = new FileInputStream(tempSaveUrl);
                    //盖章后文件保存的路径
//                    filePath = urlMap.get("filePath") + "/afterseal";
                    filename = "seal-" + filename;
                    //获取ftp连接
                    FTPClient ftp = FTPUtils.connectFtp(FtpConstant.FTP_IP, Integer.parseInt(FtpConstant.FTP_PORT), FtpConstant.FTP_USERNAME, FtpConstant.FTP_PWD);
                    //上传文件

                    flag = FTPUtils.uploadFile(ftp, filename, input, filePath);
                    if (!flag) {
                        filePath = "";
                    }
                    File file = new File(tempSaveUrl);
                    if (file.exists()) {
                        file.delete();
                    }

                } else {
                    //贷前接口盖章失败
                    filePath = "";
                }

            } catch (IOException e) {
                filePath = "";
                e.printStackTrace();
                logger.error("盖章后ftp文件保存失败：" + e.toString());
            } catch (Exception e) {
                filePath = "";
                e.printStackTrace();
            }
            File tempdfFile = new File(tempPdfSavepath);
            if (tempdfFile.exists()) {
                tempdfFile.delete();
            }
        }

        return filePath;
    }

    /**
     * 获取当前时间，并按规则生成字符串用以命名文件
     *
     * @return
     */

    public String getFileName() {
        //当前日期字符串
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNow = sdf.format(d);
        return dateNow;
    }

    /**
     * 贷前接口调用
     *
     * @param map
     * @return
     */
    public String outCompoundSeal(Map<String, Object> map) {
        Map<String, Object> paramMap = new HashMap<>();
        //是否已签名盖章过，为false时会校验是否签名盖章过，为true时不能进行签名操作
        paramMap.put("IsSealed", false);

        //文件名称
        paramMap.put("FileName", map.get("FileName").toString());

        //文件字节流，Base64编码
        paramMap.put("PdfBufferString", map.get("PdfBufferString"));
        paramMap.put("LoanKind", "TYTCBT");

        //证件号码
//        paramMap.put("idNo", map.get("idNo").toString());

        //盖章集合
        List<Object> list = new ArrayList<>();

        //第一个章的数据集合
        Map<String, Object> estampMap1 = new HashMap<>();
        //章编码 101 成都维仕小额贷款有限公司
        estampMap1.put("StampCode", "101");
        //盖章原因
        estampMap1.put("SealReason", "借据合同");
        List<Object> objectList = new ArrayList<>();
        //章的位置数据
        Map<String, String> locationMap = new HashMap<>();
        //盖章的类型，0表示按坐标Pagen、X、Y必填，1表示按字符位置签名，Char必填
        locationMap.put("Type", "0");
        locationMap.put("Pagen", "1");
        //X坐标，距离每页左下角，可先传100看下效果
        locationMap.put("X", "350");
        //Y坐标，距离每页左下角，可先传100看下效果
        locationMap.put("Y", "250");
        objectList.add(locationMap);
        estampMap1.put("EstampLocations", objectList);
        list.add(estampMap1);
        //第二个章
        List<Object> objectList2 = new ArrayList<>();
        Map<String, Object> estampMap2 = new HashMap<>();
        //章编码 124 维仕担保有限公司
        estampMap2.put("StampCode", "124");
        //盖章原因
        estampMap2.put("SealReason", "借据合同");
        //章的位置数据
        Map<String, String> locationMap2 = new HashMap<>();
        //盖章的类型，0表示按坐标Pagen、X、Y必填，1表示按字符位置签名，Char必填
        locationMap2.put("Type", "0");
        locationMap2.put("Pagen", "1");
        //X坐标，距离每页左下角，可先传100看下效果
        locationMap2.put("X", "410");
        //Y坐标，距离每页左下角，可先传100看下效果
        locationMap2.put("Y", "170");
        objectList2.add(locationMap2);
        //盖章配置，即图片显示在pdf中位置
        estampMap2.put("EstampLocations", objectList2);
        list.add(estampMap2);
        paramMap.put("EstampRequests", list);

        //调贷前cfca接口盖章
        String str = HttpClientUtil.doPostJson(HttpApiConstant.CFCA_URL, JSONArray.toJSONString(paramMap));
        if (str != null && !str.equals("")) {
            Map maps = (Map) JSON.parse(str);
            boolean isSuccess = (Boolean) maps.get("IsSeccess");
            if (isSuccess) {
                //返回的带Base64编码的字节流
                str = maps.get("FileBufferString").toString();
            } else {
                str = "";
            }
        }
        return str;
    }

    /**
     * 合同编号生成
     *
     * @return
     */
    public String getContractNo() {
        Integer re = sequenceMapper.getSeqNo("TYTCBT");
        // 主合同编号后六位是数字增长，不足六位左边补0
        String result = String.format("%0" + 6 + "d", re);
        //前面固定十二位加上数字六位和补充合同标志位
        String contractNo = con_no + result + suppConSuffix;
        return contractNo;
    }

    /**
     * 将PDF转换成base64编码
     * 1.使用BufferedInputStream和FileInputStream从File指定的文件中读取内容；
     * 2.然后建立写入到ByteArrayOutputStream底层输出流对象的缓冲输出流BufferedOutputStream
     * 3.底层输出流转换成字节数组，然后由BASE64Encoder的对象对流进行编码
     */
    static String getPDFBinary(File file) {
        String str = "";
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        try {
            //建立读取文件的文件输出流
            fin = new FileInputStream(file);
            //在文件输出流上安装节点流（更大效率读取）
            bin = new BufferedInputStream(fin);
            // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            baos = new ByteArrayOutputStream();
            //创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            //sun公司的API
            str = encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fin.close();
                bin.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 将base64编码转换成PDF
     *
     * @param base64sString 1.使用BASE64Decoder对编码的字符串解码成字节数组
     *                      2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
     *                      3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
     *                      4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
     */
    static void base64StringToPDF(String base64sString, String tempSavepath) {
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            //将base64编码的字符串解码成字节数组
            byte[] bytes = decoder.decodeBuffer(base64sString);
            //apache公司的API
            //byte[] bytes = Base64.decodeBase64(base64sString);
            //创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            //指定输出的文件http://m.nvzi91.cn/nxby/29355.html
            File file = new File(tempSavepath);
            //创建到指定文件的输出流
            fout = new FileOutputStream(file);
            //为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);
            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bin.close();
                fout.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
