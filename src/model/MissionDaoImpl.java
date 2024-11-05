package model;

import java.sql.*;
import java.util.List;

public class MissionDaoImpl implements MissionDao {

    public void addMission(Mission mission) {
        String query = "INSERT INTO MISSION (eid, cname, mloc, endd) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionDb.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, mission.getEid());
                pstmt.setString(2, mission.getCname());
                pstmt.setString(3, mission.getMloc());
                pstmt.setDate(4, mission.getEndd());
            })


        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteMission(Mission mission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteMissionById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateMission(Mission mission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mission getMissionById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Mission> getAllMissions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
