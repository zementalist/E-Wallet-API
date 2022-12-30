package com.FawryWebApp.demo.Transaction;

import com.FawryWebApp.demo.PaymentMethod.CreditCardPaymentMethod;
import com.FawryWebApp.demo.Service.Service;
import com.FawryWebApp.demo.Service.ServiceFactory;
import com.FawryWebApp.demo.User.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.FawryWebApp.demo.ServiceProvider.ServiceProviderFactory;
import com.FawryWebApp.demo.ServiceProvider.ServiceProvider;
import com.FawryWebApp.demo.AppState.ApplicationState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private ServiceProviderFactory serviceProviderFactory;
    private ServiceFactory serviceFactory;
    public TransactionController() {
        this.serviceFactory = new ServiceFactory();
        this.serviceProviderFactory = new ServiceProviderFactory();
    }
    @PostMapping("make")
    public String make(
            @RequestParam("serviceProviderId") int serviceProviderId,
            @RequestParam("serviceId") int serviceId,
            @RequestParam("paymentMethodId") int paymentMethodId,
            @RequestParam("cost") int cost
    )
    {
        int serviceProviderIndex = serviceProviderId - 1;
        int serviceIndex = serviceId - 1;

        String serviceName = ApplicationState.available_services.get(serviceIndex);
        Service service  = this.serviceFactory.makeService(serviceName);
        service.setCost(cost);
        service.setDiscountBehavior(ApplicationState.available_discounts.get(ApplicationState.available_discounts.size()-1));

        String serviceProviderName = ApplicationState.specific_providers[serviceIndex][serviceProviderIndex];
        ServiceProvider serviceProvider = serviceProviderFactory.makeServiceProvider(serviceProviderName);
        serviceProvider.setService(service);

        String messageResult = serviceProvider.provide(paymentMethodId-1);
        return messageResult;
    }

    @GetMapping("addtowallet/{amount}")
    public ResponseEntity<?> addToWallet(@PathVariable double amount) {
        User user = User.getInstance();
        if(user != null) {
            user.getWallet().receive(amount);
            Transaction transaction = new Transaction(null, new CreditCardPaymentMethod());
            ApplicationState.transactions.add(transaction);
            return new ResponseEntity<>("Amount is added to your wallet", HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("Invalid transaction", HttpStatusCode.valueOf(400));
    }

    @GetMapping("/list/{user_id}")
    public List<String> getTransactions(@PathVariable int user_id) {
        List<Transaction> transactions = ApplicationState.transactions.stream().filter(c -> c.user.id == user_id).toList();
        List<String> messages = new ArrayList<>();
        if(transactions.size() > 0) {
            int total_spent = transactions.stream().map(c -> c.paidAmount).mapToInt(Double::intValue).sum();
            for (Transaction transaction : transactions) {
                messages.add(transaction.describe());
            }
            messages.add("Total amount: " + total_spent + "$");
        }
        else {
            messages.add("No transactions");
        }
        return messages;
    }
}
