package com.colson.dal.dto;

import lombok.Data;

/**
 * @author: caoxuexing
 * @description:
 * @date: create in 11:28 2018/2/7
 * @modified By:
 */
@Data
public class SimilarMapKeyDTO {

    private Integer subjectId;
    private String type;

    public SimilarMapKeyDTO(){
    }
    public SimilarMapKeyDTO(Integer subjectId,String type){
        this.subjectId=subjectId;
        this.type=type;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        SimilarMapKeyDTO that = (SimilarMapKeyDTO) o;
        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) {return false;}
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
