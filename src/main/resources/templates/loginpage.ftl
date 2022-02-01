<!DOCTYPE html>
<html lang="en">
<#include "common/common_head.ftl">
<body>

<form action="/login" method="POST">
	<div class="main-container">
		<div class="form-group">
			<label for="login_email">Email</label>
			<input type="email" class="form-control" id="login_email" name="username" maxlength="70" placeholder="e-mail" required>
		</div>
		<div class="form-group">
			<label for="login_password">Пароль</label>
			<input type="password" class="form-control" id="login_password" placeholder="********" name="password" required>
		</div>
	</div>
	<button type="submit" class="btn btn-primary" id="submit-button">Войти</button>
</form>

<script type="text/javascript" src="/static/js/checkData.js"></script>
<script type="text/javascript" src="/static/js/checkTwoPasswords.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.js"></script>

</body>
</html>