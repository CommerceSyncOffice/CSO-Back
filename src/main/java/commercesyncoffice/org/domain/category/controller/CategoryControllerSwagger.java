package commercesyncoffice.org.domain.category.controller;

import commercesyncoffice.org.domain.category.dto.request.CategoryCreateDto;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.ExceptionResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "CategoryController", description = "Category API 입니다.")
public interface CategoryControllerSwagger {

    @Operation(summary = "카테고리 생성", description = "카테고리 생성 API")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "카테고리 생성 성공",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{ \"success\": true, \"message\": \"Successfully created category\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-Valid_1",
                            description = "카테고리명 미입력 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"카테고리명을 입력해주세요.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-Valid_2",
                            description = "카테고리명 패턴 불일치 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"카테고리명은 한/영 숫자만 입력할 수 있습니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-Valid_3",
                            description = "카테고리명 글자 수 일치하지 않을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"카테고리명은 최소 1글자, 최대 50글자 까지 입력할 수 있습니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "404-Brand",
                            description = "브랜드가 없을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"해당 브랜드는 존재하지 않습니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-BrandFilter",
                            description = "로그인 한 유저의 브랜드가 아닐 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"이 브랜드를 수정할 권한이 없습니다.\"}"))
                    )
            }
    )
    @PostMapping("/brand/{brandId}/category")
    ResponseEntity<? extends CommonResponse> createCategory(
            @RequestBody(
                    description = "카테고리 생성 DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategoryCreateDto.class),
                            examples = @ExampleObject(value = "{\"name\": \"반팔\", \"description\": \"반팔티 모음\"}")
                    )
            )
            @Valid
            CategoryCreateDto categoryCreateDto,

            @Parameter(description = "brandId는 양수입니다.", example = "1")
            @PathVariable
            Long brandId,

            @AuthenticationPrincipal
            UserDetailsImpl userDetails
    );

    @Operation(summary = "카테고리 목록 조회", description = "카테고리 목록 조회 API")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "카테고리 목록 조회 성공 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{ \"success\": true, \"message\": \"Successfully retrieved category list\", \"data\": ["
                                    + "        {"
                                    + "            \"name\": \"긴팔\""
                                    + "        },"
                                    + "        {"
                                    + "            \"name\": \"반팔\""
                                    + "        }"
                                    + "    ]}"))
                    ),
                    @ApiResponse(
                            responseCode = "404-Brand",
                            description = "브랜드가 없을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"해당 브랜드는 존재하지 않습니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-BrandFilter",
                            description = "로그인 한 유저의 브랜드가 아닐 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"이 브랜드를 수정할 권한이 없습니다.\"}"))
                    )
            }
    )
    @GetMapping("/brand/{brandId}/category")
    ResponseEntity<? extends CommonResponse> getCategory(
            @Parameter(description = "brandId는 양수입니다.", example = "1")
            @PathVariable
            Long brandId,

            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
