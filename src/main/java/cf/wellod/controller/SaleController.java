package cf.wellod.controller;

import cf.wellod.service.DaySaleService;
import cf.wellod.service.MonthSaleService;
import cf.wellod.service.YearSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class SaleController {
    @Autowired
    YearSaleService yearSaleService;

    @Autowired
    MonthSaleService monthSaleService;

    @Autowired
    DaySaleService daySaleService;

    @GetMapping("/sale/year_range")
    public Object getYearSalesByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return yearSaleService.getYearSalesRange(page, limit);
    }

    @GetMapping("/sale/month_range")
    public Object getMonthSalesByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return monthSaleService.getMonthSalesRange(page, limit);
    }

    @GetMapping("/sale/day_range")
    public Object getDaySalesByRange(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        return daySaleService.getDaySalesRange(page, limit);
    }


}
