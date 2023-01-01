package com.FawryWebApp.demo.RefundRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.FawryWebApp.demo.AppState.ApplicationState;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("refunds")
@RestController
public class RefundRequestsController {

    @GetMapping("init/{transaction_id}")
    public ResponseEntity<?> initRequest(@PathVariable int transaction_id) {
        RefundRequest request = new RefundRequest(transaction_id);
        boolean is_initiated = request.save();
        if(is_initiated)
            return new ResponseEntity<>("Request is initiated and will be reviewed by the admin", HttpStatusCode.valueOf(200));
        return new ResponseEntity<>("Transaction is not found!", HttpStatusCode.valueOf(404));
    }


    @GetMapping("")
    public List<String> index() {
        return ApplicationState.refund_requests.stream().map(c -> c.toString()).toList();
    }

    public RefundRequest getRequestByTransactId(int transaction_id) {
        List<RefundRequest> requests = ApplicationState.refund_requests.stream().filter(c -> c.transaction_id == transaction_id).toList();
        if(requests.size() == 1)
            return requests.get(0);
        return null;
    }

    @GetMapping("approve/{request_id}")
    public ResponseEntity<?> approve(@PathVariable int request_id) {
        RefundRequest request = this.getRequestByTransactId(request_id);
        if(request != null) {
            boolean is_approved = request.approve();
            if (is_approved)
                return new ResponseEntity<>("Transaction Id(" + request_id + ") is refunded successfully", HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatusCode.valueOf(404));
    }

    @GetMapping("reject/{request_id}")
    public ResponseEntity<?> reject(@PathVariable int request_id) {
        RefundRequest request = this.getRequestByTransactId(request_id);
        if(request != null) {
            request.reject();
            return new ResponseEntity<>("Request Id(" + request.transaction_id + ") rejected", HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("Refunding Request is NOT found", HttpStatusCode.valueOf(404));
    }

}
