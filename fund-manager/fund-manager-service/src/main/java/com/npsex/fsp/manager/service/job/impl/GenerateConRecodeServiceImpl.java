package com.npsex.fsp.manager.service.job.impl;

import com.npsex.fsp.commons.core.enums.StatusEnum;
import com.npsex.fsp.commons.utils.FTPUtils;
import com.npsex.fsp.commons.utils.PDFUtils;
import com.npsex.fsp.manager.mapper.job.dict.DictItemMapper;
import com.npsex.fsp.manager.mapper.job.grxxsqs.GenerateConRecodeMapper;
import com.npsex.fsp.manager.mapper.job.grxxsqs.GrxxsqsGenerStatusMapper;
import com.npsex.fsp.manager.pojo.ContractInfoEntity;
import com.npsex.fsp.manager.pojo.job.dict.DictItemEntity;
import com.npsex.fsp.manager.pojo.job.grxxsqs.GenerateConRecode;
import com.npsex.fsp.manager.pojo.job.grxxsqs.GrxxsqsGenerStatus;
import com.npsex.fsp.manager.pojo.common.FtpConstant;
import com.npsex.fsp.manager.service.job.GenerateConRecodeService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by songhuiping on 2017/7/14.
 */
@Service("generateConRecodeService")
public class GenerateConRecodeServiceImpl implements GenerateConRecodeService {

    private static final Logger logger = LogManager.getLogger(GenerateConRecodeServiceImpl.class);

    @Autowired
    private GrxxsqsGenerStatusMapper grxxsqsGenerStatusMapper;

    @Autowired
    private DictItemMapper dictItemMapper;

    @Autowired
    private GenerateConRecodeMapper generateConRecodeMapper;


    /**
     * 生成合同文档，并保存文件信息
     */
    public boolean insertContract() {
        Boolean flag = false;
        //第一步：查询t_grxxsqs_gener_status合同生成失败或初始化状态的数据
        //第二步：生成合同
        //第三步：上传ftp，上传成功后改变t_grxxsqs_gener_status状态，记录文件保存信息t_grxxsqs_recode
//        String generateStatus = map.get("generateStatus");
//        String createTime = map.get("createTime");
//        String productType = map.get("productType");
//        if (generateStatus.equals("") || generateStatus == null) {
//            logger.error("状态值不能为空");
//            return flag;
//        }
//        if (createTime.equals("") || createTime == null) {
//            logger.error("时间字段不能为空");
//            return flag;
//        }
//        if (productType.equals("") || productType == null) {
//            logger.error("产品类型不能为空");
//            return flag;
//        }
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("generateStatus", generateStatus);
//        paramMap.put("createTime", createTime);
//        paramMap.put("productType", Integer.parseInt(productType));
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
        //当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//       //时间
        String createTime = formatter.format(date);
        //前一日授信数据查询
        List<ContractInfoEntity> contractInfoEntityList = grxxsqsGenerStatusMapper.contractInfoGet(createTime);
        //生成合同需要的数据信息
        Map<String, String> conMap = new HashMap<>();
//        conMap.put("modelUrl", "/model/");
        if (contractInfoEntityList != null && contractInfoEntityList.size() > 0) {
            for (ContractInfoEntity contractInfoEntity : contractInfoEntityList) {
                //修改状态用
                Long generateStatusId = contractInfoEntity.getGenerStatusId();
                Map<String, String> dictMap = new HashMap<>();
                dictMap.put("dictName", "product_type");
                dictMap.put("dictItemCode", contractInfoEntity.getProductType().toString());
                DictItemEntity dictItemEntity = dictItemMapper.selectDict(dictMap);
                String product = dictItemEntity.getDictItemName().toLowerCase();
                //产品类型
                conMap.put("product", product);
                String modelName = product + ".pdf";
                //模板名字
//                conMap.put("modelName", modelName);
                conMap.put("modelUrl", "/model/" + modelName);
                //客户姓名
                conMap.put("custName", contractInfoEntity.getCustName());
                //客户身份证号
                conMap.put("custIdNo", contractInfoEntity.getCustIdNo());
                //授信人名字，理论上应该和客户名字一样
                conMap.put("authName", contractInfoEntity.getCustName());
                //授信时间
                conMap.put("creditTime", contractInfoEntity.getCreditTime());
                conMap.put("custId", contractInfoEntity.getCustId());

                //生成合同
                Map<String, String> resultMap = generateContract(conMap);
                //生成成功
                if (resultMap.size() > 0) {
                    //记录
                    GenerateConRecode generateConRecode = new GenerateConRecode();
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    generateConRecode.setGrxxsqsUuid(uuid);
                    generateConRecode.setFileName(resultMap.get("ftpFileName"));
                    generateConRecode.setFileUrl(resultMap.get("ftpUrl"));
                    generateConRecode.setFileType("GRXXSQS");
                    generateConRecode.setCustId(contractInfoEntity.getCustId());
                    generateConRecode.setProductType(contractInfoEntity.getProductType());
                    generateConRecode.setCreateTime(date);
                    generateConRecode.setUpdateTime(date);
                    generateConRecodeMapper.insert(generateConRecode);
                    //修改状态
                    GrxxsqsGenerStatus grxxsqsGenerStatus = new GrxxsqsGenerStatus();
                    grxxsqsGenerStatus.setGenerStatusId(generateStatusId);
                    grxxsqsGenerStatus.setGenerateStatus(Byte.parseByte(StatusEnum.SUCCESS.getCode()));
                    grxxsqsGenerStatus.setUpdateTime(new Date());
                    grxxsqsGenerStatusMapper.updateByPrimaryKeySelective(grxxsqsGenerStatus);
                    flag = true;
                } else {
                    //修改状态
                    GrxxsqsGenerStatus grxxsqsGenerStatus = new GrxxsqsGenerStatus();
                    grxxsqsGenerStatus.setGenerStatusId(generateStatusId);
                    grxxsqsGenerStatus.setGenerateStatus(Byte.parseByte(StatusEnum.FAIL.getCode()));
                    grxxsqsGenerStatus.setUpdateTime(new Date());
                    grxxsqsGenerStatusMapper.updateByPrimaryKeySelective(grxxsqsGenerStatus);
                }
            }
        }

        return flag;
    }

    /**
     * 生成合同，并保存ftp
     *
     * @param map
     * @// TODO: 2017/7/26
     */
    public Map<String, String> generateContract(Map<String, String> map) {

        Map<String, String> resultMap = new HashMap<>();
        //当前日期字符串
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        String product = map.get("product");
        String custId = map.get("custId");
        String creaditTime = map.get("creditTime");
        if (creaditTime == null || "".equals(creaditTime)) {
            return resultMap;
        }
        //授信时间必须到秒
//        if (creaditTime.length() < 19) {
//            return resultMap;
//        }
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(creaditTime);
        } catch (ParseException e) {
            return resultMap;
        }
        //模板存放路径
        String modelPath = map.get("modelUrl");
        //临时文件保存地址
        String tempPdfSavepath = "";
        String tempPdfName = custId + ".pdf";
        String rootPath = "";
        //获取根路径
        rootPath = System.getProperty("user.dir");
        int i = rootPath.indexOf("\\");
        rootPath = rootPath.substring(0, i);
        tempPdfSavepath = rootPath + "/temp/" + product + "/pdf/" + tempPdfName;
        //数据
        Map<String, String> data = new HashMap<>();
        data.put("custName", map.get("custName"));
        data.put("custIdNo", map.get("custIdNo"));
        data.put("authName", map.get("authName"));
        data.put("creditTime", creaditTime);
        //生成pdf
        boolean flag = PDFUtils.createPdf(modelPath, tempPdfSavepath, data);
        //成功
        //pdf保存信息
        //路径存放规则：产品名/日期/合同种类（/TYTCBT/20170629/GRXXSQS）
        String ftpUrl = product.toUpperCase() + "/" + dateNowStr + "/GRXXSQS";
        //文件命名规则：产品名-用户ID-时间戳（精确到毫秒）
        String ftpFileName = product.toUpperCase() + "-" + custId + "-";
        if (flag) {
            try {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(creaditTime);
                sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String str = sdf.format(date) + ".pdf";
                ftpFileName += str;
            } catch (ParseException e) {
                logger.error("授信时间解析失败：" + e);
                return resultMap;
            }
            try {
                //获取输入流
                InputStream in = new FileInputStream(tempPdfSavepath);
                //获取ftp连接
                FTPClient ftp = FTPUtils.connectFtp(FtpConstant.FTP_IP, Integer.parseInt(FtpConstant.FTP_PORT), FtpConstant.FTP_USERNAME, FtpConstant.FTP_PWD);
                //上传文件
                flag = FTPUtils.uploadFile(ftp, ftpFileName, in, ftpUrl);
                in.close();
                if(!flag){
                    return resultMap;
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("ftp文件保存失败：" + e.toString());
                return resultMap;
            }
        }
        resultMap.put("ftpFileName", ftpFileName);
        resultMap.put("ftpUrl", ftpUrl);

        File pdf = new File(tempPdfSavepath);
        if (pdf.exists()) {
            pdf.delete();
        }
        return resultMap;
    }
}
