package com.softeer.domain.usecase.myarchive

import com.softeer.domain.repository.MyArchiveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteMadeCarFeedUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository,
) {
    operator fun invoke(feedId: Long): Flow<Boolean> =
        myArchiveRepository.deleteMadeCarFeed(feedId)
}