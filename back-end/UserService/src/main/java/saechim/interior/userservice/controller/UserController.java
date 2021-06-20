package saechim.interior.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.userservice.dto.CouponDto;
import saechim.interior.userservice.dto.LoginDto;
import saechim.interior.userservice.dto.UserDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.UserEntity;
import saechim.interior.userservice.service.CouponService;
import saechim.interior.userservice.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.io.IOException;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CouponService couponService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-UserService-World";
    }

    @PostMapping("/join")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto){
        userService.JoinUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입완료");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> findByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUserId(userId));
    }

    @PostMapping("/{userId}/upload")
    public ResponseEntity<UserEntity> uploadUserProfileImage(@PathVariable String userId, @RequestParam("file")MultipartFile file) throws IOException {
        UserEntity userEntity = userService.uploadPicture(userId, file);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUserInfo());
    }

    @PostMapping("/{userId}/coupon")
    public ResponseEntity<?> giveCoupon(@PathVariable String userId, @RequestBody @Valid CouponDto couponDto){
        couponService.giveToCoupon(userId,couponDto);
        return ResponseEntity.ok("쿠폰을 받았씁니다");
    }
    @GetMapping("/{userId}/coupons")
    public ResponseEntity<?> getCouponsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(userService.findCouponListByUserId(userId));
    }

    @GetMapping("/coupons")
    public ResponseEntity<?> findAllCoupons(){
        return ResponseEntity.ok(couponService.findAllCoupon());
    }

    @GetMapping("/post/{userId}")
    public ResponseEntity<?> addPostId(@PathVariable String userId){
        return ResponseEntity.ok(userService.findPostByUserId(userId));
    }

    @GetMapping("/postdetail/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        return ResponseEntity.ok(userService.getPostDetails(id));
    }




}
