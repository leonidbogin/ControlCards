package by.ntck.control_cards.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import by.ntck.control_cards.database.objects.CED_ADMIN;
import by.ntck.control_cards.database.objects.CED_CTRL_CARD;
import by.ntck.control_cards.database.objects.CED_EXECUTOR;
import by.ntck.control_cards.objects.Check;
import by.ntck.control_cards.objects.cards.CardString;
import by.ntck.control_cards.objects.cards.ExecutorString;

public class Database {
	
	private Session session;
	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;

	public Database() {
		Locale.setDefault(Locale.ENGLISH);
		setServiceRegistry(new StandardServiceRegistryBuilder().
		        configure().loadProperties("hibernate.cfg.xml").build());
	}
	
	//Подключиться к бд
	public void Open() {
	    setSessionFactory(new Configuration().buildSessionFactory(serviceRegistry));
	}
	
	//Отключиться от бд
	public void Close() {
		sessionFactory.close();
	}
	
	//Создать сессию бд
	public void Start() {
		session = sessionFactory.openSession();
	}
	
	//Удалить сессию бд
	public void End() {
		if (session != null && session.isOpen()) session.close();
	}

	//Генерация для выборки WHERE 
	public static String GenerateWHERE(String typeExtension,
			String NAME_DOC, String DESC_DOC, String MISSION, String CTRL_FACE,	
	  		String EXECUTOR_1, String EXECUTOR_2, String EXECUTOR_3, String EXECUTOR_4, 
	  		String DATE_EXEC, String DATE_EXEC2, String DATE_CTRL, String DATE_CTRL2,
	  		String DATE_EXTENSION, String DATE_EXTENSION2, String TAKE_OFF_CTRL, String TAKE_OFF_CTRL2) {
		String WHERE = "";
		int k = 0;
		if(NAME_DOC.length() > 0) {
			k++;
			WHERE += "lower(c.NAME_DOC) LIKE lower('%" + NAME_DOC + "%') ";
		}
		if(DESC_DOC.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "lower(c.DESC_DOC) LIKE lower('%" + DESC_DOC + "%') ";
		}
		if(MISSION.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "lower(c.MISSION) LIKE lower('%" + MISSION + "%') ";
		}
		if(CTRL_FACE.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "lower(ctrl.EXEC_USER) LIKE lower('%" + CTRL_FACE + "%') ";
		}
		if(EXECUTOR_1.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "(lower(exec1.EXEC_USER) LIKE lower('%" + EXECUTOR_1 + "%') OR "
					+ "lower(exec2.EXEC_USER) LIKE lower('%" + EXECUTOR_1 + "%') OR "
					+ "lower(exec3.EXEC_USER) LIKE lower('%" + EXECUTOR_1 + "%') OR "
					+ "lower(exec4.EXEC_USER) LIKE lower('%" + EXECUTOR_1 + "%')) ";
		}
		if(EXECUTOR_2.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "(lower(exec1.EXEC_USER) LIKE lower('%" + EXECUTOR_2 + "%') OR "
					+ "lower(exec2.EXEC_USER) LIKE lower('%" + EXECUTOR_2 + "%') OR "
					+ "lower(exec3.EXEC_USER) LIKE lower('%" + EXECUTOR_2 + "%') OR "
					+ "lower(exec4.EXEC_USER) LIKE lower('%" + EXECUTOR_2 + "%')) ";
		}
		if(EXECUTOR_3.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "(lower(exec1.EXEC_USER) LIKE lower('%" + EXECUTOR_3 + "%') OR "
					+ "lower(exec2.EXEC_USER) LIKE lower('%" + EXECUTOR_3 + "%') OR "
					+ "lower(exec3.EXEC_USER) LIKE lower('%" + EXECUTOR_3 + "%') OR "
					+ "lower(exec4.EXEC_USER) LIKE lower('%" + EXECUTOR_3 + "%')) ";
		}
		if(EXECUTOR_4.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "(lower(exec1.EXEC_USER) LIKE lower('%" + EXECUTOR_4 + "%') OR "
					+ "lower(exec2.EXEC_USER) LIKE lower('%" + EXECUTOR_4 + "%') OR "
					+ "lower(exec3.EXEC_USER) LIKE lower('%" + EXECUTOR_4 + "%') OR "
					+ "lower(exec4.EXEC_USER) LIKE lower('%" + EXECUTOR_4 + "%')) ";
		}
		
		if(DATE_EXEC.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "DATE_EXEC >= TO_DATE('" + DATE_EXEC + "', 'DD.MM.YYYY') ";
		}
		if(DATE_EXEC2.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "DATE_EXEC <= TO_DATE('" + DATE_EXEC2 + "', 'DD.MM.YYYY') ";
		}
		if(DATE_CTRL.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "DATE_CTRL >= TO_DATE('" + DATE_CTRL + "', 'DD.MM.YYYY') ";
		}
		if(DATE_CTRL2.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "DATE_CTRL <= TO_DATE('" + DATE_CTRL2 + "', 'DD.MM.YYYY') ";
		}
		if (typeExtension.equals("1")) {
			if(DATE_EXTENSION.length() > 0) {
				if (k++ > 0) WHERE += "AND ";
				WHERE += "DATE_EXTENSION >= TO_DATE('" + DATE_EXTENSION + "', 'DD.MM.YYYY') ";
			}
			if(DATE_EXTENSION2.length() > 0) {
				if (k++ > 0) WHERE += "AND ";
				WHERE += "DATE_EXTENSION <= TO_DATE('" + DATE_EXTENSION2 + "', 'DD.MM.YYYY') ";
			} 
			if(DATE_EXTENSION.length() == 0 && DATE_EXTENSION2.length() == 0){
				if (k++ > 0) WHERE += "AND ";
				WHERE += "DATE_EXTENSION IS NOT NULL ";
			}	
		} else {
			if (typeExtension.equals("2")) {
				if (k++ > 0) WHERE += "AND ";
				WHERE += "DATE_EXTENSION IS NULL ";
			}
		}
		if(TAKE_OFF_CTRL.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "TAKE_OFF_CTRL >= TO_DATE('" + TAKE_OFF_CTRL + "', 'DD.MM.YYYY') ";
		}
		if(TAKE_OFF_CTRL2.length() > 0) {
			if (k++ > 0) WHERE += "AND ";
			WHERE += "TAKE_OFF_CTRL <= TO_DATE('" + TAKE_OFF_CTRL2 + "', 'DD.MM.YYYY') ";
		}
		return WHERE;
	}
	
	//Генерация новой карточки с выводом ошибок при создании
	public static Check CreateCard(Database db, String CTRL_ID,
			String NAME_DOC, String DESC_DOC, String MISSION, String CTRL_FACE,	
	  		String EXECUTOR_1, String EXECUTOR_2, String EXECUTOR_3, String EXECUTOR_4, 
	  		String DATE_EXEC, String DATE_CTRL, String DATE_EXTENSION, String TAKE_OFF_CTRL) {
		CED_CTRL_CARD card = new CED_CTRL_CARD();
		List<String> errors = new ArrayList<String>();
		card.setCTRL_ID(Long.valueOf(CTRL_ID));
		if (NAME_DOC.length() <= 256) {
			if (NAME_DOC.length() > 0) {
				card.setNAME_DOC(NAME_DOC);
			} else {
				card.setNAME_DOC(null);
			}
		} else errors.add("Поле 'Наименование документа' длинее 256 символов.");
		
		if (DESC_DOC.length() <= 256) {
			if (DESC_DOC.length() > 0) {
				card.setDESC_DOC(DESC_DOC);
			} else {
				card.setDESC_DOC(null);
			}
		} else errors.add("Поле 'Описание документа' длинее 256 символов.");
		
		if (MISSION.length() <= 512) {
			if (MISSION.length() > 0) {
				card.setMISSION(MISSION);
			} else {
				card.setMISSION(null);
			}
		} else errors.add("Поле 'Содержание поручения' длинее 512 символов.");
		
		if (NAME_DOC.length() + DESC_DOC.length() + MISSION.length() == 0) {
			errors.add("Поля 'Наименование документа', 'Описание документа', 'Содержание поручения' пустые.");
		}
		
		if(CTRL_FACE.length() <= 256)  {
			if(CTRL_FACE.length() > 0) {
				CED_EXECUTOR exec = db.GetExecutor(CTRL_FACE);
				if (exec == null) errors.add("Контрольное лицо не найдено.");
				else card.setCTRL_FACE(exec.getEXEC_ID());
			} else {
				card.setCTRL_FACE(0);
			}
		} else errors.add("Поле 'Контрольное лицо' длинее 256 символов.");
		
		
		if(EXECUTOR_1.length() <= 256) {
			if(EXECUTOR_1.length() > 0) {
				if (EXECUTOR_2.equals(EXECUTOR_1)) errors.add("Поле 'Исполнитель 1' равно с полем 'Исполнитель 2'.");
				if (EXECUTOR_3.equals(EXECUTOR_1)) errors.add("Поле 'Исполнитель 1' равно с полем 'Исполнитель 3'.");
				if (EXECUTOR_4.equals(EXECUTOR_1)) errors.add("Поле 'Исполнитель 1' равно с полем 'Исполнитель 4'.");
				CED_EXECUTOR exec = db.GetExecutor(EXECUTOR_1);
				if (exec == null) errors.add("Исполнитель 1 не найден.");
				else card.setEXECUTOR_1(exec.getEXEC_ID());
			} else {
				card.setEXECUTOR_1(0);
			}
		} else {
			errors.add("Поле 'Исполнитель 1' длинее 256 символов.");
		}
		
		if(EXECUTOR_2.length() <= 256) {
			if(EXECUTOR_2.length() > 0) {
				if (EXECUTOR_3.equals(EXECUTOR_2)) errors.add("Поле 'Исполнитель 2' равно с полем 'Исполнитель 3'.");
				if (EXECUTOR_4.equals(EXECUTOR_2)) errors.add("Поле 'Исполнитель 2' равно с полем 'Исполнитель 4'.");
				CED_EXECUTOR exec = db.GetExecutor(EXECUTOR_2);
				if (exec == null) errors.add("Исполнитель 2 не найден.");
				else card.setEXECUTOR_2(exec.getEXEC_ID());
			} else {
				card.setEXECUTOR_2(0);
			}
		} else {
			errors.add("Поле 'Исполнитель 2' длинее 256 символов.");
		}
		
		if(EXECUTOR_3.length() <= 256) {
			if(EXECUTOR_3.length() > 0) {
				if (EXECUTOR_4.equals(EXECUTOR_3)) errors.add("Поле 'Исполнитель 3' равно с полем 'Исполнитель 4'.");
				CED_EXECUTOR exec = db.GetExecutor(EXECUTOR_3);
				if (exec == null) errors.add("Исполнитель 3 не найден.");
				else card.setEXECUTOR_3(exec.getEXEC_ID());
			} else {
				card.setEXECUTOR_3(0);
			}
		} else {
			errors.add("Поле 'Исполнитель 3' длинее 256 символов.");
		}
		
		if(EXECUTOR_4.length() <= 256) {
			if(EXECUTOR_4.length() > 0) {
				CED_EXECUTOR exec = db.GetExecutor(EXECUTOR_4);
				if (exec == null) errors.add("Исполнитель 4 не найден.");
				else card.setEXECUTOR_4(exec.getEXEC_ID());
			} else {
				card.setEXECUTOR_4(0);
			}
		} else {
			errors.add("Поле 'Исполнитель 4' длинее 256 символов.");
		}
		
		
		if (Check.isValidDate(DATE_EXEC)) {
			try{
				Date date = new SimpleDateFormat("dd.MM.yyyy").parse(DATE_EXEC);
				card.setDATE_EXEC(date);
			}catch(Exception e){
				errors.add("Дата в поле 'Постановка на контроль' введена некорректно.");
			}
		} else errors.add("Дата в поле 'Постановка на контроль' введена некорректно.");
		if (Check.isValidDate(DATE_CTRL)) {
			try{
				Date date = new SimpleDateFormat("dd.MM.yyyy").parse(DATE_CTRL);
				card.setDATE_CTRL(date);
			}catch(Exception e){
				errors.add("Дата в поле 'Срок выполнения' введена некорректно.");
			}
		} else errors.add("Дата в поле 'Срок выполнения' введена некорректно.");
		if (DATE_EXTENSION != null) {
			if (DATE_EXTENSION.length() > 0) {
				if (Check.isValidDate(DATE_EXTENSION)) {
					try{
						Date date = new SimpleDateFormat("dd.MM.yyyy").parse(DATE_EXTENSION);
						card.setDATE_EXTENSION(date);
					}catch(Exception e){
						errors.add("Дата в поле 'Срок продления' введена некорректно.");
					}
				} else errors.add("Дата в поле 'Срок продления' введена некорректно.");
			} else {
				card.setDATE_EXTENSION(null);
			}
		} else {
			card.setDATE_EXTENSION(null);
		}
		if (TAKE_OFF_CTRL != null) {
			if (TAKE_OFF_CTRL.length() > 0) {
				if (Check.isValidDate(TAKE_OFF_CTRL)) {
					try{
						Date date = new SimpleDateFormat("dd.MM.yyyy").parse(TAKE_OFF_CTRL);
						card.setTAKE_OFF_CTRL(date);
					}catch(Exception e){
						errors.add("Дата в поле 'Снята с контроля' введена некорректно.");
					}
				} else errors.add("Дата в поле 'Снята с контроля' введена некорректно.");
			} else {
				card.setTAKE_OFF_CTRL(null);
			}
		} else {
			card.setTAKE_OFF_CTRL(null);
		}
		
		if(card.getDATE_EXEC() != null && card.getDATE_CTRL() != null) {
			if(card.getDATE_EXEC().after(card.getDATE_CTRL())) {
				errors.add("Дата в поле 'Постановка на контроль' больше даты в поле 'Срок выполнения'.");
			}
		}
		
		if (card.getDATE_CTRL() != null && card.getDATE_EXTENSION() != null) {
			if(card.getDATE_CTRL().after(card.getDATE_EXTENSION())) {
				errors.add("Дата в поле 'Срок выполнения' больше даты в поле 'Срок продления'.");
			}
		}
		if (card.getDATE_EXTENSION() != null && card.getTAKE_OFF_CTRL() != null) {
			if(card.getDATE_EXTENSION().after(card.getTAKE_OFF_CTRL())) {
				errors.add("Дата в поле 'Срок продления' больше даты в поле 'Снята с контроля'.");
			}
		} else if (card.getDATE_CTRL() != null && card.getTAKE_OFF_CTRL() != null) {
			if(card.getDATE_CTRL().after(card.getTAKE_OFF_CTRL())) {
				errors.add("Дата в поле 'Срок выполнения' больше даты в поле 'Снята с контроля'.");
			}
		}
		
		
		return new Check(errors, card);
	}
	
	//Проверка пароля
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Boolean CheckLogin(String ADMIN_LOGIN) {
		Start();
		Query query = session.createQuery("FROM CED_ADMIN WHERE lower(ADMIN_LOGIN) = lower(:ADMIN_LOGIN)");
		query.setParameter("ADMIN_LOGIN", ADMIN_LOGIN);
		List<CED_ADMIN> list = query.list();
		End();
		if (!list.isEmpty()) {
			return true;
		} else return false;
	}
	
	//Выборка карточек без диапозона
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CardString> GetAllCards(String WHERE, String ORDER) {
		Start();
		Query query = session.createSQLQuery("SELECT c.CTRL_ID, c.NAME_DOC, c.DESC_DOC, "
				+ "ctrl.EXEC_USER as CTRL_FACE, exec1.EXEC_USER as EXECUTOR_1, exec2.EXEC_USER as EXECUTOR_2, exec3.EXEC_USER as EXECUTOR_3, exec4.EXEC_USER as EXECUTOR_4, "
				+ "c.DATE_EXEC, c.DATE_CTRL, c.MISSION, c.DATE_EXTENSION, c.TAKE_OFF_CTRL " 
				+ "FROM CED_CTRL_CARD c "
				+ "LEFT JOIN CED_EXECUTOR ctrl ON c.CTRL_FACE = ctrl.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec1 ON c.EXECUTOR_1 = exec1.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec2 ON c.EXECUTOR_2 = exec2.EXEC_ID " 
				+ "LEFT JOIN CED_EXECUTOR exec3 ON c.EXECUTOR_3 = exec3.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec4 ON c.EXECUTOR_4 = exec4.EXEC_ID" + WHERE + "ORDER BY " + ORDER);
		List<Object[]> rows = query.list();
		End();
		List<CardString> cards = new ArrayList<CardString>();
		for(Object[] row:rows) {
			cards.add(new CardString(row));
		}
		return cards;
	}
	
	//Выборка карточек c диапозоном
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CardString> GetCards(String WHERE, String ORDER, int show_start, int show_finish) {
		Start();
		Query query = session.createSQLQuery("SELECT * FROM "
				+ "(SELECT c.CTRL_ID, c.NAME_DOC, c.DESC_DOC, "
				+ "ctrl.EXEC_USER as CTRL_FACE, exec1.EXEC_USER as EXECUTOR_1, exec2.EXEC_USER as EXECUTOR_2, exec3.EXEC_USER as EXECUTOR_3, exec4.EXEC_USER as EXECUTOR_4, "
				+ "c.DATE_EXEC, c.DATE_CTRL, c.MISSION, c.DATE_EXTENSION, c.TAKE_OFF_CTRL, "
				+ " ROW_NUMBER() OVER (ORDER BY " + ORDER + ") R " 
				+ "FROM CED_CTRL_CARD c "
				+ "LEFT JOIN CED_EXECUTOR ctrl ON c.CTRL_FACE = ctrl.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec1 ON c.EXECUTOR_1 = exec1.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec2 ON c.EXECUTOR_2 = exec2.EXEC_ID " 
				+ "LEFT JOIN CED_EXECUTOR exec3 ON c.EXECUTOR_3 = exec3.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec4 ON c.EXECUTOR_4 = exec4.EXEC_ID" + WHERE
				+ ") WHERE R BETWEEN " + show_start + " and " + show_finish);
		List<Object[]> rows = query.list();
		End();
		List<CardString> cards = new ArrayList<CardString>();
		for(Object[] row:rows) {
			cards.add(new CardString(row));
		}
		return cards;
	}
	
	//Выборка карточки по id
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CED_CTRL_CARD GetCard(String CTRL_ID) {
		Start();
		Query query = session.createQuery("FROM CED_CTRL_CARD WHERE CTRL_ID = " + CTRL_ID);
		List<CED_CTRL_CARD> rows = query.list();
		End();
		if (rows.isEmpty()) return null;
		else return rows.get(0);
	}   
	
	//Выборка карточки по id в строковом виде CardString
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CardString GetCardString(String CTRL_ID) {
		Start();
		Query query = session.createSQLQuery("SELECT c.CTRL_ID, c.NAME_DOC, c.DESC_DOC, "
				+ "ctrl.EXEC_USER as CTRL_FACE, exec1.EXEC_USER as EXECUTOR_1, exec2.EXEC_USER as EXECUTOR_2, exec3.EXEC_USER as EXECUTOR_3, exec4.EXEC_USER as EXECUTOR_4, "
				+ "c.DATE_EXEC, c.DATE_CTRL, c.MISSION, c.DATE_EXTENSION, c.TAKE_OFF_CTRL "
				+ "FROM CED_CTRL_CARD c "
				+ "LEFT JOIN CED_EXECUTOR ctrl ON c.CTRL_FACE = ctrl.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec1 ON c.EXECUTOR_1 = exec1.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec2 ON c.EXECUTOR_2 = exec2.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec3 ON c.EXECUTOR_3 = exec3.EXEC_ID "
				+ "LEFT JOIN CED_EXECUTOR exec4 ON c.EXECUTOR_4 = exec4.EXEC_ID "
				+ "WHERE CTRL_ID = " + CTRL_ID);
		List<Object[]> rows = query.list();
		End();
		CardString card = null;
		for(Object[] row:rows) {
			card = new CardString(row);
		}
		return card;
	}
	
	//Выборка всех действительных карточек  
	public List<CardString> GetCardsValid(String WHERE, String ORDER) {
		if (WHERE.length() > 0) WHERE = " WHERE TAKE_OFF_CTRL IS NULL AND " + WHERE;
		else WHERE = " WHERE TAKE_OFF_CTRL IS NULL ";
		if (ORDER.length() == 0) ORDER = "DATE_EXEC ASC";
		return GetAllCards(WHERE, ORDER);
	}
	
	//Выборка действительных карточек по диапозону
	public List<CardString> CardsValid(String WHERE, String ORDER, int show_start, int show_finish) {
		if (WHERE.length() > 0) WHERE = " WHERE TAKE_OFF_CTRL IS NULL AND " + WHERE;
		else WHERE = " WHERE TAKE_OFF_CTRL IS NULL ";
		if (ORDER.length() == 0) ORDER = "DATE_EXEC ASC";
		return GetCards(WHERE, ORDER, show_start, show_finish);
	}
	
	//Выборка всех снятых карточек  
	public List<CardString> GetCardsDeleted(String WHERE, String ORDER) {
		if (WHERE.length() > 0) WHERE = " WHERE TAKE_OFF_CTRL IS NOT NULL AND " + WHERE;
		else WHERE = " WHERE TAKE_OFF_CTRL IS NOT NULL ";
		if (ORDER.length() == 0)  ORDER = "DATE_EXEC ASC";
		return GetAllCards(WHERE, ORDER);
	}
	
	//Выборка снятых карточек по диапозону
	public List<CardString> CardsDeleted(String WHERE, String ORDER, int show_start, int show_finish) {
		if (WHERE.length() > 0) WHERE = " WHERE TAKE_OFF_CTRL IS NOT NULL AND " + WHERE;
		else WHERE = " WHERE TAKE_OFF_CTRL IS NOT NULL ";
		if (ORDER.length() == 0)  ORDER = "DATE_EXEC ASC";
		return GetCards(WHERE, ORDER, show_start, show_finish);
	}
	
	//Выборка максимального id карточек
	@SuppressWarnings("rawtypes")
	public int GetLastCTRL_ID() {
		Start();
		Query query = session.createQuery("SELECT MAX(CTRL_ID) FROM CED_CTRL_CARD");
		List list = query.list();
		End();
        int x = 0;
        if (list.get(0) != null) x = Integer.parseInt(list.get(0).toString());
		return x;
	}
	
	//Добавление карточки
	public void AddCard(CED_CTRL_CARD card) {
		Start();
		session.beginTransaction();
		session.save(card);
		session.getTransaction().commit();
		End();
	}
	
	//Обновление карточки
	public void UpdateCard(CED_CTRL_CARD card) {
		Start();
		session.beginTransaction();
		session.update(card);
		session.getTransaction().commit();
		End();
	}
	
	//Выборка исполнительных лиц
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CED_EXECUTOR> GetExecutors(String field_name, String EXEC_USER) {
		Start();
		String WHERE = "";
		if(EXEC_USER.length() > 0) WHERE = " WHERE lower(EXEC_USER) LIKE lower('%" + EXEC_USER + "%')";
		WHERE += " ORDER BY " + field_name;
		Query query = session.createQuery("FROM CED_EXECUTOR" + WHERE);
		List<CED_EXECUTOR> executors = query.list();
		End();
		return executors;
	}
	
	//Выборка исполнительных лиц в строковом виде
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<ExecutorString> GetExecutorsString(Boolean type) {
		Start();
		String exec;
		if (type) exec = "EXEC_CTRL_FACE_FLAG = 'TRUE' ";
		else exec = "EXEC_EXECUTOR_FLAG = 'TRUE' ";
		Query query = session.createQuery("FROM CED_EXECUTOR WHERE " + exec + "ORDER BY EXEC_USER");
		List<CED_EXECUTOR> rows = query.list();
		End();
		List<ExecutorString> executorsString = new ArrayList<ExecutorString>();
		for(CED_EXECUTOR row:rows) {
			executorsString.add(new ExecutorString(row.getEXEC_ID(), row.getEXEC_USER()));
		}
		return executorsString;
	}
	
	//Выборка контрольных лиц в строковом виде
	public List<ExecutorString> ListExecutorsCtrl() {
		return GetExecutorsString(true);
	}
	
	//Выборка исполнителей в строковом виде
	public List<ExecutorString> ListExecutorsExec() {
		return GetExecutorsString(false);
	}

	//Выборка исполнительного лица по id
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CED_EXECUTOR GetExecutor(long EXEC_ID) {
		Start();
		Query query = session.createQuery("FROM CED_EXECUTOR WHERE EXEC_ID = " + EXEC_ID);
		List<CED_EXECUTOR> rows = query.list();
		End();
		if (rows.isEmpty()) return null;
		else return rows.get(0);
	}
	
	//Выборка исполнительного лица по имени
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CED_EXECUTOR GetExecutor(String name) {
		Start();
		Query query = session.createQuery("FROM CED_EXECUTOR WHERE EXEC_USER = '" + name + "'");
		List<CED_EXECUTOR> rows = query.list();
		End();
		if (rows.isEmpty()) return null;
		else return rows.get(0);
	}
	
	//Выборка максимального id исполнительных лиц
	@SuppressWarnings("rawtypes")
	public int GetLastEXEC_ID() {
		Start();
		Query query = session.createQuery("SELECT MAX(EXEC_ID) FROM CED_EXECUTOR");
		List list = query.list();
		End();
	    int x = 0;
	    if (list.get(0) != null) x = Integer.parseInt(list.get(0).toString());
	    return x;
	}
	
	//Добавление исполнительного лица
	public void AddExecutor(CED_EXECUTOR ced_executor) {
		Start();
		session.beginTransaction();
		session.save(ced_executor);
		session.getTransaction().commit();
		End();
	}
	
	//Обновление исполнительного лица
	public void UpdateExecutor(CED_EXECUTOR ced_executor) {
		Start();
		session.beginTransaction();
	    session.update(ced_executor);
	    session.getTransaction().commit();
	    End();
	}
	
	//Удаление исполнительного лица
	public void RemoveExecutor(long EXEC_ID) {
		Start();
		session.beginTransaction();
        session.createQuery("DELETE FROM CED_EXECUTOR WHERE EXEC_ID = " + EXEC_ID).executeUpdate();
        session.getTransaction().commit();
		End();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}
