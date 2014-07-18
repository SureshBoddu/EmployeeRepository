package controllers;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.mize.dto.Employee;
import com.mize.service.EmployeeServiceImpl;

import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	static EmployeeServiceImpl employeeService = null;
	public static Result index() {
		return ok(index.render("Employee Service"));
	}

	public static Result getEmployeeById() {
		employeeService = new EmployeeServiceImpl();
		Employee employee = null;
		JsonNode json = request().body().asJson();
		if (json != null) {
			try {
				Employee emp=Json.fromJson(json, Employee.class);
				employee=employeeService.getEmployeeById(emp.getId());
			} catch (NumberFormatException | SQLException e) {
				play.Logger.error("Error is due to " + e.getMessage(), e);
				return internalServerError(Json.toJson(e.getMessage()));
			}
			if (employee != null) {
				play.Logger.info("Employee found");
				return ok(Json.toJson(employee));
			} else {
				play.Logger.info("Employee not found");
				return notFound("Employee not found");
			}
		} else {
			play.Logger.info("Not a valid request");
			return internalServerError(Json.toJson("Invalid request"));
		}
	}

	public static Result getAllEmployees() {
		play.Logger.debug("Entered into getAllEmployees method");
		employeeService = new EmployeeServiceImpl();
		List<Employee> list=null;
		try {
			list = employeeService.getAllEmployees();
		} catch (SQLException e) {
			play.Logger.error("Error is due to "+e.getMessage(),e);
			return internalServerError(Json.toJson(e.getMessage()));
		}
		if(list!=null){
			play.Logger.info("List is diplayed");
			return ok(Json.toJson(list));
		}
		else{
			play.Logger.info("List is empty");
			return notFound(Json.toJson("No employees in the list"));
		}
	}

	public static Result insertEmployee() {
		play.Logger.debug("Entered into insertEmployee method");
		employeeService = new EmployeeServiceImpl();
		JsonNode json = request().body().asJson();
		if (json != null) {
			Employee employee = Json.fromJson(json, Employee.class);
			int result=0;
			try {
				result = employeeService.insertEmployee(employee);
			} catch (SQLException e) {
				play.Logger.error("Error is due to "+e.getMessage(),e);
				return internalServerError(Json.toJson(e.getMessage()));
			}
			if (result == 1) {
				play.Logger
						.info("Successfully inserted employee record into database");
				return ok(Json.toJson("Successfully inserted"));
			} else {
				play.Logger.info("Employee record not inserted");
				return ok(Json.toJson("Insertion failed"));
			}
		} else {
			play.Logger.info("Not a valid inserted");
			return internalServerError("Invalid request");
		}
	}

	public static Result deleteEmployee() {
		play.Logger.debug("Entered into deleteEmployee method");
		employeeService = new EmployeeServiceImpl();
		JsonNode json = request().body().asJson();
		if (json != null) {
			Employee employee = Json.fromJson(json, Employee.class);
			int result = 0;
			try {
				result = employeeService.deleteEmployee(employee);
			} catch (SQLException e) {
				play.Logger.error("Error is due to "+e.getMessage(),e);
				return internalServerError(Json.toJson(e.getMessage()));
			}
			if (result == 1){
				play.Logger.info("Successfully deleted employee record from the database");
				return ok(Json.toJson("Successfully deleted"));
			}
			else{
				play.Logger.info("Deletion of employee record from the database failed");
				return notFound(Json.toJson("Deletion failed"));
			}	
		} else {
			play.Logger.info("Not a valid inserted");
			return badRequest(Json.toJson("Invalid request"));
		}
	}

	public static Result updateEmployee() {
		play.Logger.debug("Entered into updateEmployee method");
		employeeService = new EmployeeServiceImpl();
		JsonNode json = request().body().asJson();
		if (json != null) {
			Employee employee = Json.fromJson(json, Employee.class);
			int result=0;
			try {
				result = employeeService.updateEmployee(employee);
			} catch (SQLException e) {
				play.Logger.error("Error is due to "+e.getMessage(),e);
				return internalServerError(Json.toJson(e.getMessage()));
			}
			if (result == 1) {
				play.Logger.info("Successfully employee updated");
				return ok(Json.toJson("Successfully updated"));
			} else {
				play.Logger.info("Employee updation failed");
				return ok(Json.toJson("Updation failed"));
			}
		} else {
			play.Logger.info("Employee is not updated");
			return badRequest(Json.toJson("Invalid request"));
		}

	}
}
