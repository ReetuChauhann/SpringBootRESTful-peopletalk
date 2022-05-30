package com.reetu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Message;
import com.reetu.beans.User;

@Repository
public class UserRepo {
	@Autowired
	JdbcTemplate jdbctemplate;
	
	//adduser 
	      public String adduser(User u, MultipartFile photo) {
		      try {
			
			     String query="insert into users values(?,?,?,?,?,?,?,?,?,?)";
			     int x=jdbctemplate.update(query, new Object[] {u.getEmail(), u.getName(),u.getPhone(), u.getGender(), u.getDob(), u.getState(), u.getCity(), u.getArea(), photo.getInputStream(),u.getPassword()});
			   if(x!=0) {
				 return "success";
			   }else {
				 return "failed";
			 }
		   } catch (Exception e) {
			e.printStackTrace();
			return "failed";
		  }
	      }
		//get user by email
		    public  User getUserbyEmail(String email){
		    	  class DataMapper implements RowMapper{

					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u=new User();
						u.setEmail(rs.getString("email"));
						u.setName(rs.getString("name"));
						u.setPhone(rs.getString("phone"));
						u.setGender(rs.getString("gender"));
						u.setDob(rs.getString("dob"));
						u.setState(rs.getString("state"));
						u.setCity(rs.getString("city"));
						u.setArea(rs.getString("area"));
						u.setPassword(rs.getString("password"));
						return u;
					}
		    		  
		    	  }
		    	  try {
					
		    		   final String query="select * from users where email=?";
		    		   User u=(User)jdbctemplate.queryForObject(query, new DataMapper(), new Object[] {email});
		    		   return u;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
		      }
		    
		    //user login
		    public String userlogin(String email,String password) {
		    	class DataMapper implements RowMapper{

					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						return rs.getString("name") ;
					}
		    		
		    	}
		    try {
		    	  final String query="select name from users where email=? and password=?";
		    	  String r= (String)jdbctemplate.queryForObject(query, new DataMapper(), new Object[] {email, password});
		    	  return "success";
				
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		  }
		    
		    //search people by address
		    public List<User> Searchuserbyaddress(String state, String city, String area, String email){
		    	class DataMapper implements RowMapper{

					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u=new User();
						u.setEmail(rs.getString("email"));
						u.setName(rs.getString("name"));
						u.setPhone(rs.getString("phone"));
						u.setGender(rs.getString("gender"));
						u.setDob(rs.getString("dob"));
						u.setState(rs.getString("state"));
						u.setCity(rs.getString("city"));
						u.setArea(rs.getString("area"));
						u.setPassword(rs.getString("password"));
						return u;
					}
		    		
		    	}
		    	try {
		    		final String query="select * from users where state=? and city=? and area like? and email!=?";
		    		List<User> user=jdbctemplate.query(query, new DataMapper(), new Object[] {state,city, "%"+area+"%", email});
		    		return user;
		    		
					
				} catch (Exception e) {
					// TODO: handle exception
					return null;
				}
		    }
		    
		    //get image on behalf of email id
		    public byte[] getimage(String email) {
		    	class DataMapper implements RowMapper{

					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						return rs.getBytes("photo");
					}
		    		
		    	}
		    	try {
		    		final String query="select photo from users where email=?";
		    		byte[] b=(byte []) jdbctemplate.queryForObject(query, new DataMapper(), new Object[] {email});
		    		return b;
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return null;
				}
		    }
		    
		    //snd messg
		    public String addmessg(Message m, MultipartFile file) {
		    	try {
		    		String query="insert into message(messg,semail,remail,filename,file,cdate,ctime) values(?,?,?,?,?,?,?)";
		    		int x=jdbctemplate.update(query, new Object[] {m.getMessg(), m.getSemail(),m.getRemail(),m.getFilename(), file.getInputStream(),m.getCdate(),m.getCtime()});
					if(x!=0) {
						return "success";
					}else {
						return "failed";
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "failed";
				}
		    }
		    
		  //snd messg without
		    public String addmessgwithoutfile(Message m) {
		    	try {
		    		String query="insert into message(messg,semail,remail,cdate,ctime) values(?,?,?,?,?)";
		    		int x=jdbctemplate.update(query, new Object[] {m.getMessg(), m.getSemail(),m.getRemail(),m.getCdate(), m.getCtime() });
					if(x!=0) {
						return "success";
					}else {
						return "failed";
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "failed";
				}
		    }
		    
		    //update on behalf of email
		    public String update(User u) {
		    	try {
					 String query="update users set name=?,phone=?,gender=?,dob=?,state=?,city=?,area=? where email=?";
					int x=jdbctemplate.update(query, new Object[] {u.getName(), u.getPhone(), u.getGender(), u.getDob(), u.getState(), u.getCity(),u.getArea(),u.getEmail()});
					if(x!=0) {
						return "success";
					}else {
						return "failed";
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "failed";
				}
		    }
		    //update image on behalf of email
		    public String update(String email, MultipartFile image) {
		    	try {
					final String query="update users set photo=? where email=?";
					int x=jdbctemplate.update(query, new Object[] {image.getInputStream(), email});
					if(x!=0) {
						return "success";
					}else {
						return "failed";
					}
				} catch (Exception e) {
					// TODO: handle exception
					return "failed";
				}
		    }
		    
		    //get message
		    public List<Message> getmessg(String semail, String remail){
		    	class DataMapper implements RowMapper{

					@Override
					public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
						Message m=new Message();
						m.setPid(rs.getInt("pid"));
						m.setMessg(rs.getString("messg"));
						m.setSemail(rs.getString("semail"));
						m.setRemail(rs.getString("remail"));
						m.setFilename(rs.getString("filename"));
						m.setCdate(rs.getDate("cdate").toLocalDate());
						m.setCtime(rs.getString("ctime"));
						return m;
					}
		    		
		    	}
		    	try {
					final String query="select * from message where semail=? and remail=?";
					List<Message> m= jdbctemplate.query(query, new DataMapper(), new Object[] {semail, remail});
					return m;
				} catch (Exception e) {
					// TODO: handle exception
					return null;
				}
		    }    	
		    	//change password on behalf of oldpassword
		    	public String changepassword(String oldp, String newp, String confp) {
		    		try {
		    			 if(newp.equals(confp)) {
		    				final String query="update users set password=? where password=?";
		    				int x=jdbctemplate.update(query, new Object[] {newp, oldp});
		    			 
		    				if(x!=0) 
		    					return "success";
		    				
		    			 }else { 
		    				 return "failed";
		    			 }
		    		return "failed";
		    		
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return "failed";
					}
		    		
		    	}
		    	
		    	//get file or download file
		    	public byte[] downloadfile(int pid) {
		    		class DataMapper implements RowMapper{

						@Override
						public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
							// TODO Auto-generated method stub
							return rs.getBytes("file");
						}
		    			
		    		}
		    		try {
						final String query="select * from message where pid=?";
						byte [] b=(byte[]) jdbctemplate.queryForObject(query, new DataMapper(), new Object[] {pid});
						return b;
					} catch (Exception e) {
						// TODO: handle exception
						return null;
					}
		    	}
		    
		    
		    	//get password on behalf of email
		    	public String getpassword(String email) {
		    		class DataMapper implements RowMapper{

						@Override
						public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
							// TODO Auto-generated method stub
							return rs.getString("password");
						}
		    			
		    		}
		    		try {
						final String query="select password from users where email=?";
						String p=(String)jdbctemplate.queryForObject(query, new DataMapper(), new Object[] {email});
						return p;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return null;
					}
		    	}
		    	

}
