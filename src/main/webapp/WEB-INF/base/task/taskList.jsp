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
<link rel="stylesheet" type="text/css" href="../assets/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../assets/css/H-ui.min.css" />
</head>
<body>
<nav class="breadcrumb"> 任务列表 </nav>
<div class="page-container">
    <div class="mt-20">
        <form id="addForm">
            <table class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                <tr class="text-c">
                    <th width="3%">ID</th>
                    <th width="5%">NAME</th>
                    <th width="5%">GROUP</th>
                    <th width="7%">状态</th>
                    <th width="9%">CRON表达式</th>
                    <th width="10%">描述</th>
                    <th width="6%">是否同步</th>
                    <th width="15%">类路径</th>
                    <th width="21%">Shell脚本</th>
                    <th width="5%">方法名</th>
                    <th width="7%">更新CRON</th>
                    <th width="7%">操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="job" items="${taskList}" varStatus="status">
                        <tr class="text-c">
                            <td><span class="label label-success radius">&nbsp&nbsp&nbsp&nbsp&nbsp${status.index}&nbsp&nbsp&nbsp&nbsp&nbsp</span></td>
                            <td>${job.jobName }</td>
                            <td>${job.jobGroup }</td>
                            <td>${job.jobStatus eq 1?"运行中":"已停止"}</td>
                            <td>${job.cronExpression }</td>
                            <td>${job.description }</td>
                            <td>${job.isConcurrent eq 1?"是":"否" }</td>
                            <td>${job.beanClass eq "null" ? "" : job.beanClass}</td>
                            <td>${job.springId  eq "null" ? "" : job.springId}</td>
                            <td>${job.methodName  eq "null" ? "" : job.methodName }</td>
                            <td><span  class="label label-success radius"><a
                                    href="javascript:;"
                                    onclick="updateCron('${job.jobId}', '${basePath}task/updateCron.htm')">&nbsp&nbsp&nbsp&nbsp&nbsp更新&nbsp&nbsp&nbsp&nbsp&nbsp</a></span>
                            </td>
                            <td><c:choose>
                                <c:when test="${job.jobStatus=='1' }">
                                                            <span  class="label label-success radius"><a href="javascript:;"
                                                                                                         onclick="changeJobStatus('${job.jobId}','${basePath}task/changeJobStatus.htm','stop')">&nbsp&nbsp&nbsp&nbsp&nbsp停止&nbsp&nbsp&nbsp&nbsp&nbsp</a></span>
                                </c:when>
                                <c:otherwise>
                                                            <span  class="label label-success radius"><a href="javascript:;"
                                                                                                         onclick="changeJobStatus('${job.jobId}','${basePath}task/changeJobStatus.htm','start')">&nbsp&nbsp&nbsp&nbsp&nbsp开启&nbsp&nbsp&nbsp&nbsp&nbsp</a></span>
                                </c:otherwise>
                            </c:choose></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><span class="label label-success radius">&nbsp&nbsp&nbsp&nbsp&nbspn&nbsp&nbsp&nbsp&nbsp&nbsp</span></td>
                        <td><input style="width:80px" type="text" name="jobName" id="jobName"/></td>
                        <td><input style="width:80px" type="text" name="jobGroup" id="jobGroup"/></td>
                        <td><input style="width:80px"  disabled name="jobStatus"  id="jobStatus" value="0"/></td>
                        <td><input style="width:100px" type="text" name="cronExpression"
                                   id="cronExpression"/></td>
                        <td><input style="width:100px"  type="text" name="description" id="description"/></td>
                        <td><select  style="width:80px"  name="isConcurrent" id="isConcurrent">
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select></td>
                        <td><input style="width:200px"  type="text" name="beanClass" id="beanClass"/></td>
                        <td><input style="width:300px"   type="text" name="springId" id="springId"/></td>
                        <td><input style="width:80px"   type="text" name="methodName" id="methodName"/></td>
                        <td colspan="2"><span class="label label-success radius" onclick="add('${basePath}task/add.htm')"><a href="javascript:;">&nbsp&nbsp保存&nbsp&nbsp</a></span></td>

                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>




