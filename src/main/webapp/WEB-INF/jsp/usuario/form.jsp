<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>

<c:import url="/WEB-INF/jsp/header.jsp"/>
	<form action="<c:url value='/usuario/adiciona' />" method="post">
		<label for="usuario.nome">Nome:</label>
		<input type="text" name="usuario.nome" id="usuario.nome" class="form-control" value="${usuario.nome}">	
		<alura:validationMessage name="usuario.nome"/>
		
		
		<label for="usuario.email">Email:</label>
		<input type="text" name="usuario.email" id="usuario.email" class="form-control" value="${usuario.email}">	
		<alura:validationMessage name="usuario.email"/>
		
		<label for="usuario.login">Login:</label>
		<input type="text" name="usuario.login" id="usuario.login" class="form-control" value="${usuario.login}">	
		<alura:validationMessage name="usuario.login"/>
		
		<label for="usuario.senha">Senha:</label>
		<input type="password" name="usuario.senha" id="usuario.senha" class="form-control" >	
		<alura:validationMessage name="usuario.senha"/>
	
		<input type="submit" id="btnCadastrar" value="Cadastrar" class="btn">	
	
	</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>