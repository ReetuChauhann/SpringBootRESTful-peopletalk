package com.reetu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Message;
import com.reetu.beans.User;
import com.reetu.dao.UserRepo;

@Service
public class UserserviceImpl implements UserService {
	@Autowired
	UserRepo ur;

	@Override
	public String adduser(User u, MultipartFile photo) {
		// TODO Auto-generated method stub
		return ur.adduser(u, photo);
	}

	@Override
	public User getUserbyEmail(String email) {
		// TODO Auto-generated method stub
		return ur.getUserbyEmail(email);
	}

	@Override
	public String userlogin(String email, String password) {
		// TODO Auto-generated method stub
		return ur.userlogin(email, password);
	}

	@Override
	public List<User> Searchuserbyaddress(String state, String city, String area, String email) {
		// TODO Auto-generated method stub
		return ur.Searchuserbyaddress(state, city, area, email);
	}

	@Override
	public byte[] getimage(String email) {
		// TODO Auto-generated method stub
		return ur.getimage(email);
	}

	@Override
	public String addmessg(Message m, MultipartFile file) {
		// TODO Auto-generated method stub
		return ur.addmessg(m, file);
	}

	@Override
	public String update(User u) {
		// TODO Auto-generated method stub
		return ur.update(u);
	}

	@Override
	public String addmessgwithoutfile(Message m) {
		// TODO Auto-generated method stub
		return ur.addmessgwithoutfile(m);
	}

	@Override
	public List<Message> getmessg(String semail, String remail) {
		// TODO Auto-generated method stub
		return ur.getmessg(semail, remail);
	}

	@Override
	public String changepassword(String oldp, String newp, String confp) {
		// TODO Auto-generated method stub
		return ur.changepassword(oldp, newp, confp);
	}

	@Override
	public byte[] downloadfile(int pid) {
		// TODO Auto-generated method stub
		return ur.downloadfile(pid);
	}

	@Override
	public String update(String email, MultipartFile image) {
		// TODO Auto-generated method stub
		return ur.update(email, image);
	}

	@Override
	public String getpassword(String email) {
		// TODO Auto-generated method stub
		return ur.getpassword(email);
	}

}
