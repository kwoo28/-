package projectboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import projectboard.domain.Post;
import projectboard.dto.post.CreatePostReqDto;
import projectboard.dto.post.UpdatePostDto;
import projectboard.service.PostService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "게시글 API", description = "게시글 API 입니다.")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "게시글생성", description = "json형태로 받은 데이터로 게시글 생성합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void createUser(@Valid @RequestBody CreatePostReqDto createPostReqDto){
        Post post = new Post(createPostReqDto);
        postService.createPost(post, createPostReqDto.getTagNames());
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "게시글 고유 id와 json형태로 받은 데이터로 수정합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void updateUser(@PathVariable("id") Long id, @RequestBody UpdatePostDto updatePostDto){
        Post post = new Post(updatePostDto);
        postService.updatePost(post);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "게시글 고유 id로 받은 데이터로 삭제합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void deleteUser(@PathVariable("id") Long id){
        postService.deletePost(id);
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "게시글 제목 조회", description = "게시글 제목으로 받은 데이터로 조회합니다.")
    public List<Post> findByTitle(@PathVariable("title") String title){
        return postService.findByTitle(title);
    }

    @GetMapping("/content/{content}")
    @Operation(summary = "게시글 내용 조회", description = "게시글 내용으로 받은 데이터로 조회합니다.")
    public List<Post> findByContent(@PathVariable("content") String content){
        return postService.findByContent(content);
    }

    @GetMapping("/tag/{tagName}")
    @Operation(summary = "게시글 태그 조회", description = "태그로 받은 데이터로 조회합니다.")
    public List<Post> findByTag(@PathVariable("tagName") String tagName){
        return postService.findByTagName(tagName);
    }

    @GetMapping("/userName/{userName}")
    @Operation(summary = "게시글 작성자 닉네임 조회", description = "작성자 닉네임으로 받은 데이터로 조회합니다.")
    public List<Post> findByUserName(@PathVariable("userName") String userName){
        return postService.findByUserName(userName);
    }
}
