<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Main Page</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function login_form_check() {
		var login_form = document.login_form;
		
		if(login_form.id.value==""){
			alert("아이디를 입력하세요");
			login_form.id.focus();
			return false;
		}
		
		if(login_form.password.value==""){
			alert("패스워드를 입력하세요");
			login_form.password.focus();
			return false;
		}
		
		return true;
	}
	
	function JoinForm() {
		window.location.href = 'USER/UserAdd.do';
	}
</script>
</head>
<body>
	<div align="center" class="container">
	<div class="jumbotron" align="center">
		<h1>WSMS <br>(Web Schedule Management System)</h1>
	</div>
	
	<form class="form-horizontal" role="form" action="main.do" method="post"
	name="login_form" onsubmit="return login_form_check()">
    <div class="form-group">
      <label class="control-label col-sm-offset-3 col-sm-2" for="ID">ID:</label>
      <div class="col-sm-3">
        <input type="text" class="form-control" name="id" placeholder="Enter ID">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-offset-3  col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-3">          
        <input type="password" name="password" class="form-control" placeholder="Enter password">
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-5 col-sm-4">
        <button type="submit" class="btn btn-default">로그인</button>
  		<button type="button" class="btn btn-default" onclick="JoinForm()">회원가입</button>      
      </div>
    </div>
  </form>
  </div>
</body>
</html>