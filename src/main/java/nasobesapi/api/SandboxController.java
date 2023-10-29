package nasobesapi.api;

import nasobesapi.database.models.SandboxModel;
import nasobesapi.database.services.SandboxService;
import nasobesapi.dto.SandboxRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/sandbox")
public class SandboxController {

    private final SandboxService sandboxService;

    public SandboxController(SandboxService sandboxService) {
        this.sandboxService = sandboxService;
    }

    @PostMapping("/get-count")
    public ResponseEntity<Long> getCountQuestionApi(
            @RequestBody() SandboxRequest body
    ) {
        return ResponseEntity.ok().body(this.sandboxService.getCountQuestion(
                body.getDevSpecification(),
                body.getGrade()
        ));
    }

    @PostMapping("/get-actual-question")
    public ResponseEntity<Optional<SandboxModel>> getActualQuestionApi(
            @RequestBody() SandboxRequest body
    ) {
        return ResponseEntity.ok().body(this.sandboxService.findByNumberQuestion(
                body.getNumber(),
                body.getDevSpecification(),
                body.getGrade()
        ));
    }

}

