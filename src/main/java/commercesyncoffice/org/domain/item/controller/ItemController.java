package commercesyncoffice.org.domain.item.controller;

import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import commercesyncoffice.org.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/brand/{brandId}/item")
    public String createItem(
            @RequestBody ItemCreateDto itemCreateDto,
            @PathVariable Long brandId)
    {

        return "redirect:/brand/item/" + itemService.createItem(itemCreateDto, brandId);
    }

    @GetMapping("/brand/item/{itemId}")
    public ResponseEntity<ItemDetailDto> getItem(
            @PathVariable Long itemId
    ) {

        return ResponseEntity.ok().body(itemService.getItem(itemId));
    }

    @PatchMapping("/brand/item/{itemId}/isSerial")
    public String changeItemIsSerial(@PathVariable Long itemId) {

        itemService.changeItemIsSerial(itemId);

        return "redirect:/brand/item/" + itemId;
    }
}
