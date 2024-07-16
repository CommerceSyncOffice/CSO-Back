package commercesyncoffice.org.domain.brand.controller;

import commercesyncoffice.org.domain.brand.dto.request.BrandCreateDto;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.ExceptionResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "BrandController", description = "Brand API 입니다.")
public interface BrandControllerSwagger {

    @Operation(summary = "브랜드 생성", description = "브랜드 생성 API 입니다. ROLE_CREATE_BRAND 권한이 필요합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "브랜드 생성 성공 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{ \"success\": true, \"message\": \"Successfully created brand\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-Valid_1",
                            description = "브랜드명 미입력 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"브랜드 명을 입력해주세요\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-Valid_2",
                            description = "브랜드명 패턴 불일치 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"브랜드명은 한/영 숫자만 입력할 수 있습니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-Valid_3",
                            description = "브랜드명 글자 수 일치하지 않을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"브랜드명은 최소 1글자, 최대 50글자 까지 입력할 수 있습니다.\"}"))
                    )
            }
    )
    @PostMapping("/brand")
    ResponseEntity<? extends CommonResponse> createBrand(
            @RequestBody(
                    description = "브랜드 생성 DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BrandCreateDto.class),
                            examples = @ExampleObject(value = "{\"name\": \"스타벅스 강남점\", \"description\": \"스타벅스 강남역 출구 바로 앞\"}")
                    )
            )
            @Valid
            BrandCreateDto brandCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(summary = "브랜드 목록 조회", description = "브랜드 목록 조회 API 입니다. 어드민만 가능")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "브랜드 목록 조회 성공 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{ \"success\": true, \"message\": \"Successfully get brand list\", \"data\": ["
                                    + "        {"
                                    + "            \"name\": \"이름\","
                                    + "            \"description\": \"설명\""
                                    + "        },"
                                    + "        {"
                                    + "            \"name\": \"야호야호\","
                                    + "            \"description\": \"우하하\""
                                    + "        }"
                                    + "    ]}"))
                    ),
                    @ApiResponse(
                            responseCode = "404-Admin",
                            description = "어드민 계정이 존재하지 않을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"해당 어드민 계정은 존재하지 않습니다.\"}"))
                    )
            }
    )
    @GetMapping("/brand")
    ResponseEntity<? extends CommonResponse> getBrandList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
