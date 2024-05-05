package commercesyncoffice.org.domain.storeitem.controller;

import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.service.StoreItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StoreItemController {

    private final StoreItemService storeItemService;

    @PostMapping("/brand/store/{storeId}/storeItem")
    public String createStoreItem(
            @PathVariable Long storeId,
            @RequestBody @Valid StoreItemCreateDto storeItemCreateDto
    ) {

        storeItemService.createStoreItem(storeId, storeItemCreateDto);

        return "redirect:/store/" + storeId + "/storeItem";
    }
}
