<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/style1.css"/>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/images/favicon.ico">
</head>

<div id="tabCategories" class="cadre">
<table class="tab1">
<tr>
<th>ID</th><th>NOM CAT</th><th>DESCRIPTION</th><th>PHOTO</th>
</tr>
<c:forEach items="${categories}" var="cat">
<tr>
<td>${cat.idCategorie}</td>
<td>${cat.nomCategorie}</td>
<td>${cat.description}</td>
<td><a href="categorie?idCat=${cat.idCategorie}"><img src="photoCat?idCat=${cat.idCategorie}" width="200" height="150"/></a></td>
</tr>
</c:forEach>
</table>
</div>