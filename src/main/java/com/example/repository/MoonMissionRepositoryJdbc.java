package com.example.repository;

import com.example.model.MoonMission;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MoonMissionRepositoryJdbc extends BaseRepository<MoonMission> implements MoonMissionRepository {

    public MoonMissionRepositoryJdbc(DataSource dataSource, boolean devMode) {
        super(dataSource, devMode);
    }

    @Override
    protected MoonMission map(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new MoonMission(
                rs.getInt("mission_id"),
                rs.getString("spacecraft"),
                rs.getDate("launch_date"),
                rs.getString("carrier_rocket"),
                rs.getString("operator"),
                rs.getString("mission_type"),
                rs.getString("outcome")
        );
    }

    @Override
    public List<MoonMission> listMissions() {
        return queryList("SELECT * FROM moon_mission");
    }

    @Override
    public Optional<MoonMission> getMissionById(int missionId) {
        return querySingle("SELECT * FROM moon_mission WHERE mission_id=?", missionId);
    }

    @Override
    public int countMissionsByYear(int year) {
        return executeQuery(
                "SELECT COUNT(*) FROM moon_mission WHERE YEAR(launch_date)=?",
                rs -> { rs.next(); return rs.getInt(1); },
                year
        );
    }
}