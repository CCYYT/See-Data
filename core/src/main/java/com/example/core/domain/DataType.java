package com.example.core.domain;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 设备数据类型信息(DataType)实体类
 *
 * @author makejava
 * @since 2023-06-30 15:38:22
 */

@ApiModel("设备数据类型信息(DataType)实体类")
public class DataType implements Serializable {
    private static final long serialVersionUID = 689697599079786299L;
    @ApiModelProperty(value = "设备数据类型id")
    private Integer id;
    @ApiModelProperty(value = "设备数据类型")
    private String dataType;
    @ApiModelProperty(value = "设备数据单位")
    private String dataUnit;
    @ApiModelProperty(value = "设备数据单位符号")
    private String dataUnitSymbol;
    @ApiModelProperty(value = "数据最大值")
    private String dataMax;
    @ApiModelProperty(value = "数据最小值")
    private String dataMin;
    @ApiModelProperty(value = "图表类型")
    private String chartSort;
    @ApiModelProperty(value = "设备数据类型描述")
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }

    public String getDataUnitSymbol() {
        return dataUnitSymbol;
    }

    public void setDataUnitSymbol(String dataUnitSymbol) {
        this.dataUnitSymbol = dataUnitSymbol;
    }

    public String getDataMax() {
        return dataMax;
    }

    public void setDataMax(String dataMax) {
        this.dataMax = dataMax;
    }

    public String getDataMin() {
        return dataMin;
    }

    public void setDataMin(String dataMin) {
        this.dataMin = dataMin;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getChartSort() {
        return chartSort;
    }

    public void setChartSort(String chartSort) {
        this.chartSort = chartSort;
    }
}

