import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.internation.info.dao.UserMapper;
import com.internation.info.model.Role;
import com.internation.info.model.User;

import aaa.MD5ToEncode;


public class addUser {
	@Autowired
	UserMapper userMapper;
	@Autowired
	User user ;
	@Autowired
	MD5ToEncode md5;
	@Test
	public void test() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String name = "cy";
		String password ="111111";
		String newPsw="";
		//password = password.trim();
		//newPsw=md5.MD5Encode(password,"asdf");
		String md5Pwd = md5Pwd(password, name);  
		System.out.println("111111"+"MD5后"+md5Pwd);
		User user1 = new User();
		user1.setUserName(name);
		user1.setPassword(md5Pwd);
		user1.setId(2);
		user1.setCreateTime(new Date());
		user1.setRoleId("1");
		user1.setSex(0);
		user1.setTel("1232432");
		user1.setStatus(1);
		user1.setRoleSet(new HashSet<Role>());
		int a = userMapper.insert(user1);
		System.out.println("插入成功吗"+a);
		
	}
	 public static String md5Pwd(String password, String salt) {  
		    // TODO Auto-generated method stub  
		    String md5Pwd = new Md5Hash(password, salt).toHex();  
		    return md5Pwd;  
		} 
}
