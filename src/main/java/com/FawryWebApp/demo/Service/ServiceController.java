package com.FawryWebApp.demo.Service;

import com.FawryWebApp.demo.AppState.ApplicationState;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceController {
    @GetMapping("find/{q}")
    public List<String> search(@PathVariable String q) {
        List<String> results = ApplicationState.available_services.stream().filter(c -> c.toLowerCase().contains(q.toLowerCase())).toList();
        return results;
    }


}
