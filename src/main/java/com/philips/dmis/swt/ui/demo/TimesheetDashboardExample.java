package com.philips.dmis.swt.ui.demo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.*;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.aggregate.A;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.P;
import com.philips.dmis.swt.ui.toolkit.statement.reduce.R;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TimesheetDashboardExample extends Page {
    public static class Employee implements Serializable {
        int id;
        String name;

        public Employee() {
            // for serialization
        }

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @JsonPropertyOrder({"employeeId", "date", "code", "hours", "description"})
    public static class TimeDeclaration implements Serializable {
        int employeeId;
        Date date;
        int code;
        int hours;
        String description;

        public TimeDeclaration() {
            // for serialization
        }

        public TimeDeclaration(int employeeId, Date date, int code, int hours, String description) {
            this.employeeId = employeeId;
            this.date = date;
            this.code = code;
            this.hours = hours;
            this.description = description;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class WeekInfo implements Serializable {
        int weekNumber;
        Date date;
        String text;

        public WeekInfo() {
            // for serialization
        }

        public WeekInfo(int weekNumber, Date date) {
            this.weekNumber = weekNumber;
            this.date = date;
            this.text = formatDate(date);
        }

        static String formatDate(Date date) {
            SimpleDateFormat format = new SimpleDateFormat("ww - MMMM d, yyyy");
            return format.format(date);
        }

        public int getWeekNumber() {
            return weekNumber;
        }

        public Date getDate() {
            return date;
        }

        public String getText() {
            return text;
        }
    }

    private static final List<Employee> EMPLOYEES = new ArrayList<>();
    private static final List<TimeDeclaration> TIME_DECLARATIONS = new ArrayList<>();

    static {
        Calendar calendar = Calendar.getInstance();

        EMPLOYEES.add(new Employee(1, "John"));
        EMPLOYEES.add(new Employee(2, "Marie"));
        EMPLOYEES.add(new Employee(3, "Hannah"));

        List<String> descriptions = Arrays.asList("Worked on analysis for the client", "Review payment status",
                "Sales meeting", "Implementation meeting with stakeholders", "ACM meeting", "Requirements discussion with users",
                "End of year financial statement", "Complete reporting for Q&A", "Attend stand-up", "Pray for help");

        Random random = new Random();

        for (int week = 1; week <= calendar.getWeeksInWeekYear(); week++) {
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            for (Employee employee : EMPLOYEES) {
                for (int day = Calendar.MONDAY; day <= Calendar.FRIDAY; day++) {
                    calendar.set(Calendar.DAY_OF_WEEK, day);
                    int totalHours = random.nextInt(5, 10);
                    while (totalHours > 0) {
                        int hours = Math.min(totalHours, random.nextInt(1, 8));
                        String description = descriptions.get(random.nextInt(0, descriptions.size() - 1));
                        TIME_DECLARATIONS.add(new TimeDeclaration(employee.getId(), calendar.getTime(), random.nextInt(1, 99), hours, description));
                        totalHours -= hours;
                    }
                }
            }
        }
    }

    public TimesheetDashboardExample() throws Exception {
        super(Constants.isDemo(TimesheetDashboardExample.class));
    }

    @Override
    protected void build() throws Exception {
        StaticData viewOptions = add(new StaticData(
                DataBuilder.keyValue()
                        .add("all", "All Records")
                        .add("by-date", "Group by Date")
                        .getData()
        ));

        StaticData employeesAllData = add(new StaticData(
                DataBuilder.keyValue()
                        .add("all", "All Employees")
                        .getData()
        ));
        employeesAllData.addDataAdapter(new KeyValueViewDataAdapter());

        StaticData employeeStaticData = add(new StaticData(EMPLOYEES));
        employeeStaticData.addDataAdapter(new DtoViewDataAdapter(Employee.class));

        CalculatedValueWidget currentWeekNumber = add(new CalculatedValueWidget("currentWeekNumber", V.WeekOfYearString(V.DateNow())));
        StaticData timeDeclarationsStaticData = add(new StaticData(TIME_DECLARATIONS));

        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Timesheet Dashboard"));

        Panel filterPanel = add(new Panel(PanelType.TOOLBAR));
        HtmlSelect employeeList = new HtmlSelect();
        employeeList.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, employeesAllData,
                new KeyValueListDataAdapter());
        employeeList.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, employeeStaticData,
                new KeyValueListDataAdapter(DataAdapter.DEFAULT_PATH, "id", "name"));
        HtmlWeekInput htmlWeekInput = new HtmlWeekInput();
        htmlWeekInput.addDataSource(ValueDataSourceUsage.VALUE, currentWeekNumber,
                new ValueDataAdapter("currentWeekNumber"));
        htmlWeekInput.setRequired(true);

        filterPanel.add(employeeList);
        filterPanel.add(htmlWeekInput);
        HtmlButton resetWeek = filterPanel.add(new HtmlButton("This Week"));
        resetWeek.onClick(new ClickEventHandler(
                M.Refresh(currentWeekNumber),
                M.Refresh(timeDeclarationsStaticData)
        ));

        HtmlSelect viewType = new HtmlSelect();
        filterPanel.add(viewType);
        viewType.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, viewOptions, new KeyValueListDataAdapter());

        // create the default view, this is generated from the DTO
        DtoViewDataAdapter dtoViewDataAdapter = new DtoViewDataAdapter(TimeDeclaration.class);
        // add a format for the date field
        DateTimeFormat dateTimeFormat = new DateTimeFormat();
        dateTimeFormat.setWeekday(WeekDayType.LONG);
        dateTimeFormat.setDay(DayType.NUMERIC);
        dateTimeFormat.setMonth(MonthType.LONG);
        dtoViewDataAdapter.setFormat("Date", dateTimeFormat);
        timeDeclarationsStaticData.addDataAdapter(dtoViewDataAdapter);

        // add filter adapter to respond to selected values in our filter controls
        FilterDataAdapter filterEmployeeDataAdapter = new FilterDataAdapter();
        filterEmployeeDataAdapter.addPredicate("employeeId", P.IsEqual(V.GetValue(employeeList)));
        timeDeclarationsStaticData.addDataAdapter(filterEmployeeDataAdapter);
        // disable because we start with 'all' employees
        timeDeclarationsStaticData.setDataAdapterDisabled(filterEmployeeDataAdapter, true);

        FilterDataAdapter filterPeriodDataAdapter = new FilterDataAdapter();
        filterPeriodDataAdapter.addPredicate("date", P.IsGreaterThanOrEqual(V.DateValue(V.WeekOfYearToDate(V.GetValue(htmlWeekInput)))));
        filterPeriodDataAdapter.addPredicate("date", P.IsLessThan(V.DateValue(V.ModifyDate(V.WeekOfYearToDate(V.GetValue(htmlWeekInput)), V.OneWeek))));
        timeDeclarationsStaticData.addDataAdapter(filterPeriodDataAdapter);

        // add the group by adapter
        GroupByDataAdapter groupByDataAdapter = new GroupByDataAdapter()
                .addGroupBy("Date", "date", dateTimeFormat, JsType.DATE)
                .addAggregate("Hours", "hours", null, R.Sum());
        timeDeclarationsStaticData.addDataAdapter(groupByDataAdapter);
        // disable because we start with 'all' records
        timeDeclarationsStaticData.setDataAdapterDisabled(groupByDataAdapter, true);

        HtmlTable htmlTable = add(new HtmlTable());

        OrderedTableHeader orderedTableHeader = htmlTable.add(new OrderedTableHeader(timeDeclarationsStaticData));
        orderedTableHeader.setOrder("description", Order.ASCENDING);

        HtmlTableBody htmlTableBody = htmlTable.add(new HtmlTableBody());
        htmlTableBody.addDataSource(timeDeclarationsStaticData);

        AggregateDataAdapter aggregateDataAdapter = new AggregateDataAdapter(true);
        aggregateDataAdapter.addAggregate("Hours", "hours", null, A.Sum());

        HtmlTableFooter htmlTableFooter = htmlTable.add(new HtmlTableFooter());
        htmlTableFooter.addDataSource(timeDeclarationsStaticData, aggregateDataAdapter);

        viewType.onChange(new ChangeEventHandler(
                M.SetDataAdapterEnabled(timeDeclarationsStaticData, groupByDataAdapter,
                        V.Is(V.GetValue(viewType), V.Const("by-date"))),
                M.Refresh(timeDeclarationsStaticData)
        ));
        employeeList.onChange(new ChangeEventHandler(
                M.SetDataAdapterDisabled(timeDeclarationsStaticData, filterEmployeeDataAdapter,
                        V.Is(V.GetValue(employeeList), V.Const("all"))),
                M.Refresh(timeDeclarationsStaticData)
        ));
        htmlWeekInput.onChange(new ChangeEventHandler(
                M.Refresh(timeDeclarationsStaticData)
        ));
    }
}
