<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>emplist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/ems_spring/emsdemo/css/style.css" />
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
							<a href="#">main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Name
							</td>
							<td>
								Salary
							</td>
							<td>
								Age
							</td>
							<td>
								Operation
							</td>
						</tr>
						<s:iterator value="list">
						<tr class="row1">
							<td>
								<s:property value="id"/>
							</td>
							<td>
								<s:property value="name"/>
							</td>
							<td>
								<s:property value="salary"/>
							</td>
							<td>
								<s:property value="age"/>
							</td>
							<td>
							<a href="<s:url value='/emp/delete'/>?id=<s:property value='id'/>">删除员工信息</a>&nbsp;<a href="<s:url value='/emp/one'/>?id=<s:property value='id'/>">更新员工信息</a>
							</td>
						</tr>
						</s:iterator>
						
					</table>
					<p>
						<input type="button" class="button" value="Add Employee" onclick="location='<s:url value='/emsdemo/addEmp.jsp'/>'"/>
						<input type="button" class="button" value="execal" onclick="location='<s:url value='/emp/load'/>'"/>
					</p>
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
