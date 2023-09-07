package com.sunlands.common.dao;

import com.sunlands.common.datasource.DataSource;
import com.sunlands.common.datasource.DataSourceEnum;
import com.sunlands.common.dto.*;
import com.sunlands.mainpoolsimilarity.dto.KnowledgeTreeTypeDTO;
import com.sunlands.question.dmo.QuestionMain;
import com.sunlands.transfer.dmo.TempQuestionMainDMO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: caoxuexing
 * @description:
 * @date: create in 23:57 2018/2/2
 * @modified By:
 */
@Repository
public interface ValuableBookDAO {

    @Select({
            "select id, subject_id as subjectId, province_id as provinceId, knowledge_tree_id as knowledgeTreeId, exam_session as examSession, file_link as fileLink, file_short_link as fileShortLink ",
            "FROM t_valuable_book_file_info where subject_id=#{subjectId} ",
            "and province_id=#{provinceId} and delete_flag=0 order by create_time desc limit 1"
    })
    ValuableBookFileInfoDTO getValuableBookBySubjectIdAndProvinceId(@Param("subjectId") Integer subjectId,
                                                      @Param("provinceId") Integer provinceId);

    @Select({
            "select id, subject_id as subjectId, province_id as provinceId, knowledge_tree_id as knowledgeTreeId, exam_session as examSession, file_link as fileLink, file_short_link as fileShortLink ",
            "FROM t_valuable_book_file_info where subject_id=#{subjectId} ",
            "and province_id=#{provinceId} and knowledge_tree_id=#{knowledgeTreeId} and delete_flag=0 limit 1"
    })
    ValuableBookFileInfoDTO getValuableBookBySubjectAndProvinceAndKnowledgeTree(@Param("subjectId") Integer subjectId, @Param("provinceId") Integer provinceId, @Param("knowledgeTreeId") Integer knowledgeTreeId);


                                                                                @Select({
            "select id, subject_id as subjectId, province_id as provinceId, knowledge_tree_id as knowledgeTreeId, exam_session as examSession, file_link as fileLink, file_short_link as fileShortLink ",
            "FROM t_valuable_book_file_info where knowledge_tree_id=#{knowledgeTreeId} ",
            "and delete_flag=0 order by create_time desc limit 1"
    })
    ValuableBookFileInfoDTO getValuableBookByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId);

    /**
     * 代码描述 : 插入通关宝典文件信息表
     *
     * @param valuableBookFileInfoDTO
     * @return void
     * @author subo
     * modified by : [变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * date :  2023/1/20
     */
    @DataSource(DataSourceEnum.WRITE)
    @Insert({"<script>",
            "insert into t_valuable_book_file_info (province_id, ",
            "subject_id, knowledge_tree_id, exam_session, file_link, file_short_link)",
            "values ",
            "(#{valuableBookFileInfoDTO.provinceId,jdbcType=INTEGER},#{valuableBookFileInfoDTO.subjectId,jdbcType=INTEGER},",
            "#{valuableBookFileInfoDTO.knowledgeTreeId,jdbcType=INTEGER},#{valuableBookFileInfoDTO.examSession},",
            "#{valuableBookFileInfoDTO.fileLink,jdbcType=VARCHAR},#{valuableBookFileInfoDTO.fileShortLink,jdbcType=VARCHAR}",
            ")",
            "</script>"})
    int insertValuableBookFileInfoDTO(@Param("valuableBookFileInfoDTO") ValuableBookFileInfoDTO valuableBookFileInfoDTO);

}
