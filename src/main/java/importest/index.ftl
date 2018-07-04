<#import "macro.ftl" as u>
index<br/>

${u?size}

<#list u?keys as key>
${key}
</#list>
