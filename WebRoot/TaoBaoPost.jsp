<%@ page language="java" import="java.util.*,com.webcrawler.db.utils.*,com.webcrawler.taobao.*,com.webcrawler.view.*" pageEncoding="UTF-8"%>
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
.info{bottom: 20em;}

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
					String goodsid = request.getParameter("pid");
					int pid=1;
					//int sid=Integer.parseInt(goodsid);
					//if(shopid!=null){sid = Integer.parseInt(shopid);}	
					//页码
					String pagen = request.getParameter("pagenum");
					int pn=1;
					if(pagen!=null){pn = Integer.parseInt(pagen);if(pn<1){pn=1;}}	
					
					//楼层
					//String l = request.getParameter("lou");
					int ll=1+(pn-1)*everypagenum;
					//if(l!=null){ll = Integer.parseInt(l);}					
					//int postid=1;帖子id
					
					
					TaoBaoDao dao = new TaoBaoDao();
 					List<TBgoods> plist1 = dao.getAllGoods(); 
 					Map<String,TBshop> map = dao.getAllShops();
  					TBgoods p = plist1.get(pid-1);
									out.print("<header>");
										out.print("<h2><a href='#'>");
										String posttitle=p.getGoodName();									
										out.print(posttitle);
										out.print("</a></h2>");
										out.print("<span class='byline'>");
										out.print("商品ID：");out.print(goodsid);
										out.print("<br/>");
										out.print("<br/>");
										out.print("店铺名：");out.print(map.get(p.getShopId()).getShopName());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("店铺ID：");out.print(p.getShopId());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("描述相符：");out.print(map.get(p.getShopId()).getMiaoshu());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("店铺服务：");out.print(map.get(p.getShopId()).getFuwu());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("发货速度：");out.print(map.get(p.getShopId()).getFahuo());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
										out.print("<br/>");
										out.print("<br/>");
										out.print("关键字：");out.print(p.getKeywords());
										out.print("</span>");
									out.print("</header>");
									out.print("<div class='info'>");
									out.print("<ul class='stats'>");
										out.print("<li><a href='" + basePath +"TaoBao.jsp' class=' link-icon24-1'>");
											out.print("返回");
											out.print("</a></li>");
											out.print("<li><a href='#' class=' link-icon24-1'>");
											out.print("回到顶端");
											out.print("</a></li>");
											out.print("</ul>");
									out.print("</div>");
								 	out.print("<p >");
										String postcontent=p.getDescription();
										out.print(postcontent);
										out.print("</p>");	
										
										out.print("<div id='HF'>");							 
										out.print("<p style='padding-top:5em;'>");
										//String HF=p.getHfSum();
										//out.print(HF);
										out.print("<div id='reply'>");
										 TaoBaoDao dao1 = new TaoBaoDao();
										 List<TBreply> plist2 = dao1.getReplysByPageNo(goodsid,pn,everypagenum);						 
										 //分页页数
										 int totalpagenum=dao1.getReplyNum(goodsid)/everypagenum;
										 int tt = totalpagenum + 1;
										 
  										for(TBreply r : plist2){ 									
  										out.print("<pre>");	
  										out.print("<div id='lou' style='font-size:70%'>");
  										out.print("#");	out.print(ll);	out.print("&nbsp;&nbsp;&nbsp;&nbsp;");	
  										out.print("用户ID：");out.print(r.getId());out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
  										out.print("用户：");out.print(r.getUsername());	out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
  										out.print("购买：");out.print(r.getGoumai());
  										out.print("</div>");
  											out.print(r.getReply());
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
										out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=" + pnjian +"&lou=" + ll +"' class='button previous'>Prev Page</a>");}
										out.print("<div class='pages'>");																													
										if(pn>1){out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=1&lou=" + ll +"'><<</a>");
										out.print("<span>&hellip;</span>");}
										//判断到尾页
										if(pn<=totalpagenum+1){
										out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=" + pn +"&lou=" + ll +"' class='active'>" + pn + "</a>");
										}if(pn+1<=totalpagenum+1){
										out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=" + pp1 +"&lou=" + ll +"'>" + pp1 + "</a>");
										}if(pn+2<=totalpagenum+1){
										out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=" + pp2 +"&lou=" + ll +"'>" + pp2 + "</a>");
										
										out.print("<span>&hellip;</span>");
										out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=" + tt + "&lou=" + ll +"'>>></a>");
									}out.print("</div>");
									if(pn+1<=totalpagenum+1){out.print("<a href='" + basePath +"TaoBaoPost.jsp?pid=" + goodsid +"&pagenum=" + pnjia +"&lou=" + ll +"' class='button next'>Next Page</a>");
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
								<h1 style="font-size:150%">淘    宝    网</h1>
							</div>
					
						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li ><a href="TianYa.jsp">天涯论坛</a></li>
									<li><a href="Sina.jsp">新    浪    网</a></li>
									<li ><a href="DianPing.jsp">大众点评</a></li>
									<li class="current_page_item"><a href="TaoBao.jsp">淘    宝    网</a></li>
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