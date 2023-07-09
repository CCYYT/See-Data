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
function gaugeTemperatureOption(content){
    return option = {
        series: [
            {
                type: 'gauge',
                center: ['50%', '70%'],
                radius:'100%',
                startAngle: 200,
                endAngle: -20,
                min: content.dataMin,
                max: content.dataMax,
                splitNumber: 10,
                itemStyle: {
                    color: '#FFAB91'
                },
                progress: {
                    show: true,
                    width: 30
                },
                pointer: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        width: 30
                    }
                },
                axisTick: {
                    distance: -45,
                    splitNumber: 5,
                    lineStyle: {
                        width: 2,
                        color: '#999'
                    }
                },
                splitLine: {
                    distance: -52,
                    length: 14,
                    lineStyle: {
                        width: 3,
                        color: '#999'
                    }
                },
                axisLabel: {
                    distance: -20,
                    color: '#999',
                    fontSize: 20
                },
                anchor: {
                    show: false
                },
                title: {
                    show: false
                },
                detail: {
                    valueAnimation: true,
                    width: '60%',
                    lineHeight: 40,
                    borderRadius: 8,
                    offsetCenter: [0, '-15%'],
                    fontSize: 60,
                    fontWeight: 'bolder',
                    formatter: '{value} '+content.dataUnitSymbol,
                    color: 'inherit'
                },
                data: [
                    {
                        value: content.data[content.data.length-1]
                    }
                ]
            },
            {
                type: 'gauge',
                center: ['50%', '70%'],
                radius:'100%',
                startAngle: 200,
                endAngle: -20,
                min: content.dataMin,
                max: content.dataMax,
                itemStyle: {
                    color: '#FD7347'
                },
                progress: {
                    show: true,
                    width: 8
                },
                pointer: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                detail: {
                    show: false
                },
                data: [
                    {
                        value: content.data[content.data.length-1]
                    }
                ]
            }
        ]
    }
}
function gaugeTemperatureRefreshDataOption(content){
    return option ={
        series: [
            {
                detail: {
                    formatter: '{value} '+content.dataUnitSymbol
                },
                data: [
                    {
                        value: content.data[content.data.length-1]
                    }
                ]
            },
            {
                data: [
                    {
                        value: content.data[content.data.length-1]
                    }
                ]
            }
        ]
    };
}

