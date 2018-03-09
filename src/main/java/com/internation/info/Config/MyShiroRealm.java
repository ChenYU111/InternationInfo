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
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		logger.info("权限配置-->doGetAuthorizationInfo");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		logger.info("----------------------------->" + principals.getPrimaryPrincipal());
		/*User user = (User) principals.getPrimaryPrincipal();
		
		 * for(Role role:user.getRoleList()){
		 * authorizationInfo.addRole(role.getRole_name()); for(Function
		 * function:role.getFunctionList()){
		 * authorizationInfo.addStringPermission(function.getPermission()); } }
		 
		String roleStr = user.getRoleId();
		 int uId = user.getId();
					roleExample.createCriteria().andRoleIdEqualTo(str);
					List<Role> roleList = roleMapper.selectByExample(roleExample);
					if(roleList!=null&&roleList.size()>0){
						Role r = new Role();
						authorizationInfo.addRole(r.getRoleName());
					}
				}
			}
		}		
		 * logger.info("用户"+user.getName()+"具有的角色:"+authorizationInfo.getRoles()
		 * ); logger.info("用户"+user.getName()+"具有的权限："+authorizationInfo.
		 * getStringPermissions());
		 */
		return authorizationInfo;
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