import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class WebCrawler {

	/**
	 * @param args
	 */
	private static String Text_File_Path = "D:\\HtmlDownload\\htmlsrc.html";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			File file = new File(Text_File_Path);
			FileWriter fpWriter = new FileWriter(file);
			Socket webclient = new Socket("www.bnu.edu.cn", 80);
			PrintWriter result = new PrintWriter(webclient.getOutputStream(),
					true);
			BufferedReader receiver = new BufferedReader(new InputStreamReader(
					webclient.getInputStream(), "utf-8"));
			result.println("GET / HTTP/1.1");
			result.println("Host: bnu.edu.cn");
			result.println("Connection: Close");
			result.println();

			boolean bRet = true;
			StringBuffer sBuffer = new StringBuffer(8096);
			while (bRet) {
				if (receiver.ready()) {
					int idx = 0;
					while (idx != -1) {
						idx = receiver.read();
						if (idx == '<')
							break;
					}
					while (idx != -1) {
						sBuffer.append((char) idx);
						idx = receiver.read();
					}
					bRet = false;
				}
			}
			System.out.println(sBuffer.toString());
			fpWriter.write(sBuffer.toString());
			webclient.close();
			fpWriter.close();
		} catch (UnknownHostException e) {
			System.err.println("无法指定主机。");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("下载失败，请检查输入地址是否正确！");
			System.exit(1);
		}

	}

}