package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.brand.model.BrandId;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.item.model.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import commercesyncoffice.org.domain.storeitem.model.StoreItem;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.exception.StoreItemException;
import commercesyncoffice.org.domain.storeitem.message.ExceptionCode;
import commercesyncoffice.org.domain.storeitem.repository.StoreItemRepository;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreItemServiceImplV1 implements StoreItemService {

    private final StoreItemRepository storeItemRepository;
    private final StoreService storeService;
    private final ItemService itemService;
    private final BrandService brandService;

    @Override
    @Transactional
    public void createStoreItem(UserDetails userDetails, Long storeId,
            StoreItemCreateDto storeItemCreateDto) {

        Store store = storeService.getStoreById(storeId);
        Item item = itemService.getItemWithBrandByItemId(storeItemCreateDto.itemId());
        brandService.validateBrand(userDetails, BrandId.from(item.getBrandId()));

        if (storeItemRepository.existsByItemIdAndStoreId(item.getId(), store.getId())) {
            throw new StoreItemException(ExceptionCode.ALREADY_REGISTERED_ITEM);
        }

        storeItemRepository.save(StoreItem.of(store, item, storeItemCreateDto));
    }

    @Override
    @Transactional
    public void saleStoreItem(UserDetails userDetails, Long storeItemId,
            StoreItemSaleDto storeItemSaleDto) {

        StoreItem storeItem = storeItemRepository.findByIdWithPessimisticLock(storeItemId)
                .orElseThrow(() -> new StoreItemException(ExceptionCode.NOT_FOUND_STORE_ITEM));

        validateUserDetails(userDetails, storeItemId);

        storeItem.saleStock(storeItemSaleDto);
    }

    //TODO 이거 브랜드 서비스로 옮기기
    private void validateUserDetails(UserDetails userDetails, Long storeItemId) {

        if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.MEMBER)) {

            if (!storeItemRepository.existsMemberByStoreItemIdAndMemberUsername(storeItemId, userDetails.getUsername())) {
                throw new StoreItemException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
            }
        } else if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.ADMIN)) {

            if (!storeItemRepository.existsMemberByStoreItemIdAndAdminUsername(storeItemId, userDetails.getUsername())) {
                throw new StoreItemException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
            }
        }
    }
}
