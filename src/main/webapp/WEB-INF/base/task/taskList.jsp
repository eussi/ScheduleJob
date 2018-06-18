<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
    String path = request.getContextPath();
    String rootPath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + "/";
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setAttribute("basePath", basePath);
    request.setAttribute("rootPath", rootPath);
    pageContext.setAttribute("newLineChar", "\n");
%>
<title>任务列表</title>
<link rel="icon" type="image/png" href="../assets/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/app-icon72x72@2x.png">
<link rel="stylesheet" href="../assets/css/amazeui.min.css" />
<link rel="stylesheet" href="../assets/css/admin.css">
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/amazeui.min.js"></script>
<script src="../assets/js/app.js"></script>
<script src="../assets/js/taskList.js"></script>
<script src="../scripts/validateCron.js"></script>
</head>
<body>
	<div class="admin-content">
		<div class="am-cf am-padding">
			<div class="am-fl am-cf">
				<strong class="am-text-primary am-text-lg">任务列表</strong>
			</div>
		</div>
		<div class="am-g">
			<div class="am-u-sm-12">
				<form id="addForm">
					<table
						class="am-table am-table-bd am-table-striped admin-content-table">
						<thead>
							<tr>
								<th>ID</th>
								<td>NAME</td>
								<td>GROUP</td>
								<td>状态</td>
								<td>CRON表达式</td>
								<td>描述</td>
								<td>是否同步</td>
								<td>类路径</td>
								<td>SPRING ID</td>
								<td>方法名</td>
								<td>更新CRON</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="job" items="${taskList}" varStatus="status">
								<tr>
									<td><span class="am-badge am-badge-success"
										style="width: 80px; display: block">${status.index}</span></td>
									<td>${job.jobName }</td>
									<td>${job.jobGroup }</td>
									<td>${job.jobStatus eq 1?"运行中":"已停止"}</td>
									<td>${job.cronExpression }</td>
									<td>${job.description }</td>
									<td>${job.isConcurrent eq 1?"是":"否" }</td>
									<td>${job.beanClass eq "null" ? "" : job.beanClass}</td>
									<td>${job.springId  eq "null" ? "" : job.springId}</td>
									<td>${job.methodName }</td>
									<td><span class="am-badge am-badge-success"
										style="width: 80px; display: block"><a
											href="javascript:;"
											onclick="updateCron('${job.jobId}', '${basePath}task/updateCron.htm')">更新</a></span>
									</td>
									<td><c:choose>
											<c:when test="${job.jobStatus=='1' }">
												<span class="am-badge am-badge-success"
													style="width: 80px; display: block"><a href="javascript:;"
													onclick="changeJobStatus('${job.jobId}','${basePath}task/changeJobStatus.htm','stop')">停止</a></span>
											</c:when>
											<c:otherwise>
												<span class="am-badge am-badge-success"
													style="width: 80px; display: block"><a href="javascript:;"
													onclick="changeJobStatus('${job.jobId}','${basePath}task/changeJobStatus.htm','start')">开启</a></span>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
							<tr>
								<td><span class="am-badge am-badge-success"
									style="width: 80px; display: block">n</span></td>
								<td><input type="text" name="jobName" id="jobName"/></td>
								<td><input type="text" name="jobGroup" id="jobGroup"/></td>
								<td><input type="hidden" name="jobStatus" value="0"/></td>
								<td><input type="text" name="cronExpression"
									id="cronExpression"/></td>
								<td><input type="text" name="description" id="description"/></td>
								<td><select name="isConcurrent" id="isConcurrent">
										<option value="1">1</option>
										<option value="0">0</option>
								</select></td>
								<td><input type="text" name="beanClass" id="beanClass"/></td>
								<td><input type="text" name="springId" id="springId"/></td>
								<td><input type="text" name="methodName" id="methodName"/></td>
								<td><input type="button"
									onclick="add('${basePath}task/add.htm')" value="保存" /></td>

							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>




