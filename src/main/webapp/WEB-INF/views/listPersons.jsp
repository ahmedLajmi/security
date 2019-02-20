<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Liste des personnes</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/magnific-popup.css">

<!-- Owl Carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/owl.theme.default.min.css">

<!-- Theme style  -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/interne/css/style.css">

<!-- Modernizr JS -->
<script
	src="${pageContext.request.contextPath}/resources/interne/js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

</head>
<body>

	<div class="colorlib-loader"></div>

	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<div class="top-menu">
				<div class="container">
					<div class="row">
						<div class="col-md-2">
							<div id="colorlib-logo">
								<a href="${pageContext.request.contextPath}/">Accueil</a>
							</div>
						</div>
						<div class="col-md-10 text-right menu-1">
							<ul>
								<li><a href="index.html"></a></li>

								<c:if test="${role == 'ROLE_ADMIN'}">
									<li class="has-dropdown"><a href="#">Gérer les
											personnes</a>
										<ul class="dropdown">
											<li><a href="${pageContext.request.contextPath}/persons">Liste
													des personnes</a></li>
											<li><a
												href="${pageContext.request.contextPath}/add/person">Ajouter
													une personne</a></li>
										</ul></li>
								</c:if>
								<c:if test="${role == 'ROLE_USER'}">
									<li><a
										href="${pageContext.request.contextPath}/add/person">Ajouter
											une personne</a></li>
								</c:if>
								<li><a href="${pageContext.request.contextPath}/search">Rechercher
										des personnes</a></li>
								<li><a href="${pageContext.request.contextPath}/logout">Se
										déconnecter</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
		<section id="home" class="video-hero"
			style="height: 400px; background-image: url(${pageContext.request.contextPath}/resources/interne/images/cover_img_1.jpg);  background-size:cover; background-position: center center;background-attachment:fixed;"
			data-section="home">
			<div class="overlay"></div>
			<div class="display-t display-t2 text-center">
				<div class="display-tc display-tc2">
					<div class="container">
						<div class="col-md-12 col-md-offset-0">
							<div class="animate-box">
								<h2>Liste des personnes</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<div id="colorlib-contact">
			<div class="container" >
				<c:if test="${not empty message}">
				<div class=row id="popup">
					<div class="alert alert-success" role="alert">${message}</div>
				</div>
				</c:if>
				<div class="row">
					<div class="col-md-2 col-md-push-6 animate-box"></div>
					<div class="col-md-10 col-md-pull-1 animate-box">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Civilité</th>
									<th scope="col">Prénom</th>
									<th scope="col">Nom</th>
									<th scope="col">Adresse</th>
									<th scope="col">Date de naissance</th>
									<th scope="col">Options</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="person" items="${persons}">
									<tr>
										<td><c:out value="${person.id}" /></td>
										<td><c:out value="${person.gender}" /></td>
										<td><c:out value="${person.firstName}" /></td>
										<td><c:out value="${person.lastName}" /></td>
										<td><c:out value="${person.address}" /></td>
										<td><fmt:formatDate value="${person.birthDay}"
												pattern="dd/MM/yyyy" /></td>
										<td><a
											href="${pageContext.request.contextPath}/update/${person.id}"><button
													type="button" class="btn btn-warning">Modifier</button></a> <a
											href="${pageContext.request.contextPath}/delete/${person.id}"><button
													type="button" class="btn btn-danger">Supprimer</button></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.waypoints.min.js"></script>
	<!-- Stellar Parallax -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.stellar.min.js"></script>
	<!-- YTPlayer -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.mb.YTPlayer.min.js"></script>
	<!-- Owl carousel -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/magnific-popup-options.js"></script>
	<!-- Counters -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/jquery.countTo.js"></script>

	<!-- Main -->
	<script
		src="${pageContext.request.contextPath}/resources/interne/js/main.js"></script>

</body>


<script type="text/javascript">
jQuery(document).ready(function(){
    jQuery("#popup").delay(3000).hide(500);
});

</script>
</html>

