<#import "/spring.ftl" as spring>
<html>
<h1>My items</h1>
<ul>
<#list items as item>
     <li>${item}</li>
</#list>
</ul>
<br>
<a href="/logout">Logout</a>
</html>