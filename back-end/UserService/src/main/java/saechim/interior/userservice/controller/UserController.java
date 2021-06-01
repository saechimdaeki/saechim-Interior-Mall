package saechim.interior.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saechim.interior.userservice.dto.UserDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.service.UserService;

import javax.validation.Valid;

@RequestMapping("/userservice")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
}
