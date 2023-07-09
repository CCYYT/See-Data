/**
 * 时间格式化
* */

function formatDate(value){
    if (value == null) {
        return null;
    }
    var dt = new Date(value);
    let year = dt.getFullYear();
    let month = dt.getMonth()+1;
    let day = dt.getDate();
    let hour = dt.getHours();
    let minute = dt.getMinutes();
    return year+"年"+month+"月"+day+"日 "+hour+":"+minute;
}

/**
 * 指定格式 格式化时间
* */
function formatDateByFormat(value,format){
    if (value == null) {
        return null;
    }
    var dt = new Date(value);
    let year = dt.getFullYear();
    let month = dt.getMonth()+1;
    let day = dt.getDate();
    let hour = dt.getHours();
    let minute = dt.getMinutes();
    let second = dt.getSeconds();
    return format
            .replace("yyyy",year)
            .replace("MM",month)
            .replace("dd",day)
            .replace("hh",hour)
            .replace("mm",minute)
            .replace("ss",second);
}
/**
 * 用户类型格式化
* */
function formatUserType(row,column){
    // 获取单元格数据
    let value = row[column.property];
    return formatUserType(value);
}
function formatUserType(value){
    if (value == null) {
        return null;
    }
    switch (value){
        case 0: return "普通用户"; break;
        case 1: return "区域管理员"; break;
        case 2: return "用户组管理员"; break;
        default: return "未知角色";
    }
}


/**
 * 用户状态格式化
* */
function formatUserStatus(row,column){
    // 获取单元格数据
    let value = row[column.property];
    return formatUserStatus(value);
}
function formatUserStatus(value){
    if (value == null) {
        return null;
    }
    switch (value){
        case 0:return "启用";break;
        case 1:return "未启用";break;
        default: return "未知状态";
    }
}

/**
 * 设备状态格式化
 */
function formatDeviceStatus(row,column){
    let value = row[column.property];
    return formatDeviceStatus(value);
}
function formatDeviceStatus(value){
    if (value == null) {
        return null;
    }
    switch (value){
        case 0:return "离线";break;
        case 1:return "在线";break;
        case 2:return "未启用";break;
        default: return "未知状态";
    }
}

/**
 * 预警等级格式化
* */
function formatWarningLevel(value){
    if (value == null) {
        return null;
    }
    switch (value){
        case 0:return "普通";break;
        case 1:return "警告";break;
        case 2:return "严重";break;
        case 3:return "危险";break;
        default: return "未知状态";
    }
}

/**
 * 预警规则格式化
 * */
function formatWarningRules(value){
    if (value == null) {
        return null;
    }
    switch (value){
        case 0:return "小于";break;
        case 1:return "等于";break;
        case 2:return "大于";break;
        default: return "未知状态";
    }
}
/**
 * 提示方式格式化
* */
function formatReminderMethod(value) {
    if (value == null) {
        return null;
    }
    switch (value) {
        case 0:return "无";break;
        case 1:return "邮件";break;
        default:return "未知状态";
    }
}
/**
 * 警报处理状态格式化
* */
function formatWarningStatus(value)
{
    if (value == null) {
        return null;
    }
    switch (value) {
        case 0:return "待处理";break;
        case 1:return "已处理";break;
        default:return "未知状态";
    }
}

/**
 * 编码格式 格式化
* */
function formatCommandCodec(value)
{
    if (value == null) {
        return null;
    }
    switch (value) {
        case 0:return "无";break;
        case 1:return "JSON";break;
        default:return "未知状态";
    }
}
/**
 * 编码格式 格式化
* */
function formatChartSort(value)
{
    if (value == null) {
        return null;
    }
    switch (value) {
        case "basicArea":return "面积图";break;
        case "gaugeTemperatureOption":return "气温仪表图";break;
        default:return "未知图表类型";
    }
}
