/**
 * 处理响应值
 * 只关心业务 不关心样式
 * */


/**
 * 启用通用通知
 * @param that 调用者的this
 * */
function commonRequestEnableTip(that){
    commonTipInit(that);
}

/**
 * 通用分页查询请求
 * @param uri                    请求地址
 * @param pageNum                请求页码
 * @param page                   请求条件(回调 请求成功会将值添加到page中)
 * @param successCallBack(res)   请求成功时的回调函数(res 请求结果)
 * @param failCallBack(err)      请求失败时的回调函数(err 请求结果)
 * */
function commonGetPageRequest(uri,pageNum,page,successCallBack,failCallBack) {
    page.page = pageNum-1 < 0?0:pageNum-1;
    //发送ajax请求
    var page = filterInvalidPageFieldRuleValue(page);
    axios.post(uri,page).then((res)=>{
        successCallBack(res);
    }).catch((err)=>{
        failCallBack(err);
    });
};

/**
 * 通用添加请求
 * @param uri      请求地址
 * @param formData 请求数据
 * @param successCallBack(res)   请求成功时的回调函数(res 请求结果)
 * @param failCallBack(err)      请求失败时的回调函数(err 请求结果)
 * @param finallyCallBack()      请求完成时的回调函数()
* */
function commonAddRequest (uri,formData,successCallBack,failCallBack,finallyCallBack) {
    //发送ajax请求
    axios.post(uri,formData).then((res)=>{
        //如果操作成功，关闭弹层，显示数据
        if(res.data.code == 20011){
            commonSuccessTip("添加成功");
            successCallBack(res);
        }else if(res.data.code == 20010){
            commonErrorTip("添加失败");
            failCallBack(res);
        }else if(res.data.code == 20053){
            commonErrorTip(res.data.msg);
            commonInfoTip("3秒后跳转登入界面");
            setTimeout(()=>{
                location.href = "../pages/user/login.html";
            },3000);
        }else{
            commonErrorTip(res.data.msg);
        }
    }).finally(()=>{
        finallyCallBack();
    });
};

/**
 * 通过唯一键 查询单条数据
 * @param uri      请求地址
 * @param successCallBack(res)   请求成功时的回调函数(res 请求结果)
 * @param failCallBack(err)      请求失败时的回调函数(err 请求结果)
 * */
function commonQuerySingleResult(uri,successCallBack,failCallBack) {
    //查询数据，根据id查询
    axios.get(uri).then((res)=>{
        if(res.data.code == 20041){
            successCallBack(res);
        }else if(res.data.code == 20053){
            commonErrorTip(res.data.msg);
            commonInfoTip("3秒后跳转登入界面");
            setTimeout(()=>{
                location.href = "../pages/user/login.html";
            },3000);
        } else{
            commonErrorTip(res.data.msg);
            failCallBack(res);
        }
    });
};

/**
 * 获取所有数据
 * @param uri      请求地址
 * @param successCallBack(res)   请求成功时的回调函数(res 请求结果)
 * @param failCallBack(err)      请求失败时的回调函数(err 请求结果)
 * */
function commonGetAllRequest(uri,successCallBack,failCallBack) {
    commonQuerySingleResult(uri,successCallBack,failCallBack);
};

/**
 * 通用编辑请求
 * @param uri      请求地址
 * @param formData 请求参数
 * @param successCallBack(res)   请求成功时的回调函数(res 请求结果)
 * @param failCallBack(err)      请求失败时的回调函数(err 请求结果)
 * @param finallyCallBack()      请求完成时的回调函数()
 * */
function commonEditRequest(uri,formData,successCallBack,failCallBack,finallyCallBack) {
    //发送ajax请求
    axios.put(uri,formData).then((res)=> {
        //如果操作成功，关闭弹层，显示数据
        if (res.data.code == 20031) {
            commonSuccessTip("修改成功");
            successCallBack(res);
        } else if (res.data.code == 20030) {
            commonErrorTip("修改失败");
            failCallBack(res);
        } else {
            commonErrorTip(res.data.msg);
            failCallBack(res);
        }
    }).finally(()=>{
        finallyCallBack();
    });
};

/**
 * 通用删除请求
 * @param uri      请求地址
 * @param successCallBack(res)   请求成功时的回调函数(res 请求结果)
 * @param failCallBack(err)      请求失败时的回调函数(err 请求结果)
 * @param finallyCallBack()      请求完成时的回调函数()
 * */
function commonDeleteRequest(uri,successCallBack,failCallBack,finallyCallBack) {

    //调用通用提示框
    commonConfirmTip("此操作会删除与此字段相关的数据，是否继续？","提示",'info',
        ()=>{
            axios.delete(uri).then((res)=>{
                if(res.data.code == 20021){
                    commonSuccessTip("删除成功");
                    successCallBack();
                }else{
                    commonErrorTip("删除失败");
                    failCallBack(res);
                }
            }).finally(()=>{
                finallyCallBack();
            });
        },
        ()=>{
            //取消删除
            commonInfoTip("取消删除操作");
        })
}
