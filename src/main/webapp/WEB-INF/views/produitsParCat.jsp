<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style1.css" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/images/favicon.ico">
</head>

<div id="tabCategories" class="cadre">
	<table class="tab1">
		<tr>
			<td colspan="3">${categorie.nomCategorie}</td>
		</tr>
		<tr>
			<th>PHOTO</th>
			<th>Description</th>
			<th>Prix</th>
		</tr>
		<c:forEach items="${produits}" var="prod">
			<tr>
				<td><img alt="${prod.description }"
					src="photoProd?idProd=${prod.idProduit}" width="100" height="80" /></td>
				<td>${prod.description}</td>
				<td>${prod.prix }DH</td>
				<td>
				<f:form method="get" action="ajouter" modelAttribute="produit">
				<input type="submit" value="add to cart">
				</f:form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div>
${panier}
</div>