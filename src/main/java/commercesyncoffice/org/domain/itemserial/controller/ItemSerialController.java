package commercesyncoffice.org.domain.itemserial.controller;

import static commercesyncoffice.org.domain.itemserial.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.itemserial.dto.request.ItemSerialCreateDto;
import commercesyncoffice.org.domain.itemserial.message.SuccessMessage;
import commercesyncoffice.org.domain.itemserial.service.ItemSerialService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemSerialController {

    private final ItemSerialService itemSerialService;

    @PostMapping("/brand/item/{itemId}/item_serial")
    public ResponseEntity<? extends CommonResponse> createItemSerial(
            @PathVariable Long itemId,
            @RequestBody @Valid ItemSerialCreateDto itemSerialCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        itemSerialService.createItemSerial(userDetails, itemId, itemSerialCreateDto);

        return ResponseEntity.status(SUCCESS_CREATE_ITEM_SERIAL.getHttpStatus())
                             .body(success(SUCCESS_CREATE_ITEM_SERIAL.getMessage(), itemId));
    }
}
