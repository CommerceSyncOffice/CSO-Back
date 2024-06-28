package commercesyncoffice.org.domain.storeitem.controller;

import commercesyncoffice.org.domain.storeitem.dto.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.service.StoreItemService;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
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
            @RequestBody @Valid StoreItemCreateDto storeItemCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        storeItemService.createStoreItem(userDetails, storeId, storeItemCreateDto);

        return "redirect:/store/" + storeId + "/storeItem";
    }

    @PatchMapping("/brand/store/storeItem/{storeItemId}/stock_sell")
    public String saleStoreItem(
            @PathVariable Long storeItemId,
            @RequestBody @Valid StoreItemSaleDto storeItemSaleDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        storeItemService.saleStoreItem(userDetails, storeItemId, storeItemSaleDto);

        return "redirect:/store/storeItem" + storeItemId;
    }
}
