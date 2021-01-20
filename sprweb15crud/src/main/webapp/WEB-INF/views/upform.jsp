<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
** 회원 수정 **</p>
<form action="update" method="post">
번호 : ${dto.num}<br>
<input type="hidden" name="num" value="${dto.num}">
이름 : <input type="text" name="name" value="${dto.name}"><br>
주소 : <input type="text" name="addr" value="${dto.addr}"><br>
<br>
<input type="submit" value="수정">
</form>
</body>
</html>