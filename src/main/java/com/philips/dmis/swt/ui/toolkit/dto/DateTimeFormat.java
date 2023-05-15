package com.philips.dmis.swt.ui.toolkit.dto;

public class DateTimeFormat extends TextFormat {
    private String locale;
    private String calendar; // Unicode Technical Standard 35
    private String timeZone; // IANA time zone name
    private Boolean hour12;
    private String dayPeriod;
    private String numberingSystem; // Unicode Technical Standard 35
    private Integer fractionalSecondDigits;
    private DateStyleType dateStyle;
    private TimeStyleType timeStyle;
    private WeekDayType weekday;
    private EraType era;
    private YearType year;
    private MonthType month;
    private DayType day;
    private HourType hour;
    private MinuteType minute;
    private SecondType second;
    private TimeZoneNameType timeZoneName;
    private HourCycleType hourCycle;
    private FormatMatcherType formatMatcher;
    private LocaleMatcherType localeMatcher;

    public String getLocale() {
        return locale;
    }

    public DateTimeFormat setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public String getCalendar() {
        return calendar;
    }

    public DateTimeFormat setCalendar(String calendar) {
        this.calendar = calendar;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public DateTimeFormat setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public Boolean getHour12() {
        return hour12;
    }

    public DateTimeFormat setHour12(Boolean hour12) {
        this.hour12 = hour12;
        return this;
    }

    public String getDayPeriod() {
        return dayPeriod;
    }

    public DateTimeFormat setDayPeriod(String dayPeriod) {
        this.dayPeriod = dayPeriod;
        return this;
    }

    public String getNumberingSystem() {
        return numberingSystem;
    }

    public DateTimeFormat setNumberingSystem(String numberingSystem) {
        this.numberingSystem = numberingSystem;
        return this;
    }

    public Integer getFractionalSecondDigits() {
        return fractionalSecondDigits;
    }

    public DateTimeFormat setFractionalSecondDigits(Integer fractionalSecondDigits) {
        this.fractionalSecondDigits = fractionalSecondDigits;
        return this;
    }

    public DateStyleType getDateStyle() {
        return dateStyle;
    }

    public DateTimeFormat setDateStyle(DateStyleType dateStyle) {
        this.dateStyle = dateStyle;
        return this;
    }

    public TimeStyleType getTimeStyle() {
        return timeStyle;
    }

    public DateTimeFormat setTimeStyle(TimeStyleType timeStyle) {
        this.timeStyle = timeStyle;
        return this;
    }

    public WeekDayType getWeekday() {
        return weekday;
    }

    public DateTimeFormat setWeekday(WeekDayType weekday) {
        this.weekday = weekday;
        return this;
    }

    public EraType getEra() {
        return era;
    }

    public DateTimeFormat setEra(EraType era) {
        this.era = era;
        return this;
    }

    public YearType getYear() {
        return year;
    }

    public DateTimeFormat setYear(YearType year) {
        this.year = year;
        return this;
    }

    public MonthType getMonth() {
        return month;
    }

    public DateTimeFormat setMonth(MonthType month) {
        this.month = month;
        return this;
    }

    public DayType getDay() {
        return day;
    }

    public DateTimeFormat setDay(DayType day) {
        this.day = day;
        return this;
    }

    public HourType getHour() {
        return hour;
    }

    public DateTimeFormat setHour(HourType hour) {
        this.hour = hour;
        return this;
    }

    public MinuteType getMinute() {
        return minute;
    }

    public DateTimeFormat setMinute(MinuteType minute) {
        this.minute = minute;
        return this;
    }

    public SecondType getSecond() {
        return second;
    }

    public DateTimeFormat setSecond(SecondType second) {
        this.second = second;
        return this;
    }

    public TimeZoneNameType getTimeZoneName() {
        return timeZoneName;
    }

    public DateTimeFormat setTimeZoneName(TimeZoneNameType timeZoneName) {
        this.timeZoneName = timeZoneName;
        return this;
    }

    public HourCycleType getHourCycle() {
        return hourCycle;
    }

    public DateTimeFormat setHourCycle(HourCycleType hourCycle) {
        this.hourCycle = hourCycle;
        return this;
    }

    public FormatMatcherType getFormatMatcher() {
        return formatMatcher;
    }

    public DateTimeFormat setFormatMatcher(FormatMatcherType formatMatcher) {
        this.formatMatcher = formatMatcher;
        return this;
    }

    public LocaleMatcherType getLocaleMatcher() {
        return localeMatcher;
    }

    public DateTimeFormat setLocaleMatcher(LocaleMatcherType localeMatcher) {
        this.localeMatcher = localeMatcher;
        return this;
    }

    @Override
    public DataType getFormatType() {
        return DataType.DATE;
    }

    @Override
    public boolean isValidDataType(DataType dataType) {
        return dataType == DataType.DATE;
    }

    @Override
    public void getProperties(CompactMap properties) {
        super.getProperties(properties);
        properties.put("locale", locale);
        properties.put("calendar", calendar);
        properties.put("timeZone", timeZone);
        properties.put("hour12", hour12);
        properties.put("dayPeriod", dayPeriod);
        properties.put("numberingSystem", numberingSystem);
        properties.put("fractionalSecondDigits", fractionalSecondDigits);
        properties.put("dateStyle", dateStyle);
        properties.put("timeStyle", timeStyle);
        properties.put("weekday", weekday);
        properties.put("era", era);
        properties.put("year", year);
        properties.put("month", month);
        properties.put("day", day);
        properties.put("hour", hour);
        properties.put("minute", minute);
        properties.put("second", second);
        properties.put("timeZoneName", timeZoneName);
        properties.put("hourCycle", hourCycle);
        properties.put("formatMatcher", formatMatcher);
        properties.put("localeMatcher", localeMatcher);
    }
}
