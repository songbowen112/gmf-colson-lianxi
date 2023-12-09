package com.colson.dal.dao;

import com.colson.dal.dto.KnowledgeTree;
import com.colson.dal.dto.SubjectAIRel;
import com.colson.dal.dto.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface KnowledgeTreeDAO {
    /**
     * add by xiayimin 20170815
     * 新增知识树,并返回该知识树在表中的主键值
     *
     * @return
     */
    @Insert({
            "INSERT INTO t_knowledge_tree (subject_id,creator,operator)VALUES(#{resKnowledgeTreeDTO.subjectId},#{resKnowledgeTreeDTO.operator},#{resKnowledgeTreeDTO.operator})"
    })
    @SelectKey(before = false, keyProperty = "resKnowledgeTreeDTO.id", resultType = int.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertKnowledgeTree(@Param("resKnowledgeTreeDTO") ResKnowledgeTreeDTO resKnowledgeTreeDTO);
    /**
     * 新增知识点
     * @param node
     * @return
     */
    @Insert({
            "INSERT INTO t_knowledge_node (knowledge_tree_id,serial_number,name,level,parent_node_id,creator,operator,outline_requirement,description)" ,
            "VALUES(#{knowledgeTreeId},#{serialNumber},#{name},#{level},#{parentNodeId},#{creator},#{operator},#{outlineRequirement},#{description})"
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertKnowledgeNode(KnowledgeNode node);
    /**
     *
     * @param node
     * @return
     */
    @Update({
            "update t_knowledge_node " ,
            "SET serial_number = #{serialNumber} , name =#{name} ,description = #{description} , outline_requirement = #{outlineRequirement} , operator = #{operator} " ,
            "where id = #{id}"
    })
    int updateKnowledgeNode(KnowledgeNode node);

    /**
     * 根据条件查询知识树
     * @param subjectId
     * @param provinceId
     * @return
     */
    @Select({
            "<script>" ,
            "SELECT a.id FROM t_knowledge_tree as a ",
            "INNER JOIN t_knowledge_tree_province_proj2nd_rel as b ON b.knowledge_tree_id = a.id AND b.province_id = #{provinceId} AND b.delete_flag = 0 ",
            "WHERE a.subject_id = #{subjectId} AND a.delete_flag = 0 ",
            "limit 1" ,
            "</script>"
    })
    Integer getKnowledgeTreeIdByCondition(@Param("subjectId") Integer subjectId, @Param("provinceId") Integer provinceId);

    /**
     * 删除知识树的关联二级项目和省份
     * @param knowledgeTreeId
     * @return
     */
    @Update({
            "UPDATE t_knowledge_tree_province_proj2nd_rel SET delete_flag = 1 where knowledge_tree_id = #{knowledgeTreeId}"
    })
    int deleteRelByKnowledgeTreeId(@Param("knowledgeTreeId")Integer knowledgeTreeId);

    /**
     * 根据id查询知识树
     * @param id
     * @return
     */
    @Select({
        "SELECT a.id,b.name,a.subject_id as subjectId,a.creator,a.operator,a.create_time as createTime,a.update_time as updateTime,a.delete_flag as deleteFlag,a.language_type as languageType " ,
        " from t_knowledge_tree a" ,
        "inner join ent_subject b on a.subject_id = b.id and b.delete_flag = 0" ,
        " where a.id = #{id} and a.delete_flag = 0"
    })
    KnowledgeTree selectKnowledgeTreeById(@Param("id")Integer id);

    @Select({
            "SELECT b.id,b.province_name as name" ,
            "from t_knowledge_tree_province_proj2nd_rel a" ,
            "INNER JOIN sch_local_province b on a.province_id = b.id and b.delete_flag = 0" ,
            "where a.delete_flag = 0 and a.knowledge_tree_id = #{knowledgeTreeId} GROUP BY b.id"
    })
    List<ResProvinceDTO> queryProvincesByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId);

    @Select({
            "SELECT b.id,b.`name`" ,
            "from t_knowledge_tree_province_proj2nd_rel a" ,
            "INNER JOIN ent_proj_2nd b on a.project_2nd_id = b.id and b.delete_flag = 0" ,
            "where a.delete_flag = 0 and a.knowledge_tree_id = #{knowledgeTreeId} GROUP BY b.id"
    })
    List<ResProjectSecondDTO> queryProject2ndsByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId);

    @Select({
            "SELECT GROUP_CONCAT(DISTINCT b.`name`)",
            "FROM t_knowledge_tree_province_proj2nd_rel a",
            "INNER JOIN ent_proj_2nd b on a.project_2nd_id = b.id and b.delete_flag = 0",
            "WHERE a.delete_flag = 0 and a.knowledge_tree_id = #{treeId}"
    })
    String queryProj2ndNamesByTreeId(@Param("treeId") Integer treeId);

    /**
     * 根据知识树id，级别和父节点id获取子节点信息
     * @param level
     * @param parentNodeId
     * @return
     */
    @Select({
        "<script>" ,
        "SELECT id,serial_number as serialNumber,knowledge_tree_id as knowledgeTreeId,`name`,`level`,invalid_flag as invalidFlag,parent_node_id as parentNodeId," +
        "description, outline_requirement as outlineRequirementCode, knowledge_map_rel_flag as knowledgeMapRelFlag",
        "from t_knowledge_node " ,
        "where delete_flag = 0" ,  //and invalid_flag = 0 禁用状态也返回
        "and knowledge_tree_id = #{knowledgeTreeId} and `LEVEL` = #{level} " ,
        "<if test=\"parentNodeId != null\">and parent_node_id = #{parentNodeId}</if>" ,
        "<if test=\"parentNodeId == null\">and parent_node_id is NULL</if>" ,
        "<if test=\"invalidFlag != null\">and invalid_flag = #{invalidFlag}</if>" ,
//        "order by id desc" ,  // 排序测试用
        "</script>"
    })
    List<ResKnowledgeNodeDTO> queryChildrenByCondition(@Param("knowledgeTreeId") Integer knowledgeTreeId, @Param("level") Integer level, @Param("parentNodeId") Integer parentNodeId,
                                                       @Param("invalidFlag") Integer invalidFlag);
    /**
     * add by xiayimin 20170815
     * 功能描述：将Excel中知识点，插入表t_knowledge_node中
     *
     * @param resKnowledgeNodeDTOs
     * @return
     */
    @Insert({
            "<script>",
            "insert into t_knowledge_node (knowledge_tree_id,serial_number,name,level,parent_node_id,creator,operator,outline_requirement) ",
            "values",
            "<foreach collection=\"resKnowledgeNodeDTOs\" item=\"item\"  separator=\",\">",
            "(#{item.knowledgeTreeId}, ",
            "#{item.serialNumber}, #{item.name}, #{item.level},",
            "(select e.id from t_knowledge_node e where e.knowledge_tree_id=#{item.knowledgeTreeId} and e.serial_number=#{item.parentSerialNumber}),",
            "#{item.creator},#{item.operator},#{item.outlineRequirementCode})",
            "</foreach>",
             "</script>" })
   int saveExcelData(@Param("resKnowledgeNodeDTOs") List<ResKnowledgeNodeDTO> resKnowledgeNodeDTOs);

    /**
     * 根据科目id和二级项目id获取已有知识树的省份列表
     * @param subjectId
     * @param projectSecondId
     * @return
     */
   @Select({
       "SELECT a.province_id" ,
       "from t_knowledge_tree_province_proj2nd_rel a" ,
       "INNER JOIN t_knowledge_tree b on a.knowledge_tree_id = b.id and b.delete_flag = 0" ,
       "where a.delete_flag = 0 " ,
       "and b.subject_id = #{subjectId} " ,
       "and a.project_2nd_id = #{projectSecondId}"
   })
   List<Integer> queryProvinceIdsBySubjectIdAndPr2Id(@Param("subjectId") Integer subjectId, @Param("projectSecondId") Integer projectSecondId);
    /**
     * 根据id获取省份信息
     * @param id
     * @return
     */
   @Select({
       "select id,province_name as name from sch_local_province where delete_flag = 0 and id = #{id}"
   })
   ResProvinceDTO getProvinceById(@Param("id") Integer id);
    /**
     * 根据id查询二级项目信息
      * @param id
     * @return
     */
   @Select({
        "select id,name from ent_proj_2nd where delete_flag = 0 and id = #{id}"
    })
   ResProjectSecondDTO getProjectSecondById(@Param("id") Integer id);
    /**
     * 删除知识点
     * @param knowledgeNodeId
     * @return
     */
   @Update({
           "UPDATE t_knowledge_node set delete_flag = 1 where id = #{knowledgeNodeId}"
   })
   int deleteKnowledgeNode(@Param("knowledgeNodeId") Integer knowledgeNodeId);

    /**
     * 修改语言类型
     * @return
     */
    @Update({
            "UPDATE t_knowledge_tree SET language_type = #{languageType} where id = #{id}"
    })
    int changeKnowledgeLanguageType(@Param("id") Integer id,@Param("languageType") Integer languageType);

    /**
     * 禁用/启用知识点
     * @param knowledgeNodeId
     * @param invalidFlag
     * @return
     */
   @Update({
           "UPDATE t_knowledge_node SET invalid_flag = #{invalidFlag} where id = #{knowledgeNodeId}"
   })
   int changeKnowledgeNodeStatus(@Param("knowledgeNodeId") Integer knowledgeNodeId,@Param("invalidFlag") Integer invalidFlag);

    /**
     * 根据知识点编号查询知识点
     * @param parentNodeId
     * @param serialNumber
     * @return
     */
    @Select({
            "<script>" ,
            "SELECT id,knowledge_tree_id as knowledgeTreeId,serial_number as serialNumber," ,
            "name,description,`level`,parent_node_id as parentNodeId,outline_requirement as outlineRequirement," ,
            "invalid_flag as invalidFlag,create_time as createTime,creator,operator,update_time as updateTime,delete_flag as deleteFlag " ,
            "from t_knowledge_node " ,
            "where delete_flag = 0 and serial_number = #{serialNumber} and knowledge_tree_id = #{knowledgeTreeId}" ,
            "<if test=\"parentNodeId != null\">and parent_node_id = #{parentNodeId}</if>" ,
            "<if test=\"parentNodeId == null\">and parent_node_id is null</if>" ,  // 章节点
            "limit 1" ,
            "</script>"
    })
    KnowledgeNode queryExistingKnowledgeNodeBySerialNumber(@Param("knowledgeTreeId") Integer knowledgeTreeId, @Param("parentNodeId") Integer parentNodeId,@Param("serialNumber") String serialNumber);

    /**
     * 根据id查询知识点
     * @param id
     * @return
     */
   @Select({
           "SELECT id,knowledge_tree_id as knowledgeTreeId,serial_number as serialNumber," ,
           "name,description,`level`,parent_node_id as parentNodeId,outline_requirement as outlineRequirement," ,
           "invalid_flag as invalidFlag,create_time as createTime,creator,operator,update_time as updateTime,delete_flag as deleteFlag " ,
           "from t_knowledge_node where delete_flag = 0 and id = #{id}"
   })
   KnowledgeNode getKnowledgeNodeById(@Param("id") Integer id);

    /**
     * 校验当前末级知识点是否挂题
     * @param knowledgeNodeId
     * @return
     */
   @Select({
           "SELECT count(*) from (" ,
               "SELECT b.* from t_knowledge_node a INNER JOIN t_question_main b on a.id = b.main_node_id and b.current_version = 1 and b.delete_flag = 0 where a.delete_flag = 0 and a.id = #{knowledgeNodeId} " , // and b.invalid_flag = 0 and b.current_version = 1
               "UNION " ,
               "SELECT b.* from t_knowledge_node a INNER JOIN t_question_main b on a.id = b.vice_node_id_1 and b.current_version = 1 and b.delete_flag = 0 where a.delete_flag = 0 and a.id = #{knowledgeNodeId} " , // and b.invalid_flag = 0 and b.current_version = 1
               "UNION" ,
               "SELECT b.* from t_knowledge_node a INNER JOIN t_question_main b on a.id = b.vice_node_id_2 and b.current_version = 1 and b.delete_flag = 0 where a.delete_flag = 0 and a.id = #{knowledgeNodeId} " , // and b.invalid_flag = 0 and b.current_version = 1
           ")c"
    })
   int checkQuestionByNodeId(@Param("knowledgeNodeId") Integer knowledgeNodeId);

    /**
     * 校验当前知识树是否挂题
     * @param knowledgeTreeId
     * @return
     */
   @Select({
           "SELECT count(*) FROM t_question_main where knowledge_tree_id = #{knowledgeTreeId} and delete_flag = 0" // and invalid_flag = 0 and current_version = 1
   })
   int checkQuestionByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId);

    /**
     * 根据知识树id获取已有关联关系数量
     * @param knowledgeId
     * @return
     */
   @Select({
            "SELECT count(*) from t_knowledge_tree_province_proj2nd_rel where knowledge_tree_id = #{knowledgeId} and delete_flag = 0"
    })
   int selectRelCountByKnowledgeTreeId(@Param("knowledgeId") Integer knowledgeId);

   @Select({
           "SELECT province_name from sch_local_province where id = #{id} and delete_flag = 0"
   })
   String getProvinceNameById(@Param("id") Integer id);

   @Select({
           "SELECT `name` from ent_proj_2nd where id = #{id} and delete_flag = 0"
   })
   String getProjectSecondNameById(@Param("id") Integer id);

    /**
     * 插入知识树修改记录
     * @param knowledgeTreeId
     * @param oprator
     * @param description
     * @return
     */
    @Insert({
            "insert into t_knowledge_tree_edit_log(knowledge_tree_id,operator,description) " ,
            "values (#{knowledgeTreeId},#{operator}, #{description})"
    })
    int insertKnowledgeTreeEditLog(@Param("knowledgeTreeId") Integer knowledgeTreeId, @Param("operator") String oprator, @Param("description") String description);

    /**
     * 查询省份在当前科目下的知识树
     * @param subjectId
     * @param provinceId
     * @return
     */
    @Select({
            "SELECT a.id" ,
            "from t_knowledge_tree a" ,
            "INNER JOIN t_knowledge_tree_province_proj2nd_rel b on a.id = b.knowledge_tree_id" ,
            "INNER JOIN t_knowledge_node c on a.id = c.knowledge_tree_id and c.`level` = 1" ,
            "where a.delete_flag = 0 and b.delete_flag = 0 and c.delete_flag = 0 " ,
            "and a.subject_id = #{subjectId} and b.province_id = #{provinceId} and b.project_2nd_id is not null" , // 此处只需查询不指定二级项目的知识树
            "GROUP BY a.id"
    })
    List<Integer> queryValidKnowledgeTreeIdsBySubjectIdAndProvinceId(@Param("subjectId") Integer subjectId, @Param("provinceId") Integer provinceId);

    /**
     * 查询该科目下的文字题、判断论述题是否可以开启AI判分
     * @param subjectId
     * @return
     */
    @Select({
            "SELECT id,subject_id subjectId,judge_essay_ai_flag judgeEssayAIFlag,essay_ai_flag essayAIFlag,order_fill_blank_ai_flag orderFillBlankAIFlag, disorder_fill_blank_ai_flag disorderFillBlankAIFlag ,",
            "create_time createTime,update_time updateTime,operator,delete_flag deleteFlag ",
            "from t_subject_essay_ai_rel",
            " where subject_id = #{subjectId} and delete_flag = 0",
            "LIMIT 1"
    })
    SubjectAIRel selectAIJudgementBySubjectId(@Param("subjectId") Integer subjectId);

    /**
     * 批量插入知识点
     * @param list
     * @return
     */
    @Insert({
            "<script>",
            "insert into t_knowledge_node (knowledge_tree_id,serial_number,name,level,parent_node_id,creator,operator,outline_requirement,description, invalid_flag)",
            "values",
            "<foreach collection=\"list\" item=\"item\" separator=\",\">",
            "(#{item.knowledgeTreeId},#{item.serialNumber},#{item.name},#{item.level},#{item.parentNodeId},",
            "#{item.creator},#{item.operator},#{item.outlineRequirement},#{item.description}, #{item.invalidFlag})",
            "</foreach>",
            "</script>"})
//    @Options(useGeneratedKeys=true, keyProperty="list[0].id", keyColumn="id")
    @SelectKey(before = false, keyProperty = "list[0].id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int batchInsertKnowNodes(@Param("list")List<KnowledgeNode> list);

    /**
     * 根据知识点id批量查询知识点
     * @param list
     * @return
     */
    @Select({
            "<script>",
            "SELECT id,knowledge_tree_id as knowledgeTreeId,serial_number as serialNumber," ,
            "name,description,`level`,parent_node_id as parentNodeId,outline_requirement as outlineRequirement," ,
            "invalid_flag as invalidFlag,create_time as createTime,creator,operator,update_time as updateTime,delete_flag as deleteFlag " ,
            "from t_knowledge_node where delete_flag = 0 and id in(",
            "<foreach collection=\"list\" item=\"item\" separator=\",\">",
            "#{item}",
            "</foreach>)",
            "</script>"})
    List<KnowledgeNode> batchQueryKnowledgeNodesById(@Param("list")List<Integer> list);

    /**
     * 批量查询知识点子节点的个数，用于判断知识点是否是末级知识点
     * @param nodeList
     * @return
     */
    @Select({
            "<script>",
            "SELECT count(*)" ,
            "from t_knowledge_node where delete_flag = 0 and parent_node_id in(",
            "<foreach collection=\"nodeList\" item=\"item\" separator=\",\">",
            "#{item.id}",
            "</foreach>)",
            "</script>"})
    int batchQueryChildrenNodes(@Param("nodeList") List<KnowledgeNode> nodeList);

    /**
     * 批量查询挂题的知识点，用于校验知识点集合中有没有某一个知识点下挂有题目
     * @param idList
     * @return
     */
    @Select({
            "<script>",
            "SELECT a.id,a.knowledge_tree_id as knowledgeTreeId,a.serial_number as serialNumber," ,
            "a.name,a.description,a.level,a.parent_node_id as parentNodeId,a.outline_requirement as outlineRequirement," ,
            "a.invalid_flag as invalidFlag,a.create_time as createTime,a.creator,a.operator,a.update_time as updateTime,a.delete_flag as deleteFlag " ,
            "from t_knowledge_node a INNER JOIN t_question_main b on (a.id = b.vice_node_id_1 OR a.id = b.main_node_id OR a.id = b.vice_node_id_2)",
            "where a.delete_flag = 0 and b.delete_flag = 0 and b.current_version = 1 and a.id in(",
            "<foreach collection=\"idList\" item=\"item\" separator=\",\">",
            "#{item}",
            "</foreach>)",
            "</script>"})
    List<KnowledgeNode> batchCheckQuestionByNodeId(@Param("idList") List<Integer> idList);

    /**
     * 根据知识点id批量删除知识点
     * @param idList
     * @return
     */
    @Update({
            "<script>",
            "UPDATE t_knowledge_node set delete_flag = 1 where id in(",
            "<foreach collection=\"idList\" item=\"item\" separator=\",\">",
            "#{item}",
            "</foreach>)",
            "</script>"})
    int batchDeleteKnowledgeNodes(@Param("idList") List<Integer> idList);

    @Select({"<script>",
            "SELECT a.id, a.serial_number as serialNumber, a.knowledge_tree_id as knowledgeTreeId, a.`name`, a.`level`,",
            "IF(a.invalid_flag = 0 AND a.delete_flag = 0, 0, 1) as invalidFlag, a.parent_node_id as parentNodeId, a.description, a.outline_requirement as outlineRequirementCode, b.version",
            "FROM t_knowledge_node as a",
            "INNER JOIN (",
            "  SELECT * FROM (",
            "    SELECT knowledge_node_id, version",
            "    FROM t_knowledge_multi_version_node",
            "    WHERE knowledge_tree_id = #{knowledgeTreeId} and `LEVEL` = #{level}",
            "    ORDER BY knowledge_node_id ASC, version DESC",
            "  ) as t",
            "  GROUP BY t.knowledge_node_id",
            ") as b ON b.knowledge_node_id = a.id",
            "WHERE a.knowledge_tree_id = #{knowledgeTreeId} and a.`LEVEL` = #{level}",
            "<if test=\"parentNodeId != null\">and a.parent_node_id = #{parentNodeId}</if>",
            "<if test=\"parentNodeId == null\">and a.parent_node_id is NULL</if>",
            "</script>"})
    List<ResKnowledgeNodeDTO> queryChildrenNodeWithInvalidAndDelete(@Param("knowledgeTreeId") Integer knowledgeTreeId,@Param("level") Integer level, @Param("parentNodeId") Integer parentNodeId);

    @Delete({"UPDATE t_knowledge_tree_charge SET delete_flag = 1 WHERE knowledge_tree_id = #{knowledgeTreeId}"})
    void deleteKnowledgeCharge(@Param("knowledgeTreeId") Integer knowledgeTreeId);

    @Select({"SELECT knowledge_tree_id",
            "FROM t_knowledge_tree_charge",
            "WHERE charge_teacher_email = #{email} AND delete_flag = 0"})
    Set<Integer> listKnowledgeChargeTreeId(@Param("email") String email);

    @Select({"SELECT b.subject_id",
            "FROM t_knowledge_tree_charge as a",
            "INNER JOIN t_knowledge_tree as b ON b.id = a.knowledge_tree_id AND b.delete_flag = 0",
            "WHERE a.charge_teacher_email = #{email} AND a.delete_flag = 0"})
    Set<Integer> listKnowledgeChargeSubjectId(@Param("email") String email);

    @Select({"<script>",
            "SELECT b.id, b.`name`",
            "FROM t_knowledge_tree as a ",
            "INNER JOIN ent_subject as b ON b.id = a.subject_id AND b.delete_flag = 0",
            "WHERE a.id IN",
            "<foreach collection=\"treeIdList\" item=\"treeId\" open=\"(\" close=\")\" separator=\",\">",
            "#{treeId}",
            "</foreach>",
            "</script>"})
    List<ResSubjectDTO> listSubjectByTreeId(@Param("treeIdList") Collection<Integer> treeIdList);

}
