package com.internation.info.Config;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.internation.info.dao.RoleMapper;
import com.internation.info.dao.UserMapper;
import com.internation.info.model.Role;
import com.internation.info.model.RoleExample;
import com.internation.info.model.User;
import com.internation.info.model.UserExample;

@Repository
public class MyShiroRealm extends AuthorizingRealm{
	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserExample userExample;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleExample roleExample;

	 @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
	        logger.info("##################执行Shiro权限认证##################");
	        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
	        String loginName = (String)super.getAvailablePrincipal(principalCollection); 
	        //到数据库查是否有此对象
	        userExample.createCriteria().andUserNameEqualTo(loginName);
	        List<User> userList=userMapper.selectByExample(userExample);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	        if(userList!=null&&userList.size()>0){
	            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
	            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
	            //用户的角色集合
	            
	            //info.setRoles(roleMapper.selectByExample(roleExample.createCrit));
	          /*  //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
	            List<Role> roleList=user.getRoleList();
	            for (Role role : roleList) {
	                info.addStringPermissions(role.getPermissionsName());
	            }*/
	            // 或者按下面这样添加
	            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
//	            simpleAuthorInfo.addRole("admin");  
	            //添加权限  
//	            simpleAuthorInfo.addStringPermission("admin:manage");  
//	            logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
	            return info;
	        }
	        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
	        return null;
	    }
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		logger.info(
				"验证当前Subject时获取到token为：" + "ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE)");
		userExample.createCriteria().andUserNameEqualTo(token.getUsername());
		logger.info("获取用户之前=========================================");
		List<User> userList = userMapper.selectByExample(userExample);
		if (userList.size() == 0) {
			throw new UnknownAccountException("不存在该用户");
		}
		if (userList != null && userList.size() > 0) {
			User user = new User();
			user = userList.get(0);
			logger.info("因为用空指针，测试密码是否为null        " + user.getPassword());
			ByteSource salt = ByteSource.Util.bytes(user.getUserName());
			String realmName = getName();
			SimpleAuthenticationInfo info = null;
			info = new SimpleAuthenticationInfo(user, user.getPassword(), salt, realmName);
			return info;
		}
		return null;
	}
}