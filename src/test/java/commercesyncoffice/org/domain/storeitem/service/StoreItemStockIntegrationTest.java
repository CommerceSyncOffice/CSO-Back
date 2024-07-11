package commercesyncoffice.org.domain.storeitem.service;

import static org.assertj.core.api.Assertions.*;

import commercesyncoffice.org.domain.admin.model.Admin;
import commercesyncoffice.org.domain.admin.repository.AdminRepository;
import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.dto.request.BrandCreateDto;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import commercesyncoffice.org.domain.item.model.Item;
import commercesyncoffice.org.domain.item.dto.request.ItemCreateDto;
import commercesyncoffice.org.domain.item.repository.ItemRepository;
import commercesyncoffice.org.domain.memberrole.model.MemberRoleEnum;
import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.store.dto.request.StoreCreateDto;
import commercesyncoffice.org.domain.store.repository.StoreRepository;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.repository.StoreItemRepository;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
class StoreItemStockIntegrationTest {

    @Autowired
    private StoreItemService storeItemService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StoreItemRepository storeItemRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    private StoreItem storeItem;
    private UserDetailsImpl userDetails;

    static {
        System.setProperty("jwt.secret", "Zm9yVGVzdOq4uOqyjOunjOuTpOyWtOyjvOyEuOyalDMy6riA7J6Q6rCA64SY7Ja07JW865Cc64uk6rOg7ZWY64qU642w7J2066CH6rKM6ri46rKM7ZW07JW87ZWY64KY7JqU");
        System.setProperty("spring.mail.username", "username");
        System.setProperty("spring.mail.password", "password");
    }



    @BeforeEach
    void setUp() {

        Admin admin = Admin.builder().username("username").password("password").email("email").build();
        adminRepository.save(admin);
        Brand brand = Brand.of(new BrandCreateDto("브랜드 이름", "브랜드 설명"), admin);
        brandRepository.save(brand);
        Store store = Store.of(brand, new StoreCreateDto("스토어", "주소"));
        storeRepository.save(store);
        Item item = Item.createItem(
                new ItemCreateDto("아이템 이름", 5000, 10000, "설명", "이미지 링크", "A1", false, null), null,
                brand);
        itemRepository.save(item);
        storeItem = StoreItem.of(store, item,
                new StoreItemCreateDto(item.getId(), 100, 0, 0));
        storeItem = storeItemRepository.save(storeItem);
        List<String> roles = new ArrayList<>();
        roles.add(MemberRoleEnum.ROLE_SALE_STORE_ITEM.name());
        userDetails = new UserDetailsImpl("username", "password", JwtUtil.ADMIN, roles.stream().map(
                SimpleGrantedAuthority::new).collect(Collectors.toList()), false, false, false, false);
    }

    @AfterEach
    @Transactional
    void tearDown() {
        storeItemRepository.deleteAll();
        itemRepository.deleteAll();
        storeRepository.deleteAll();
        brandRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    @DisplayName("수량 1개 감소 테스트")
    void test() {
        // Given
        Integer saleStock = 1;
        StoreItemSaleDto storeItemSaleDto = new StoreItemSaleDto(saleStock);
        // When
        storeItemService.saleStoreItem(userDetails, storeItem.getId(), storeItemSaleDto);

        // Then
        StoreItem findStoreItem = storeItemRepository.findById(storeItem.getId()).orElseThrow();

        assertThat(findStoreItem.getStock()).isEqualTo(99);
        assertThat(findStoreItem.getSaleCnt()).isEqualTo(1);
    }

    @Test
    @DisplayName("동시에 100개의 요청으로 수량 감소 테스트")
    void decrease_100_request_to_store_item() throws InterruptedException {
        // Given
        Integer saleStock = 1;
        StoreItemSaleDto storeItemSaleDto = new StoreItemSaleDto(saleStock);

        final int threadCnt = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCnt);

        // When
        for (int i = 0; i < threadCnt; i++) {
            executorService.submit(() -> {
                try {
                    storeItemService.saleStoreItem(userDetails, storeItem.getId(), storeItemSaleDto);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        // Then
        StoreItem findStoreItem = storeItemRepository.findById(storeItem.getId()).orElseThrow();
        assertThat(findStoreItem.getStock()).isEqualTo(0);
        assertThat(findStoreItem.getSaleCnt()).isEqualTo(100);
    }
}