package com.qzgf.application.systemManage.taglib.config;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qzgf.application.systemManage.taglib.domain.ConfigFacade;
import com.qzgf.application.systemManage.taglib.dto.Config;

public class SysConfig {

	private static final Log logger = LogFactory.getLog(SysConfig.class);

	private HashMap<String, Config> configs;

	private ConfigFacade configFacade;

    public ConfigFacade getConfigFacade() {
		return configFacade;
	}

	public void setConfigFacade(ConfigFacade configFacade) {
		this.configFacade = configFacade;
	}

	public HashMap<String, Config> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, Config> configs) {
		this.configs = configs;
	}

	public void init(){
		
	}
	
	
	public SysConfig(){
		this.configs = new HashMap<String, Config>();
		this.loadConfigs();
	}

	@SuppressWarnings("unchecked")
	public void loadConfigs() {
		logger.info("Load sys config...");								 
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		configFacade=(ConfigFacade)context.getBean("configFacade");  
		List l = this.configFacade.findConfigs();
		for (int i = 0; i < l.size(); i++) {
			Config config = (Config) l.get(i);
			this.configs.put(config.getId(), config);
		}
		logger.info("Load sys config complete," + l.size() + " items is loaded.");
	}

	public Config getConfigContext(String key) {
		return (Config) this.configs.get(key);
	}

	public void setConfigContext(String key, String confContext) {
		Config config = this.getConfigContext(key);
		config.setConfContext(confContext);
		this.configs.put(key, config);
	}

	public int getIntValue(String key, int defaultValue) {
		Config config = this.getConfigContext(key);
		return NumberUtils.toInt(config.getConfContext(), defaultValue);
	}

	public String getStringValue(String key) {
		Config config = this.getConfigContext(key);
		return StringUtils.trimToEmpty(config.getConfContext());
	}

	public float getFloatValue(String key, float defaultValue) {
		Config config = this.getConfigContext(key);
		return NumberUtils.toFloat(config.getConfContext(), defaultValue);
	}

	public int getIsOpen() {
		return this.getIntValue("IsOpen", 1);
	}

	public void setIsOpen(int isOpen) {
		this.setConfigContext("IsOpen", String.valueOf(isOpen));
	}

	public boolean isOpen() {
		return (this.getIsOpen() == 1);
	}

	public String getCloseReason() {
		return this.getStringValue("CloseReason");
	}

	public void setCloseReason(String closeReason) {
		this.setConfigContext("CloseReason", closeReason);
	}

	public String getGuideName() {
		return this.getStringValue("GuideName");
	}
	
	
	public void setGuideName(String guideName) {
		this.setConfigContext("GuideName", guideName);
	}

	public String getGuideUrl() {
		return this.getStringValue("GuideUrl");
	}

	public void setGuideUrl(String guideUrl) {
		this.setConfigContext("GuideUrl", guideUrl);
	}

	public String getWebName() {
		return this.getStringValue("WebName");
	}

	public void setWebName(String webName) {
		this.setConfigContext("WebName", webName);
	}

	public String getWebUrl() {
		return this.getStringValue("WebUrl");
	}

	public void setWebUrl(String webUrl) {
		this.setConfigContext("WebUrl", webUrl);
	}

	public String getWebmasterEmail() {
		return this.getStringValue("WebmasterEmail");
	}

	public void setWebmasterEmail(String webmasterEmail) {
		this.setConfigContext("WebmasterEmail", webmasterEmail);
	}

	public String getPrivacyUrl() {
		return this.getStringValue("PrivacyUrl");
	}

	public void setPrivacyUrl(String privacyUrl) {
		this.setConfigContext("PrivacyUrl", privacyUrl);
	}

	public String getCopyRightMsg() {
		return this.getStringValue("CopyRightMsg");
	}

	public void setCopyRightMsg(String copyRightMsg) {
		this.setConfigContext("CopyRightMsg", copyRightMsg);
	}

	public String getMetaKeywords() {
		return this.getStringValue("MetaKeywords");
	}

	public void setMetaKeywords(String metaKeywords) {
		this.setConfigContext("MetaKeywords", metaKeywords);
	}

	public String getMetaDescription() {
		return this.getStringValue("MetaDescription");
	}

	public void setMetaDescription(String metaDescription) {
		this.setConfigContext("MetaDescription", metaDescription);
	}

	public int getCanSeePageNum() {
		return this.getIntValue("CanSeePageNum", 10);
	}

	public void setCanSeePageNum(int canSeePageNum) {
		this.setConfigContext("CanSeePageNum", String.valueOf(canSeePageNum));
	}

	public int getUseSafeLogin() {
		return this.getIntValue("UseSafeLogin", 0);
	}

	public void setUseSafeLogin(int useSafeLogin) {
		this.setConfigContext("UseSafeLogin", String.valueOf(useSafeLogin));
	}

	public boolean isUseSafeLogin() {
		return (getUseSafeLogin() == 1);
	}

	public int getUseAuthCode() {
		return this.getIntValue("UseAuthCode", 1);
	}

	public void setUseAuthCode(int useAuthCode) {
		this.setConfigContext("UseAuthCode", String.valueOf(useAuthCode));
	}

	public boolean isUseAuthCode() {
		return (getUseAuthCode() == 1);
	}

	public int getUseRegAuthCode() {
		return this.getIntValue("UseRegAuthCode", 1);
	}

	public void setUseRegAuthCode(int useRegAuthCode) {
		this.setConfigContext("UseRegAuthCode", String.valueOf(useRegAuthCode));
	}

	public boolean isUseRegAuthCode() {
		return (getUseRegAuthCode() == 1);
	}

	public String getLogoutUrl() {
		return this.getStringValue("LogoutUrl");
	}

	public void setLogoutUrl(String logoutUrl) {
		this.setConfigContext("LogoutUrl", logoutUrl);
	}

	public int getDateShowType() {
		return this.getIntValue("DateShowType", 0);
	}

	public void setDateShowType(int dateShowType) {
		this.setConfigContext("DateShowType", String.valueOf(dateShowType));
	}

	public int getDefaultTimeZone() {
		return this.getIntValue("DefaultTimeZone", 0);
	}

	public void setDefaultTimeZone(int defaultTimeZone) {
		this.setConfigContext("DefaultTimeZone", String.valueOf(defaultTimeZone));
	}

	public String getForumDateTimeFormat() {
		return this.getStringValue("ForumDateTimeFormat");
	}

	public void setForumDateTimeFormat(String forumDateTimeFormat) {
		this.setConfigContext("ForumDateTimeFormat", forumDateTimeFormat);
	}

	public String getPostDateTimeFormat() {
		return this.getStringValue("PostDateTimeFormat");
	}

	public void setPostDateTimeFormat(String postDateTimeFormat) {
		this.setConfigContext("PostDateTimeFormat", postDateTimeFormat);
	}

	public String getOtherDateTimeFormat() {
		return this.getStringValue("OtherDateTimeFormat");
	}

	public void setOtherDateTimeFormat(String otherDateTimeFormat) {
		this.setConfigContext("OtherDateTimeFormat", otherDateTimeFormat);
	}

	public String getRegDateTimeFormat() {
		return this.getStringValue("RegDateTimeFormat");
	}

	public void setRegDateTimeFormat(String regDateTimeFormat) {
		this.setConfigContext("RegDateTimeFormat", regDateTimeFormat);
	}

	public String getBirthDateTimeFormat() {
		return this.getStringValue("BirthDateTimeFormat");
	}

	public void setBirthDateTimeFormat(String birthDateTimeFormat) {
		this.setConfigContext("BirthDateTimeFormat", birthDateTimeFormat);
	}

	public String getTimeFormat() {
		return this.getStringValue("TimeFormat");
	}

	public void setTimeFormat(String timeFormat) {
		this.setConfigContext("TimeFormat", timeFormat);
	}

	public String getCookiePath() {
		return this.getStringValue("CookiePath");
	}

	public void setCookiePath(String cookiePath) {
		this.setConfigContext("CookiePath", cookiePath);
	}

	public String getCookieDomain() {
		return this.getStringValue("CookieDomain");
	}

	public void setCookieDomain(String cookieDomain) {
		this.setConfigContext("CookieDomain", cookieDomain);
	}

	public String getCookieKey() {
		return this.getStringValue("CookieKey");
	}

	public void setCookieKey(String cookieKey) {
		this.setConfigContext("CookieKey", cookieKey);
	}

	public int getUsePass() {
		return this.getIntValue("UsePass", 0);
	}

	public void setUsePass(int usePass) {
		this.setConfigContext("UsePass", String.valueOf(usePass));
	}

	public boolean isUsePass() {
		return (getUsePass() == 1);
	}

	public String getPassUrl() {
		return this.getStringValue("PassUrl");
	}

	public void setPassUrl(String passUrl) {
		this.setConfigContext("PassUrl", passUrl);
	}

	public String getPassRegUrl() {
		return this.getStringValue("PassRegUrl");
	}

	public void setPassRegUrl(String passRegUrl) {
		this.setConfigContext("PassRegUrl", passRegUrl);
	}

	public String getPassChangeUrl() {
		return this.getStringValue("PassChangeUrl");
	}

	public void setPassChangeUrl(String passChangeUrl) {
		this.setConfigContext("PassChangeUrl", passChangeUrl);
	}

	public int getUseScreen() {
		return this.getIntValue("UseScreen", 1);
	}

	public void setUseScreen(int useScreen) {
		this.setConfigContext("UseScreen", String.valueOf(useScreen));
	}

	public String getBestrowScreen() {
		return this.getStringValue("BestrowScreen");
	}

	public void setBestrowScreen(String bestrowScreen) {
		this.setConfigContext("BestrowScreen", bestrowScreen);
	}

	public String getScreenWord() {
		return this.getStringValue("ScreenWord");
	}

	public void setScreenWord(String screenWord) {
		this.setConfigContext("ScreenWord", screenWord);
	}

	public int getUseEmail() {
		return this.getIntValue("UseEmail", 1);
	}

	public void setUseEmail(int useEmail) {
		this.setConfigContext("UseEmail", String.valueOf(useEmail));
	}

	public int getSmtpAuth() {
		return this.getIntValue("SmtpAuth", 1);
	}

	public void setSmtpAuth(int smtpAuth) {
		this.setConfigContext("SmtpAuth", String.valueOf(smtpAuth));
	}

	public String getSenderEmail() {
		return this.getStringValue("SenderEmail");
	}

	public void setSenderEmail(String senderEmail) {
		this.setConfigContext("SenderEmail", senderEmail);
	}

	public String getSmtpServer() {
		return this.getStringValue("SmtpServer");
	}

	public void setSmtpServer(String smtpServer) {
		this.setConfigContext("SmtpServer", smtpServer);
	}

	public int getSmtpPort() {
		return this.getIntValue("SmtpPort", 25);
	}

	public void setSmtpPort(int smtpPort) {
		this.setConfigContext("SmtpPort", String.valueOf(smtpPort));
	}

	public String getSmtpUser() {
		return this.getStringValue("SmtpUser");
	}

	public void setSmtpUser(String smtpUser) {
		this.setConfigContext("SmtpUser", smtpUser);
	}

	public String getSmtpPasswd() {
		return this.getStringValue("SmtpPasswd");
	}

	public void setSmtpPasswd(String smtpPasswd) {
		this.setConfigContext("SmtpPasswd", smtpPasswd);
	}

	public int getOpenUserReg() {
		return this.getIntValue("OpenUserReg", 1);
	}

	public void setOpenUserReg(int openUserReg) {
		this.setConfigContext("OpenUserReg", String.valueOf(openUserReg));
	}

	public int getCheckRegUser() {
		return this.getIntValue("CheckRegUser", 0);
	}

	public boolean isCheckRegUser() {
		return (this.getCheckRegUser() == 1);
	}

	public void setCheckRegUser(int checkRegUser) {
		this.setConfigContext("CheckRegUser", String.valueOf(checkRegUser));
	}

	public int getCheckRegUserEmail() {
		return this.getIntValue("CheckRegUserEmail", 0);
	}

	public boolean isCheckRegUserEmail() {
		return (this.getCheckRegUserEmail() == 1);
	}

	public void setCheckRegUserEmail(int checkRegUserEmail) {
		this.setConfigContext("CheckRegUserEmail", String.valueOf(checkRegUserEmail));
	}

	public String getCanNotRegUserName() {
		return this.getStringValue("CanNotRegUserName");
	}

	public void setCanNotRegUserName(String canNotRegUserName) {
		this.setConfigContext("CanNotRegUserName", canNotRegUserName);
	}

	public String getCanNotUseNickName() {
		return this.getStringValue("CanNotUseNickName");
	}

	public void setCanNotUseNickName(String canNotUseNickName) {
		this.setConfigContext("CanNotUseNickName", canNotUseNickName);
	}

	public int getUseSign() {
		return this.getIntValue("UseSign", 1);
	}

	public void setUseSign(int useSign) {
		this.setConfigContext("UseSign", String.valueOf(useSign));
	}

	public int getSignMaxLen() {
		return this.getIntValue("SignMaxLen", 300);
	}

	public void setSignMaxLen(int signMaxLen) {
		this.setConfigContext("SignMaxLen", String.valueOf(signMaxLen));
	}

	public int getSignUseHtml() {
		return this.getIntValue("SignUseHtml", 0);
	}

	public void setSignUseHtml(int signUseHtml) {
		this.setConfigContext("SignUseHtml", String.valueOf(signUseHtml));
	}

	public boolean isSignUseHtml() {
		return (this.getSignUseHtml() == 1);
	}

	public int getSignUseUBB() {
		return this.getIntValue("SignUseUBB", 0);
	}

	public void setSignUseUBB(int signUseUBB) {
		this.setConfigContext("SignUseUBB", String.valueOf(signUseUBB));
	}

	public boolean isSignUseUBB() {
		return (this.getSignUseUBB() == 1);
	}

	public int getSignUseSmile() {
		return this.getIntValue("SignUseSmile", 1);
	}

	public void setSignUseSmile(int signUseSmile) {
		this.setConfigContext("SignUseSmile", String.valueOf(signUseSmile));
	}

	public boolean isSignUseSmile() {
		return (this.getSignUseSmile() == 1);
	}

	public int getUseFace() {
		return this.getIntValue("UseFace", 1);
	}

	public void setUseFace(int useFace) {
		this.setConfigContext("UseFace", String.valueOf(useFace));
	}

	public int getFaceHigh() {
		return this.getIntValue("FaceHigh", 100);
	}

	public void setFaceHigh(int faceHigh) {
		this.setConfigContext("FaceHigh", String.valueOf(faceHigh));
	}

	public int getFaceWidth() {
		return this.getIntValue("FaceWidth", 100);
	}

	public void setFaceWidth(int faceWidth) {
		this.setConfigContext("FaceWidth", String.valueOf(faceWidth));
	}

	public int getFaceSize() {
		return this.getIntValue("FaceSize", 50);
	}

	public void setFaceSize(int faceSize) {
		this.setConfigContext("FaceSize", String.valueOf(faceSize));
	}

	public int getUseForbid() {
		return this.getIntValue("UseForbid", 1);
	}

	public void setUseForbid(int useForbid) {
		this.setConfigContext("UseForbid", String.valueOf(useForbid));
	}

	public String getForbidIP() {
		return this.getStringValue("ForbidIP");
	}

	public void setForbidIP(String forbidIP) {
		this.setConfigContext("ForbidIP", forbidIP);
	}

	public String getForbidEmail() {
		return this.getStringValue("ForbidEmail");
	}

	public void setForbidEmail(String forbidEmail) {
		this.setConfigContext("ForbidEmail", forbidEmail);
	}

	public int getPostMinSize() {
		return this.getIntValue("PostMinSize", 3);
	}

	public void setPostMinSize(int postMinSize) {
		this.setConfigContext("PostMinSize", String.valueOf(postMinSize));
	}

	public int getPostMaxSize() {
		return this.getIntValue("PostMaxSize", 150000);
	}

	public void setPostMaxSize(int postMaxSize) {
		this.setConfigContext("PostMaxSize", String.valueOf(postMaxSize));
	}

	public int getQuoteMaxSize() {
		return this.getIntValue("QuoteMaxSize", 300);
	}

	public void setQuoteMaxSize(int quoteMaxSize) {
		this.setConfigContext("QuoteMaxSize", String.valueOf(quoteMaxSize));
	}

	public int getPostCheckTime() {
		return this.getIntValue("PostCheckTime", 0);
	}

	public void setPostCheckTime(int postCheckTime) {
		this.setConfigContext("PostCheckTime", String.valueOf(postCheckTime));
	}

	public int getEditPostTitleLimit() {
		return this.getIntValue("EditPostTitleLimit", 0);
	}

	public void setEditPostTitleLimit(int editPostTitleLimit) {
		this.setConfigContext("EditPostTitleLimit", String.valueOf(editPostTitleLimit));
	}

	public int getEditPostLimit() {
		return this.getIntValue("EditPostLimit", 0);
	}

	public void setEditPostLimit(int editPostLimit) {
		this.setConfigContext("EditPostLimit", String.valueOf(editPostLimit));
	}

	public int getLogIP() {
		return this.getIntValue("LogIP", 0);
	}

	public void setLogIP(int logIP) {
		this.setConfigContext("LogIP", String.valueOf(logIP));
	}

	public int getEditInterface() {
		return this.getIntValue("EditInterface", 2);
	}

	public void setEditInterface(int editInterface) {
		this.setConfigContext("EditInterface", String.valueOf(editInterface));
	}

	public int getAttachmentNum() {
		return this.getIntValue("AttachmentNum", 10);
	}

	public void setAttachmentNum(int attachmentNum) {
		this.setConfigContext("AttachmentNum", String.valueOf(attachmentNum));
	}

	public int getCanDelAttachmentOverTime() {
		return this.getIntValue("CanDelAttachmentOverTime", 1);
	}

	public void setCanDelAttachmentOverTime(int canDelAttachmentOverTime) {
		this.setConfigContext("CanDelAttachmentOverTime", String.valueOf(canDelAttachmentOverTime));
	}

	public int getCanDelAttachmentClosedPost() {
		return this.getIntValue("CanDelAttachmentClosedPost", 1);
	}

	public void setCanDelAttachmentClosedPost(int canDelAttachmentClosedPost) {
		this.setConfigContext("CanDelAttachmentClosedPost", String.valueOf(canDelAttachmentClosedPost));
	}

	public int getReduceAttachImg() {
		return this.getIntValue("ReduceAttachImg", 1);
	}

	public void setReduceAttachImg(int reduceAttachImg) {
		this.setConfigContext("ReduceAttachImg", String.valueOf(reduceAttachImg));
	}

	public int getViewAttachImg() {
		return this.getIntValue("ViewAttachImg", 1);
	}

	public void setViewAttachImg(int viewAttachImg) {
		this.setConfigContext("ViewAttachImg", String.valueOf(viewAttachImg));
	}

	public int getReduceAttachImgSize() {
		return this.getIntValue("ReduceAttachImgSize", 200);
	}

	public void setReduceAttachImgSize(int reduceAttachImgSize) {
		this.setConfigContext("ReduceAttachImgSize", String.valueOf(reduceAttachImgSize));
	}

	public int getAttachImgRow() {
		return this.getIntValue("AttachImgRow", 3);
	}

	public void setAttachImgRow(int attachImgRow) {
		this.setConfigContext("AttachImgRow", String.valueOf(attachImgRow));
	}

	public String getAttachImgType() {
		return this.getStringValue("AttachImgType");
	}

	public void setAttachImgType(String attachImgType) {
		this.setConfigContext("AttachImgType", attachImgType);
	}

	public String[] getAttachImgTypes() {
		String[] types = getAttachImgType().split(",");
		if (types == null || types.length == 0) {
			types = new String[3];
			types[0] = "gif";
			types[1] = "jpg";
			types[2] = "jpeg";
		}
		return types;
	}

	public boolean isAttachImg(String fileName) {
		return FilenameUtils.isExtension(fileName, getAttachImgTypes());
	}

	public String getAttachFileType() {
		return this.getStringValue("AttachFileType");
	}

	public void setAttachFileType(String attachFileType) {
		this.setConfigContext("AttachFileType", attachFileType);
	}

	public boolean isAllowAttachFileType(String fileName) {
		String[] ftypes = this.getAttachFileType().split(",");
		if (ftypes != null) {
			return FilenameUtils.isExtension(fileName, ftypes);
		}
		return false;
	}

	public int getAttachFileSize() {
		return this.getIntValue("AttachFileSize", 300);
	}

	public void setAttachFileSize(int attachFileSize) {
		this.setConfigContext("AttachFileSize", String.valueOf(attachFileSize));
	}

	public int getVoteItemNum() {
		return this.getIntValue("VoteItemNum", 10);
	}

	public void setVoteItemNum(int voteItemNum) {
		this.setConfigContext("VoteItemNum", String.valueOf(voteItemNum));
	}

	public int getVoteItemLength() {
		return this.getIntValue("VoteItemLength", 150);
	}

	public void setVoteItemLength(int voteItemLength) {
		this.setConfigContext("VoteItemLength", String.valueOf(voteItemLength));
	}

	public int getVoteUpdatePost() {
		return this.getIntValue("VoteUpdatePost", 1);
	}

	public void setVoteUpdatePost(int voteUpdatePost) {
		this.setConfigContext("VoteUpdatePost", String.valueOf(voteUpdatePost));
	}

	public int getVoteChange() {
		return this.getIntValue("VoteChange", 0);
	}

	public void setVoteChange(int voteChange) {
		this.setConfigContext("VoteChange", String.valueOf(voteChange));
	}

	public int getUsePm() {
		return this.getIntValue("UsePm", 1);
	}

	public boolean isUserPm() {
		return (this.getUsePm() == 1);
	}

	public void setUsePm(int usePm) {
		this.setConfigContext("UsePm", String.valueOf(usePm));
	}

	public int getCheckNewPm() {
		return this.getIntValue("CheckNewPm", 0);
	}

	public boolean isCheckNewPm() {
		return (this.getCheckNewPm() == 1);
	}

	public void setCheckNewPm(int checkNewPm) {
		this.setConfigContext("CheckNewPm", String.valueOf(checkNewPm));
	}

	public int getPmMaxLength() {
		return this.getIntValue("PmMaxLength", 1500);
	}

	public void setPmMaxLength(int pmMaxLength) {
		this.setConfigContext("PmMaxLength", String.valueOf(pmMaxLength));
	}

	public int getPmFloodTime() {
		return this.getIntValue("PmFloodTime", 30);
	}

	public void setPmFloodTime(int pmFloodTime) {
		this.setConfigContext("PmFloodTime", String.valueOf(pmFloodTime));
	}

	public boolean isInPmFloodTime(long atime) {
		if ((System.currentTimeMillis() - atime) < (getPmFloodTime() * 1000)) {
			return true;
		} else {
			return false;
		}
	}

	public int getPmPerPage() {
		return this.getIntValue("PmPerPage", 5);
	}

	public void setPmPerPage(int pmPerPage) {
		this.setConfigContext("PmPerPage", String.valueOf(pmPerPage));
	}

	public int getPmAllowUBB() {
		return this.getIntValue("PmAllowUBB", 0);
	}

	public boolean isPmAllowUBB() {
		return (this.getPmAllowUBB() == 1);
	}

	public void setPmAllowUBB(int pmAllowUBB) {
		this.setConfigContext("PmAllowUBB", String.valueOf(pmAllowUBB));
	}

	public int getPmAllowSmilies() {
		return this.getIntValue("PmAllowSmilies", 1);
	}

	public boolean isPmAllowSmilies() {
		return (this.getPmAllowSmilies() == 1);
	}

	public void setPmAllowSmilies(int pmAllowSmilies) {
		this.setConfigContext("PmAllowSmilies", String.valueOf(pmAllowSmilies));
	}

	public int getPmAllowHTML() {
		return this.getIntValue("PmAllowHTML", 0);
	}

	public boolean isPmAllowHTML() {
		return (this.getPmAllowHTML() == 1);
	}

	public void setPmAllowHTML(int pmAllowHTML) {
		this.setConfigContext("PmAllowHTML", String.valueOf(pmAllowHTML));
	}

	public int getPmAllowFace() {
		return this.getIntValue("PmAllowFace", 1);
	}

	public boolean isPmAllowFace() {
		return (this.getPmAllowFace() == 1);
	}

	public void setPmAllowFace(int pmAllowFace) {
		this.setConfigContext("PmAllowFace", String.valueOf(pmAllowFace));
	}

	public int getForumPrePage() {
		return this.getIntValue("ForumPrePage", 20);
	}

	public void setForumPrePage(int forumPrePage) {
		this.setConfigContext("ForumPrePage", String.valueOf(forumPrePage));
	}

	public int getForumHotViews() {
		return this.getIntValue("ForumHotViews", 150);
	}

	public void setForumHotViews(int forumHotViews) {
		this.setConfigContext("ForumHotViews", String.valueOf(forumHotViews));
	}

	public int getForumHotRes() {
		return this.getIntValue("ForumHotRes", 15);
	}

	public void setForumHotRes(int forumHotRes) {
		this.setConfigContext("ForumHotRes", String.valueOf(forumHotRes));
	}

	public int getUseLinkToPages() {
		return this.getIntValue("UseLinkToPages", 1);
	}

	public boolean isUseLinkToPages() {
		return (this.getUseLinkToPages() == 1);
	}

	public void setUseLinkToPages(int useLinkToPages) {
		this.setConfigContext("UseLinkToPages", String.valueOf(useLinkToPages));
	}

	public int getMaxMultiPage() {
		return this.getIntValue("MaxMultiPage", 3);
	}

	public void setMaxMultiPage(int maxMultiPage) {
		this.setConfigContext("MaxMultiPage", String.valueOf(maxMultiPage));
	}

	public int getPostViewLength() {
		return this.getIntValue("PostViewLength", 300);
	}

	public void setPostViewLength(int postViewLength) {
		this.setConfigContext("PostViewLength", String.valueOf(postViewLength));
	}

	public int getPostPerPage() {
		return this.getIntValue("PostPerPage", 10);
	}

	public void setPostPerPage(int postPerPage) {
		this.setConfigContext("PostPerPage", String.valueOf(postPerPage));
	}

	public String getUserPerPage() {
		return this.getStringValue("UserPerPage");
	}

	public void setUserPerPage(String userPerPage) {
		this.setConfigContext("UserPerPage", userPerPage);
	}

	public String getPostDefFaceImg() {
		return this.getStringValue("PostDefFaceImg");
	}

	public void setPostDefFaceImg(String postDefFaceImg) {
		this.setConfigContext("PostDefFaceImg", postDefFaceImg);
	}

	public int getPostVoteRec() {
		return this.getIntValue("PostVoteRec", 1);
	}

	public boolean isPostVoteRec() {
		return (this.getPostVoteRec() == 1);
	}

	public void setPostVoteRec(int postVoteRec) {
		this.setConfigContext("PostVoteRec", String.valueOf(postVoteRec));
	}

	public int getPostShowUserImg() {
		return this.getIntValue("PostShowUserImg", 1);
	}

	public boolean isPostShowUserImg() {
		return (this.getPostShowUserImg() == 1);
	}

	public void setPostShowUserImg(int postShowUserImg) {
		this.setConfigContext("PostShowUserImg", String.valueOf(postShowUserImg));
	}

	public int getPostHiddenTypeMoney() {
		return this.getIntValue("PostHiddenTypeMoney", 0);
	}

	public void setPostHiddenTypeMoney(int postHiddenTypeMoney) {
		this.setConfigContext("PostHiddenTypeMoney", String.valueOf(postHiddenTypeMoney));
	}

	public int getPostHiddenTypeRe() {
		return this.getIntValue("PostHiddenTypeRe", 0);
	}

	public void setPostHiddenTypeRe(int postHiddenTypeRe) {
		this.setConfigContext("PostHiddenTypeRe", String.valueOf(postHiddenTypeRe));
	}

	public int getPostHiddenTypeArtNum() {
		return this.getIntValue("PostHiddenTypeArtNum", 0);
	}

	public void setPostHiddenTypeArtNum(int postHiddenTypeArtNum) {
		this.setConfigContext("PostHiddenTypeArtNum", String.valueOf(postHiddenTypeArtNum));
	}

	public String getPostPriceList() {
		return this.getStringValue("PostPriceList");
	}

	public void setPostPriceList(String postPriceList) {
		this.setConfigContext("PostPriceList", postPriceList);
	}

	public String[] getPostPriceLists() {
		String[] prices = this.getPostPriceList().split(",");
		if (prices == null) {
			prices = new String[1];
			prices[0] = "0";
		}
		return prices;
	}

	public float getPostPriceTax() {
		return this.getFloatValue("PostPriceTax", (float) 0.5);
	}

	public void setPostPriceTax(float postPriceTax) {
		this.setConfigContext("PostPriceTax", String.valueOf(postPriceTax));
	}

	public int getUserOnlineTime() {
		return this.getIntValue("UserOnlineTime", 300);
	}

	public void setUserOnlineTime(int userOnlineTime) {
		this.setConfigContext("UserOnlineTime", String.valueOf(userOnlineTime));
	}

	public int getUsePostPeriodOfTime() {
		return this.getIntValue("UsePostPeriodOfTime", 0);
	}

	public void setUsePostPeriodOfTime(int usePostPeriodOfTime) {
		this.setConfigContext("UsePostPeriodOfTime", String.valueOf(usePostPeriodOfTime));
	}

	public boolean isUsePostPeriodOfTime() {
		return this.getUsePostPeriodOfTime() == 1;
	}

	public int getPostPeriodOfTimeDay() {
		return this.getIntValue("PostPeriodOfTimeDay", 1);
	}

	public void setPostPeriodOfTimeDay(int postPeriodOfTimeDay) {
		this.setConfigContext("PostPeriodOfTimeDay", String.valueOf(postPeriodOfTimeDay));
	}

	public int getPostPeriodOfTimeStart() {
		return this.getIntValue("PostPeriodOfTimeStart", 9);
	}

	public void setPostPeriodOfTimeStart(int postPeriodOfTimeStart) {
		this.setConfigContext("PostPeriodOfTimeStart", String.valueOf(postPeriodOfTimeStart));
	}

	public int getPostPeriodOfTimeEnd() {
		return this.getIntValue("PostPeriodOfTimeEnd", 20);
	}

	public void setPostPeriodOfTimeEnd(int postPeriodOfTimeEnd) {
		this.setConfigContext("PostPeriodOfTimeEnd", String.valueOf(postPeriodOfTimeEnd));
	}

	public int getPostToHistoryDay() {
		return this.getIntValue("PostToHistoryDay", 90);
	}

	public void setPostToHistoryDay(int postToHistoryDay) {
		this.setConfigContext("PostToHistoryDay", String.valueOf(postToHistoryDay));
	}

	public boolean isInPostPeriodOfTime(long nowtime) {
		long starttime = 0;
		long endtime = 0;
		Calendar cld = Calendar.getInstance();
		int year = cld.get(Calendar.YEAR);
		int month = cld.get(Calendar.MONTH);
		int day = cld.get(Calendar.DAY_OF_MONTH);
		Calendar todaycld = Calendar.getInstance();
		todaycld.set(year, month, day, this.getPostPeriodOfTimeStart(), 0, 0);
		starttime = todaycld.getTime().getTime();
		Calendar cldend = Calendar.getInstance();
		cldend.set(year, month, day, this.getPostPeriodOfTimeEnd(), 0, 0);
		endtime = cldend.getTime().getTime();

		if (nowtime >= starttime && nowtime <= endtime) {
			return true;
		} else {
			return false;
		}
	}

	public String bestrowScreen(String txt) {
		if (StringUtils.isNotBlank(this.getScreenWord())) {
			String[] words = this.getScreenWord().split(";");
			for (int i = 0; i < words.length; i++) {
				txt = txt.replaceAll(words[i], this.getBestrowScreen());
			}
		}
		return txt;
	}

	public boolean containScreenWord(String txt) {
		if (StringUtils.isNotBlank(this.getScreenWord())) {
			String[] words = this.getScreenWord().split(";");
			for (int i = 0; i < words.length; i++) {
				Pattern p = Pattern.compile(words[i]);
				Matcher m = p.matcher(txt);
				if (m.find()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isCanNotRegUserName(String userName) {
		if (StringUtils.isNotBlank(this.getCanNotRegUserName())) {
			String[] names = this.getCanNotRegUserName().split(",");
			for (int i = 0; i < names.length; i++) {
				if (userName.indexOf(names[i]) != -1) {
					return true;
				}
			}
		}
		return false;
	}

	public String bestrowScreenNickName(String txt) {
		if (StringUtils.isNotBlank(this.getCanNotUseNickName())) {
			String[] words = this.getCanNotUseNickName().split(";");
			for (int i = 0; i < words.length; i++) {
				txt = txt.replaceAll(words[i], this.getBestrowScreen());
			}
		}
		return txt;
	}

	public boolean isForbidIP(String ip) {
		if (StringUtils.isNotBlank(this.getForbidIP())) {
			String[] fips = this.getForbidIP().split(",");
			for (int i = 0; i < fips.length; i++) {
				if (ip.equals(fips[i]) || ip.startsWith(fips[i])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isForbidEmail(String email) {
		if (StringUtils.isNotBlank(this.getForbidEmail())) {
			String[] femails = this.getForbidEmail().split(",");
			for (int i = 0; i < femails.length; i++) {
				if (email.equals(femails[i]) || email.endsWith(femails[i])) {
					return true;
				}
			}
		}
		return false;
	}

	public String[] getUserPostPerPageNum() {
		String ppn = this.getUserPerPage();
		if (StringUtils.isBlank(ppn)) {
			String[] ppns = { String.valueOf(this.getPostPerPage()) };
			return ppns;
		} else {
			return ppn.split(",");
		}
	}


}
