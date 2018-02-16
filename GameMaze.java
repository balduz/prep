import java.util.*;

public class GameMaze {
  private static class Room {
    private List<Door> doors;
    private String key;
    private boolean endRoom;

    Room(String key, boolean endRoom) {
      this.doors = new ArrayList<Door>();
      this.key = key;
      this.endRoom = endRoom;
    }

    public void addDoor(Door door) { doors.add(door); }
    public List<Door> getDoors() { return doors; }
    public boolean hasKey() { return key != null && !key.isEmpty(); }
    public String getKey() { return key; }
    public boolean isEndRoom() { return endRoom; }
  }

  private static class Door {
    private List<Room> rooms;
    private boolean locked;
    private String unlockedBy;

    Door(List<Room> rooms, boolean locked, String unlockedBy) {
      this.rooms = rooms;
      this.locked = locked;
      this.unlockedBy = unlockedBy;
    }

    public List<Room> getRooms() { return rooms; }
    public boolean isLocked() { return locked; }
    public String getUnlockedBy() { return unlockedBy; }
    public void unlock() { this.locked = false; }

  }

  private static boolean unlockDoor(Door door, Set<String> availableKeys) {
    String unlockedBy = door.getUnlockedBy();
    if (availableKeys.contains(unlockedBy)) {
      door.unlock();
      availableKeys.remove(unlockedBy);
      return true;
    }
    return false;
  }

  private static boolean isMazeSolvable(Room startingRoom) {
    Set<Room> visitedRooms = new HashSet<>();

    Map<String, Door> lockedDoors = new HashMap<>();
    Set<String> availableKeys = new HashSet<>();

    Queue<Room> toVisit = new LinkedList<>();
    toVisit.add(startingRoom);

    while (!toVisit.isEmpty()) {
      Room current = toVisit.poll();
      if (current.isEndRoom()) {
        return true;
      }
      if (visitedRooms.contains(current)) {
        continue;
      }

      visitedRooms.add(current);

      if (current.hasKey()) {
        String key = current.getKey();
        if (lockedDoors.containsKey(key)) {
          Door door = lockedDoors.get(key);
          door.unlock();
          lockedDoors.remove(key);
          for (Room newlyUnlockedRoom : door.getRooms()) {
            toVisit.add(newlyUnlockedRoom);
          }
        } else {
          availableKeys.add(key);
        }
      }

      for (Door door : current.getDoors()) {
        if (door.isLocked()) {
          if (!unlockDoor(door, availableKeys)) {
            lockedDoors.put(door.getUnlockedBy(), door);
            continue;
          }
        }

        for (Room room : door.getRooms()) {
          toVisit.add(room);
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    /* 
     *  x (r1) -  x (r2) +(blue) end (r5)
     *  |
     *  x (r3) -  k  (r4)
    */
    Room r1 = new Room(null, false);
    Room r2 = new Room(null, false);
    Room r3 = new Room(null, false);
    Room r4 = new Room("blue", false);
    Room r5 = new Room(null, true);

    List<Room> rs = new ArrayList<>();
    rs.add(r1);
    rs.add(r2);
    Door d1 = new Door(rs, false, null);

    rs = new ArrayList<>();
    rs.add(r2);
    rs.add(r5);
    Door d2 = new Door(rs, true, "blue");

    rs = new ArrayList<>();
    rs.add(r1);
    rs.add(r3);
    Door d3 = new Door(rs, false, null);

    rs = new ArrayList<>();
    rs.add(r3);
    rs.add(r4);
    Door d4 = new Door(rs, false, null);

    r1.addDoor(d1);
    r1.addDoor(d3);

    r2.addDoor(d1);
    r2.addDoor(d2);

    r3.addDoor(d3);
    r3.addDoor(d4);

    r4.addDoor(d4);

    r5.addDoor(d2);

    System.out.println(isMazeSolvable(r1));
  }
}