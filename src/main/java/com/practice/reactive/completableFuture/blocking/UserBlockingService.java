package com.practice.reactive.completableFuture.blocking;

import com.practice.reactive.completableFuture.blocking.repository.ArticleRepository;
import com.practice.reactive.completableFuture.blocking.repository.FollowRepository;
import com.practice.reactive.completableFuture.blocking.repository.ImageRepository;
import com.practice.reactive.completableFuture.blocking.repository.UserRepository;
import com.practice.reactive.completableFuture.common.Article;
import com.practice.reactive.completableFuture.common.Image;
import com.practice.reactive.completableFuture.common.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserBlockingService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final FollowRepository followRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id)
                .map(user -> {
                    var image = imageRepository.findById(user.getProfileImageId())
                            .map(imageEntity -> {
                                return new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl());
                            });

                    var articles = articleRepository.findAllByUserId(user.getId())
                            .stream().map(articleEntity ->
                                    new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent()))
                            .collect(Collectors.toList());

                    var followCount = followRepository.countByUserId(user.getId());

                    return new User(
                            user.getId(),
                            user.getName(),
                            user.getAge(),
                            image,
                            articles,
                            followCount
                    );
                });
    }
}
