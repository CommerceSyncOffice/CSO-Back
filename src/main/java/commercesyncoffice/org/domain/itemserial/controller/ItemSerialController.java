package commercesyncoffice.org.domain.itemserial.controller;

import commercesyncoffice.org.domain.itemserial.dto.ItemSerialCreateDto;
import commercesyncoffice.org.domain.itemserial.service.ItemSerialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemSerialController {

    private final ItemSerialService itemSerialService;

    @PostMapping("/brand/item/{itemId}/item_serial")
    public String createItemSerial(
            @PathVariable Long itemId,
            @RequestBody @Valid ItemSerialCreateDto itemSerialCreateDto
    ) {

        itemSerialService.createItemSerial(itemId, itemSerialCreateDto);

        return "redirect:/brand/item/" + itemId;
    }
}
