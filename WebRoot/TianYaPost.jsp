<%@ page language="java" import="java.util.*,com.webcrawler.db.utils.*,com.webcrawler.tianya.*,com.webcrawler.view.*" pageEncoding="UTF-8"%>
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
<style type="text/css"> 
.info{bottom: 25em;}

pre{white-space: pre-wrap;
word-wrap: break-word;
background-color: #F5F5EB;
padding: 15px 20px;
border: 1px solid #c1c0ab;
border-left-width: 4px;
margin: 20px 0;
}

.lou{float: left; padding-top: 1px; padding-left: 1px;
margin-bottom: 0em;
font-size: 70%;
 }

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
					<% 
					//int pagenum=1;当前页数
					int everypagenum=5;//每页回复数
					//帖子ID
					String postid = request.getParameter("pid");
					int pid=1;
					if(postid!=null){pid = Integer.parseInt(postid);}	
					//页码
					String pagen = request.getParameter("pagenum");
					int pn=1;
					if(pagen!=null){pn = Integer.parseInt(pagen);if(pn<1){pn=1;}}	
					
					//楼层
					//String l = request.getParameter("lou");
					int ll=1+(pn-1)*everypagenum;
					//if(l!=null){ll = Integer.parseInt(l);}					
					//int postid=1;帖子id
					
					
					TianYaDao dao = new TianYaDao();
 					List<PostInfo> plist = dao.getAllPost(); 
 					Map<String,UserInfo> map = dao.getAllUsers();
 					
  					PostInfo p = plist.get(pid-1);
									out.print("<header>");
										out.print("<h2><a href='#'>");
										String posttitle=p.getTitle();										
										out.print(posttitle);
										out.print("</a></h2>");
										out.print("<span class='byline'>");
										out.print("发帖时间：");out.print(p.getTime());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("楼主uid：");out.print(p.getAuthor());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("楼主：");out.print(map.get(p.getAuthor()).getUname());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("楼主主页：");out.print(map.get(p.getAuthor()).getUrl());
										out.print("</span>");
									out.print("</header>");
									out.print("<div class='info'>");
									out.print("<ul class='stats'>");
										out.print("<li><a href='" + basePath +"TianYa.jsp' class=' link-icon24-1'>");
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
										out.print("<div id='reply'>");
										 TianYaDao dao1 = new TianYaDao();
										 List<ContentItem> plist1 = dao1.getReplysByPageNo(pid,pn,everypagenum);	
										 //回复对象，取回复ID
										 List<ContentItem> plist2 = dao1.getAllReplys(pid);				 										 
										 //分页页数
										 int totalpagenum=dao1.getReplyNum(pid)/everypagenum;
										 int tt = totalpagenum + 1;
  										for(ContentItem r : plist1){
  																		
  										out.print("<pre>");	
  										out.print("<div id='lou'>");
  										out.print("#");	out.print(ll);out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
  										out.print("层主uid：");out.print(plist2.get(ll-1).getUid());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("层主：");out.print(map.get(plist2.get(ll-1).getUid()).getUname());
  										
  										out.print("</div>");
  										out.print(r.getContent());
  											out.print("</pre>");
  											ll++;	
  										}
										out.print("</div>");
										
										out.print("</p>");	
										out.print("<div class='pager'>");	
										//上一页页数，下一页页数
										int pnjian = pn-1;
										int pnjia = pn+1;
										//页数叠加
										int pp1 = pn+1;
										int pp2 = pn+2;
										if(pn>1){
										out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + postid +"&pagenum=" + pnjian +"' class='button previous'>Prev Page</a>");}
										out.print("<div class='pages'>");																													
										if(pn>1){out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + 1 +"&pagenum=1&'><<</a>");
										out.print("<span>&hellip;</span>");}
										//判断到尾页
										if(pn<=totalpagenum+1){
										out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + postid +"&pagenum=" + pn +"' class='active'>" + pn + "</a>");
										}if(pn+1<=totalpagenum+1){
										out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + postid +"&pagenum=" + pp1 +"'>" + pp1 + "</a>");
										}if(pn+2<=totalpagenum+1){
										out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + postid +"&pagenum=" + pp2 +"'>" + pp2 + "</a>");
										
										out.print("<span>&hellip;</span>");
										out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + postid +"&pagenum=" + tt + "'>>></a>");
									}out.print("</div>");
									if(pn+1<=totalpagenum+1){out.print("<a href='" + basePath +"TianYaPost.jsp?pid=" + postid +"&pagenum=" + pnjia +"' class='button next'>Next Page</a>");
									}
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
								<h1 style="font-size:150%">天涯论坛</h1>
							</div>
					
						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li class="current_page_item"><a href="#">天涯论坛</a></li>
									<li><a href="Sina.jsp">新    浪    网</a></li>
									<li ><a href="DianPing.jsp">大众点评</a></li>
									<li><a href="TaoBao.jsp">淘    宝    网</a></li>
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