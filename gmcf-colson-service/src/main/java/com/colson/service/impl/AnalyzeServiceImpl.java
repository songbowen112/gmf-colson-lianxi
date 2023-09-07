package com.colson.service.impl;

import com.colson.common.docx4j.*;
import com.colson.common.emum.OutlineRequirementEnum;
import com.colson.common.emum.QuestionTypeEnum;
import com.colson.common.utils.PathUtil;
import com.colson.dal.dao.AnalyzeDAO;
import com.colson.dal.dao.ValuableBookDAO;
import com.colson.dal.dto.*;
import com.colson.service.AnalyzeService;
import com.colson.service.TikuCommonService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Lazy(value = false)
public class AnalyzeServiceImpl implements AnalyzeService {

    private Logger logger = LoggerFactory.getLogger(AnalyzeServiceImpl.class);

	@Value("${valuableBookPath:}")
	private String valuableBookPath;

	@Autowired
	AnalyzeDAO analyzeDAO;

	@Autowired
	TikuCommonService tikuCommonService;

	private AnalyzeService self;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ValuableBookDAO valuableBookDAO;


	/**
	 * 获取自身代理类引用对象
	 * @return
	 */
	private AnalyzeService getSelf() {
		if (null != self) {
			return self;
		}
		self = context.getBean(AnalyzeService.class);
		return self;
	}

	/**
	 * 通关宝典模板
	 */
	static SimpleCrossDict simpleCrossDictTemplate;

	static {
		try {
			simpleCrossDictTemplate = new SimpleCrossDict(AnalyzeServiceImpl.class.getClassLoader().getResourceAsStream("通关宝典模版.docx"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Value("${ftp.ip:}")
	private String ftpIp;
	@Value("${ftp.port:}")
	private String ftpPort;
	@Value("${ftp.userName:}")
	private String ftpUsername;
	@Value("${ftp.passWord:}")
	private String ftpPassword;
	@Value("${ftp.basePath:}")
	private String basePath;
	@Value("${ftp.charsetName:}")
	private String charsetName;

	public void generateAnalyzeKnowledgeWordDoc(int knowTreeId, Integer provinceId, String examProvinceName) throws Exception {
        /*
        	生成考情分析文档分为3个步骤:
        	1. 数据查询封装
        		1.1 调用服务获取考情分析数据
        		1.2 调用服务获取知识树数据
        		1.3 组织封装数据成 Word文档生成需要的数据对象
        	2. 制备文档
        		2.1 首页数据填充处理
        		2.2 章节数据填充处理
        		2.3 目录刷新处理
        	3. 输出文档到Ftp服务器
         */
		logger.info("generateAnalyzeKnowledgeWordDoc({}, {}, {}) --- start", knowTreeId, provinceId, examProvinceName);
		// 查询考试省份名称
		if (null == examProvinceName || examProvinceName.trim().isEmpty()) {
			List<ResProvinceDTO> provinces = tikuCommonService.getProvincesByknowledgeTreeId(knowTreeId);
			if (null != provinces && !provinces.isEmpty()) {
				for (ResProvinceDTO province : provinces) {
					if (province.getId().equals(provinceId)) {
						examProvinceName = province.getName();
						break;
					}
				}
			}
		}
		// 数据查询封装
		ResKnowledgeTreeDTO treeNode = tikuCommonService.getKnowledgeTreeById(knowTreeId, null);
		// 获取层级数据map
		Map<String, CrossDictTableData> analyzeKnowledgeMap = packageAnalyzeKnowledgeToMap(knowTreeId, provinceId);
		// 查询考期
		List<String> examSessions = this.queryExamSession(knowTreeId, provinceId);
		Assert.isTrue(!CollectionUtils.isEmpty(examSessions),"考期不存在");
		// 组织封装首页数据
		CrossDictHomePage homePage = getCrossDictHomePage(treeNode, examSessions, examProvinceName);

		// 添加首页处理器
		SimpleCrossDict simpleCrossDict = simpleCrossDictTemplate.clone();
		simpleCrossDict.addHandler(new XmlHomePageHandler(homePage, simpleCrossDict));

		// 创建章节处理器
		XmlChapterHandler chapterHandler = new XmlChapterHandler(simpleCrossDict);
		// 封装章节知识数据
		packageChapters(chapterHandler, treeNode, analyzeKnowledgeMap);
		// 添加章节处理器
		simpleCrossDict.addHandler(chapterHandler);
		// 添加目录处理器
		simpleCrossDict.addHandler(new UpdateTOCHandler(simpleCrossDict));

		String fileName = "通关宝典-" + treeNode.getName() + "-" + examProvinceName + ".docx";
		fileName = PathUtil.getCurrentPath() + File.separator + fileName;

		// 添加输出到SFS处理器
		SfsUploadHandler sfsUploadHandler = new SfsUploadHandler(simpleCrossDict, fileName);
		simpleCrossDict.addHandler(sfsUploadHandler);

		//		handle(simpleCrossDict, fileName);
		// 处理数据, 有可能因为数据原因导致处理出现异常
		simpleCrossDict.handle();
		// 输出文件
		logger.info("generateAnalyzeKnowledgeWordDoc({}, {}, {}) --- end", knowTreeId, provinceId, examProvinceName);
	}

	public void handle(WordMLPackageWare ware, String fileName) throws Exception {
		FileInputStream in = null;
		fileName = PathUtil.getCurrentPath() + File.separator + fileName;
		ware.getWordMLPackage().save(new File(fileName));
		// 将上传到服务器中的文件读取出来存入sfs文件服务器
		try {
			File tmpFile = getFile(fileName);
		} catch (Exception e) {
			throw new RuntimeException("将上传到服务器中的文件读取出来存入sfs文件服务器时出现错误！" + e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 获取保存在服务器中的文件
	 *
	 * @param fileName
	 * @return
	 */
	private File getFile(String fileName) throws Exception {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new Exception(fileName + "不存在");
		}
		return file;
	}

	/**
	 * 获取题型分析数据
	 * @param knowledgeId       知识树ID
	 * @param provinceId        考试省份ID
	 */
	private Map<String, CrossDictTableData> packageAnalyzeKnowledgeToMap(int knowledgeId, int provinceId) {
		int initialCapacity = 16;
		int maxLevel = 4;
		Map<String, CrossDictTableData> resultMap = new HashMap<>(initialCapacity);
		for (int i = 1; i <= maxLevel; i++) {
		    // 遍历查询层级数据, 共4层
			List<AnalyzeKnowledgeNodeDTO> analyzeKnowledgeNodeDTOS = null;
			try {
				analyzeKnowledgeNodeDTOS = getSelf().analyzeKnowledgeNode(knowledgeId, provinceId, i, new ArrayList<>());
			} catch (Exception e) {
				logger.error("查询考情分析错误, 知识树id: {}, 考试省份id: {}, 层级: {}, 错误消息: {}", knowledgeId, provinceId, i, e.getMessage());
				continue;
			}
			// 封装数据到Map集合中
			packageAnalyzeKnowledgeNode(resultMap, analyzeKnowledgeNodeDTOS, i);
		}

		return resultMap;
	}

	/**
	 * 打包数据到Map集合中, 编号为Key, 题型分析为value
	 *
	 * @param resultMap	                结果集
	 * @param analyzeKnowledgeNodeDTOS  知识树节点集合
     * @param level                     知识层级
	 * @return
	 */
	private Map<String,CrossDictTableData> packageAnalyzeKnowledgeNode(Map<String, CrossDictTableData> resultMap, List<AnalyzeKnowledgeNodeDTO> analyzeKnowledgeNodeDTOS, int level) {
		if (null != analyzeKnowledgeNodeDTOS && !analyzeKnowledgeNodeDTOS.isEmpty()) {
            for (AnalyzeKnowledgeNodeDTO analyzeKnowledgeNodeDTO : analyzeKnowledgeNodeDTOS) {
                // 如果层级不等则跳过处理, 层级不等数据不正确
                if (analyzeKnowledgeNodeDTO.getLevel() != level) {
                    continue;
                }
                // 将数据放入Map中
                putDataToMap(analyzeKnowledgeNodeDTO, resultMap);
            }
		}
		return resultMap;
	}

    /**
     * 将数据封装到Map集合中, SerialNumber为Key, 章节表数据对象为Value
     * @param analyzeKnowledgeNodeDTO   知识树节点
     * @param resultMap                 结果集
     */
	private void putDataToMap(AnalyzeKnowledgeNodeDTO analyzeKnowledgeNodeDTO, Map<String, CrossDictTableData> resultMap) {
	    // 转换大纲要求
        String nameByCode = OutlineRequirementEnum.getNameByCode(analyzeKnowledgeNodeDTO.getOutlineRequirement());
        // 构建表基础数据对象
        CrossDictTableData crossDictTableData = new CrossDictTableData(analyzeKnowledgeNodeDTO.getLevel().toString(), nameByCode);
        // 按照题型数据封装数据对象
        List<CrossDictTableData.PerRowData> list = new ArrayList<>();
        list.add(crossDictTableData.new PerRowData("", analyzeKnowledgeNodeDTO.getCount().toString(), analyzeKnowledgeNodeDTO.getCountPercent(), analyzeKnowledgeNodeDTO.getScore().toEngineeringString(), analyzeKnowledgeNodeDTO.getScorePercent()));
        List<AnalyzeKnowledgeNodeDTO> questionTypeStatistic = analyzeKnowledgeNodeDTO.getQuestionTypeStatistic();
        if (null != questionTypeStatistic && !questionTypeStatistic.isEmpty()) {
            for (AnalyzeKnowledgeNodeDTO knowledgeNodeDTO : questionTypeStatistic) {
                list.add(crossDictTableData.new PerRowData(QuestionTypeEnum.getNameByCode(knowledgeNodeDTO.getQuestionType()), knowledgeNodeDTO.getCount().toString(), knowledgeNodeDTO.getCountPercent(), knowledgeNodeDTO.getScore().toString(), knowledgeNodeDTO.getScorePercent()));
            }
        }
        crossDictTableData.setPerRowDataList(list);
        resultMap.put(analyzeKnowledgeNodeDTO.getSerialNumber(), crossDictTableData);
	}

    /**
     * 获取通关宝典首页数据对象
     * @param treeNode          知识树根节点
     * @param examSessions      考期
     * @param examProvinceName  考试省份
     * @return
     */
    private CrossDictHomePage getCrossDictHomePage(ResKnowledgeTreeDTO treeNode, List<String> examSessions, String examProvinceName) {
        // 拼接考试适用省份
        StringBuilder provinceBuilder = new StringBuilder();
        for (ResProvinceDTO resProvinceDTO : treeNode.getProvinceList()) {
            provinceBuilder.append(resProvinceDTO.getName()).append("、");
        }
        String province = provinceBuilder.substring(0, provinceBuilder.length() - 1);

        StringBuilder sb = new StringBuilder();
        for (String session : examSessions) {
            sb.append(session).append(", ");
        }
        String substring = sb.substring(0, sb.length() - 2);

        // 创建首页数据对象
        CrossDictHomePage homePage = new CrossDictHomePage(treeNode.getName(), province, examProvinceName, substring);
        return homePage;
    }

	/**
	 * 获取章节结构
	 *
	 * @param treeNode	知识树
	 * @param analyzeKnowledgeMap	考情分析Map数据
	 * @return
	 */
	private void packageChapters(XmlChapterHandler chapterHandler, ResKnowledgeTreeDTO treeNode, Map<String, CrossDictTableData> analyzeKnowledgeMap) {
		List<ResKnowledgeNodeDTO> children = treeNode.getChildren();
		if (null == children || children.isEmpty()) {
			return;
		}
		for (ResKnowledgeNodeDTO child : children) {
			chapterHandler.addChapter(getSubKnowledge(1, child, analyzeKnowledgeMap));
		}
	}

	/**
	 * 获取知识树
	 * @param level         知识树层级
	 * @param treeNode      知识树节点
	 * @param analyzeKnowledgeMap	考情分析Map数据
	 * @return
	 */
	private CrossDictChapterKnowledge getSubKnowledge(int level, ResKnowledgeNodeDTO treeNode, Map<String, CrossDictTableData> analyzeKnowledgeMap) {
		// 创建章节知识点
		CrossDictTableData crossDictTableData = analyzeKnowledgeMap.get(treeNode.getSerialNumber());
		CrossDictChapterKnowledge node = new CrossDictChapterKnowledge(level, treeNode.getName(), crossDictTableData);
//		System.out.println(treeNode.getName() + treeNode.getDescription());
		if (null != treeNode.getDescription() && !treeNode.getDescription().isEmpty()) {
			// 将Html文本切割为字符串集合数据
			List<String> strings = Docx4jUtils.htmlParagraphSplit(treeNode.getDescription());
			for (String string : strings) {
				// 向节点中添加一个段落数据
				node.addParagraph(string);
			}
		}
		List<ResKnowledgeNodeDTO> children = treeNode.getChildren();
		if (null == children || children.isEmpty()) {
			return node;
		}
		for (ResKnowledgeNodeDTO child : children) {
			node.addSubChapter(getSubKnowledge(level + 1, child, analyzeKnowledgeMap));
		}
		return node;
	}
	

	@Override
	public ValuableBookFileInfoDTO queryValuableBookByKnowledgeTreeId(Integer knowledgeTreeId) {
		Assert.notNull(knowledgeTreeId, "知识树id不能为空");
		String sfsPath = null;
		try {
			//考试省份写死35-全国
			Integer examProvinceId = 35;
			this.generateAnalyzeKnowledgeWordDoc(knowledgeTreeId, examProvinceId, null);
		} catch (Exception e) {
			logger.error("queryFilePathBySubjectAndProvince error, e:{}", e.getMessage());
		}
		if (StringUtils.isNotEmpty(sfsPath)) {
			ValuableBookFileInfoDTO valuableBookFileInfoDTO = new ValuableBookFileInfoDTO();
			valuableBookFileInfoDTO.setFileLink(sfsPath);
			return valuableBookFileInfoDTO;
		}
		return null;
	}

	@Override
	public ValuableBookFileInfoDTO getValuableBookBySubjectAndProvinceAndKnowledgeTree(Integer subjectId, Integer provinceId, Integer knowledgeTreeId) {
		return valuableBookDAO.getValuableBookBySubjectIdAndProvinceId(subjectId, provinceId);
	}

	@Override
	public ValuableBookFileInfoDTO getValuableBookByKnowledgeTreeId(Integer knowledgeTreeId) {
		return valuableBookDAO.getValuableBookByKnowledgeTreeId(knowledgeTreeId);
	}

	@Override
	public List<AnalyzeKnowledgeNodeDTO> analyzeKnowledgeNode(int knowTreeId, Integer provinceId, int targetLevel, List<String> examSession) {
		List<AnalyzeKnowledgeNodeDTO> analyzeKnowledgeNodeDTOList = analyzeDAO.analyzeExam(knowTreeId, provinceId, targetLevel, examSession);
		Map<String, List<AnalyzeKnowledgeNodeDTO>> analyzeMap = new HashMap<>();
		int totalCountSum = 0;  //所有知识点的考察次数
		BigDecimal totalScoreSum = new BigDecimal(0); //所有知识点的考察分值
		for (AnalyzeKnowledgeNodeDTO analyzeKnowledgeNodeDTO : analyzeKnowledgeNodeDTOList) {
			List<AnalyzeKnowledgeNodeDTO> analyzeList = analyzeMap.computeIfAbsent(analyzeKnowledgeNodeDTO.getSerialNumber(), k -> new ArrayList<>());
			analyzeList.add(analyzeKnowledgeNodeDTO);
			//计算该知识树下所有知识点的考察次数和考察分值
			totalCountSum += analyzeKnowledgeNodeDTO.getCount();
			totalScoreSum = totalScoreSum.add(analyzeKnowledgeNodeDTO.getScore());
		}
		return analyze(analyzeMap, totalCountSum, totalScoreSum);
	}

	public List<String> queryExamSession(int knowTreeId, Integer provinceId) {
		return analyzeDAO.queryExamSession(knowTreeId, provinceId);
	}

	public List<AnalyzeKnowledgeNodeDTO> analyze(Map<String, List<AnalyzeKnowledgeNodeDTO>> analyzeMap,
												 int totalCountSum, BigDecimal totalScoreSum) {
		List<AnalyzeKnowledgeNodeDTO> analyzeList = new ArrayList<>();
		//统计一个知识点所有题型的考察分值和考察次数, 并且统计前三种常考题型
		for (String serialNumber : analyzeMap.keySet()) {
			//获取一个知识点的所有题型的统计数据
			List<AnalyzeKnowledgeNodeDTO> analyzeKnowNodeList = analyzeMap.get(serialNumber);
			//没有统计数据则跳过该知识点,实际上这个判断的结果不会为true
			if (CollectionUtils.isEmpty(analyzeKnowNodeList)) {
				continue;
			}
			//如果只有一条试题类型为空的统计结果, 说明没有真题涉及到该知识点
			if (analyzeKnowNodeList.size() == 1 && StringUtils.isBlank(analyzeKnowNodeList.get(0).getQuestionType())) {
				analyzeList.add(analyzeKnowNodeList.get(0));
				continue;
			}

			//拷贝一个 AnalyzeKnowledgeNodeDTO 对象, 统计该知识点所有题型的考察次数和分值
			AnalyzeKnowledgeNodeDTO analyzeKnowNode = copyAnalyzeDTO(analyzeKnowNodeList.get(0));
			analyzeKnowNode.setCount(0);
			analyzeKnowNode.setScore(new BigDecimal(0));
			analyzeKnowNode.setQuestionTypeStatistic(new ArrayList<AnalyzeKnowledgeNodeDTO>());

			analyzeList.add(analyzeKnowNode);

			//按照试题数量和总分排序analyzeKnowNodeList
			Comparator<AnalyzeKnowledgeNodeDTO> comparator = new AnalyzeKnowledgeNodeDTO.CompareByCountAndScore();
			Collections.sort(analyzeKnowNodeList, comparator);

			//将一个知识点的前三种常考题型的统计结果 放到QuestionTypeStatistic列表里
			for (AnalyzeKnowledgeNodeDTO analyzeNodeQuestionType : analyzeKnowNodeList) {
				if (analyzeNodeQuestionType.getQuestionType() != null
						&& analyzeKnowNode.getQuestionTypeStatistic().size() < 3) {
					//计算该知识点下每种题型的考察次数占比和考察分值占比
					float countPercent = (totalCountSum == 0 || analyzeNodeQuestionType.getCount() == 0) ? 0f : (float) analyzeNodeQuestionType.getCount() / totalCountSum;
					analyzeNodeQuestionType.setCountPercent(floatToPercent(countPercent));
					float scorePercent = (BigDecimal.ZERO.compareTo(totalScoreSum) == 0 || BigDecimal.ZERO.compareTo(analyzeNodeQuestionType.getScore()) == 0) ? 0f : analyzeNodeQuestionType.getScore().
							divide(totalScoreSum, 3, BigDecimal.ROUND_HALF_UP).floatValue();
					analyzeNodeQuestionType.setScorePercent(floatToPercent(scorePercent));
					analyzeKnowNode.getQuestionTypeStatistic().add(analyzeNodeQuestionType);
				}
				//计算该知识点所有题型的考察次数和分值
				analyzeKnowNode.setCount(analyzeKnowNode.getCount() + analyzeNodeQuestionType.getCount());
				analyzeKnowNode.setScore(analyzeKnowNode.getScore().add(analyzeNodeQuestionType.getScore()));
			}

			//计算该知识点所有题型的考察次数占比和考察分值占比
			float countPercent = (totalCountSum == 0 || analyzeKnowNode.getCount() == 0) ? 0f : (float) analyzeKnowNode.getCount() / totalCountSum;
			analyzeKnowNode.setCountPercent(floatToPercent(countPercent));
			float scorePercent = (BigDecimal.ZERO.compareTo(totalScoreSum) == 0 || BigDecimal.ZERO.compareTo(analyzeKnowNode.getScore()) == 0) ? 0f : analyzeKnowNode.getScore().divide(totalScoreSum, 3, BigDecimal.ROUND_HALF_UP).floatValue();
			analyzeKnowNode.setScorePercent(floatToPercent(scorePercent));
		}
		//按照知识点编号排序
		Comparator<AnalyzeKnowledgeNodeDTO> comparator = new AnalyzeKnowledgeNodeDTO.CompareBySerialNumber();
		analyzeList.sort(comparator);
		return analyzeList;
	}

	private AnalyzeKnowledgeNodeDTO copyAnalyzeDTO (AnalyzeKnowledgeNodeDTO analyzeKnowledgeNodeDTO) {
		AnalyzeKnowledgeNodeDTO analyzeKnowNode = new AnalyzeKnowledgeNodeDTO();
		analyzeKnowNode.setSerialNumber(analyzeKnowledgeNodeDTO.getSerialNumber());
		analyzeKnowNode.setSerialNumber(analyzeKnowledgeNodeDTO.getSerialNumber());
		analyzeKnowNode.setNodeName(analyzeKnowledgeNodeDTO.getNodeName());
		analyzeKnowNode.setLevel(analyzeKnowledgeNodeDTO.getLevel());
		analyzeKnowNode.setOutlineRequirement(analyzeKnowledgeNodeDTO.getOutlineRequirement());
		return analyzeKnowNode;
	}

	private String floatToPercent(float num){
		return String.format("%.2f%%", num * 100) ;
	}


}
