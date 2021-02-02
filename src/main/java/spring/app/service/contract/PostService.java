package spring.app.service.contract;

import spring.app.domain.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post addPost(Post post);

    Post updatePost(Post post);

    Post deletePost(long id);

    long getPostsCount();
}
