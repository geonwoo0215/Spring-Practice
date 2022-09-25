package springsecurity.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MapController {

    @GetMapping("/map")
    public String map() {
        log.info("카카오 api");
        return "map";
    }

}
