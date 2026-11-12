package com.bank.dao;

import com.bank.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Legacy DAO - inline SQL for accounts.
 */
public class AccountDAO {

    public List<Account> findByUserId(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Account> list = new ArrayList<Account>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT id, user_id, account_number, balance FROM ACCOUNTS WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserId(rs.getInt("user_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setBalance(rs.getBigDecimal("balance"));
                list.add(acc);
            }
            return list;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            DBUtil.closeQuietly(conn);
        }
    }

    public Account findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT id, user_id, account_number, balance FROM ACCOUNTS WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserId(rs.getInt("user_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setBalance(rs.getBigDecimal("balance"));
                return acc;
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            DBUtil.closeQuietly(conn);
        }
    }

    public Account findByAccountNumber(String accountNumber) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT id, user_id, account_number, balance FROM ACCOUNTS WHERE account_number = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNumber);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserId(rs.getInt("user_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setBalance(rs.getBigDecimal("balance"));
                return acc;
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            DBUtil.closeQuietly(conn);
        }
    }

    public List<Account> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Account> list = new ArrayList<Account>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT id, user_id, account_number, balance FROM ACCOUNTS ORDER BY id";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserId(rs.getInt("user_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setBalance(rs.getBigDecimal("balance"));
                list.add(acc);
            }
            return list;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            DBUtil.closeQuietly(conn);
        }
    }

    public void updateBalance(int accountId, java.math.BigDecimal newBalance) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE ACCOUNTS SET balance = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBigDecimal(1, newBalance);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            DBUtil.closeQuietly(conn);
        }
    }

    public void create(int userId, String accountNumber, java.math.BigDecimal balance) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO ACCOUNTS (user_id, account_number, balance) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, accountNumber);
            stmt.setBigDecimal(3, balance != null ? balance : java.math.BigDecimal.ZERO);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            DBUtil.closeQuietly(conn);
        }
    }
}
