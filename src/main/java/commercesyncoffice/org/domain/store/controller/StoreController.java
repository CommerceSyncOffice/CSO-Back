package commercesyncoffice.org.domain.store.controller;

import commercesyncoffice.org.domain.store.dto.StoreCreateDto;
import commercesyncoffice.org.domain.store.dto.StoreListDto;
import commercesyncoffice.org.domain.store.service.StoreService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/brand/{brandId}/store")
    public String createStore(@PathVariable Long brandId, @Valid @RequestBody StoreCreateDto storeCreateDto) {

        storeService.createStore(brandId, storeCreateDto);

        return "redirect:/brand/" + brandId + "/store";
    }

    @GetMapping("/brand/{brandId}/store")
    public ResponseEntity<List<StoreListDto>> getStoreList(@PathVariable Long brandId) {

        return ResponseEntity.ok().body(storeService.getStoreList(brandId));
    }
}
