package com.softeer.domain.usecase.myarchive

import com.softeer.domain.repository.MyArchiveRepository
import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository,
) {
    suspend operator fun invoke(feedId: Long): Long? =
        myArchiveRepository.addBookmark(feedId)
}