package Company.api;

import Company.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cheque")
@RequiredArgsConstructor
public class ChequeListApi {
    private final ChequeService chequeService;
}
