package com.colson.dal.dto;


public class ResProvinceDTO {
    private Integer id;
    private String name;
    private Integer optionalFlag = 1; // 1可选，0不可选；默认可选

    public Integer getOptionalFlag() {
        return optionalFlag;
    }

    public void setOptionalFlag(Integer optionalFlag) {
        this.optionalFlag = optionalFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResProvinceDTO that = (ResProvinceDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
