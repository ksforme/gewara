package com.gewara.ucenter.vo.member;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gewara.api.vo.BaseVo;
import com.gewara.ucenter.constant.MemberConstant;
import com.gewara.util.JsonUtils;

public class MemberInfoVo extends BaseVo {
	private static final long serialVersionUID = -3838425704891306595L;

	private Long id;
	//从Member中移动到此处
	private String nickname;		//与Member中相同
	private String sex; // 性别
	private String tag; // 感兴趣的版块
	
	private String fromcity;
	private String realname;
	private String invitetype;//注册类型，比如：抽奖邀请
	private String source; //注册来源
	
	//系统信息
	private String regfrom;
	private String headpic; 		//头像
	private Timestamp addtime;
	private Timestamp updatetime;
	private Integer expvalue;
	private String newtask;//新手任务
	private String otherinfo;	//其他信息，如： usecard=xxxxx;changehis=2222
	private Integer pointvalue;//积分总值
	private Long inviteid; //邀请人ID
	private String ip; //注册IP

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public Timestamp getAddtime() {
		return addtime;
	}
	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public String getHeadpicUrl(){
		if(StringUtils.isNotBlank(headpic)) return headpic;
		return "img/default_head.png";
	}
	public String getLogo(){
		return getHeadpicUrl();
	}
	public Long getInviteid() {
		return inviteid;
	}
	
	public void setInviteid(Long inviteid) {
		this.inviteid = inviteid;
	}

	public String getInvitetype() {
		return invitetype;
	}

	public void setInvitetype(String invitetype) {
		this.invitetype = invitetype;
	}


	public String getRegfrom() {
		return regfrom;
	}

	public void setRegfrom(String regfrom) {
		this.regfrom = regfrom;
	}
	public String getNewtask() {
		return newtask;
	}

	public void setNewtask(String newtask) {
		this.newtask = newtask;
	}

	public MemberInfoVo() {
	}
	
	public Integer getExpvalue() {
		return expvalue;
	}

	public void setExpvalue(Integer expvalue) {
		this.expvalue = expvalue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public Serializable realId() {
		return id;
	}
	
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getFromcity() {
		return fromcity;
	}

	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}

	public List<String> getFinishedTaskList() {
		if(StringUtils.isBlank(newtask)) return new ArrayList<String>();
		return Arrays.asList(newtask.split(","));
	}
	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}
	public Integer getPointvalue() {
		return pointvalue;
	}
	public void setPointvalue(Integer pointvalue) {
		this.pointvalue = pointvalue;
	}
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}

	public List<String> getNewTaskList(){
		if(StringUtils.isBlank(this.newtask)) return new ArrayList<String>();
		return Arrays.asList(StringUtils.split(this.newtask, ","));
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public boolean isRegisterSource(String rsource){
		if(StringUtils.isBlank(rsource)) return false;
		return StringUtils.equals(this.source, rsource);
	}
	
	public boolean isBindSuccess(){
		String value = JsonUtils.getJsonValueByKey(this.otherinfo, MemberConstant.TAG_SOURCE);
		if(StringUtils.isBlank(value)) return true;
		return StringUtils.equals(value, "success");
	}
	
	public int getBuyticket(){ //返回1标识购票用户
		List<String> taskList = new ArrayList<String>();
		if(StringUtils.isNotBlank(getNewtask()))  {
			taskList = new ArrayList(Arrays.asList(StringUtils.split(getNewtask(), ",")));
		}
		if(taskList.contains(MemberConstant.TASK_BUYED_TICKET)) return 1;
		return 0;
	}
	
	public boolean isFinishedTask(String task) {
		if(StringUtils.isNotBlank(this.getNewtask())){
			return ! MemberConstant.TASK_LIST.contains(task) /**不存在的任务*/
				||getNewtask().contains(task);
		}else {
			return false;
		}
	}
}
