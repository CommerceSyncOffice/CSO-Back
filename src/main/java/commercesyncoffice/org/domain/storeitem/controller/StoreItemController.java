package commercesyncoffice.org.domain.storeitem.controller;

import static commercesyncoffice.org.domain.storeitem.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.service.StoreItemService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreItemController {

    private final StoreItemService storeItemService;

    @PostMapping("/brand/store/{storeId}/storeItem")
    public ResponseEntity<? extends CommonResponse> createStoreItem(
            @PathVariable Long storeId,
            @RequestBody @Valid StoreItemCreateDto storeItemCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        storeItemService.createStoreItem(userDetails, storeId, storeItemCreateDto);

        return ResponseEntity.status(SUCCESS_CREATE_STORE_ITEM.getHttpStatus())
                             .body(success(SUCCESS_CREATE_STORE_ITEM.getMessage(), storeId));
    }

    @PatchMapping("/brand/store/storeItem/{storeItemId}/stock_sell")
    public ResponseEntity<? extends CommonResponse> saleStoreItem(
            @PathVariable Long storeItemId,
            @RequestBody @Valid StoreItemSaleDto storeItemSaleDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        storeItemService.saleStoreItem(userDetails, storeItemId, storeItemSaleDto);

        return ResponseEntity.status(SUCCESS_SALE_STORE_ITEM.getHttpStatus())
                             .body(success(SUCCESS_SALE_STORE_ITEM.getMessage(), storeItemId));
    }
}
