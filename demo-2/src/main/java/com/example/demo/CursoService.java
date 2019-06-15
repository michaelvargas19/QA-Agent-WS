package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.model.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CursoService {
	
	private List<Post> datos;

			
	//@RequestMapping("/posts")
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	
	public List<Post> findPost() {
		return this.datos;
	}
	
	@RequestMapping(value = "/posts/{id}" , method = RequestMethod.GET)
	Post findPost(@PathVariable("id") Integer id) {
		return this.findById(id);
	}
	
	//CONSTRUCTOR Y METODOS-------------------------------------------------
	public CursoService() {
		this.datos = new ArrayList<Post>();
		this.datos.add(new Post(1,1,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit","quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"));
		this.datos.add(new Post(2,1,"qui est esse","est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"));
		this.datos.add(new Post(3,1,"ea molestias quasi exercitationem repellat qui ipsa sit aut","et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut"));
		this.datos.add(new Post(4,1,"eum et est occaecati","ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit"));
		this.datos.add(new Post(5,1,"nesciunt quas odio",""
				+ "repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque"));
	}
	
	private Post findById(long id){
		for (Post p : datos) {
			if(p.getId()==id)
				return p;
		}
		
		return null;
	}
	
	//CLASE (Post)---------------------------------------------
	class Post {
		long id;
		long userid;
		String title;
		String body;
		Post(long id,long userid,String title,String body){
			this.id=id;
			this.userid=userid;
			this.title=title;
			this.body=body;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public long getUserid() {
			return userid;
		}
		public void setUserid(long userid) {
			this.userid = userid;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		
	}
	
	
	

}
