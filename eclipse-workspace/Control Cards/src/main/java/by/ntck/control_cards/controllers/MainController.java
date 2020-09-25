package by.ntck.control_cards.controllers;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import by.ntck.control_cards.database.Database;
import by.ntck.control_cards.database.objects.CED_CTRL_CARD;
import by.ntck.control_cards.database.objects.CED_EXECUTOR;
import by.ntck.control_cards.objects.cards.CardString;
import by.ntck.control_cards.objects.Session;
import by.ntck.control_cards.objects.Check;
import by.ntck.control_cards.objects.Login;
import by.ntck.control_cards.PrintExcel;

@Controller 
@SessionAttributes({"sessionJSP"})
public class MainController {
	
	//Создание сессии
	@ModelAttribute("sessionJSP")
	public Session CreateSession() {
		return new Session();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView Main() { 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:valid");
		return modelAndView;
	}
	
	@RequestMapping(value = "/valid", method = RequestMethod.GET)
	public ModelAndView Valid(@ModelAttribute("sessionJSP") Session session) { 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		session.setPage_name("valid");
		Database db = new Database();
		db.Open();
		modelAndView.addObject("executorsCtrlJSP", db.ListExecutorsCtrl());
		modelAndView.addObject("executorsExecJSP", db.ListExecutorsExec());
		db.Close();
		modelAndView.addObject("page_textJSP", "Действующие");
		modelAndView.addObject("page_text_smallJSP", "действующих");
		modelAndView.addObject("field_nameJSP", "c.DATE_EXEC ASC");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleted", method = RequestMethod.GET)
	public ModelAndView Deleted(@ModelAttribute("sessionJSP") Session session) { 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		session.setPage_name("deleted");
		Database db = new Database();
		db.Open();
		modelAndView.addObject("executorsCtrlJSP", db.ListExecutorsCtrl());
		modelAndView.addObject("executorsExecJSP", db.ListExecutorsExec());
		db.Close();
		modelAndView.addObject("page_textJSP", "Снятые");
		modelAndView.addObject("page_text_smallJSP", "снятых");
		modelAndView.addObject("field_nameJSP", "c.DATE_EXEC ASC");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add",  method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Add(@ModelAttribute("sessionJSP") Session session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getLogin()) {
			modelAndView.setViewName("add");
			session.setPage_name("add");
			Database db = new Database();
			db.Open();
			modelAndView.addObject("executorsCtrlJSP", db.ListExecutorsCtrl());
			modelAndView.addObject("executorsExecJSP", db.ListExecutorsExec());
			db.Close();
		} else {
			modelAndView.setViewName("redirect:valid");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/users",  method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Users(@ModelAttribute("sessionJSP") Session session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getLogin()) {
			modelAndView.setViewName("users");
			session.setPage_name("users");
			modelAndView.addObject("field_nameJSP", "EXEC_USER ASC");
		} else {
			modelAndView.setViewName("redirect:valid");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Login(@ModelAttribute("sessionJSP") Session session, @ModelAttribute("loginJSP") Login login) { 
		ModelAndView modelAndView = new ModelAndView();
		if (session.getLogin()) session.Logout();
		if (login.getLogin() != null) {
			if (login.getLogin().length() > 0) {
				Database db = new Database();
				db.Open();
				if(db.CheckLogin(login.getLogin())) {
					session.Login();
					modelAndView.setViewName("redirect:valid");
					modelAndView.addObject("loginJSP", new Login());
				} else {
					modelAndView.setViewName("login");
					modelAndView.addObject("loginJSP", new Login("Неверный логин!"));
				}
				db.Close();	
			} else {
				modelAndView.setViewName("login");
				modelAndView.addObject("loginJSP", new Login("Поле логин пусто!"));
			}
			
		} else {
			modelAndView.setViewName("login");
			modelAndView.addObject("loginJSP", new Login());
		}		
		return modelAndView;
	}
	
	//AJAX request, event of opening / closing search
	@RequestMapping(value = "/change-search-mode", method = RequestMethod.POST)
	public @ResponseBody void ChangeSearchModeAjax(  		
  		@RequestParam boolean flag,
  		@ModelAttribute("sessionJSP") Session session){
		session.setSearch_open(flag);
	}
	
	
	//AJAX запрос на выборку карточек с диапазоном
	@RequestMapping(value = "/get-cards", method = RequestMethod.POST)
	public @ResponseBody Object CardsAjax(
  		@RequestParam String page_name,	@RequestParam String field_name, @RequestParam String typeExtension,
  		@RequestParam String NAME_DOC, @RequestParam String DESC_DOC, @RequestParam String MISSION,	@RequestParam String CTRL_FACE,	
  		@RequestParam String EXECUTOR_1, @RequestParam String EXECUTOR_2, @RequestParam String EXECUTOR_3, @RequestParam String EXECUTOR_4, 
  		@RequestParam String DATE_EXEC, @RequestParam String DATE_EXEC2, @RequestParam String DATE_CTRL, @RequestParam String DATE_CTRL2,
  		@RequestParam String DATE_EXTENSION, @RequestParam String DATE_EXTENSION2, @RequestParam String TAKE_OFF_CTRL, @RequestParam String TAKE_OFF_CTRL2,
  		@RequestParam int next_show_start,	@RequestParam int next_show_finish) {

		Database db = new Database();
		byte[] jsonUTF8 = null;
		try {
			db.Open();
			String WHERE = Database.GenerateWHERE(typeExtension,
					NAME_DOC, DESC_DOC, MISSION, CTRL_FACE,	
			  		EXECUTOR_1, EXECUTOR_2, EXECUTOR_3, EXECUTOR_4, 
			  		DATE_EXEC, DATE_EXEC2, DATE_CTRL, DATE_CTRL2,
			  		DATE_EXTENSION, DATE_EXTENSION2, TAKE_OFF_CTRL, TAKE_OFF_CTRL2);
			List<CardString> cards;
			if (page_name.equals("valid")) cards = db.CardsValid(WHERE, field_name, next_show_start, next_show_finish);	
			else cards = db.CardsDeleted(WHERE, field_name, next_show_start, next_show_finish);
			String json = new Gson().toJson(cards);
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			db.Close();	
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на выборку карточки по id
	@RequestMapping(value = "/get-card", method = RequestMethod.POST)
	public @ResponseBody Object CardAjax(
  		@RequestParam String CTRL_ID) {
		Database db = new Database();
		byte[] jsonUTF8 = null;
		try {
			db.Open();
			CardString card = db.GetCardString(CTRL_ID);
			String json = new Gson().toJson(card);
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
			db.Close();	
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на обновление карточки
	@RequestMapping(value = "/update-card", method = RequestMethod.POST)
	public @ResponseBody Object UpdateCardAjax(
  		@RequestParam String CTRL_ID, @RequestParam String NAME_DOC, @RequestParam String DESC_DOC, @RequestParam String MISSION, @RequestParam String CTRL_FACE,
  		@RequestParam String EXECUTOR_1, @RequestParam String EXECUTOR_2, @RequestParam String EXECUTOR_3, @RequestParam String EXECUTOR_4,
  		@RequestParam String DATE_EXEC,	@RequestParam String DATE_CTRL,	@RequestParam String DATE_EXTENSION, @RequestParam String TAKE_OFF_CTRL, @ModelAttribute("sessionJSP") Session session) {

		byte[] jsonUTF8 = null;
		Check check = new Check();	
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				check = Database.CreateCard(db, CTRL_ID, 
						NAME_DOC, DESC_DOC, MISSION, CTRL_FACE,	
				  		EXECUTOR_1, EXECUTOR_2, EXECUTOR_3, EXECUTOR_4, 
				  		DATE_EXEC, DATE_CTRL, DATE_EXTENSION, TAKE_OFF_CTRL);
				if (check.getErrors().isEmpty()) {
					db.UpdateCard(check.getCard());
				}
			} catch (Error e) {
				check.addErrors("Неизвестная ошибка. Обновите страницу и попробуйте ещё раз.");
			} finally {
				db.Close();
			}
		} else {
			check.addErrors("Ошибка доступа.");
		}
		
		String json = new Gson().toJson(check.getErrors());
		try {
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на добавление карточки
	@RequestMapping(value = "/add-card", method = RequestMethod.POST)
	public @ResponseBody Object AddAjax(  		
  		@RequestParam String NAME_DOC, @RequestParam String DESC_DOC, @RequestParam String MISSION,	@RequestParam String CTRL_FACE,
  		@RequestParam String EXECUTOR_1, @RequestParam String EXECUTOR_2, @RequestParam String EXECUTOR_3, @RequestParam String EXECUTOR_4,
  		@RequestParam String DATE_EXEC,	@RequestParam String DATE_CTRL, @ModelAttribute("sessionJSP") Session session) {
		byte[] jsonUTF8 = null;
		Check check = new Check();	
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				String CTRL_ID = String.valueOf(db.GetLastCTRL_ID()+1);
				check = Database.CreateCard(db, CTRL_ID, 
						NAME_DOC, DESC_DOC, MISSION, CTRL_FACE,	
				  		EXECUTOR_1, EXECUTOR_2, EXECUTOR_3, EXECUTOR_4, 
				  		DATE_EXEC, DATE_CTRL, null, null);
				if (check.getErrors().isEmpty()) {
					db.AddCard(check.getCard());
				}
			} catch(Error e) {
				check.addErrors("Неизвестная ошибка. Обновите страницу и попробуйте ещё раз.");
			} finally {
				db.Close();
			}
		} else {
			check.addErrors("Ошибка доступа.");
		}
		String json = new Gson().toJson(check.getErrors());
		try {
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на обновление даты продления карточки
	@RequestMapping(value = "/update-date-extension", method = RequestMethod.POST)
	public @ResponseBody Object UpdateDATE_EXTENSIONAjax(  		
		@RequestParam String CTRL_ID,
		@RequestParam String DATE_EXTENSION, @ModelAttribute("sessionJSP") Session session){

		byte[] jsonUTF8 = null;
		Check check = new Check();	
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				CED_CTRL_CARD card = db.GetCard(CTRL_ID);
				if (Check.isValidDate(DATE_EXTENSION)) {
					try{
						Date date = new SimpleDateFormat("dd.MM.yyyy").parse(DATE_EXTENSION);
						card.setDATE_EXTENSION(date);
						if (card.getDATE_CTRL() != null && card.getDATE_EXTENSION() != null) {
							if(card.getDATE_CTRL().after(card.getDATE_EXTENSION())) {
								check.addErrors("Дата в поле 'Срок выполнения' больше даты в поле 'Срок продления'.");
							}
						}
						if (check.getErrors().isEmpty()) db.UpdateCard(card);
					}catch(Exception e){
						check.addErrors("Дата в поле 'Срок продления' введена некорректно.");
					}
				} else check.addErrors("Дата в поле 'Срок продления' введена некорректно.");
			} catch(Error e) {
				check.addErrors("Неизвестная ошибка. Обновите страницу и попробуйте ещё раз.");
			} finally {
				db.Close();
			}
		} else {
			check.addErrors("Ошибка доступа.");
		}
		String json = new Gson().toJson(check.getErrors());
		try {
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на обновление даты снятия с контроля карточки
	@RequestMapping(value = "/update-take-off-ctrl", method = RequestMethod.POST)
	public @ResponseBody Object UpdateTAKE_OFF_CTRLAjax(  		
  		@RequestParam String CTRL_ID,
  		@RequestParam String TAKE_OFF_CTRL, @ModelAttribute("sessionJSP") Session session){
		
		byte[] jsonUTF8 = null;
		Check check = new Check();	
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				CED_CTRL_CARD card = db.GetCard(CTRL_ID);
				if (Check.isValidDate(TAKE_OFF_CTRL)) {
					try{
						Date date = new SimpleDateFormat("dd.MM.yyyy").parse(TAKE_OFF_CTRL);
						card.setTAKE_OFF_CTRL(date);
						if (card.getDATE_EXTENSION() != null && card.getTAKE_OFF_CTRL() != null) {
							if(card.getDATE_EXTENSION().after(card.getTAKE_OFF_CTRL())) {
								check.addErrors("Дата в поле 'Срок продления' больше даты в поле 'Снята с контроля'.");
							}
						} else if (card.getDATE_CTRL() != null && card.getTAKE_OFF_CTRL() != null) {
							if(card.getDATE_CTRL().after(card.getTAKE_OFF_CTRL())) {
								check.addErrors("Дата в поле 'Срок выполнения' больше даты в поле 'Снята с контроля'.");
							}
						}
						if (check.getErrors().isEmpty()) db.UpdateCard(card);
					}catch(Exception e){
						check.addErrors("Дата в поле 'Снята с контроля' введена некорректно.");
					}
				} else check.addErrors("Дата в поле 'Снята с контроля' введена некорректно.");
			} catch(Error e) {
				check.addErrors("Неизвестная ошибка. Обновите страницу и попробуйте ещё раз.");
			} finally {
				db.Close();
			}
		} else {
			check.addErrors("Ошибка доступа.");
		}
		String json = new Gson().toJson(check.getErrors());
		try {
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на выборку исполнительных лиц
	@RequestMapping(value = "/get-executors", method = RequestMethod.POST)
	public @ResponseBody Object ExecutorsAjax(
  		@RequestParam String field_name,
  		@RequestParam String EXEC_USER) {
		
		Database db = new Database();
		List<CED_EXECUTOR> executors = new ArrayList<CED_EXECUTOR>();
		try {
			db.Open();
			executors = db.GetExecutors(field_name, EXEC_USER);
		} catch(Error e) {
			e.printStackTrace();
		} finally {
			db.Close();	
		}
		String json = new Gson().toJson(executors);
		byte[] jsonUTF8 = null;
		try {
			jsonUTF8 = json.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonUTF8;
	}
	
	//AJAX запрос на добавление исполнительного лица
	@RequestMapping(value = "/add-executor", method = RequestMethod.POST)
	public @ResponseBody String AddExecutorAjax(  		
  		@RequestParam String EXEC_USER,
  		@RequestParam String EXEC_CTRL_FACE_FLAG,
  		@RequestParam String EXEC_EXECUTOR_FLAG, @ModelAttribute("sessionJSP") Session session){
			
		String result = "TRUE";
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				CED_EXECUTOR exec = new CED_EXECUTOR();
				exec.setEXEC_ID(db.GetLastEXEC_ID() + 1);
				exec.setEXEC_USER(EXEC_USER);
				exec.setEXEC_CTRL_FACE_FLAG(EXEC_CTRL_FACE_FLAG);
				exec.setEXEC_EXECUTOR_FLAG(EXEC_EXECUTOR_FLAG);
				db.AddExecutor(exec);
			} catch(Error e) {
				result = "FALSE";
			} finally {
				db.Close();
			}
		} else {
			result = "FALSE";
		}
		return result;
	}
	
	//AJAX запрос на удаление исполнительного лица по id 
	@RequestMapping(value = "/remove-executor", method = RequestMethod.POST)
	public @ResponseBody String RemoveExecutorAjax(  		
  		@RequestParam long EXEC_ID, @ModelAttribute("sessionJSP") Session session){
		
		String result = "TRUE";
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				db.RemoveExecutor(EXEC_ID);
			} catch(Error e) {
				result = "FALSE";
			} finally {
				db.Close();
			}
		} else {
			result = "FALSE";
		}		
		return result;
	}
	
	//AJAX запрос на обновление ФИО исполнительного лица
	@RequestMapping(value = "/update-executor-user", method = RequestMethod.POST)
	public @ResponseBody String UpdateExecutorUSERAjax(  		
  		@RequestParam long EXEC_ID,
  		@RequestParam String EXEC_USER, @ModelAttribute("sessionJSP") Session session){
		
		String result = "TRUE";
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				CED_EXECUTOR exec = db.GetExecutor(EXEC_ID);
				exec.setEXEC_USER(EXEC_USER);
				db.UpdateExecutor(exec);
			} catch(Error e) {
				result = "FALSE";
			} finally {
				db.Close();
			}
		} else {
			result = "FALSE";
		}		
		return result;
	}
	
	//AJAX запрос на обновление флага контрольное лицо у исполнительного лица
	@RequestMapping(value = "/update-executor-ctrl", method = RequestMethod.POST)
	public @ResponseBody String UpdateExecutorCTRLAjax(  		
  		@RequestParam long EXEC_ID,
  		@RequestParam String EXEC_CTRL_FACE_FLAG, @ModelAttribute("sessionJSP") Session session){
		
		String result = "TRUE";
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				CED_EXECUTOR exec = db.GetExecutor(EXEC_ID);
				exec.setEXEC_CTRL_FACE_FLAG(EXEC_CTRL_FACE_FLAG);
				db.UpdateExecutor(exec);
			} catch(Error e) {
				result = "FALSE";
			} finally {
				db.Close();
			}
		} else {
			result = "FALSE";
		}		
		return result;
	}
	
	//AJAX запрос на обновление флага исполнитель у исполнительного лица
	@RequestMapping(value = "/update-executor-exec", method = RequestMethod.POST)
	public @ResponseBody String UpdateExecutorEXECAjax(  		
  		@RequestParam long EXEC_ID,
  		@RequestParam String EXEC_EXECUTOR_FLAG, @ModelAttribute("sessionJSP") Session session){
		
		String result = "TRUE";
		if(session.getLogin()) {
			Database db = new Database();
			try {
				db.Open();
				CED_EXECUTOR exec = db.GetExecutor(EXEC_ID);
				exec.setEXEC_EXECUTOR_FLAG(EXEC_EXECUTOR_FLAG);
				db.UpdateExecutor(exec);
			} catch(Error e) {
				result = "FALSE";
			} finally {
				db.Close();
			}
		} else {
			result = "FALSE";
		}	
		return result;
	}
	
	//Печать карточки pdf
	@RequestMapping(value = "/print-card-pdf", method = RequestMethod.POST)
	public ModelAndView PrintCardPdf(
  		@RequestParam String watchCTRL_ID,
  		@ModelAttribute("sessionJSP") Session session) {
			
		ModelAndView modelAndView = new ModelAndView();
		Database db = new Database();
		try {
			db.Open();
			CardString card;
			card = db.GetCardString(watchCTRL_ID);	
			modelAndView.addObject("cardJSP", card);
			modelAndView.setViewName("print-card");
			if (card.getTAKE_OFF_CTRL().length() > 0) {
				modelAndView.addObject("page_textJSP", "Снятая с контроля");
				modelAndView.addObject("page_text_smallJSP", "снятой с контроля");
				modelAndView.addObject("typeJSP", "deleted");
			} else {
				modelAndView.addObject("page_textJSP", "Действующая");
				modelAndView.addObject("page_text_smallJSP", "действующей");
				modelAndView.addObject("typeJSP", "valid");
			}
		} catch (Error e) {
			e.printStackTrace();
		} finally {
			db.Close();	
		}
		return modelAndView;
	}
	
	//Печать списком pdf
	@RequestMapping(value = "/print", method = RequestMethod.POST)
	public ModelAndView PrintPdf(
  		@RequestParam String page_name,	@RequestParam String field_name, @RequestParam String typeExtension,
  		@RequestParam String NAME_DOC, @RequestParam String DESC_DOC, @RequestParam String MISSION,	@RequestParam String CTRL_FACE,	
  		@RequestParam String EXECUTOR_1, @RequestParam String EXECUTOR_2, @RequestParam String EXECUTOR_3, @RequestParam String EXECUTOR_4, 
  		@RequestParam String DATE_EXEC, @RequestParam String DATE_EXEC2, @RequestParam String DATE_CTRL, @RequestParam String DATE_CTRL2,
  		@RequestParam String DATE_EXTENSION, @RequestParam String DATE_EXTENSION2, @RequestParam String TAKE_OFF_CTRL, @RequestParam String TAKE_OFF_CTRL2,
  		@ModelAttribute("sessionJSP") Session session) {
			
		ModelAndView modelAndView = new ModelAndView();
		Database db = new Database();
		try {
			db.Open();
			String WHERE = Database.GenerateWHERE(typeExtension,
					NAME_DOC, DESC_DOC, MISSION, CTRL_FACE,	
			  		EXECUTOR_1, EXECUTOR_2, EXECUTOR_3, EXECUTOR_4, 
			  		DATE_EXEC, DATE_EXEC2, DATE_CTRL, DATE_CTRL2,
			  		DATE_EXTENSION, DATE_EXTENSION2, TAKE_OFF_CTRL, TAKE_OFF_CTRL2);
			List<CardString> cards;
			if (page_name.equals("valid")) cards = db.GetCardsValid(WHERE, field_name);	
			else cards = db.GetCardsDeleted(WHERE, field_name);
			modelAndView.addObject("cardsJSP", cards);
			modelAndView.setViewName("print");
		} catch (Error e) {
			e.printStackTrace();
		} finally {
			db.Close();	
		}
		
		session.setPage_name(page_name);
		if (page_name.equals("valid")) {
			modelAndView.addObject("page_text_smallJSP", "действующих");
		} else {
			modelAndView.addObject("page_text_smallJSP", "снятых");
		}
		modelAndView.addObject("field_nameJSP", field_name);
		return modelAndView;
	}

	//Эспорт в excel списком
	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	public void PrintExcel(  
			String page_name, String field_name, String typeExtension,
	  		String NAME_DOC, String DESC_DOC, String MISSION,	String CTRL_FACE,	
	  		String EXECUTOR_1, String EXECUTOR_2, String EXECUTOR_3, String EXECUTOR_4, 
	  		String DATE_EXEC, String DATE_EXEC2, String DATE_CTRL, String DATE_CTRL2,
	  		String DATE_EXTENSION, String DATE_EXTENSION2, String TAKE_OFF_CTRL, String TAKE_OFF_CTRL2,
	  		HttpServletResponse response ) {
		
		Database db = new Database();
		try {
			db.Open();
			String WHERE = Database.GenerateWHERE(typeExtension,
					NAME_DOC, DESC_DOC, MISSION, CTRL_FACE,	
			  		EXECUTOR_1, EXECUTOR_2, EXECUTOR_3, EXECUTOR_4, 
			  		DATE_EXEC, DATE_EXEC2, DATE_CTRL, DATE_CTRL2,
			  		DATE_EXTENSION, DATE_EXTENSION2, TAKE_OFF_CTRL, TAKE_OFF_CTRL2);
			List<CardString> cards;
			if (page_name.equals("valid")) cards = db.GetCardsValid(WHERE, field_name);	
			else cards = db.GetCardsDeleted(WHERE, field_name);
			PrintExcel.WriteIntoExcel(cards, response);
		} catch (Error e) {
			e.printStackTrace();
		} finally {
			db.Close();	
		}
	}
}