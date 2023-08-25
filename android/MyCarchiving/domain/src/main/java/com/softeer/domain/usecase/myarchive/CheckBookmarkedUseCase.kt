package com.softeer.domain.usecase.myarchive

import com.softeer.domain.repository.MyArchiveRepository
import javax.inject.Inject

class CheckBookmarkedUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository,
) {
    suspend operator fun invoke(feedId: Long): Boolean =
        myArchiveRepository.checkBookmarked(feedId)
}