package com.example.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MoonMissionRepositoryJdbc {

    // LIST MOON MISSIONS
    private void listMoonMissions(Connection connection) throws SQLException {
        String sql = "SELECT mission_id, spacecraft FROM moon_mission";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Moon Missions:");
            while (rs.next()) {
                System.out.println(rs.getInt("mission_id") + ": " + rs.getString("spacecraft"));
            }
        }
    }


    // GET MOON MISSION BY ID
    private void getMoonMissionById(Connection connection, InputStream in) throws SQLException, IOException {
        System.out.print("Enter mission_id: ");
        int id = Integer.parseInt(readLine(in));

        String sql = "SELECT * FROM moon_mission WHERE mission_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Spacecraft: " + rs.getString("spacecraft"));
                    System.out.println("Launch Date: " + rs.getDate("launch_date"));
                    System.out.println("Carrier Rocket: " + rs.getString("carrier_rocket"));
                    System.out.println("Operator: " + rs.getString("operator"));
                    System.out.println("Mission Type: " + rs.getString("mission_type"));
                    System.out.println("Outcome: " + rs.getString("outcome"));
                } else {
                    System.out.println("No mission found with id " + id);
                }
            }
        }
    }

    // COUNT MISSIONS BY YEAR
    private void countMissionsByYear(Connection connection, InputStream in) throws SQLException, IOException {
        System.out.print("Enter year: ");
        int year = Integer.parseInt(readLine(in));

        String sql = "SELECT COUNT(*) FROM moon_mission WHERE YEAR(launch_date) = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Number of missions in " + year + ": " + rs.getInt(1));
                }
            }
        }
    }

}
