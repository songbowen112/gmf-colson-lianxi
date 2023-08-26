package com.colson.dal.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 试卷内试题查重 dto
 * @author chenliang
 * @date 2021-12-03 17:31
 */
@Data
public class SimilarityQuestionPaperDTO implements Comparable<SimilarityQuestionPaperDTO>{

    private Integer sequence;
    private String questionCode;
    private Integer similarSequence;
    private String similarQuestionCode;
    private String similarity;

    @Override
    public int compareTo(SimilarityQuestionPaperDTO o) {
        if(StringUtils.isBlank(o.getSimilarity())) {
            o.setSimilarity("0%");
        }
        if(StringUtils.isBlank(this.similarity)) {
            this.similarity = "0%";
        }
        Integer other = Integer.parseInt(o.getSimilarity().substring(0, o.getSimilarity().length()-1));
        Integer cur = (Integer.parseInt(this.similarity.substring(0, this.similarity.length()-1)));
        if(other.intValue() > cur.intValue()) {
            return 1;
        } else if(cur.intValue() > other.intValue()) {
            return -1;
        } else {
            if(Objects.isNull(sequence)) {
                this.sequence = 0;
            }
            Integer oSequence = !Objects.isNull(o.getSequence()) ? o.getSequence() : 0;
            if(this.sequence.intValue() > oSequence.intValue()) {
                return 1;
            } else if(oSequence.intValue() > this.sequence.intValue()) {
                return -1;
            } else {
                Integer sSequence = !Objects.isNull(this.similarSequence) ? this.similarSequence : 0;
                Integer sOSequence = !Objects.isNull(o.getSimilarSequence()) ? o.getSimilarSequence() : 0;
                return sSequence.compareTo(sOSequence);
            }
        }
    }

}
