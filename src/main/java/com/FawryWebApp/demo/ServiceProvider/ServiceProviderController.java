package com.FawryWebApp.demo.ServiceProvider;

import com.FawryWebApp.demo.AppState.ApplicationState;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("provider")
public class ServiceProviderController {

    @GetMapping("")
    public List<String> index() {
        return ApplicationState.available_providers.stream().map(c -> c.name).toList();
    }

    @PostMapping("store")
    public ResponseEntity<?> store(@RequestParam String name) {
        ServiceProvider provider = new ServiceProvider(name) {
            @Override
            public void displayForm() {}
        };
        ApplicationState.available_providers.add(provider);
        return new ResponseEntity<>("New provider is added", HttpStatusCode.valueOf(200));
    }
}
