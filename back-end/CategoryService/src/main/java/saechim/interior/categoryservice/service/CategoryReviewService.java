package saechim.interior.categoryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.categoryservice.repository.CategoryReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryReviewService {
    private final CategoryReviewRepository categoryReviewRepository;
}
