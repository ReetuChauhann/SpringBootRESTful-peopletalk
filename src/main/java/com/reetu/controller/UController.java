package com.reetu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Message;
import com.reetu.beans.User;
import com.reetu.service.UserService;

@RestController
public class UController {

	            @Autowired
	            UserService us;
	            
	            @PostMapping("/adduser")
	            public ResponseEntity<String> adduser(@RequestPart("User") User u, @RequestPart("photo") MultipartFile photo){
	            	String s=us.adduser(u, photo);
	            	if(s.equals("success")) {
	            		return new ResponseEntity<String>(s, HttpStatus.OK);
	            	}else {
	            		return new ResponseEntity<String>(s, HttpStatus.NOT_MODIFIED);
	            	}
	            	
	            }
	            
	            @GetMapping("/getuserbyemail/{email}")
	            public User getuserbyemail(@PathVariable("email") String email) {
	            	User u=us.getUserbyEmail(email);
	            	return u;
	            }
	            
	            @PostMapping(value = "/userLogin/{email}/{password}")
	        	public String checkCompanyLogin(@PathVariable String email, @PathVariable String password) {
	        		String r=us.userlogin(email, password);
	        		return r;
	        	}
	        	
	        	@GetMapping("/serachuser/{state}/{city}/{area}/{email}")
	        	public List<User> Searchuserbyaddress(@PathVariable("state") String state,@PathVariable("city") String city, @PathVariable("area") String area, @PathVariable("email") String email){
	        		if(area.equals("nodata")) {
	        			area="";
	        		}
	        		List<User> u=us.Searchuserbyaddress(state, city, area, email);
	        		return u;
	        		
	        	}
	        	
	        	@GetMapping("/getimage/{email}")
	        	public byte[] getimage(@PathVariable("email") String email) {
	        		byte[] b=us.getimage(email);
	        		return b;
	        	}
	        	
	        	@PostMapping("/addmessg")
	        	public ResponseEntity<String> addmessg(@RequestPart("Message") Message m, @RequestPart("file") MultipartFile file){
	        		String s=us.addmessg(m, file);
	        		if(s.equals("success")) {
	        			return new ResponseEntity<String>(s, HttpStatus.OK);
	        		}else {
	        			return new ResponseEntity<String>(s, HttpStatus.NOT_MODIFIED);
	        		}
	        	}
	        	
	        	@PostMapping("/addmessgwithoutfile")
	        	public ResponseEntity<String> addmessg(@RequestBody Message m){
	        		String s=us.addmessgwithoutfile(m);
	        		if(s.equals("success")) {
	        			return new ResponseEntity<String>(s, HttpStatus.OK);
	        		}else {
	        			return new ResponseEntity<String>(s, HttpStatus.NOT_MODIFIED);
	        		}
	        	}
	        	
	        	@PutMapping("/update")
	        	public ResponseEntity<String> update(@RequestBody User u){
	        		String s=us.update(u);
	        		if(s.equals("success")) {
	        			return new ResponseEntity<String>(s, HttpStatus.OK);
	        		}else {
	        			return new ResponseEntity<String>(s, HttpStatus.NOT_MODIFIED);
	        		}
	        	}
	        	
	        	@GetMapping("/getmessg/{semail}/{remail}")
	        	public List<Message> getmessage(@PathVariable("semail") String semail, @PathVariable("remail") String remail){
	        		List<Message> m=us.getmessg(semail, remail);
	        		return m;
	        	}
	        	
	        	@PutMapping("/updatepassword/{oldp}/{newp}/{confp}")
	        	public String update(@PathVariable("oldp") String oldp, @PathVariable("newp") String newp, @PathVariable("confp") String confp) {
	        		String s=us.changepassword(oldp, newp, confp);
	        		return s;
	        	}
	        	
	        	@GetMapping("/downloadfile/{pid}")
	        	public byte[] downloadfile(@PathVariable("pid") int pid) {
	        		byte[] b= us.downloadfile(pid);
	        		return b;
	        	}
	        	
	        	@PutMapping("/update/{email}")
	        	public ResponseEntity<String> update(@PathVariable("email") String email, @RequestPart("image") MultipartFile image){
	        		String s=us.update(email, image);
	        		if(s.equals("success")) {
	        			return new ResponseEntity<String>(s, HttpStatus.OK);
	        		}else {
	        			return new ResponseEntity<String>(s, HttpStatus.NOT_MODIFIED);
	        		}
	        	}
	        	
	        	@GetMapping("/getpassword/{email}")
	        	public String getpassword(@PathVariable("email") String email) {
	        		String p=us.getpassword(email);
	        		return p;
	        	}
}
