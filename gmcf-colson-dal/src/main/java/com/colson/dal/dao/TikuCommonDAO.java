package com.colson.dal.dao;

import com.colson.dal.dto.ResProvinceDTO;
import com.colson.dal.dto.ResSubjectAndProvinceDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by PC on 2017/12/29.
 */
@Repository
public interface TikuCommonDAO {

    /**
     * 代码描述 : 根据科目、省份、二级项目查找知识树
     *
     * @author songbowen
     * modified by : [变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * date :  2022/12/05
     */
    @Select({"<script>",
            "select a.id from t_knowledge_tree as a ",
            "INNER JOIN t_knowledge_tree_province_proj2nd_rel as b ON b.knowledge_tree_id = a.id AND b.delete_flag = 0",
            "JOIN ent_subject as c on a.subject_id=c.id and c.delete_flag=0",
            "where a.delete_flag = 0 ",
            "<if test=\"subjectIdList != null and subjectIdList.size > 0 \"> ",
            "AND c.id in",
            "<foreach item=\"item\" index=\"index\" collection=\"subjectIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"provinceIdList != null and provinceIdList.size > 0 \"> ",
            "AND b.province_id in",
            "<foreach item=\"item\" index=\"index\" collection=\"provinceIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"projectSecondId != null\"> AND b.project_2nd_id = #{projectSecondId} </if>",
            "</script>"})
    Set<Integer> selectTreeIdList(@Param("subjectIdList") List<Integer> subjectIdList, @Param("provinceIdList") List<Integer> provinceIdList, @Param("projectSecondId") Integer projectSecondId);



    /**
     * 根据科目查询知识树id列表
     * @param subjectId
     * @return
     */
    @Select({
            "SELECT b.id" ,
            "from t_knowledge_tree_province_proj2nd_rel a" ,
            "INNER JOIN t_knowledge_tree b on a.knowledge_tree_id = b.id" ,
            "WHERE a.delete_flag = 0 AND b.delete_flag = 0 AND b.subject_id = #{subjectId}" ,
            "GROUP BY b.id"
    })
    List<Integer> getKnowledgeTreeIdsBySubjectId(@Param("subjectId") Integer subjectId);


    /**
     * 根据考期区间获取真题考期列表
     * @param beginSession
     * @param endSession
     * @return
     */
    @Select({
            "SELECT esn.id",
            "FROM t_exam_session esn",
            "INNER JOIN t_question_main_exam_session_province sq ON sq.exam_session = esn.id AND sq.delete_flag = 0",
            "INNER JOIN t_question_main qm ON qm.id = sq.t_question_main_id ",
            "WHERE esn.`session` between #{beginSession} and #{endSession}",
            "and qm.source_type='REAL_QUESTION' ",
            "and esn.delete_flag = 0 and qm.delete_flag = 0 and qm.invalid_flag = 0 and qm.current_version = 1 ",
            "group by esn.id",
            "ORDER BY esn.session DESC"
    })
    List<Integer> getExamSessionIdsBySession(@Param("beginSession") String beginSession, @Param("endSession") String endSession);

    /**
     * 根据考期区间获取真题考期列表
     * @return
     */
    @Select({
            "SELECT c.id",
            "FROM ent_subject as c where c.delete_flag=0",
            "group by c.id",
    })
    List<Integer> getAllSubjectId();

    /**
     * 获取所有省份列表（不包含全国）
     *
     * @return
     */
    @Select({
            "select lp.id,lp.province_name name from sch_local_province lp where lp.delete_flag=0 and lp.province_name != '全国'"
    })
    List<ResProvinceDTO> getAllProvinces();

    /**
     * 根据科目id，省份id和二级项目id获取真题归属省份列表
     * @param knowledgeTreeId
     * @return
     */
    @Select({
            "SELECT lp.id,lp.province_name name ",
            "FROM sch_local_province lp",
            "INNER JOIN t_question_main_exam_session_province sq ON sq.exam_province = lp.id AND sq.delete_flag = 0",
            "INNER JOIN t_question_main qm ON qm.id = sq.t_question_main_id",
            "WHERE qm.knowledge_tree_id = #{knowledgeTreeId}",
            "and qm.source_type='REAL_QUESTION'",
            "and lp.delete_flag=0 and qm.delete_flag=0 and qm.invalid_flag=0 and qm.current_version = 1",
            "group by lp.id"
    })
    List<ResProvinceDTO> getProvincesByknowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId);

    /**
     * 根据知识树id查询省份列表
     * @param knowledgeTreeId
     * @return
     */
    @Select({
            "SELECT a.id provinceId ,c.subject_id subjectId " ,
            "from sch_local_province a " ,
            "INNER JOIN t_knowledge_tree_province_proj2nd_rel b on a.id = b.province_id " ,
            "INNER JOIN t_knowledge_tree c on c.id = b.knowledge_tree_id " ,
            "where a.delete_flag = 0 and b.delete_flag = 0 and c.delete_flag = 0" ,
            "and c.id = #{knowledgeTreeId}" ,
            "GROUP BY a.id, c.subject_id"
    })
    List<ResSubjectAndProvinceDTO> queryByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId);

}
