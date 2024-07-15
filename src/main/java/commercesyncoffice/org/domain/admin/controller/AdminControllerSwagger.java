package commercesyncoffice.org.domain.admin.controller;

import commercesyncoffice.org.domain.admin.dto.request.AdminLoginDto;
import commercesyncoffice.org.domain.admin.dto.request.AdminSignUpDto;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.ExceptionResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Tag(name = "AdminController", description = "어드민 API")
public interface AdminControllerSwagger {

    @Operation(summary = "어드민 회원가입", description = "어드민 계정 회원가입 메서드입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "가입 성공 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{ \"success\": true, \"message\": \"가입 성공\"}"))
                    ),
                    // CheckedException
                    @ApiResponse(
                            responseCode = "409-SignUp_1",
                            description = "이미 존재하는 아이디",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"이미 존재하는 아이디입니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "409-SignUp_2",
                            description = "이미 존재하는 이메일",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"이미 존재하는 이메일입니다.\"}"))
                    ),
                    // Username
                    @ApiResponse(
                            responseCode = "400-UsernameValid_1",
                            description = "아이디 입력하지 않았을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"아이디를 입력해주세요\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-UsernameValid_2",
                            description = "영어 소문자 및 대문자, 숫자 입력하지 않았을 시 (패턴 불일치)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"아이디에는 영어 소문자 및 숫자만 입력해주세요\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-UsernameValid_3",
                            description = "아이디 길이와 일치 하지 않을 시 (사이즈 불일치)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"아이디는 최소 6자 이상, 최대 20자 이하가 되어야 합니다.\"}"))
                    ),
                    // Password
                    @ApiResponse(
                            responseCode = "400-PasswordValid_1",
                            description = "비밀번호 입력하지 않았을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"비밀번호를 입력해주세요\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-PasswordValid_2",
                            description = "비밀번호 길이와 일치하지 않을 시 (사이즈 불일치)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"비밀번호는 최소 8자 이상 20자 이하여야 합니다.\"}"))
                    ),
                    // Email
                    @ApiResponse(
                            responseCode = "400-EmailValid_1",
                            description = "유효하지 않은 이메일 주소 입력 시 (패턴 불일치)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"유효한 이메일 주소를 입력해주세요\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-EmailValid_2",
                            description = "이메일 길이와 일치 하지 않을 시 (사이즈 불일치)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"이메일은 최소 4자 이상 60자 이하여야 합니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "400-EmailValid_3",
                            description = "이메일 입력하지 않았을 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"이메일을 입력해주세요\"}"))
                    )
            }
    )
    @PostMapping("/admin/signup")
    ResponseEntity<? extends CommonResponse> signup(
            @RequestBody(
                    description = "어드민 회원가입 DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AdminSignUpDto.class),
                            examples = @ExampleObject(value = "{\"username\": \"username1004\", \"password\": \"password\", \"email\": \"CSOProject@gmail.com\", \"profileImg\": \"https://www.aws.com/s3/cso-project/images/1004.png\"}")
                    )
            )
            @Valid
            AdminSignUpDto adminSignUpDto
    );

    @Operation(summary = "어드민 로그인", description = "어드민 계정 로그인 메서드입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그인 성공 시",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{ \"success\": true, \"message\": \"로그인 성공\"}"))
                    ),
                    // CheckedException
                    @ApiResponse(
                            responseCode = "409-Login_1",
                            description = "존재하지 않는 아이디",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"존재하지 않는 어드민 아이디입니다.\"}"))
                    ),
                    @ApiResponse(
                            responseCode = "409-Login_2",
                            description = "일치하지 않는 비밀번호",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class), examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"비밀번호가 일치하지 않습니다.\"}"))
                    )
            }
    )
    @PostMapping("/admin/login")
    ResponseEntity<? extends CommonResponse> login(
            @RequestBody(
                    description = "어드민 로그인 DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AdminLoginDto.class),
                            examples = @ExampleObject(value = "{\"username\": \"username1004\", \"password\": \"password\"}")
                    )
            )
            @Valid
            AdminLoginDto adminLoginDto,
            HttpServletResponse response
    );
}

