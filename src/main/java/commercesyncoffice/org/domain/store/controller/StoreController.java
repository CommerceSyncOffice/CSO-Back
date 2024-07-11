package commercesyncoffice.org.domain.store.controller;

import static commercesyncoffice.org.domain.store.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.store.dto.request.StoreCreateDto;
import commercesyncoffice.org.domain.store.service.StoreService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/brand/{brandId}/store")
    public ResponseEntity<? extends CommonResponse> createStore(
            @PathVariable Long brandId,
            @Valid @RequestBody StoreCreateDto storeCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        storeService.createStore(userDetails, brandId, storeCreateDto);

        return ResponseEntity.status(SUCCESS_CREATE_STORE.getHttpStatus()).body(success(SUCCESS_CREATE_STORE.getMessage(), brandId));
    }

    @GetMapping("/brand/{brandId}/store")
    public ResponseEntity<? extends CommonResponse> getStoreList(
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(SUCCESS_GET_STORE_LIST.getHttpStatus())
                             .body(success(SUCCESS_GET_STORE_LIST.getMessage(), storeService.getStoreList(userDetails, brandId)));
    }
}
