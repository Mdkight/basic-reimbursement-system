package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.objects.Category;

public class CategoryDatabase {
	
	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> allCategories = new ArrayList<Category>();

		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("select categories.reimbursementtype, categories.reimColor, sum(reimbursements.amount) from reimbursements  inner join categories on reimbursements.reimbursementtype = categories.reimbursementtype where reimbursements.accepted='true' group by categories.reimbursementtype");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category cat = new Category(rs.getString(1), rs.getString(2), rs.getInt(3));
				allCategories.add(cat);


			}
			findPercentages(allCategories);
			return allCategories;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected ArrayList<Category> findPercentages(ArrayList<Category> categories) {
		
		double totalExpense = 0;
		double percent;
		

		for (Category cat:categories) {
			totalExpense = (totalExpense + cat.getAmount());


		}
		
		for (Category cat:categories) {
			percent = ((cat.getAmount()/totalExpense) * 100);
			cat.setPercentage(percent);

		}

		return categories;
	}
	

}
