package app.service.timeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Author Fizz Pu
 * @Date 2020/10/9 下午8:13
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


@Component
public class CurTime {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ZoneId zoneId = ZoneId.systemDefault();

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    // 获得时区的时间
    public ZonedDateTime getZonedDateTime(){
        return ZonedDateTime.now(zoneId);
    }

    // 获得本地时间
    public LocalDateTime getLocalDateTime(){
        return getZonedDateTime().toLocalDateTime();
    }

    // 获得本地时间
    public Date getCurTime(){
        return Date.from(getLocalDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }

    // 本地时间加上若干分钟数
    public LocalDateTime plusMinutes(long minutes){
        LocalDateTime localDateTime = getLocalDateTime();
        return localDateTime.plusMinutes(minutes);
    }

    // 本地时间加上若干分钟数, 返回值是Date
    public Date plusMinutesDate(long minutes){
        LocalDateTime plusRes = plusMinutes(minutes);
        return Date.from(plusRes.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 本地时间加上若干天数
    public LocalDateTime plusDays(long days){
        LocalDateTime localDateTime = getLocalDateTime();
        return localDateTime.plusDays(days);
    }

    // 本地时间加上若干天数, 返回值是Date
    public Date plusDaysDate(long days){
        LocalDateTime plusRes = plusDays(days);
        return Date.from(plusRes.atZone(ZoneId.systemDefault()).toInstant());
    }

    @PostConstruct
    void init(){
        logger.info("时间组件CurTime初始化成功");
    }
}

