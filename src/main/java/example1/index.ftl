
开始打印ftl内容
${name[0..2]}
${name?substring(0,2)}这种截取方法已经废弃
${user.name}
SharedVariable：${share}
特殊名字：${.vars['A strange name!']}


<#list .data_model?keys as key>
${key}
</#list>


${.globals.share}

${.version}
${list?min}
${list?max}


${172.535}
${172.425}
${f}
${f1}
