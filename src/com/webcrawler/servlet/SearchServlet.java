package com.webcrawler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webcrawler.crawlers.DianPCrawler;
import com.webcrawler.crawlers.TaobaoCrawler;
import com.webcrawler.crawlers.TianYaCrawler;
import com.webcrawler.sina.Sina;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter("search");
		if (url.indexOf("dianping") > 0) {
//			new DianPCrawler().start(url);
			request.getRequestDispatcher("/DianPing.jsp")
					.forward(request, response);
		} else if (url.indexOf("tmall") > 0) {
			new TaobaoCrawler().start(url);
			request.getRequestDispatcher("/TaoBao.jsp")
					.forward(request, response);
		} else if (url.indexOf("tianya") > 0) {
			new TianYaCrawler().start(url);
			request.getRequestDispatcher("/TianYa.jsp")
					.forward(request, response);
		} else if (url.indexOf("sina") > 0) {
			new Sina().login("jesse_mu@sina.cn", "789456");;
			request.getRequestDispatcher("/Sina.jsp")
					.forward(request, response);
		}
	}

}
