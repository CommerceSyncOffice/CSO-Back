package commercesyncoffice.org.domain.item.controller;

import static commercesyncoffice.org.domain.item.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.item.dto.request.ItemChangeCategoryDto;
import commercesyncoffice.org.domain.item.dto.request.ItemCreateDto;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/brand/{brandId}/item")
    public ResponseEntity<? extends CommonResponse> createItem(
            @RequestBody ItemCreateDto itemCreateDto,
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(SUCCESS_CREATE_ITEM.getHttpStatus())
                             .body(success(SUCCESS_CREATE_ITEM.getMessage(), itemService.createItem(userDetails, itemCreateDto, brandId)));
    }

    @GetMapping("/brand/item/{itemId}")
    public ResponseEntity<? extends CommonResponse> getItem(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(SUCCESS_GET_ITEM.getHttpStatus())
                             .body(success(SUCCESS_GET_ITEM.getMessage(), itemService.getItem(userDetails, itemId)));
    }

    @PatchMapping("/brand/item/{itemId}/isSerial")
    public ResponseEntity<? extends CommonResponse> changeItemIsSerial(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        itemService.changeItemIsSerial(userDetails, itemId);

        return ResponseEntity.status(SUCCESS_CHANGE_ITEM_IS_SERIAL.getHttpStatus())
                             .body(success(SUCCESS_CHANGE_ITEM_IS_SERIAL.getMessage(), itemId));
    }

    @PatchMapping("/brand/item/{itemId}/category")
    public ResponseEntity<? extends CommonResponse> changeItemCategory(
            @RequestBody ItemChangeCategoryDto itemChangeCategoryDto,
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        itemService.changeItemCategory(userDetails, itemId, itemChangeCategoryDto);

        return ResponseEntity.status(SUCCESS_CHANGE_ITEM_CATEGORY.getHttpStatus())
                             .body(success(SUCCESS_CHANGE_ITEM_CATEGORY.getMessage(), itemId));
    }
}
