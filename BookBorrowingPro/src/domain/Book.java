package domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Book implements Serializable{
	private long id;
	private String name;
	private String category;
	private long quantity;
	private double price;
	private MultipartFile image;
	private String imagePath;
	public Book() {
		
	}
	public Book(long id,String name,String category,long quantity,
			double price,String imagePath) {
				this.id=id;
				this.name=name;
				this.category=category;
				this.quantity=quantity;
				this.price=price;
				//this.image=image;
				this.imagePath=imagePath;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "{id:"+id+",name:"+name+",category:"+category+
				"quantity:"+quantity+",price:"+price+"}";
	} 
	
}


