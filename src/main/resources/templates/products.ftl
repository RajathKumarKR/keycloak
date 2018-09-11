<#import "/spring.ftl" as spring>
<html>
<h1>My products</h1>
<ul>
<#list products as product>
   <a href="/submodules?customer=${product}"> <li>${product}</li><a>
</#list>
</ul>
<br>
<a href="/logout">Logout</a>
</html>