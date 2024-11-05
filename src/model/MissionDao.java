package model;

import java.util.List;

public interface MissionDao {

    public void addMission(Mission mission);
    public void deleteMission(Mission mission);
    public void deleteMissionById(int id);
    public void updateMission(Mission mission);
    public Mission getMissionById(int id);
    public List<Mission> getAllMissions();
    
}
