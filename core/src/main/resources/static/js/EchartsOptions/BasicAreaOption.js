/**
 *  content的数据结构
 * {
 *     data:[],           //  数据
 *     dataUnit:"",       //  数据单位
 *     dataUnitSymbol:"", //  数据单位符号
 *     date:[],
 *     dataMax:"",
 *     dataMin:""
 *}
 */
function basicAreaOption(content){
    return option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer:{
                        label:{
                            formatter: function (params){
                                return formatDate(params.value)
                            }
                        }
                    }
                },
                xAxis: {
                    name: '时间',
                    type: 'category',
                    boundaryGap: false,
                    data: content.date,
                    axisLabel: {
                        formatter: (val) => formatDateByFormat(val,"dd号 hh:mm:ss")
                    }
                },
                yAxis: {
                    type: 'value',
                    name: content.dataUnit,
                    min: content.dataMin,
                    max: content.dataMax,
                    axisLabel: {
                        formatter: '{value} '+content.dataUnitSymbol
                    }
                },
                series: [
                    {
                        name:content.dataUnit,
                        data: content.data,
                        type: 'line',
                        smooth:true,
                        areaStyle: {}
                    }
                ]
            };
}
function basicAreaRefreshDataOption(content){
    return option ={
                xAxis: {
                    data: content.date
                },
                yAxis: {
                    name: content.dataUnit,
                    axisLabel: {
                        formatter: '{value} '+content.dataUnitSymbol
                    }
                },
                series: [
                    {
                        name:content.dataUnit,
                        data: content.data,
                    }
                ]
            }
}

