/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.javashop.core.service.impl;
import java.util.List;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.eop.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberManager;

/**
 * 会员管理
 * @author kingapex
 *2010-4-30上午10:07:24
 */
public class MemberManager extends BaseSupport<Member> implements IMemberManager{
 
	private IMemberLvManager memberLvManager;
	
	
	public int add(Member member) {
		
		if(member==null) throw new IllegalArgumentException("member is null");
		if(member.getUname()==null) throw new IllegalArgumentException("member' uname is null");
		if(member.getPassword() ==null) throw new IllegalArgumentException("member' password is null");
		if(member.getEmail()==null) throw new IllegalArgumentException("member'email is null");
		
		if(this.checkname( member.getUname()) ==1){
			return 0;
		}
		
		Integer lvid  = memberLvManager.getDefaultLv();
		member.setLv_id(lvid);
		
		member.setSex(0);
		member.setProvince_id(0);
		member.setCity_id(0);
		member.setRegion_id(0);
		member.setPoint(0);
		member.setAdvance(0D);
		member.setRegtime(System.currentTimeMillis());//lzf add
		member.setPassword(StringUtil.md5(member.getPassword()));
		this.baseDaoSupport.insert("member", member);
		return 1;
	}

	
	public Member get(Integer memberId) {
		String sql = "select * from member where member_id=?";
	    Member m = this.baseDaoSupport.queryForObject(sql, Member.class, memberId);
	    return m;
	}

	
	public Member getMemberByUname(String uname) {
		String sql = "select * from member where uname=?";
	    List list = this.baseDaoSupport.queryForList(sql, Member.class, uname);
	    Member m = null;
	    if(list!=null && list.size()>0){
	    	m = (Member)list.get(0);
	    }
	    return m;
	} 

	
	public Member edit(Member member) {
		this.baseDaoSupport.update("member", member, "member_id="
				+ member.getMember_id());
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		return null;
	}



	
	public int checkname(String name) {
		String sql = "select count(0) from member where uname=?";
		int count = this.baseDaoSupport.queryForInt(sql, name);
		count = count > 0 ? 1 : 0;
		return count;
	}

	
	public Page list(String order, int page, int pageSize) {
		order = order == null ? " m.member_id desc" : order;
		String sql = "select m.*,lv.name as lv_name from "+this.getTableName("member")+" m left join " +this.getTableName("member_lv")+" lv on m.lv_id = lv.lv_id ";
		//sql += "  and lv.userid = "+this.getCurrentUserid()+" and lv.siteid="+this.getCurrentSiteid();
		sql += " order by  " + order;
		Page webpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}
	
	
    public void delete(String id){
		if (id == null || id.equals(""))
			return;
		String sql = "delete from member where member_id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
    }



 

	
	public void updatePassword(String password) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		this.updatePassword(member.getMember_id(), password);
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		
	}
	

	
	public void updatePassword(Integer memberid, String password) {
		password = password == null ? StringUtil.md5("") : StringUtil
				.md5(password);
		String sql = "update member set password = ? where member_id =? ";
		this.baseDaoSupport.execute(sql,password,memberid);
	}
	

	
	public int login(String username, String password) {
		String sql = "select m.*,l.name as lvname from "+this.getTableName("member")+" m left join "+this.getTableName("member_lv")+" l on m.lv_id = l.lv_id where m.uname=? and password=?";
		List<Member > list  =this.daoSupport.queryForList(sql, Member.class, username,com.enation.framework.util.StringUtil.md5(password));
		if(list==null || list.isEmpty()  ){
			return 0;
		}
		 
		Member member = list.get(0);
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		return 1;
	}

	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}

	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

	
	public void addMoney(Integer memberid, Double num) {
		String sql  ="update member set advance=advance+? where member_id=?";
		this.baseDaoSupport.execute(sql, num,memberid);
	}

	
	
	
	public void cutMoney(Integer memberid, Double num) {
		Member member  = this.get(memberid);	
		if(member.getAdvance()<num){
			throw new  RuntimeException("预存款不足:需要["+num+"],剩余["+member.getAdvance()+"]");
		}
		String sql  ="update member set advance=advance-? where member_id=?";
		this.baseDaoSupport.execute(sql, num,memberid);
	}

	
}
