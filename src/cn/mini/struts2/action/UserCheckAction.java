package cn.mini.struts2.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import Utils.Md5Compute;
import Utils.MiniMailUtil;
import cn.mini.domain.SpaceDatum;
import cn.mini.domain.UnitDatum;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.domain.UserBaseDatum;
import cn.mini.exception.UserServiceException;
import cn.mini.formbean.SpaceDatumForm;
import cn.mini.formbean.UnitDatumForm;
import cn.mini.formbean.UserBaseDailsForm;
import cn.mini.formbean.UserBaseDatumForm;
import cn.mini.formbean.UserBaseForm;
import cn.mini.service.UserDatumService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("userCheckAction")
public class UserCheckAction extends ActionSupport implements ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private String code,name,token,email;
	private UserBase user;
	private UserBaseDatum ubm;
	private UserBaseDails uda;
	private SpaceDatum spd;
	private UnitDatum udm;
    private String confirmation;
	private int expiry,userId;
	private static String EMAI_URL;
	private HttpServletResponse response;
	
	@Resource(name="userServiceImpl")
	private UserService us=null;	    
	@Resource(name="userDatumServiceImpl")
	private UserDatumService udsl=null;	 

	public String mycode() {
		return "mycode";
	}
	public void code() throws IOException{
		String secode =	(String) ActionContext.getContext().getSession().get("code");
		if(secode==null||code==null||!code.equalsIgnoreCase(secode)){
			response.getWriter().write("code is error");
		}else{
			response.getWriter().write("code is yes");
		}
	}
	public void account() throws IOException{
		try {
			us.selectUserService(name);	
		} catch (Exception e) {
			response.getWriter().write("username is ok");
			return ;
		}
		response.getWriter().write("username is exsit");
	}
	public void email() throws IOException{
		try {
			UserBase uemail=udsl.exitsEmail(email);
			if(!uemail.getActive().equals("")){
				response.getWriter().write("email is ok");
				return;
			}	
		} catch (Exception e) {
			response.getWriter().write("email is ok");
			return ;
		}
		response.getWriter().write("email is exsit");
		
	}
	public void register() throws IOException {
		Map<String,Object> sesstion=ActionContext.getContext().getSession();
		try {
			us.selectUserService(name);
			response.getWriter().write("register is error");
			return;
		} catch (Exception e) {}
		try {
			UserBase uemail=udsl.exitsEmail(email);
			if(uemail.getActive().equals("")){
				response.getWriter().write("register is error");
				return;
			}		
		} catch (Exception e) {}
		try {
			//检验code 和token
			if(!code.equalsIgnoreCase((String) sesstion.get("code"))){
				throw new UserServiceException("code check is error");
			}
			if(!token.equals((String) sesstion.get("token"))){
				throw new UserServiceException("token check is error");
			}
			sesstion.remove("code");
			sesstion.remove("token");	
			
			user.setActive(UUID.randomUUID().toString().replace("-", "/"));
			
			UserBaseDails ubd=new UserBaseDails();
			ubd.setUser(user);
			UnitDatum ud=new UnitDatum();
			ud.setUser(user);
			ubm.setUser(user);
			if(ubm.getName().equals("")){
				ubm.setName(user.getName());
			}
			SpaceDatum sd=new SpaceDatum();
			sd.setUser(user);
			sd.setNickName(ubm.getName());		
			//检验表单Bean
			UserBaseForm userForm=new UserBaseForm(user);
			if(!userForm.CheckBean()){
				throw new Exception("用户数据异常");
			}
			UserBaseDatumForm userDaumForm=new UserBaseDatumForm(ubm);
			
			if(!userDaumForm.CheckBean()){
				throw new Exception("用户资料异常");
			}
			//保存客户信息
			user.setPassword(Md5Compute.getmd5(user.getPassword()));
			user.setDate(new java.sql.Date(new java.util.Date().getTime()));			
			user.setUserBaseDatum(ubm);
			us.saveUser(user);
			udsl.saveSpaceDatum(sd);
			udsl.saveUnitDatum(ud);
			udsl.saveUserBaseDails(ubd);
			udsl.saveUserBaseDatum(ubm);			
			
			if(EMAI_URL==null){
				Properties p = new Properties();
				InputStream in=null;
				try {
					in=UserCheckAction.class.getClassLoader().getResourceAsStream("../config/register.properties");
					p .load(in);
					EMAI_URL  =p.getProperty("emailURL");		
				} catch (IOException e) {
					
				}finally{
					if(in!=null){
						try {in.close();} catch (IOException e) {}
					}
					p=null;
				}
			}
			MiniMailUtil mini=MiniMailUtil.getInstance();
			mini.sendMail("请激活你的帐号，完成注册", "亲爱的"+user.getName()+":<br><br>欢迎加入Viki!<br><br>	请点击下面的链接完成注册:<br><br>"+
					EMAI_URL+"user/check_checkRegister?userId="+user.getId()+"&confirmation="+user.getActive()+"<br><br>如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进Viki。<br><br>如果您没有注册过本站账号请不要进行相关操作，以免对您造成损失！"
							, user.getEmail());
			
		} catch (Exception e) {
			response.getWriter().write("register is error："+e);
			return;
		}
		sesstion.put("sgin", user.getName());
		sesstion.put("id", user.getId());
		response.getWriter().write("register is ok");
	}
	public String checkRegister(){
		try {
			UserBase user=us.findUserService(userId);		
			if(user.getActive().equals("")){
				ActionContext.getContext().put("state", "对不起，该账号已经被激活，此链接已失效，本页面将在5秒后跳转。");
			}else if(user.getActive().equals(confirmation)){
				user.setActive("");
				us.updateUser(user);
				us.removeNoCheckUser(user.getEmail());
				ActionContext.getContext().put("state", "激活成功已经激活成功，本页面将在5秒后跳转。");
			}else {
				ActionContext.getContext().put("state", "很抱歉！您的账号激活失败");
			}
		} catch (Exception e) {
			return "my404";
		}
		return "checkRegister";
	}
	public void update() throws IOException {
		try {
			UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));		
			if(ubm!=null){
				UserBaseDatumForm uf=new UserBaseDatumForm();	
				UserBaseDatum tubd= user.getUserBaseDatum();
				tubd.setAddr(ubm.getAddr());
				tubd.setBloodGroup(ubm.getBloodGroup());
				tubd.setBirthday(ubm.getBirthday());
				tubd.setInfo(ubm.getInfo());
				tubd.setSex(ubm.getSex());
				uf.setUserDatum(tubd);
				if(!uf.CheckBean()){
					throw new Exception("数据异常");
				}
				udsl.updateUserBaseDatum(tubd);
			}else if(uda!=null){
				UserBaseDails tubd= udsl.getUserBaseDails(user);
				tubd.setDrinkHabits(uda.getDrinkHabits());
				tubd.setEducation(uda.getEducation());
				tubd.setFigure(uda.getFigure());
				tubd.setMaritalStatus(uda.getMaritalStatus());
				tubd.setMotto(uda.getMotto());
				tubd.setPersonalAddress(uda.getPersonalAddress());
				tubd.setPersonality(uda.getPersonality());
				tubd.setSleepHabits(uda.getSleepHabits());
				tubd.setSmokHabits(uda.getSmokHabits());
				tubd.setVoc(uda.getVoc());
				UserBaseDailsForm ubdf=new UserBaseDailsForm();
				ubdf.setUserDails(tubd);
				if(!ubdf.CheckBean()){
					throw new Exception("数据异常");
				}
				udsl.updateUserBaseDails(tubd);
			}else if(spd!=null){
				SpaceDatum tubd =udsl.getSpaceDatum(user);
				tubd.setNickName(spd.getNickName());
				tubd.setRank(spd.getRank());
				tubd.setSpacePassWord(spd.getSpacePassWord());
				tubd.setVisible(spd.getVisible());
				SpaceDatumForm sdf=new SpaceDatumForm(tubd);
				if(!sdf.CheckBean()){
					throw new Exception("数据异常");
				}
				UserBaseDatum userDa=user.getUserBaseDatum();
				userDa.setName(spd.getNickName());
				udsl.updateUserBaseDatum(userDa);
				udsl.updateSpaceDatum(tubd);
			}else if(udm!=null){
				UnitDatum tubd =udsl.getUnitDatum(user);				
				if(udm.getSchoolAddress()!=null){
					tubd.setSchoolAddress(udm.getSchoolAddress());
					tubd.setSchoolTime(udm.getSchoolTime());
					tubd.setSchoolType(udm.getSchoolType());
				}else {
					tubd.setWorkTime(udm.getWorkTime());
					tubd.setWorkUnit(udm.getWorkUnit());
				}
				UnitDatumForm unitDForm=new UnitDatumForm();
				unitDForm.setUm(tubd);
				if(!unitDForm.CheckBean()){
					throw new Exception("数据异常");
				}
				udsl.updateUnitDatum(tubd);
			}
		} catch (Exception e) {
			response.getWriter().write("message is error");
			return ;
		}
		response.getWriter().write("message is ok");
	}
	public void login() throws IOException{
		try {		
			String pass=Md5Compute.getmd5(user.getPassword());
			int id=us.sginUserService(user.getName(), pass);
			if(expiry>0){
				String path= ServletActionContext.getRequest().getContextPath();
				Cookie csgin=new Cookie("sgin",user.getName());
				csgin.setMaxAge(24*60*60*expiry);
				csgin.setPath(path);
				Cookie cid=new Cookie("id",id+"");
				cid.setPath(path);
				cid.setMaxAge(24*60*60*expiry);
				Cookie cpass=new Cookie("pptoken",pass);
				cpass.setPath(path);
				cpass.setMaxAge(24*60*60*expiry);
				ServletActionContext.getResponse().addCookie(csgin);
				ServletActionContext.getResponse().addCookie(cid);
				ServletActionContext.getResponse().addCookie(cpass);
			}
			ActionContext.getContext().getSession().put("sgin", user.getName());
			ActionContext.getContext().getSession().put("id", id);
			ActionContext.getContext().getSession().put("pptoken", pass);
			JSONObject json=new JSONObject();
			json.put("name", user.getName());
			json.put("id", id);
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			response.getWriter().write("sgin is error");
			return;
		}
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserBase getUser() {
		return user;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public int getExpiry() {
		return expiry;
	}
	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}
	public UserBaseDatum getUbm() {
		return ubm;
	}
	public void setUbm(UserBaseDatum ubm) {
		this.ubm = ubm;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	public UserBaseDails getUda() {
		return uda;
	}
	public void setUda(UserBaseDails uda) {
		this.uda = uda;
	}
	public SpaceDatum getSpd() {
		return spd;
	}
	public void setSpd(SpaceDatum spd) {
		this.spd = spd;
	}
	public UnitDatum getUdm() {
		return udm;
	}
	public void setUdm(UnitDatum udm) {
		this.udm = udm;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;	
	}
	
}
