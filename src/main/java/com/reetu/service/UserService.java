package com.reetu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Message;
import com.reetu.beans.User;

public interface UserService {
	public String adduser(User u, MultipartFile photo);
	 public  User getUserbyEmail(String email);
	 public String userlogin(String email,String password);
	 public List<User> Searchuserbyaddress(String state, String city, String area, String email);
	 public byte[] getimage(String email);
	 public String addmessg(Message m, MultipartFile file);
	 public String update(User u);
	 public String addmessgwithoutfile(Message m);
	 public List<Message> getmessg(String semail, String remail);
	 public String changepassword(String oldp, String newp, String confp);
	 public byte[] downloadfile(int pid);
	 public String update(String email, MultipartFile image);
	 public String getpassword(String email);

}
