<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="addEmployee" modelAttribute="empUpdateBean">

		<table>
			<tr>

				<td><form:hidden path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Emp Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>

			<tr>
				<td><form:label path="email">Emp Mail</form:label></td>
				<td><form:input path="email" /></td>
			<tr>
				<td><form:label path="gender">Gender</form:label></td>
				<td>
				Male<form:radiobutton path="gender" value="Male" /> 
				Female<form:radiobutton path="gender" value="Female" />
				</td>
			</tr>


			<tr>
				<td>Country:</td>
				<td><form:select path="country">
						<form:option value="India" label="India" />
						<form:option value="U.S.A" label="U.S.A" />
						<form:option value="U.K" label="U.K" />
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Edit Employee" /></td>
		</table>
	</form:form>
</body>
</html>