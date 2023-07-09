/**
 * chartMap数据结构：{
 *              chartName:{
 *                  echarts:echarts
 *                  refreshOption:function()
 *                  }
 *                  ...
 *              }
 *              echarts:        echarts对象
 *              refreshData:    数据刷新函数
* */
var chartMap={};

/**
 * 图表构建器
 * @param chartInfo    图表信息
 * chartInfo数据结构：{
 *                     chartName:{
 *                         elementId:"",
 *                         chartSort:"",
 *                         content:{}
 *                     },
 *                     ...
 *                  }
 *                  chartName:  图表名称   唯一
 *                  elementId:  DOM元素id
 *                  chartSort:  图表类型
 *                  content:    图表参数
* */
function chartBuilder(chartInfo){
    var chartDom,option,chart,refreshOptionFunction;
    for (let entry of Object.entries(chartInfo)) {
        if(chartMap[entry[0]] == null){
            chartDom = document.getElementById(entry[1].elementId);
            chart = echarts.init(chartDom);
            switch (entry[1].chartSort){
                case "basicArea":
                    option = basicAreaOption(entry[1].content);
                    refreshOptionFunction = basicAreaRefreshDataOption;
                    break;
                case "gaugeTemperatureOption":
                    option = gaugeTemperatureOption(entry[1].content)
                    refreshOptionFunction = gaugeTemperatureRefreshDataOption;
                    break;
                case null:return;
                default:console.log("没有类型为： "+entry[1].chartSort+" 的图表");
            }
            chartMap[entry[0]] = {"echarts":chart,"refreshOption":refreshOptionFunction};
            option && chart.setOption(option);
        }
    }
}

/**
 * 图表加载数据时
 * @param chartName 图表名字
 *
 * */
function dataLoading(chartName){
    var chart = chartMap[chartName];
    if(chart != null){
        chart.echarts.showLoading();
    }else {
        console.log("dataLoading: 没有图表名为："+chartName+"的图表");
    }
}

/**
 * 图表加载数据时
 * @param chartName 图表名字
 *
 * */
function dataLoaded(chartName){
    var chart = chartMap[chartName];
    if(chart != null){
        chart.echarts.hideLoading();
    }else {
        console.log("dataLoaded: 没有图表名为："+chartName+"的图表");
    }
}

/**
 * 图表数据刷新
 * @param chartName 图表名字
 * @param content   数据
 *
 * content的数据结构
 * {
 *         data:[],           //  数据
 *         dataUnit:"",       //  数据单位
 *         dataUnitSymbol:"", //  数据单位符号
 *         date:[]            // 时间
 * }
 *
* */
function refreshData(chartName,content){
    var chart = chartMap[chartName],option;
    if(chart != null){
        option = chart.refreshOption(content);
        option && chart.echarts.setOption(option);
    }else {
        console.log("refreshData: 没有图表名为："+chartName+"的图表");
    }
}

/**
 * 图表大小更新
 * @param chartInfo
 *
 * */
function chartResize(chartInfo){
    var chart;
    for (let entry of Object.entries(chartInfo)) {
        chart = chartMap[entry[0]];
        if(chart != null){
            chart.echarts.resize();
        }
    }
}
