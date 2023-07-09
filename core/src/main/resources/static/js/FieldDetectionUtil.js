/**
 * 1.删除page.{key}.condition.value为''的元素condition
 * 2.删除page.{key}.condition.value为null的元素condition
 * */
function filterInvalidPageFieldRuleValue(page){
    if(page.fieldRule == null){
        return page;
    }
    var page = JSON.parse(JSON.stringify(page)); // page深拷贝
    for (let entry of Object.entries(page.fieldRule)) {
        if(entry[1].condition != null){
            if(entry[1].condition.value == null || entry[1].condition.value[0]==''){
                if(entry[1].sort == null){
                    delete page.fieldRule[entry[0]];
                }else {
                    delete page.fieldRule[entry[0]].condition;
                }
            }
        }
    }
    return page;
}
