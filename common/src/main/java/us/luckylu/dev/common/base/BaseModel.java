package us.luckylu.dev.common.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author lu
 * @create 2019-03-21 11:33
 */
@Setter
@Getter
public class BaseModel {

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected Long version;

    public Long createdAtToUnixtime() {
        if (createdAt == null) {
            return null;
        }
        return createdAt.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public Long updatedAtToUnixtime() {
        if (updatedAt == null) {
            return null;
        }
        return updatedAt.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public String createdAtToDatetime() {
        if (createdAt == null) {
            return null;
        }
        return createdAt.format(DATE_TIME_FORMATTER);
    }

    public String updatedAtToDatetime() {
        if (updatedAt == null) {
            return null;
        }
        return updatedAt.format(DATE_TIME_FORMATTER);
    }
}
