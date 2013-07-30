<%@ page language="java" import="java.util.*,com.webcrawler.db.utils.*,com.webcrawler.sina.*,com.webcrawler.view.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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

	<body class="left-sidebar">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Content -->
					<div id="content">
						<div id="content-inner">
					<!-- 分页显示 -->
							<!-- Post -->
								<article class="is-post is-post-excerpt">
					<% 
					int everypagenum=5;//每页帖子数
					//帖子ID
					int postid=1;					 				
					//页码
					String pagen = request.getParameter("pagenum");
					int pn=1;
					if(pagen!=null){pn = Integer.parseInt(pagen);if(pn<1){pn=1;}}				
					//楼层
					int ll=1+(pn-1)*everypagenum;
					
					WbDao dao =new WbDao();
 					List<WbContent> plist = dao.getContentsByPageNo(pn, everypagenum);
 					//用户
 					List<WbContent> plist1 = dao.getAllContents();
 					Map<String,WbUser> map = dao.getAllUsers();
 					
  					for(WbContent p : plist){
									out.print("<header>");
										out.print("<h2>博主：<a href='javascript:void(0)'>");
										//String posttitle=p.getContent();
										//if(posttitle.length()>50)
  										//{posttitle = posttitle.substring(0, 50) + "...";}
										out.print(map.get(p.getUserId()).getUserName());
										out.print("</a></h2>");
										out.print("<span class='byline'>");
										out.print("用户uid：");out.print(plist1.get(postid-1).getUserId());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("用户主页：");out.print(map.get(plist1.get(postid-1).getUserId()).getUrl());
										out.print("</span>");
									out.print("</header>");
									//out.print("<div class='info'>");
									//out.print("<ul class='stats'>");
										//out.print("<li><a href='javascript:void(0)' class='link-icon24 link-icon24-1'>");
											//out.print(p.getHfSum());
											//out.print("</a></li>");
											//out.print("<li><a href='javascript:void(0)' class='link-icon24 link-icon24-2'>");
											//out.print(p.getDjSum());
											//out.print("</a></li>");
											//out.print("</ul>");
									//out.print("</div>");
								 	out.print("<p style='background: url(images/sep_post.png) 40px bottom no-repeat;'>");
										String postcontent=p.getContent();
										//if(postcontent.length()>150)
  										//{postcontent = postcontent.substring(0, 150) + "...";}
										out.print(postcontent);
										//out.print("<strong style='font-size:70%'><a href='" + basePath +"SinaPost.jsp?pid=" + postid +"'>阅读全文>></a></strong>");
										out.print("</p>");		
										postid++;			 
							}
  					%> 
								</article>
						
							<!-- Post -->												
							<!-- Pager -->
								<div class="pager">
										
										<% 
										//上一页页数，下一页页数
										int totalpagenum=dao.getContentsNum()/everypagenum;
										int tt = totalpagenum + 1;
										int pnjian = pn-1;
										int pnjia = pn+1;
										//页数叠加
										int pp1 = pn+1;
										int pp2 = pn+2;
										if(pn>1){
									out.print("<a href='" + basePath + "Sina.jsp?pagenum=" + pnjian + "' class='button previous'>Prev Page</a>");}																
									out.print("<div class='pages'>");
									if(pn>1){
									out.print("<a href='" + basePath +"Sina.jsp?pagenum=" + 1 +"'><<</a>");
									out.print("<span>&hellip;</span>");}
									if(pn<=totalpagenum+1){
										out.print("<a href='" + basePath + "Sina.jsp?pagenum=" + pn + "' class='active'>" + pn + "</a>");
										}if(pn+1<=totalpagenum+1){
										out.print("<a href='" + basePath + "Sina.jsp?pagenum=" + pp1 + "'>" + pp1 + "</a>");	
										}if(pn+2<=totalpagenum+1){
										out.print("<a href='" + basePath + "Sina.jsp?pagenum=" + pp2 + "'>" + pp2 + "</a>");	
										
									out.print("<span>&hellip;</span>");		
									out.print("<a href='" + basePath +"Sina.jsp?pagenum=" + tt +"'>>></a>");								
									}out.print("</div>");	
									if(pn+1<=totalpagenum+1){out.print("<a href='" + basePath + "Sina.jsp?pagenum=" + pnjia + "' class='button next'>Next Page</a>");																	
								}%>
								</div>

						</div>
					</div>

				<!-- Sidebar -->
					<div id="sidebar">
					
						<!-- Logo -->
							<div id="logo">
								<h1 style="font-size:150%">新    浪    网</h1>
							</div>
					
						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li ><a href="TianYa.jsp">天涯论坛</a></li>
									<li class="current_page_item"><a href="Sina.jsp">新    浪    网</a></li>
									<li><a href="DianPing.jsp">大众点评</a></li>
									<li><a href="TaoBao.jsp">淘    宝    网</a></li>
									<li>
									<section class="is-search">
									<form method="post" action="SearchServlet">
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