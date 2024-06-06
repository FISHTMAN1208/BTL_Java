package test_btl;
import java.time.LocalDateTime;

public class Plan {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Plan(String name, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(endTime)) {
            return "Hết hạn";
        } else {
            return "Đang hoàn thành";
        }
    }
}
