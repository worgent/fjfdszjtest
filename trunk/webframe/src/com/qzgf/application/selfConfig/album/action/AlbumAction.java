package com.qzgf.application.selfConfig.album.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.application.BaseAction;
import com.qzgf.application.selfConfig.album.domain.AlbumFacade;
import com.qzgf.application.systemManage.taglib.config.SysConfig;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.comm.Constant;
import com.qzgf.utils.comm.ImgUtil;
import com.qzgf.utils.comm.WebFrameUtil;

/**
 * 相册表现层
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class AlbumAction extends BaseAction {
	Log log = LogFactory.getLog(AlbumAction.class);

	@SuppressWarnings("unchecked")
	private HashMap search = new HashMap();
	private AlbumFacade albumFacade;
	private SysConfig sysConfig;
	private PageList pageList;
	private String userId;
	private String a_Id; //相册ID
	private String a_desc;
	private String a_name;
	
	//上传图片
	private File upload;
	private String uploadFileName;
	private String ajaxCodeid;
	private String ajaxMsg;
	private String p_Title;
	private String p_Desc;
	
	private String content;
	private String p_Id;
	
	private String xml;
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			log.error(e);
			return "index";
		}
	}

	@SuppressWarnings("unchecked")
	public String index() {
		
		search.put("USERID", "1");
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(5); //设置每行显示的相册数为五个
		//设置fileName是为了返回到前台后的页面跳转
		//fileName="http://localhost:8088/webframe/test/list.do?action=index&page=3&total=49"
		pages.setFileName("album.do?action=" + this.getAction());
		this.setPageList(this.albumFacade.findClientAlbums(search, pages)); 
		return "index";
	
	}
	
	@SuppressWarnings("unchecked")
	public String toNewAlbum() {
		this.setAction("addNewAlbum");
	
		return "newAlbum";
	}
	
	@SuppressWarnings("unchecked")
	public String toEditAlbum() {
		this.setAction("editAlbum");
		search.put("A_ID", this.a_Id);
		List list=this.albumFacade.findClientAlbumByAlbumId(search);
		if (!list.isEmpty() && list != null) { 
			Map map=(HashMap)list.get(0);
			this.setA_desc((String)map.get("A_DESC"));
			this.setA_name((String)map.get("A_NAME"));
			this.setA_Id((String)map.get("A_ID"));
		} else{
			//提示该相册已被删
		}
			
		return "newAlbum";
	}
	
	/**
	 * 创建新相册
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toAddAlbum() {
		this.setAction("addNewAlbum");
		
		this.setA_desc("");
		this.setA_name("");
		this.setA_Id("");
		
		return "newAlbum";
	}
	
	@SuppressWarnings("unchecked")
	public String editAlbum() {
		search.put("USERID", "1");
		search.put("A_ID", this.a_Id);
		search.put("A_NAME", this.a_name);
		search.put("A_DESC", this.a_desc);
		try {
			boolean flag=this.albumFacade.updateAlbumById(search);
			if(flag){
				this.addActionMessage(this.getText("webframe.dataupdate.succeed"));
			}else{
				this.addActionError(this.getText("error.dataupdate.failed"));
			}
		} catch (RuntimeException e) {
			this.addActionError(this.getText("error.dataupdate.failed"));
			e.printStackTrace();
		}
		return "newAlbum";
	}
	
	@SuppressWarnings("unchecked")
	public String addNewAlbum() {
		search.put("A_NAME", a_name);
		search.put("A_DESC", a_desc);
		search.put("USERID", "1");
		try {
			this.albumFacade.insertAlbum(search);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
		}
		this.setAction("index");
		return this.index();
	}
	
	//根据某一相册查询该相册下面的所有的照片
	@SuppressWarnings("unchecked")
	public String viewPhotos(){
		search.put("A_ID", a_Id);
		
		//获得这个相册的名称
		List albumList = this.albumFacade.findClientAlbumByAlbumId(search);
		Map albumMap=(HashMap)albumList.get(0);
		if (!albumMap.isEmpty() && albumMap != null) { 
			this.setA_name((String)albumMap.get("A_NAME"));
		} else
			this.setA_name("");
		
		this.setA_Id(a_Id);
		try {
			this.setA_name(URLEncoder.encode(a_name,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		search.put("USERID", "1");
		Pages pages = new Pages();
		pages.setPage(this.getPage());//默认第一页
		pages.setPerPageNum(5); //设置每行显示的相册数为五个
		this.setPageList(this.albumFacade.findClientPhotoByAlbumId(search, pages)); 
		return "photos";
	}
	
	/**
	 * 到添加照片页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String toAddPhoto()throws Exception{
		System.out.println(a_name);
		this.setA_Id(a_Id);
		try {
			this.setA_name(URLDecoder.decode(a_name, "UTF-8"));
		} catch (RuntimeException e) {
			
		}
		return "newPhoto";
	}
	
	@SuppressWarnings("unchecked")
	public String addPhoto()throws Exception{

		if (this.getUpload() == null || StringUtils.isBlank(this.getUploadFileName())) {
			this.setAjaxCodeid("1");
			this.setAjaxMsg(this.getText("error.userupimg.null"));
			return "userFaceUpComponent";
		}
		userId="1";
		
		//生成的文件名:用户Id+系统当前时间+"."+当前文件的后缀
		String distFileName = userId + System.currentTimeMillis() + "."
		+ FilenameUtils.getExtension(this.getUploadFileName());
		try{
		String fullPicFile = WebFrameUtil.getUserWebFilePath(userId) + distFileName;
		String fullPicFileSmall = WebFrameUtil.getUserWebFilePath(userId) + distFileName+ Constant.IMG_SMALL_FILEPREFIX;
		
		//保存到本地
		OutputStream bos = new FileOutputStream(fullPicFile);
		IOUtils.copy(new FileInputStream(this.getUpload()), bos);
		ImgUtil.reduceImg(fullPicFile, fullPicFileSmall, 120, 50,0);
		//关闭数据流
		bos.close();
		//保存到数据库
		search.put("P_TITLE", p_Title);
		search.put("P_DESC", p_Desc);
		search.put("P_BIG_PIC", distFileName);
		search.put("P_SMALL_PIC", distFileName+"_Small");
		search.put("A_ID", a_Id);
		
		this.albumFacade.insertPhoto(search);
		this.addActionMessage(this.getText("webframe.addphoto.succeed"));//照片添加成功
		this.setP_Desc("");
		this.setP_Title("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return uppage();
	}
	
	

	@SuppressWarnings("unchecked")
	public String listPhotoWords(){
		search.put("P_ID", this.p_Id);
		this.setPageList(this.albumFacade.findPhotoWordByPhotoId(search, null));
		return "listPhotoWords";
	}
	
	public String uppage(){
		this.setA_Id(a_Id);
		return "uppage";
	}

	//加载新评论的页面
	@SuppressWarnings("unchecked")
	public String toNewWordPage(){
		return "editNewWord";
	}
	
	@SuppressWarnings("unchecked")
	public String editdo(){
		System.out.println(content);
		try {
			content=URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		content=this.sysConfig.bestrowScreen(content);
		content = WebFrameUtil.filterText(content, false, false, true);
		System.out.println(content);
		search.put("PW_CONTENT", content);
		search.put("P_ID", "2009082300000011");
		search.put("USERID", "1");
		try {
			this.albumFacade.insertPhotoWord(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("成功发表评论").append("</value>");
			sb.append("</check>");
			xml = sb.toString();
			
			return "xml"; 
		} catch (Exception e) {
			e.printStackTrace();
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<check>");
			sb.append("<value>").append("出错了,请重试").append("</value>");
			sb.append("</check>");
			xml = sb.toString();
		}
		return "xml";
	
	}

	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}
	
	public AlbumFacade getAlbumFacade() {
		return albumFacade;
	}

	public void setAlbumFacade(AlbumFacade albumFacade) {
		this.albumFacade = albumFacade;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getA_Id() {
		return a_Id;
	}

	public void setA_Id(String id) {
		a_Id = id;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getAjaxCodeid() {
		return ajaxCodeid;
	}

	public void setAjaxCodeid(String ajaxCodeid) {
		this.ajaxCodeid = ajaxCodeid;
	}

	public String getAjaxMsg() {
		return ajaxMsg;
	}

	public void setAjaxMsg(String ajaxMsg) {
		this.ajaxMsg = ajaxMsg;
	}

	public String getP_Title() {
		return p_Title;
	}

	public void setP_Title(String title) {
		p_Title = title;
	}

	public String getP_Desc() {
		return p_Desc;
	}

	public void setP_Desc(String desc) {
		p_Desc = desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String getP_Id() {
		return p_Id;
	}

	public void setP_Id(String id) {
		p_Id = id;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getA_desc() {
		return a_desc;
	}

	public void setA_desc(String a_desc) {
		this.a_desc = a_desc;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
}
