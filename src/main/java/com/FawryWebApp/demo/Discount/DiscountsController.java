package com.FawryWebApp.demo.Discount;

import com.FawryWebApp.demo.AppState.ApplicationState;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("discounts")
public class DiscountsController {
    @GetMapping("")
    public List<String> index() {
        return ApplicationState.available_discounts.stream().map(c -> c.toString()).toList();
    }

    @PostMapping("store")
    public ResponseEntity<?> store(@RequestParam int discount_type_id, @RequestParam float percentage) {
        Discount discount;
        switch(discount_type_id) {
            case 1:
                discount = new OverallDiscount(percentage);
                break;
            case 2:
                discount = new SpecificDiscount(percentage);
                break;
            default:
                discount = new NoDiscount(percentage);
        }
        ApplicationState.available_discounts.add(discount);
        return new ResponseEntity<>(discount.toString(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("remove/{discount_id}")
    public ResponseEntity<?> remove(int discount_id) {
        ApplicationState.available_discounts.remove(discount_id-1);
        return new ResponseEntity<>("Discount Removed.", HttpStatusCode.valueOf(200));
    }

}
