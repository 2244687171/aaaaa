<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>update Emp</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="/ems_spring/emsdemo/css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								2009/11/20
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="#">Main</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						update Emp info:
					</h1>
					<form action="<s:url value='/emp/update'/>" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									ID:
								</td>
								<td valign="middle" align="left">
									<input type="hidden" class="inputgri" name="emp.id" value="<s:property value='emp.id'/>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="emp.name" value="<s:property value='emp.name'/>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									工资:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="emp.salary" value="<s:property value='emp.salary'/>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									年龄:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="emp.age" value="<s:property value='emp.age'/>"/>
								</td>
							</tr>
							
						</table>
						<p>
							<input type="submit" class="button" value="更新" />
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
