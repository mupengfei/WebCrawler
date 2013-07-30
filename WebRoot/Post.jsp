<%@ page language="java" import="java.util.*,com.webcrawler.db.utils.*,com.webcrawler.tianya.*,com.webcrawler.view.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>

	<head>
		<title>社区媒体数据收集系统</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400italic,700|Open+Sans+Condensed:300,700" rel="stylesheet" />
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="js/config.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-ui.min.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
			<link rel="stylesheet" href="css/style-wide.css" />
		</noscript>
	</head>
<style type="text/css"> 
.info{bottom: 25em;}
</style>
	<body class="left-sidebar">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Content -->
					<div id="content">
						<div id="content-inner">
					<!--   -->
					
					
					
					
							<!-- Post -->
								<article class="is-post is-post-excerpt">
					<% TianYaDao dao = new TianYaDao();
 					List<PostInfo> plist = dao.getAllPost(); 
  					PostInfo p = plist.get(0);
									out.print("<header>");
										out.print("<h2><a href='#'>");
										String posttitle=p.getTitle();										
										out.print(posttitle);
										out.print("</a></h2>");
										out.print("<span class='byline'>");
										out.print("发帖时间：");out.print(p.getTime());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("楼主：");out.print(p.getAuthor());
										out.print("</span>");
									out.print("</header>");
									out.print("<div class='info'>");
									out.print("<ul class='stats'>");
										out.print("<li><a href='#'class=' link-icon24-1'>");
											out.print("返回");
											out.print("</a></li>");
											out.print("<li><a href='#' class=' link-icon24-1'>");
											out.print("回到顶端");
											out.print("</a></li>");
											out.print("</ul>");
									out.print("</div>");
								 	out.print("<p >");
										String postcontent=p.getContent();
										out.print(postcontent);
										out.print("</p>");	
										
										out.print("<div id='HF'>");							 
										out.print("<p style='padding-top:5em;'>");
										String HF=p.getHfSum();
										out.print(HF);
										out.print("</p>");	
										out.print("<div class='pager'>");
									out.print("<div class='pages'>");
										out.print("<a href='#' class='active'>1</a>");
										out.print("<a href='#'>2</a>");
										out.print("<a href='#'>3</a>");
										out.print("<a href='#'>4</a>");
										out.print("<span>&hellip;</span>");
										out.print("<a href=’#‘>20</a>");
									out.print("</div>");
									out.print("<a href='#' class='button next'>Next Page</a>");
							out.print("	</div>");
										out.print("</div>");
  					%> 
								</article>
						
							<!-- Post -->												
							<!-- Pager -->
								

						</div>
					</div>

				<!-- Sidebar -->
					<div id="sidebar">
					
						<!-- Logo -->
							<div id="logo">
								<h1 style="font-size:150%">WebCrawler</h1>
							</div>
					
						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li class="current_page_item"><a href="#">天涯论坛</a></li>
									<li><a href="#">草榴社区</a></li>
									<li><a href="#">网易论坛</a></li>
									<li><a href="#">虎扑论坛</a></li>
									<li>
									<section class="is-search">
									<form method="post" action="#">
										<input type="text" class="text" name="search" placeholder="请输入爬虫目标URL" />
									</form>
									</section>
									</li>
								</ul>
							</nav>

					</div>

			</div>

	</body>
</html>