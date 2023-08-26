package com.colson.dal.dao;

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

}
