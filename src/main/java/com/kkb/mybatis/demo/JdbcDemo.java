package com.kkb.mybatis.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JdbcDemo {

	@Test
	public void test() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 通过驱动管理类获取数据库链接connection = DriverManager
			connection = DriverManager.getConnection("jdbc:mysql://20x6780k13.imwork.net:9526/FSI_Tickets?characterEncoding=utf-8",
					"root", "fsi2018");

			// 定义sql语句 ?表示占位符
			String sql = "select * from users where name = ?";
			// 获取预处理 statement
			preparedStatement = connection.prepareStatement(sql);

			// 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的
			preparedStatement.setString(1, "Savy Pan");
			// 向数据库发出 sql 执行查询，查询出结果集
			resultSet = preparedStatement.executeQuery();
			// 遍历查询结果集
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + " " + resultSet.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block e.printStackTrace();
				}
			}
		}

	}
}
