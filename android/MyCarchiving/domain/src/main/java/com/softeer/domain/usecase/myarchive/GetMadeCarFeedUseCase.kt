package com.softeer.domain.usecase.myarchive

import androidx.paging.PagingData
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.repository.MyArchiveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMadeCarFeedUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository,
) {
    operator fun invoke(): Flow<PagingData<MyArchiveFeed>> =
        myArchiveRepository.getMadeCarFeeds()
}