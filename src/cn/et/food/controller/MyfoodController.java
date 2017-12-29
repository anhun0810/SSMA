package cn.et.food.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.service.MyFoodService;

@Controller
public class MyfoodController {
	@Autowired
	MyFoodService di;
	@ResponseBody
	@RequestMapping(value="/queryFoodList",method={RequestMethod.GET})
	public List<Map<String, Object>> queryFood(String foodname,OutputStream out) throws Exception{
		List<Map<String, Object>> queryAllFood = di.queryAllFood(foodname);
		return queryAllFood;
	}
	
	@RequestMapping(value="/food/{foodId}",method={RequestMethod.DELETE})
	public String deleteFood(@PathVariable String foodId ,OutputStream out) throws Exception{
		try {
			di.deleteFood(foodId);
			out.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			out.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	@RequestMapping(value="/food/{foodId}",method={RequestMethod.PUT})
	public String updateFood(@PathVariable String foodId ,String foodName,String price,OutputStream out) throws Exception{
		try {
			di.updateFood(foodId, foodName, price);
			out.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			out.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	@RequestMapping(value="/food",method={RequestMethod.POST})
	public String saveFood(String foodName,String price,OutputStream out) throws Exception{
		try {
			di.saveFood(foodName, price);
			out.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			out.write("0".getBytes("UTF-8"));
		}
		return null;
	}
}
