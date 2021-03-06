package ssm.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ssm.shop.entity.Goods;
import ssm.shop.service.CartService;

/*
 * 购物车控制类
 * 购物车的数据直接存储在session当中
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	
	/*
	 * 添加商品到购物车
	 * 此处包含两种情况；一是购物车已存在该商品；二是购物车里没这种商品
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addGoods", method = RequestMethod.POST)
	public void addGoods(String goodsId, HttpSession session) {

		Map<Goods, Integer> cart = null;
		if (session.getAttribute("cart") == null) {
			cart = new HashMap<Goods, Integer>();
		} else {
			cart = (Map<Goods, Integer>) session.getAttribute("cart");
		}

		boolean exist = true;// true代表加入购物车的是一个新的元素

		Goods goods = cartService.getGoodsById(goodsId);
		for (Goods key : cart.keySet()) {
			if (key != null && key.equals(goods)) {
				cart.put(key, cart.get(key) + 1);
				// System.out.println(cart.get(goods));
				exist = false;
			}
		}

		if (exist) {
			cart.put(goods, 1);
		}
		session.setAttribute("cart", cart);

		for (Goods key : cart.keySet()) {
			System.out.println(key + " " + cart.get(key));
		}
	}

	/*
	 * 显示购物车
	 */
	@RequestMapping("/showCart")
	public String showCart() {

		return "/cart/showCart";
	}
}
