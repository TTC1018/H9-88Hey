package com.softeer.domain.usecase.myarchive

import com.softeer.domain.repository.MyArchiveRepository
import javax.inject.Inject

class DeleteMadeCarFeedUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository,
) {
    suspend operator fun invoke(feedId: Long): Boolean =
        myArchiveRepository.deleteMadeCarFeed(feedId)
}