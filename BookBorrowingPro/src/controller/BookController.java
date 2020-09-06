package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.BorrowItem;
import domain.BookOrder;
import domain.Book;
import domain.Student;

@Controller
public class BookController {
	private static List<Book> bookList;
	private static int BookCounter=0;
	static {
		bookList=new ArrayList<Book>();
		bookList.add(new Book(1,"JAVAEE框架","计算机类",50,100,"JAVAEE.jpg"));
		bookList.add(new Book(2,"马克思主义思想","哲学类",60,50,"makesi.jpg"));
		bookList.add(new Book(3,"中国近代史","文学类",70,25,"histroy.jpg"));
		BookCounter+=3;
	}
	public static Book findBook(long id) {
		for(Book b:bookList) {
			if(b.getId()==id) {
				return b;
			}
		}
		return null;
	}
	public BookOrder createBookOrder(long userId,long[] bookIds){//借阅书籍
		//从我的书籍中移除书籍条目，并且将书籍条目添加到书籍详单对象中，返回新的详单
		BookOrder bookorder=new BookOrder();
		List<BorrowItem> borrowItems=new ArrayList<BorrowItem>();
		Student stu=UserController.findStudent(userId);
		for(long i:bookIds) {
			for(BorrowItem item:stu.getBorrow().getBorrowItems()) {
				if(item.getBook().getId()==i) {
					borrowItems.add(item);
				}
			}
		}
		stu.getBorrow().getBorrowItems().removeAll(borrowItems);
		bookorder.setBorrowItems(borrowItems);
		return bookorder;
	}
	@RequestMapping(value="/book_list")//图书列表
	public String doListBook(Model m) {
		m.addAttribute("all_books",bookList);
		return "allBooksForm";
	}
	@RequestMapping(value="/manage_book")//管理图书
	public String doManageBook(Model m) {
		
		m.addAttribute("all_books",bookList);
		return "manageBookForm";
	}
	@RequestMapping(value="/delete_book")//删除图书
	public String doDeleteBook(@RequestParam String id) {
		long longId=Long.valueOf(id);
		for(Book b:bookList) {
			if(b.getId()==longId) {
				bookList.remove(b);
				return "redirect:manage_book";
			}
		}
		return "redirect:manage_book";
	}
	@RequestMapping(value="/get_book_json")//获取json请求
	public void getBookJson(@RequestParam long id,
			HttpServletResponse response) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Book r=null;
		for(Book b:bookList) {
			if(b.getId()==id) {
				r=b;
				break;
			}
		}
    	//System.out.println(mapper.writeValueAsString(r) );
		response.setContentType("text/html;charset=UTF-8");
    	response.getWriter().println(mapper.writeValueAsString(r));
	}
	@RequestMapping(value="/do_modify_book")//修改图书，图书管理员
	public String doModifyProduct(Book book) {
		//System.out.println(book);
		for(Book b:bookList) {
			if(b.getId()==book.getId()) {
				b.setName(book.getName());
				b.setCategory(book.getCategory());
				b.setQuantity(book.getQuantity());
				b.setPrice(book.getPrice());
			}
		}
		return "redirect:manage_book";
	}
	@RequestMapping(value="/do_add_book")//添加图书，图书管理员
	public String doAddProduct(HttpServletRequest request,
			@ModelAttribute Book book) throws Exception{
		BookCounter++;
		book.setId(BookCounter);
		//
		if(!book.getImage().isEmpty()){
			// 上传文件路径
			String path = request.getServletContext().getRealPath(
	                "/image/");
			//生成唯一的UUID
			String prefix = UUID.randomUUID().toString();
            prefix = prefix.replace("-", ""); //去掉中间的-
			// 上传文件名
			String filename = prefix+"_"+book.getImage().getOriginalFilename();
			
		    File filepath = new File(path,filename);
			// 判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
	        // 将上传文件保存到一个目标文件当中
	       book.getImage().transferTo(new File(path+File.separator+ filename));
	        // 将用户添加到model
	        book.setImagePath(filename);
	        book.setImage(null);
			bookList.add(book);
		}
		//
		return "redirect:manage_book";
	}
	@RequestMapping(value="/put_in_borrow")//加入借书栏，学生用户
	public void doPutInBorrow(@RequestParam long userId,
			@RequestParam long bookId,@RequestParam long number,
			HttpSession session, 
			HttpServletResponse response) throws Exception{
		Book r=null;
		String error=null;
		Student stu=(Student)session.getAttribute("user");
		int successFlag=0,borrowSize=0;
		long quantity=0;

		for(Book b:bookList) {
			if(b.getId()==bookId) {
				r=b;
				quantity=r.getQuantity();
				if((r.getQuantity()-number)<0) {
					error="库存不够";
				}else {
					r.setQuantity(r.getQuantity()-number);
					stu.getBorrow().addBorrowItem(r,number);
				}
				break;
			}
		}
		response.setContentType("text/html;charset=UTF-8");
    //	response.getWriter().println(mapper.writeValueAsString(r));
		if(r==null) {
			error="不存在的商品";
		}
		if(error!=null) {
			successFlag=1;
		}else {
			borrowSize=stu.getBorrow().getBorrowItems().size();
		}
		String s=String.format("{\"success\":%d,\"size\":%d,"
				+ "\"quantity\":%d,\"error\":\"%s\"}",
				successFlag,borrowSize,quantity,error);
		response.getWriter().println(s);
	}
	@RequestMapping(value="/display_borrow")//显示借书，学生用户
	public String doDisplayBorrow(Model model,HttpSession session) {
		Student stu=(Student)session.getAttribute("user");
		model.addAttribute("borrow",stu.getBorrow());
		return "borrowInfo";
	}
	@RequestMapping(value="/borrow_check")//借书选择，学生用户
	public String doBorrowCheck(@RequestParam long[] borrow_book_id,
			HttpSession session ) {
		//cart_product_id是选中的商品id
		Student user=(Student)session.getAttribute("user"); //获取当前session中的用户对象
		BookOrder bookorder=createBookOrder(user.getId(),borrow_book_id);//创建新的图书订单
		user.getOrderHistory().getBookorders().add(bookorder); //往用户的历史订单中添加订单
		return "redirect:book_list";
	}
	@RequestMapping(value="/return_check")//借书选择，学生用户
	public String doReturnCheck(@RequestParam long[] return_book_id,
			HttpSession session ) {
		//cart_product_id是选中的商品id
		Student user=(Student)session.getAttribute("user"); //获取当前session中的用户对象
		BookOrder bookorder=createBookOrder(user.getId(),return_book_id);//创建新的图书订单
		user.getOrderHistory().getBookorders().remove(bookorder); //往用户的历史订单中添加订单
		return "redirect:book_list";
	}
	@RequestMapping(value="/history_orders")//历史借书详单，学生用户
	public String displayHistoryOrders() {
		return "historyOrdersForm";
	}
}