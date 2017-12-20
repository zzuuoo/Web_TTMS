package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Flag;
import bean.play;
import net.sf.json.JSONObject;
import service.PlaySrv;
/**
 * Servlet implementation class AddPlay
 */
@WebServlet("/AddPlay")
public class AddPlay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		private int play_id;
//		private int play_type_id;
//		private int play_lang_id;
//		private String play_name;
//		private String play_introduction;
//		private String play_image;
//		private int play_length;
//		private double play_ticket_price;
//		private int play_status;//    0：待安排演出1：已安排演出 -1：下线',
		
		  PrintWriter out = response.getWriter();
		  Flag result = new Flag();
		  result.setFlag("no");
		  int flag=0;
		  
		//获取前端数据
		  String play_name = request.getParameter("play_name");
		  String play_image = request.getParameter("play_image");
		  int  play_type_id = new Integer(request.getParameter("play_type_id"));
		  int play_lang_id = new Integer(request.getParameter("play_lang_id"));//列
		  String studio_play_introduction = request.getParameter("play_introduction");
		  int  play_length = new Integer(request.getParameter("play_length"));
		  int play_status = new Integer(request.getParameter("play_status"));//列
		  double play_ticket_price = new Double(request.getParameter("play_ticket_price"));


//		保存到数据库中
	      play  p =new play();
//	      p.setPlay_image(play_image);
//	      p.setPlay_introduction(studio_play_introduction);
//	      p.setPlay_lang_id(play_lang_id);
//	      p.setPlay_length(play_length);
//	      p.setPlay_name(play_name);
//	      p.setPlay_status(play_status);
//	      p.setPlay_ticket_price(play_ticket_price);
//	      p.setPlay_type_id(play_type_id);
	      
	      
	      
	      
	      
	      
	      String paramName = "", paramValue = "";

	        // 创建该对象
	        DiskFileItemFactory dff = new DiskFileItemFactory();
	        // 指定在内存中缓存数据大小,单位为byte
	        dff.setSizeThreshold(1024000);
	        // 创建上传对象
	        ServletFileUpload sfu = new ServletFileUpload(dff);
	        // sfu.setHeaderEncoding("UTF-8");
	        // 指定单个上传文件的最大尺寸(单个文件大小不超过2M)
	        sfu.setFileSizeMax(1024 * 1024 * 2);
	        // 指定一次上传多个文件的总尺寸(总文件大小不超过6M)
	        // sfu.setSizeMax(1024 * 1024 * 6);
	        try
	        {
	            List<FileItem> uploaditems = sfu.parseRequest(request);
	            for (FileItem fileItem : uploaditems)
	            {
	                // 对应表单中的控件的name
	                paramName = fileItem.getFieldName();
	                System.out.print(paramName + ":");

	                if (fileItem.isFormField())
	                {
	                    // 1.如果是普通表单控件，获取文本框中数据
	                    // 重新编码,解决乱码
	                    paramValue = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
	                    System.out.println(paramValue);

	                    if ("play_type_id".equals(paramName))
	                    	p.setPlay_type_id(new Integer(paramValue));
	                    if ("play_lang_id".equals(paramName))
	                        p.setPlay_lang_id(new Integer(paramValue));
	                    if ("play_name".equals(paramName))
	                        p.setPlay_name(paramValue);
	                    if ("play_introduction".equals(paramName))
	                        p.setPlay_introduction(paramValue);
	                    if ("play_length".equals(paramName))
	                        p.setPlay_length(new Integer(paramValue));
	                    if ("play_ticket_price".equals(paramName))
	                        p.setPlay_ticket_price(new Double(paramValue));
	                    if("play_status".equals(paramName))
	                    	p.setPlay_status(new Integer(paramValue));
	                }
	                else
	                {
	                    // 2.如果是上传文件，则保存在服务器端应用根目录
	                    // 获得文件大小
	                    Long size = fileItem.getSize();
	                    // 获得全路径文件名
	                    String fileName = fileItem.getName();
	                    // 截取文件名
	                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
	                    System.out.println("\n文件名：" + fileName + "\t大小：" + size + "byte");

	                    // 将文件保存到指定的路径
	                    File file = new File(getServletContext().getRealPath("/") + fileName);
	                    fileItem.write(file);
	                    p.setPlay_image(fileName);
	                }
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }

	      
	      
	      
	      
	      
	      
	      
	      
	      
	      PlaySrv srv = new PlaySrv();
	      flag=srv.add(p);
		if(flag==1) {
//			成功,设置flag返回信息给前端，插入成功了
			result.setFlag("yes");
		}
		//反馈情况
		JSONObject object = JSONObject.fromObject(result);
		out.write(object.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
