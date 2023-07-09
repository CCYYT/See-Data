var userThis;
function commonTipInit(that){
    userThis = that;
}

function commonSuccessTip(msg){
    if(userThis != null){
        userThis.$message.success(msg);
    }
}

function commonErrorTip(msg){
    if(userThis != null){
        userThis.$message.error(msg);
    }
}

function commonInfoTip(msg){
    if(userThis != null){
        userThis.$message.info(msg);
    }
}
/**
 * 通用提示框
 * @param msg    提示框消息
 * @param title  提示框标题
 * @param type   提示框类型
 * @param determineCallBack   确定后的回调函数
 * @param cancelCallBack      取消后的回调函数
 * */
function commonConfirmTip(msg,title,type,determineCallBack,cancelCallBack){
    if(userThis != null) {
        //弹出提示框
        userThis.$confirm(msg, title, {
            type: type
        }).then(() => {
            //确定后调用回调函数
            determineCallBack();
        }).catch(() => {
            //取消后调用回调函数
            cancelCallBack();
        });
    }else{
        // 未开启提示框直接删除
        determineCallBack();
    }
}
