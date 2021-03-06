package com.cafe.kiosk;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UrlPathHelper;

import com.model.dao.MenuDao;
import com.model.dao.OderDao;
import com.model.dto.ClientDto;
import com.model.dto.MenuDto;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(@RequestParam String id, HttpServletRequest request) {

		logger.info("Welcome " + id);
		ClientDto.getinstance().setName(id);

		if (id.equals("admin"))
			return "admin/oderlist";
		else
			return "user/first_page";
	}

	@RequestMapping(value = { "/admin_menuInsert", "/admin_menuDelete", "/admin_menuModify", "/admin_menuModify.do",
			"/admin_menuinventory", "/admin_oderlist", "/admin_oderMagenment" })
	public String admin(HttpServletRequest request, HttpSession session) {
		UrlPathHelper urls = new UrlPathHelper();
		String url = urls.getOriginatingServletPath(request);
		String returnUrl = "";
		if ("/admin_menuInsert".equals(url)) {
			returnUrl = "admin/admin_menuInsert";

		} else if ("/admin_menuDelete".equals(url)) {
			returnUrl = "admin/admin_menuDelete";

		} else if ("/admin_menuModify".equals(url)) {
			returnUrl = "admin/admin_menuModify";

		} else if ("/admin_menuModify.do".equals(url)) {
			returnUrl = "admin/admin_menuModifyOK";

		} else if ("/admin_menuinventory".equals(url)) {
			returnUrl = "admin/Inventory_Mangenment";

		} else if ("/admin_oderlist".equals(url)) {
			returnUrl = "admin/oderlist";
		} else if ("/admin_oderMagenment".equals(url)) {
			returnUrl = "admin/oder_Magenment";
		}

		return returnUrl;
	}

	@RequestMapping(value = "/admin_menuInsert.do")
	public String insertmenu(HttpServletRequest request) {
		int a = MenuDao.getInstance().insertMenu(request);
		if (a == 1) {
			logger.info("메뉴 삽입 성공");
			return "user/menu_list";
		} else
			return "admin/admin_menuInsert";
	}

	@RequestMapping(value = "/admin_menuDelete.do")
	public String deletemenu(HttpServletRequest request, @RequestParam String name) {
		int a = MenuDao.getInstance().deleteMenu(name);

		if (a == 1) {
			logger.info("메뉴 삭제 완료");
			return "admin/admin_menuDelete";
		} else
			return "user/menu_list";
	}

	@RequestMapping(value = "/admin_menuModifyOK.do")
	public String updatemenu(HttpServletRequest request) {
		int a = MenuDao.getInstance().updateMenu(request);
		if (a == 1) {
			logger.info("메뉴 수정 완료");
			return "user/menu_list";
		} else
			return "admin/admin_menuModify";
	}

	@RequestMapping(value = "/inventoryUpdate.do")
	public String updateinventory(HttpServletRequest request) {
		MenuDao.getInstance().updateinventory(request);
		return "admin/Inventory_Mangenment";
	}

	@RequestMapping(value = "/receiptPrint.do")
	public String receipPrint(HttpServletRequest request) {
		String num = request.getParameter("odernum");
		String oder = OderDao.getInstance().getOneOder(num);
		request.setAttribute("oneOder", oder);
		return "receiptPrint";
	}

	@RequestMapping(value = "/deleteOder.do")
	public String deleteOder(HttpServletRequest request) {
		String num = request.getParameter("odernum");
		OderDao.getInstance().deleteOder(num);
		return "admin/oderlist";
	}

	@RequestMapping(value = "/startOder.do")
	public String startOder(HttpServletRequest request) {
		String num = request.getParameter("odernum");
		OderDao.getInstance().startOder(num);
		return "admin/oderlist";
	}

	@RequestMapping(value = "/showImage")//spring으로 옮길 때 한 쿼리문에서 전부 다룰 수 있게 해보자
	public void showImage(HttpServletRequest request, HttpServletResponse response){
		MenuDao.getInstance().showImage(request, response);
	}

	@RequestMapping(value = { "/Cart.do", "/oder.do", "/menulist.do" })
	public String user(HttpServletRequest request) {
		UrlPathHelper urls = new UrlPathHelper();
		String url = urls.getOriginatingServletPath(request);
		String returnUrl = "";
		if ("/Cart.do".equals(url)) {
			returnUrl = "user/Cart";
		} else if ("/oder.do".equals(url)) {
			returnUrl = "user/Payment_Result";
		} else if ("/menulist.do".equals(url)) {
			returnUrl = "user/menu_list";
		}
		return returnUrl;
	}
}
