package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import play.libs.Json;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;
import com.mize.dto.Employee;
import com.mize.service.EmployeeService;
//import play.mvc.Controller;

@Controller
public class Application extends play.mvc.Controller {
	@Autowired
	EmployeeService employeeService;

	public Result index() {
		return ok(index.render("Employee Service"));
	}

	public Result getEmployeeById() {
		Employee employee = null;
		JsonNode json = request().body().asJson();
		if (json != null) {
			try {
				Employee emp=Json.fromJson(json, Employee.class);
				employee=employeeService.getEmployeeById(emp.getId());
				if(employee!=null){
					play.Logger.info("Employee displayed");
					return ok(Json.toJson(employee));
				}else{
					play.Logger.info("Employee not found");
					return ok(Json.toJson("Employee not found"));
				}
			} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
				play.Logger.error("Error is due to " + emptyResultDataAccessException.getMessage(), emptyResultDataAccessException);
				return ok(Json.toJson("No employee with this id"));
			} catch (Exception exception) {
				play.Logger.error("Error is due to " + exception.getMessage(), exception);
				return ok(Json.toJson(exception.getMessage()));
			}
			
		} else {
			play.Logger.info("Not a valid request");
			return internalServerError(Json.toJson("Invalid request"));
		}
	}

	public Result getAllEmployees() {
		play.Logger.debug("Entered into getAllEmployees method");
		List<Employee> list=null;
		try {
			list = employeeService.getAllEmployees();
		} catch (Exception e) {
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

	public Result insertEmployee() {
		play.Logger.debug("Entered into insertEmployee method");
		JsonNode json = request().body().asJson();
		if (json != null) {
			Employee employee = Json.fromJson(json, Employee.class);
			int result=0;
			try {
				result = employeeService.insertEmployee(employee);
			} catch (DuplicateKeyException duplicateKeyException) {
				play.Logger.error("Error is due to "+duplicateKeyException.getMessage(),duplicateKeyException);
				return ok(Json.toJson("Employee with the id already exist"));
			} catch (Exception exception) {
				play.Logger.error("Error is due to "+exception.getMessage(),exception);
				return internalServerError(Json.toJson(exception.getMessage()));
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

	public Result deleteEmployee() {
		play.Logger.debug("Entered into deleteEmployee method");
		JsonNode json = request().body().asJson();
		if (json != null) {
			Employee employee = Json.fromJson(json, Employee.class);
			int result = 0;
			try {
				result = employeeService.deleteEmployee(employee);
			} catch (Exception e) {
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

	public Result updateEmployee() {
		play.Logger.debug("Entered into updateEmployee method");
		JsonNode json = request().body().asJson();
		if (json != null) {
			Employee employee = Json.fromJson(json, Employee.class);
			int result=0;
			try {
				result = employeeService.updateEmployee(employee);
			} catch (Exception e) {
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
