package com.colson.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.colson.common.utils.PathUtil;
import com.colson.service.PaperUploadService;
import com.colson.service.dto.DownloadPdfParamInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

/**
 * 下载真题pdf定时任务
 */
@Controller
@RequestMapping("/api/download")
@Slf4j
public class DownloadPdfController{

    @Autowired
    PaperUploadService paperUploadService;

    //自考科目id，多个逗号分隔
    private static String selfStudySubjectIds = "495,812,1177,1201,1147,1149,1148,390,142,491,754,459,482,74,327,557,648,180,856,1243,636,146,563,52,1215,120,143,694,808,895,894,1170,570,305,761,883,1260,444,703,41,181,643,26,90,44,1179,841,546,313,311,312,331,571,744,1221,1219,1214,685,700,80,1190,974,93,94,705,973,460,488,139,1180,106,846,330,545,573,535,150,840,370,164,393,399,32,105,976,384,969,83,697,182,137,115,1027,575,1172,1262,314,816,1176,576,1301,40,197,191,213,680,34,972,757,47,126,266,99,241,36,46,391,977,425,1024,635,178,978,73,102,84,1169,35,629,817,1178,730,65,140,695,707,753,687,739,756,736,708,738,735,1198,1202,709,737,710,711,1218,740,1223,713,1234,1224,1226,714,1222,755,715,693,77,751,749,741,179,368,1173,69,95,169,144,875,556,377,243,633,845,261,238,351,100,154,339,64,581,388,110,1230,265,116,487,492,441,387,376,447,239,1175,874,1185,165,645,1328,1329,1330,560,558,62,153,647,1220,899,897,888,893,892,900,898,890,889,374,121,57,117,252,1259,183,194,1168,811,1253,1181,27,360,113,818,549,398,654,1,13,542,1256,295,348,1026,98,634,177,108,637,172,103,124,92,70,584,967,968,585,366,38,762,282,586,692,986,587,421,147,175,132,255,1196,503,354,843,499,971,717,272,527,326,322,316,588,323,317,296,589,981,187,214,209,189,184,185,681,674,876,879,729,590,870,258,867,868,1244,1242,857,1296,861,1237,866,865,1236,257,698,699,49,448,1197,355,335,485,2,490,1205,871,860,1245,25,478,439,539,858,1247,862,1246,642,638,592,641,364,433,455,365,371,500,984,321,651,389,718,719,734,1225,51,445,702,101,279,689,53,152,593,87,1241,68,561,264,244,595,186,720,966,965,167,226,696,731,732,383,626,61,758,880,683,821,822,882,823,824,632,188,33,211,210,877,216,596,195,206,1010,676,212,215,678,878,525,193,202,104,135,1191,1184,813,1252,464,630,373,684,859,1238,60,253,67,627,896,625,66,869,1235,863,750,864,1195,278,118,174,682,639,190,504,745,43,30,267,526,236,451,597,690,200,263,733,631,1023,88,1250,691,141,59,31,809,815,646,1174,649,1171,810,1251,242,628,372,559,91,24,449,501,688,450,454,470,842,286,598,523,985,133,1193,1151,148,980,271,979,173,97,176,721,752,149,394,438,474,432,783,467,346,338,336,1189,310,349,337,746,600,1029,72,747,1186,231,446,476,458,486,426,475,502,543,1187,256,277,1194,328,395,71,452,453,55,45,601,677,196,9,424,483,457,520,602,380,162,722,1240,675,679,891,723,240,1204,324,96,127,219,221,220,224,227,1303,225,223,222,76,259,686,748,1188,724,431,662,1331,8,1203,493,780,781,743,435,204,171,1028,650,1182,1183,814,367,369,50,273,29,644,48,531,661,652,548,536,605,657,512,517,1199,655,510,513,547,534,656,518,1200,660,514,20,511,537,529,528,538,505,544,530,532,658,533,507,506,519,873,442,725,1228,123,157,119,128,471,163,381,352,307,319,19,39,308,320,315,356,287,345,334,299,300,302,304,844,358,318,288,325,359,297,1011,289,290,309,477,612,508,550,653,659,515,541,509,357,347,201,199,807,982,63,203,1025,386,270,701,122,114,37,618,129,28,166,970,1097,205,245,260,484,461,430,797,379,363,473,456,481,437,436,428,427,472,466,468,170,151,17,237,375,168,56,462,480,440,429,620,469,54,479,640,801,621,799,800,1261,726,247,494,975,125,1030,727,268,382,291,131,1192,75,292,58";

    //考期区间
    private static String beginSession = "1704";
    private static String endSession = "2204";

    //真题试卷类型
    private static String realExamType = "REAL_EXAM";

    //试卷上下线状态
    private static Integer invalidFlag = 0;

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void downloadPdf(String s) throws Exception {
        DownloadPdfParamInfo paramDTO;
        try {
            if (StringUtils.isBlank(s)) {
                paramDTO = new DownloadPdfParamInfo();
            } else {
                paramDTO = JSONObject.parseObject(s, DownloadPdfParamInfo.class);
            }
            //默认查询自考的所有科目
            if (StringUtils.isBlank(paramDTO.getSubjectIds())) {
                paramDTO.setSubjectIds(selfStudySubjectIds);
            }
            //默认考期设置
            if (StringUtils.isBlank(paramDTO.getBeginSession())) {
                paramDTO.setBeginSession(beginSession);
            }
            if (StringUtils.isBlank(paramDTO.getEndSession())) {
                paramDTO.setEndSession(endSession);
            }
            //默认查询上线的试卷
            if (null == paramDTO.getInvalidFlag()) {
                paramDTO.setInvalidFlag(invalidFlag);
            }
            //默认查询真题试卷
            if (StringUtils.isBlank(paramDTO.getExerciseType())) {
                paramDTO.setExerciseType(realExamType);
            }
            String currentPath = PathUtil.getCurrentPath();
            //模版路径
            String templatePath = currentPath + File.separator + "pdf" + File.separator + "template" + File.separator + "download_real_exercise.html";
            //字体路径
            String fontPath = currentPath + File.separator + "pdf" + File.separator + "font" + File.separator + "msyh.ttf";
            log.info("DownloadPdfJob ===== 资源路径, templatePath={}，fontPath={}", templatePath, fontPath);
            paperUploadService.downloadPdf(paramDTO, templatePath, fontPath, currentPath);
        } catch (Exception e) {
            log.error("DownloadPdfJob fail", e);
        }
        log.info("DownloadPdfJob end");
    }
}
