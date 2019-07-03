<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="images/icon.png" type="image/x-icon">
    <title>SBA</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
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
        <div class="empty-list">
            Список пользователей пока пуст.
            <a href="/">Главная</a>
        </div>
    </#if>
</div>

</body>
</html>