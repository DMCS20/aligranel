package com.group2.aligranel.review.mapper;

import com.group2.aligranel.review.dto.ReviewResponseDTO;
import com.group2.aligranel.review.model.Review;
import com.group2.aligranel.review.dto.ReviewRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponseDTO toReviewResponseDTO(Review review);
    @Mapping(target = "userId", ignore = true)
    Review toReview(ReviewRequestDTO reviewRequestDTO);
    List<ReviewResponseDTO> toReviewResponseDTOList(List<Review> reviews);
    @Mapping(target = "userId", ignore = true)
    List<Review> toReviewList(List<ReviewRequestDTO> reviewRequestDTOList);

    @Mapping(target = "userId", ignore = true)
    void updateReviewFromDTO(ReviewRequestDTO reviewRequestDTO, @MappingTarget Review review);
}
