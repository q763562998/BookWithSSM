package com.athome.controlor;

import com.alibaba.druid.sql.PagerUtils;
import com.athome.Utils.PageUtils;
import com.athome.mapper.BookMapper;
import com.athome.mapper.CartItemMapper;
import com.athome.mapper.MyOrderMapper;
import com.athome.pojo.*;
import com.athome.service.BookService;
import com.athome.service.BookServiceImpl;

import com.athome.service.CartItemServiceImpl;
import com.athome.service.UserServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import sun.misc.Resource;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller

public class Controlor {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    CartItemServiceImpl cartItemService;

    @Autowired
    MyOrderMapper myOrderMapper;

    @RequestMapping("hello")
    public String testHello(Map<String, Object> map) {


        List<UserBean> user = userService.getAllUser();
        map.put("user", user);
        System.out.println("测试成功");
        return "success";//此时已经没有success
    }


    /**
     * 用户登录
     *
     * @param userName    前端中用户输入的账号
     * @param password    前端中用户输入的密码
     * @param map
     * @param httpSession 用于存放session 账号名字
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password, Map<String, Object> map, HttpSession httpSession) {
//        System.out.println("login的GET被调用");
        UserBean user = userService.getUserByNameAndPassword(userName, password);
        if (user == null) {
            System.out.println("用户不存在");
            String msg = "账号或密码错误";
            map.put("msg", msg);
            map.put("falseName", userName);
            return "user/login";
        } else {
            System.out.println("登录成功");
            if (map.get("msg") != null) {

                map.remove("msg");

            }
            httpSession.setAttribute("trueName", userName);
            return "user/login_success";
        }

    }

    /**
     * 注销用户
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/exit")
    public String exit(HttpSession httpSession) {

        httpSession.removeAttribute("trueName");
        httpSession.removeAttribute("cartItem");
        httpSession.removeAttribute("totalMoney");
        httpSession.removeAttribute("totalNum");
        /**
         * httpSession.setAttribute("cartItem",cartItem);
         *             httpSession.setAttribute("totalNum",totalNum);
         *             httpSession.setAttribute("totalMoney",totalMoney);
         */
        return "dicOfIndex/index";
    }


    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param email
     * @param map
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(String username, String password, String email, Map<String, Object> map, HttpSession httpSession) {

        UserBean user = userService.getUserByName(username);
        if (user != null) {
            System.out.println("用户名已存在");
            String isexit = "用户名已存在";
            map.put("isexit", isexit);
            map.put("isexitName", username);
            return "user/regist";
        } else {
            UserBean bean = new UserBean(username, password, email);
            System.out.println(bean);
            userService.insertUser(bean);

            httpSession.setAttribute("registerName", username);

            return "user/regist_success";
        }

    }


    /**
     * 查询所有书
     * <p>
     * 注意，因为首页无法获得任何信息，所以首页先跳转到方法请求，请求再跳回另一个文件夹下的的首页
     *
     * @return
     */
    //这一代码优化为到方法 pages下

    /**
     * 因为在管理页面 发现使用request域拿不到之前保存的book对象，所以又重新找一次
     *
     * @param map
     * @return
     */
    @RequestMapping("/managerBook/{num}")
    public String managerBook(Map<String, Object> map, @PathVariable("num") int pageNum, HttpSession httpSession) {
        PageHelper.startPage(pageNum, 5);
        List<BookBean> managerBook = bookService.getAllBook();
        PageInfo<BookBean> info = new PageInfo(managerBook, 5);
        String path = "/BookWithSSM";
        String method = "/managerBook/";
        String page = PageUtils.getPage(info, path, method);
        httpSession.setAttribute("currentNum", pageNum);
        map.put("managerPage", page);
        map.put("managerBook", managerBook);
        return "manager/book_manager";
    }

    /**
     * 为了回显数据，先保存到request域中
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("update/{id}")
    public String updateBook(@PathVariable("id") int id, Map<String, Object> map) {

        BookBean updateBook = bookService.getById(id);
        System.out.println(updateBook);
        map.put("updateBook", updateBook);
        return "manager/book_edit";
    }

    /**
     * 已经获得了前端用户输入的信息
     * 进行真正的修改
     *
     * @param id
     * @param book_name
     * @param book_price
     * @param book_author
     * @param book_stock
     * @return
     */
    @RequestMapping("/realUpdate/{id}")
    public String realUpdate(@PathVariable("id") Integer id, String book_name, double book_price, String book_author, int book_stock, HttpSession httpSession) {
        BookBean bean = new BookBean(id, book_name, book_author, book_price, book_stock);
        bookService.updateBook(bean);
        Integer currentNum = (Integer) httpSession.getAttribute("currentNum");
//        System.out.println(currentNum);
        return "redirect:/managerBook/" + currentNum;
    }


    /**
     * 删除书籍，通过id
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Integer id, HttpSession httpSession) {
        bookService.deleteBook(id);
        Integer currentNum = (Integer) httpSession.getAttribute("currentNum");
        System.out.println(currentNum);
        return "redirect:/managerBook/" + currentNum;
    }

    /**
     * 添加书籍
     *
     * @return
     */
    @RequestMapping("/insertBook")
    public String insertBook(String book_name, double book_price, String book_author, int book_stock, HttpSession httpSession) {
        BookBean bean = new BookBean(null, book_name, book_author, book_price, book_stock);

        Integer currentNum = (Integer) httpSession.getAttribute("currentNum");
        bookService.insertBook(bean);

        return "redirect:/managerBook/" + currentNum;
    }


    /**
     * 添加书本进入购物车
     * <p>
     * 在后台可以通过用户名来搜索关于哪个用户的购物车信息
     * <p>
     * 实现用户一登陆就可以知道之前的购物车信息
     *
     * @param id
     * @param book_name
     * @param book_author
     * @param book_price
     * @param book_amount
     * @param httpSession
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/insertToCart/{id}")
    public String insertToCart(@PathVariable("id") int id, String book_name, String book_author, Double book_price, Integer book_amount, HttpSession httpSession) {
//        System.out.println("1");
//        System.out.println(book_name);
//        System.out.println(book_author);
//        System.out.println(book_price);
//        System.out.println(book_amount);
//        BookBean bean = new BookBean(null, book_name, book_author, book_price, book_amount);
        long date = System.currentTimeMillis();
        int totalNum = 0;
        double totalMoney = 0;

        String name = (String) httpSession.getAttribute("trueName");
        if (name == null) {
//            return "redirect:3";
            return "user/login";
        }
//        System.out.println("2");
        if (httpSession.getAttribute("cartItem") == null) {

            List<Cart> allCart = cartItemService.getAllCartByName(name);
            System.out.println(allCart);
            boolean isExit = false;
            for (int i = 0; i < allCart.size(); i++) {
                if (allCart.get(i).getBookId() == id) {
                    isExit = true;
                }
            }
            if (isExit == false) {
                CartItem cartItem = new CartItem();
//            item.getBookBeanList().add(bean);
                Cart cart = new Cart(String.valueOf(date), id, name, book_name, book_price, 1, book_price);
                allCart.add(cart);
                cartItem.getCarts().addAll(allCart);
                httpSession.setAttribute("cartItem", cartItem);
                totalNum++;
                totalMoney = book_price;
                cartItemService.insertCartItem(cart);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.getCarts().addAll(allCart);
                for (int i = 0; i < cartItem.getCarts().size(); i++) {
                    if (cartItem.getCarts().get(i).getBookId() == id) {
                        Cart cart = new Cart(String.valueOf(date), id, name, book_name, book_price, cartItem.getCarts().get(i).getCount() + 1, cartItem.getCarts().get(i).getTotalPrice() + book_price);
                        cartItem.getCarts().set(i, cart);
//                    i=cartItem.getCarts().size();

                        cartItemService.updateCartItem(cart);
                    }
                    totalNum += cartItem.getCarts().get(i).getCount();
                    totalMoney += cartItem.getCarts().get(i).getTotalPrice();
                }
                httpSession.setAttribute("cartItem", cartItem);
            }
        } else {
            boolean flag = false;
            CartItem cartItem = (CartItem) httpSession.getAttribute("cartItem");
            for (int i = 0; i < cartItem.getCarts().size(); i++) {
                if (cartItem.getCarts().get(i).getBookId() == id) {
                    Cart cart = new Cart(String.valueOf(date), id, name, book_name, book_price, cartItem.getCarts().get(i).getCount() + 1, cartItem.getCarts().get(i).getTotalPrice() + book_price);
                    cartItem.getCarts().set(i, cart);
//                    i=cartItem.getCarts().size();
                    flag = true;
                    cartItemService.updateCartItem(cart);
                }
                totalNum += cartItem.getCarts().get(i).getCount();
                totalMoney += cartItem.getCarts().get(i).getTotalPrice();
            }
            if (flag == false) {
                //说明原先没有相同的书本订单
                //重新添加进订单
                Cart cart = new Cart(String.valueOf(date), id, name, book_name, book_price, 1, book_price);
                cartItem.getCarts().add(cart);
                totalNum++;
                totalMoney += book_price;
                cartItemService.insertCartItem(cart);
            }
            httpSession.setAttribute("cartItem", cartItem);
            httpSession.setAttribute("totalNum", totalNum);
            httpSession.setAttribute("totalMoney", totalMoney);
        }
        System.out.println("异常前");
//        int t=10/0;
//        new Cart("1")
//        cartItemService.insertCartItem();
        System.out.println("异常后");
        Integer pageNum = (Integer) httpSession.getAttribute("pageNum");
        return "redirect:/pages/" + pageNum;
    }

    /**
     * 实现用户直接进入购物车后，之前购物车信息的展示
     *
     * @param httpSession
     * @param map
     * @return
     */
    @RequestMapping("/beforeCart")
    public String beforeCart(HttpSession httpSession, Map<String, Object> map) {
        String name = (String) httpSession.getAttribute("trueName");
        List<Cart> cart = cartItemService.getAllCartByName(name);
        CartItem cartItem = new CartItem();
        cartItem.getCarts().addAll(cart);

//        System.out.println(cart);
        int totalNumber = 0;
        double totalMoney = 0;
        for (int i = 0; i < cart.size(); i++) {
            totalNumber += cart.get(i).getCount();
            totalMoney += cart.get(i).getTotalPrice();
        }
        httpSession.setAttribute("totalNum", totalNumber);
        httpSession.setAttribute("totalMoney", totalMoney);
        httpSession.setAttribute("cartItem", cartItem);
        return "cart/cart";
    }

    /**
     * 删除购物车的订单
     *
     * @return
     */
    @RequestMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable("id") String orderNumber) {
        System.out.println(orderNumber);
        cartItemService.deleteCartItem(orderNumber);

        return "redirect:/beforeCart";
    }

    /**
     * 清理购物车信息
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/clearCart")
    public String clearCart(HttpSession httpSession) {
        cartItemService.deleteAllCartItem();
        httpSession.removeAttribute("totalNum");
        httpSession.removeAttribute("totalMoney");
        return "redirect:/beforeCart";
    }

    /**
     * 购物车结账
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/setCount")
    public String setCount(HttpSession httpSession) {
        List<Cart> allCart = cartItemService.getAllCart();
        MyOrder order = new MyOrder();
        order.setCartItemList(new ArrayList<>());
//        order.getCartItemList().addAll(allCart);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String orderTime = dateTime.format(formatter);
        order.setDateTime(orderTime);
        String userName = (String) httpSession.getAttribute("trueName");
        System.out.println(userName);
        order.setUserName(userName);
        long currentTimeMillis = System.currentTimeMillis();
        order.setOrderNumber(String.valueOf(currentTimeMillis));
        System.out.println(order);

//        order.getCartItemList().addAll(allCart);
        for (int i = 0; i < allCart.size(); i++) {
            ArrayList<Cart> list = new ArrayList<>();
            list.add(allCart.get(i));
//            list 是购物车的信息
            order.getCartItemList().add(allCart.get(i));
            System.out.println(order);
            myOrderMapper.InsertMyorder(order);
            order.getCartItemList().remove(allCart.get(i));
        }

//        myOrderMapper.InsertMyorder(order);

        cartItemService.deleteAllCartItem();
        httpSession.removeAttribute("totalNum");
        httpSession.removeAttribute("totalMoney");
        return "redirect:/beforeCart";
    }

    /**
     * 查看某个用户的订单
     *
     * @param name
     * @param map
     * @return
     */
    @RequestMapping("/showMyOrder/{name}")
    public String showMyOrder(@PathVariable("name") String name, Map<String, Object> map) {
        List<MyOrder> orderList = myOrderMapper.getAllMyOrderByName(name);
        map.put("orderList", orderList);
        return "order/order";
    }


    /**
     * 分页功能
     *
     * @param map
     * @param pageNum
     * @return
     */
    @RequestMapping("/pages/{pageNum}")
    public String pages(Map<String, Object> map, @PathVariable("pageNum") int pageNum, HttpSession httpSession) {

        PageHelper.startPage(pageNum, 2);
        List<BookBean> book = bookService.getAllBook();
//        System.out.println(book);
        PageInfo<BookBean> pageInfo = new PageInfo<>(book, 5);
//        System.out.println(pageInfo);
        String path = "/BookWithSSM";
        String method = "/pages/";
        String page = PageUtils.getPage(pageInfo, path, method);
        map.put("page", page);
        map.put("book", book);
        httpSession.setAttribute("pageNum", pageNum);
//        System.out.println(page);
        return "client/index";
    }




}
