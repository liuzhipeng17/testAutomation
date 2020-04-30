package entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    public String orderNo;
    //private LocalDate date;
    public String customerName;
    public List<OrderLine> orderLines;

}

