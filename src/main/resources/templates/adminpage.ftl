<!DOCTYPE html>
<html lang="en">
<#include "common/common_head.ftl">
<body>

<div class="admin-container">
    <#if users?? && users?has_content>
		<a href="/">Главная</a>
		<table>
			<tr>
				<th>ФИО</th>
				<th>Подтверждено</th>
			</tr>
            <#list users as user>
				<tr>
					<td class="name-field">${user.getFio()}</td>
					<td>${user.isApproved()?string('Да','Нет')}</td>
				</tr>
            </#list>
		</table>
    <#else>
        <#if userInfo?? && userInfo?has_content>
			<div>
				Пользователь: ${user.getFio()}
			</div>
        <#else>
			<div class="empty-list">
				Список пользователей пока пуст.
				<a href="/">Главная</a>
			</div>
        </#if>
    </#if>
</div>

</body>
</html>