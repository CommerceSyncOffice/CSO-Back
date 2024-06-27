package commercesyncoffice.org.domain.item.controller;

import commercesyncoffice.org.domain.item.dto.ItemChangeCategoryDto;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return "redirect:/brand/item/" + itemService.createItem(userDetails, itemCreateDto, brandId);
    }

    @GetMapping("/brand/item/{itemId}")
    public ResponseEntity<ItemDetailDto> getItem(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.ok().body(itemService.getItem(userDetails, itemId));
    }

    @PatchMapping("/brand/item/{itemId}/isSerial")
    public String changeItemIsSerial(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        itemService.changeItemIsSerial(userDetails, itemId);

        return "redirect:/brand/item/" + itemId;
    }

    @PatchMapping("/brand/item/{itemId}/category")
    public String changeItemCategory(
            @RequestBody ItemChangeCategoryDto itemChangeCategoryDto,
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        itemService.changeItemCategory(userDetails, itemId, itemChangeCategoryDto);

        return "redirect:/brand/item/" + itemId;
    }
}
